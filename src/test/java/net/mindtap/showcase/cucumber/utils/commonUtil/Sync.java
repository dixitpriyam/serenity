package net.mindtap.showcase.cucumber.utils.commonUtil;


/**
 * Created by codoid-etl on 2/28/2017.
 */
public class Sync {

    public static void waitForSeconds(long lngSeconds) {
        try {
            Thread.sleep(lngSeconds * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
