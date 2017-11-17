package net.mindtap.showcase.cucumber.utils.commonUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by dineshmadan on 2/7/2017.
 */
public class FileUtil implements Constants {
    public static String fileRead(String filename) throws IOException {
        String strJSONFilePath=JSONBODY_FOLDER_PATH.concat(filename);
        return new String(Files.readAllBytes(Paths.get(".",strJSONFilePath)));
    }
}
