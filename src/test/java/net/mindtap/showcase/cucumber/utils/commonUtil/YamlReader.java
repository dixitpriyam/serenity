package net.mindtap.showcase.cucumber.utils.commonUtil;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by abdul on 2/7/2017.
 */
public class YamlReader implements Constants {

    public static ArrayList<String> getTestDataKeyValue(String keyname) {
        File file = new File(TESTDATA_FOLDER_PATH);
        String strabsoluteFilePath = file.getAbsolutePath();
        ArrayList<String> key = new ArrayList<String>();
        Yaml yaml = new Yaml();
        try {
            InputStream ios = new FileInputStream(new File(strabsoluteFilePath));
            Map<String, Object> result = (Map<String, Object>) yaml.load(ios);
            key.add(keyname.toString().concat(":"+(result.get(keyname).toString())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return key;
    }
}