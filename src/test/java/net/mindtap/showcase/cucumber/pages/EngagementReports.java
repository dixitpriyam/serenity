package net.mindtap.showcase.cucumber.pages;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.assertj.core.api.Fail;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONCompare;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.JSONCompareResult;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.serenitybdd.core.Serenity;

/**
 * Created by analytics on 4/19/2017.
 */
public class EngagementReports {

	public static void validateErrorMessage(String message) {
		boolean validationStatus = false;
		if (message.equalsIgnoreCase("field"))
			validationStatus = validateResponseJson();
		else
			validationStatus = ((String) Serenity.sessionVariableCalled("responsebody")).contains(message);
		assertThat(validationStatus).isTrue();
	}

	public static boolean validateResponseJson() {
		String responseJson = (String) Serenity.sessionVariableCalled("responsebody");
		String expectedJson = "{ \"engagementScoreInfo\":" + " {\"engagementScoreTotal\": 0,"
				+ "\"engagementScorePercentile\": 0," + "\"engagementCategory\": \"LOW\","
				+ "\"metricIdToNormalizedEngagementScoreMap\": {}" + "}}";
		JsonNode actualNode = null, expectedNode = null;
		try {
			actualNode = (JsonNode) new ObjectMapper().readTree(responseJson);
			expectedNode = (JsonNode) new ObjectMapper().readTree(expectedJson);
		} catch (IOException e) {
			e.printStackTrace();
		}
		JsonNode details = actualNode.get("report").get("studentDetails");
		Iterator<String> fields = details.get(0).get("engagementScoreInfo").fieldNames();
		while (fields.hasNext()) {
			String f = fields.next();
			if (!expectedNode.get("engagementScoreInfo").has(f))
				return false;
		}
		return true;
	}

	public static void compareEngagmentReport(String schemaJson) {
		try {
			Path p = Paths.get("./reports");
			if (!Files.exists(p))
				Files.createDirectory(Paths.get("./reports"));
			System.out.println("====ENGAGEMENT REPORT SCHEMA JSON (expected json|saved as ER_SCHEMA.json)====");
			if (schemaJson != null)
				Files.write(Paths.get("./reports/ER_SCHEMA.json"), schemaJson.getBytes());
			System.out.println(schemaJson);
			String engagementReportJson = (String) Serenity.sessionVariableCalled("responsebody");
			System.out.println("====ENGAGEMENT REPORT JSON (actual json|saved as ER.json)====");
			if (engagementReportJson != null)
				Files.write(Paths.get("./reports/ER.json"), engagementReportJson.getBytes());
			System.out.println(engagementReportJson);
			if (schemaJson != null && engagementReportJson != null) {
				System.out.println("===COMPARISON RESULT===");

				JSONCompareResult result = JSONCompare.compareJSON(schemaJson, engagementReportJson,
						JSONCompareMode.LENIENT);
				System.out.println(result);
				System.out.println("====EXPECTED UNMATCHED FIELDS====");
				List<String> expectedFieldList = Arrays
						.asList(new String[] { "report.courseUri", "report.generationDate", "report.preaggregated" });
				System.out.println(expectedFieldList);
				System.out.println("====ACTUAL UNMATCHED FIELDS (saved as Difference.txt)====");
				List<String> actualFieldList = result.getFieldFailures().stream().map(f -> f.getField()).sorted()
						.collect(Collectors.toList());
				System.out.println(actualFieldList);
				Files.write(Paths.get("./reports/Difference.json"), actualFieldList);

				assertThat(expectedFieldList.containsAll(actualFieldList)).isTrue();
			}else
				Fail.fail("Either schema json or expected json is not defined");
		} catch (JSONException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
