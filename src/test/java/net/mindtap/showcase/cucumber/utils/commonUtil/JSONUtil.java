package net.mindtap.showcase.cucumber.utils.commonUtil;

import net.mindtap.showcase.cucumber.utils.httpUtil.PropFileHandler;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;
/**
 * Created by sabdul on 2/7/2017.
 */
public class JSONUtil implements Constants {

    public static void createJsonForEventsInRILog(){
        Map<String, Object> logsdetails = new HashMap<String, Object>();
        Map<String, Object> eventdetails = new HashMap<String, Object>();

        for (LogEntry entry : getDriver().manage().logs().get(LogType.PERFORMANCE)) {
            logsdetails.put("first", entry.toMap());
            if (logsdetails.values().toString().contains(POSTDATA) && logsdetails.values().toString().contains(EVENT_ACTION)&& logsdetails.values().toString().contains(COURSE_URI)) {
                String jsonstring = logsdetails.values().toString();
                String strEventJSON=new String();
                try {
                    strEventJSON = (jsonstring.substring(jsonstring.indexOf(POSTDATA) + 11, jsonstring.indexOf(REFERRER_POLICY) - 3)).replace("\\", "");
                }
                catch (Exception e){
                    strEventJSON = (jsonstring.substring(jsonstring.indexOf(POSTDATA) + 11, jsonstring.indexOf(URL) - 2)).replace("\\", "");
                }
                System.out.println(strEventJSON);
                JSONParser parser = new JSONParser();
                JSONArray array = null;
                try {
                    array = (JSONArray) parser.parse(strEventJSON);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String strEventAction = (String) ((JSONObject) array.get(0)).get("eventAction");
                String strEventCategory = (String) ((JSONObject) array.get(0)).get("eventCategory");
                if (eventdetails.get(strEventCategory + strEventAction) != null){
                    String event = eventdetails.get(strEventCategory + strEventAction).toString()+","+((JSONObject)array.get(0)).toJSONString();
                    eventdetails.put(strEventCategory + strEventAction, event);
                }
                else {
                    String a= ((JSONObject) array.get(0)).toJSONString();
                    eventdetails.put(strEventCategory+strEventAction,a);
                }

            }

        }
        for (Map.Entry<String, Object> entry : eventdetails.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            System.out.println("KEY:"+key);
            System.out.println("VALUE:"+value);
            PropFileHandler.writeProperty(key+"_JsonBody",value.toString());
          //  PropFileHandler.writeProperty(event+"-"+Category+"_JsonBody",value);
            try (FileWriter file = new FileWriter(JSONBODY_FOLDER_PATH+key+".json")) {
                List<String> jsonBody = new ArrayList<>();
                jsonBody.add((String) value);
                file.write(String.valueOf(jsonBody));
                file.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
