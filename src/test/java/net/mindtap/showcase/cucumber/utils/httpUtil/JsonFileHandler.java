package net.mindtap.showcase.cucumber.utils.httpUtil;

import org.json.simple.JSONObject;

import java.io.*;
import java.util.List;
import java.util.Properties;


public class JsonFileHandler {

    public JsonFileHandler() {
        // TODO Auto-generated constructor stub
    }

    static Properties properties = new Properties();
    static String filePath = "resources/requestbody/search_event_body.json";

    /**
     * This method is used to read the value of the given property from the
     * properties file.
     *
     * @param
     *            : the property whose value is to be fetched.
     * @return the value of the given property.
     * @throws IOException
     */

    public static void createJSONFile(List<String> jsonBody) {
        JSONObject obj = new JSONObject();
        /**
         * Creating the Json Body
         * */

        obj.put("eventAction", jsonBody.get(0));
        obj.put("eventCategory", jsonBody.get(1));
        obj.put("eventLabel", jsonBody.get(2));
        obj.put("courseKey", jsonBody.get(3));
        obj.put("coreTextISBN", jsonBody.get(4));
        obj.put("userSSOGuid", jsonBody.get(5));
        obj.put("productPlatform", jsonBody.get(6));
        obj.put("environment", jsonBody.get(7));
        obj.put("activityCGI", jsonBody.get(8));
        obj.put("localTime", jsonBody.get(9));
        obj.put("eventValue", jsonBody.get(10));

        try (FileWriter file = new FileWriter(filePath)) {

            file.write(obj.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("JSON BODY: \t"+obj.toString());

        System.out.println("******************* JSON File SUCCESSFULLY Created*************************");
    }

    public static void createJSON(List<String> jsonBody,String event,String Category) {
        JSONObject obj = new JSONObject();
        /**
         * Creating the Json Body
         * */

        obj.put("eventAction", jsonBody.get(0));
        obj.put("eventCategory", jsonBody.get(1));
        obj.put("eventLabel", jsonBody.get(2));
        obj.put("courseKey", jsonBody.get(3));
        obj.put("coreTextISBN", jsonBody.get(4));
        obj.put("userSSOGuid", jsonBody.get(5));
        obj.put("productPlatform", jsonBody.get(6));
        obj.put("environment", jsonBody.get(7));
        obj.put("activityCGI", jsonBody.get(8));
        obj.put("localTime", jsonBody.get(9));
        obj.put("eventValue", jsonBody.get(10));

        PropFileHandler.writeProperty(event+"-"+Category+"_JsonBody","["+obj.toJSONString()+"]");

        System.out.println("JSON BODY: \t"+"["+obj.toJSONString()+"]");

        System.out.println("******************* JSON File SUCCESSFULLY Created*************************");
    }


    public static String readProperty(String property) {
        InputStream inPropFile = null;
        try {
            inPropFile = new FileInputStream(filePath);
            properties.load(inPropFile);
        } catch (IOException e) {
            System.out.println("There was Exception reading the Test data");
        }
        String value = properties.getProperty(property);
        return value;
    }

    public static String readHartfordProperty(String property) {
        InputStream inPropFile = null;
        try {
            inPropFile = new FileInputStream("data/hartford.properties");
            properties.load(inPropFile);

        } catch (IOException e) {
        }
        String value = properties.getProperty(property);

        return value;
    }

    /**
     * This method is used to store a new property in the properties file.
     *
     * @param property
     *            : name for the new property.
     * @param value
     *            : value for the new property.
     * @throws IOException
     */
    public static void writeProperty(String property, String value) {
        // Write properties file.
        // OutputStream outPropFile = null;

        try {
            InputStream inPropFile = new FileInputStream(filePath);
            properties.load(inPropFile);
            inPropFile.close();
            OutputStream outPropFile = new FileOutputStream(filePath);
            // System.getProperty("user.dir")+"\\AnswerKeys\\"+top+".properties"

            properties.setProperty(property, value);
            properties.store(outPropFile, null);
            outPropFile.close();
        } catch (IOException e) {
        }
    }


    public static void clearProperty() {
        try {
            InputStream inPropFile = new FileInputStream(filePath);
            properties.load(inPropFile);
            inPropFile.close();
            OutputStream outPropFile = new FileOutputStream(filePath);
            properties.clear();
            outPropFile.close();
        } catch (IOException e) {
        }
    }

    public static void writeDataInReportsData(String property, String str) {
        try {
            InputStream inPropFile = new FileInputStream(
                    System.getProperty("user.dir") + "\\ReportsData\\Data.properties");
            properties.load(inPropFile);
            inPropFile.close();
            OutputStream outPropFile = new FileOutputStream(
                    System.getProperty("user.dir") + "\\ReportsData\\Data.properties");
            // System.getProperty("user.dir")+"\\AnswerKeys\\"+top+".properties"

            properties.setProperty(property, str);
            properties.store(outPropFile, null);
            outPropFile.close();
        } catch (IOException e) {
        }
    }

    public static String readReportsData(String property) {
        InputStream inPropFile = null;
        try {
            inPropFile = new FileInputStream(System.getProperty("user.dir") + "\\ReportsData\\Data.properties");

            properties.load(inPropFile);

        } catch (IOException e) {
            System.out.println("There was Exception reading the Test data");
        }
        String value = properties.getProperty(property);
        return value;
    }

    public static void clearPropertyFromReports() {
        try {
            InputStream inPropFile = new FileInputStream(
                    System.getProperty("user.dir") + "\\ReportsData\\Data.properties");
            properties.load(inPropFile);
            inPropFile.close();
            OutputStream outPropFile = new FileOutputStream(
                    System.getProperty("user.dir") + "\\ReportsData\\Data.properties");
            properties.clear();
            outPropFile.close();
        } catch (IOException e) {
        }
    }


}
