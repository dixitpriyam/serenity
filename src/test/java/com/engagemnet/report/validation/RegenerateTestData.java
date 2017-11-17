package com.engagemnet.report.validation;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import net.mindtap.showcase.cucumber.utils.S3Utils.S3UploadData;
import net.serenitybdd.junit.runners.TestClassAnnotations;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import java.util.ArrayList;
import java.util.List;

public class RegenerateTestData {
	private static String system = "qa-automation";
	private static String environment = "staging";
	private static String key = null;
	private static long courseDuration = 10;
	private static String covalent = System.getProperty("user.dir") + "/src/test/resources/data/covalent";
	private static String capeventmessages = System.getProperty("user.dir")
			+ "/src/test/resources/data/capeventmessages/cap-interaction-events.json";

	public static void runRegenerateTestData()
			  {
				  System.out.println("Regenerating Test Data ......");

			  	try {
					String courseKey = key != null ? key : uuid();
					String courseUri = "mindtap:" + system + ":" + environment + ":course:" + courseKey;
					String courseStructureUri = "mindtap:" + system + ":" + environment + ":course-structure:" + courseKey;
					ZonedDateTime seedMoment = ZonedDateTime.now(ZoneOffset.UTC);
					ZonedDateTime endDate = ZonedDateTime.now(ZoneOffset.UTC).plusDays(courseDuration)
							.truncatedTo(ChronoUnit.SECONDS);

					System.out.println("courseKey: "+courseKey);
					System.out.println("courseUri: "+courseUri);
					System.out.println("courseStructureUri: "+courseStructureUri);

					updateCapEventMessages(capeventmessages, courseUri, courseKey, seedMoment);
					updateCovalentXmlFiles(courseUri, courseStructureUri, endDate);


					uploadCapEventMessages("cap-interaction-events.json",System.getProperty("user.dir")+"/src/test/resources/data/capeventmessages/cap-interaction-events.json");
					uploadCovalentXmlFiles("/src/test/resources/data/covalent");


					uploadCourseUriFile(courseUri);


					System.out.println("courseKey"+courseKey + "// " +"endDate"+endDate);
					System.out.println("<h4>Regenerate Test Data Phase completed ....!!!!!!</h4>");
				}
				catch (Exception e){
					System.out.println("Regenerating Test Data Failed");
			  		e.printStackTrace();
				}



	}

	private static void uploadCourseUriFile(String courseUri) {


		Writer writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream("courseUri.txt"), "utf-8"));
			writer.write(courseUri);
		} catch (IOException ex) {
			// report
		} finally {
			try {writer.close();} catch (Exception ex) {/*ignore*/}
		}


		S3UploadData.uploadData("courseUri.txt",System.getProperty("user.dir")+"/courseUri.txt");
		System.out.println("courseUri.txt file succesfully uploaded");
	}

	private static void uploadCapEventMessages(String fileName, String filePath) {
		S3UploadData.uploadData(fileName,filePath);
	}

	private static void uploadCovalentXmlFiles(String pckPath) {
		System.out.println("pckPath: "+pckPath);
		File ob =new File(System.getProperty("user.dir")+pckPath);
		String[] str = new String[0];
		str=ob.list();
		for(String name:str){
			String filepath=System.getProperty("user.dir")+pckPath+"/"+name;
			S3UploadData.uploadData(name,filepath);
			System.out.println(name+" file successfully Uploaded to S3");
		}
	}

	private static String uuid() {
		return UUID.randomUUID().toString();
	}

	private static void updateCapEventMessages(String path, String courseUri, String courseKey,
			ZonedDateTime seedMoment) throws IOException {
		JSONParser parser = new JSONParser();
		Object obj = null;
		try {
			obj = parser.parse(new FileReader(path));
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONArray jsonArray = (JSONArray) obj;
		long increment = 1;
		for (Object object : jsonArray) {
			JSONObject jsonObject = (JSONObject) object;
			jsonObject.put("courseUri", courseUri);
			jsonObject.put("courseKey", courseKey);
			jsonObject.put("localTime", seedMoment.plusSeconds(increment).toString());
			increment++;
		}
		try (FileWriter file = new FileWriter(path)) {
			file.write(jsonArray.toJSONString());
			file.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void updateCovalentXmlFiles(String courseUri, String courseStructureUri, ZonedDateTime endDate)
			throws IOException {
		try (java.util.stream.Stream<Path> paths = Files.walk(Paths.get(covalent))) {

			paths.forEach(filePath -> {

				if (Files.isRegularFile(filePath)) {
					DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder docBuilder;
					try {
						docBuilder = docFactory.newDocumentBuilder();
						Document doc = docBuilder.parse(filePath.toString());
						Node header = doc.getChildNodes().item(0);
						String operation = header.getNodeName().split(":")[1];
						Element root = (Element) header;

						switch (operation) {
						case "push-course-message":
							((Element) root.getElementsByTagName("ns8:course").item(0)).setAttribute("ns8:course-uri",
									courseUri);
							((Element) root.getElementsByTagName("ns8:course").item(0)).setAttribute("end-date",
									endDate.toString());
							((Element) root.getElementsByTagName("ns8:course").item(0))
									.getElementsByTagName("ns8:structure").item(0).setNodeValue(courseStructureUri);
							break;
						case "push-course-structure-message":
							((Element) root.getElementsByTagName("ns8:structure").item(0))
									.setAttribute("ns8:course-structure-uri", courseStructureUri);
							break;
						case "provide-activity-aggregates-message":
							String oldCourseUri = ((Element) root.getElementsByTagName("cms:activity-aggregate")
									.item(0)).getAttribute("course-uri");
							String oldAggregateUri = ((Element) root.getElementsByTagName("cms:activity-aggregate")
									.item(0)).getAttribute("activity-aggregate-uri");
							String newAggregateUri = courseUri + oldAggregateUri.replace(oldCourseUri, "");
							((Element) root.getElementsByTagName("cms:activity-aggregate").item(0))
									.setAttribute("course-uri", courseUri);
							((Element) root.getElementsByTagName("cms:activity-aggregate").item(0))
									.setAttribute("activity-aggregate-uri", newAggregateUri);
							break;
						default:
							System.out.println("Unhandled operation: " + operation);
						}

						doc.getDocumentElement().normalize();
						TransformerFactory transformerFactory = TransformerFactory.newInstance();
						Transformer transformer = transformerFactory.newTransformer();
						DOMSource source = new DOMSource(doc);
						StreamResult result = new StreamResult(new File(filePath.toString()));
						transformer.setOutputProperty(OutputKeys.INDENT, "yes");
						transformer.transform(source, result);

					} catch (Exception e1) {
						System.out.println("Error in updating:" + filePath);
						e1.printStackTrace();
					}

				}
			});
		}
	}

}
