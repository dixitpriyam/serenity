package net.mindtap.showcase.cucumber.utils.httpUtil;

import org.apache.http.client.utils.URIBuilder;

import java.net.URLDecoder;
import java.util.List;

/**
 * Created by abdul on 2/7/2017.
 */
public class URIUtil {

    public static String getURI(String strBaseURL,String scheme, List<String> URLParams){
        String strURL = "";
        try {
            URIBuilder baseUri = new URIBuilder();
            if(scheme!=null){
            	baseUri.setScheme(scheme);
            }else{
            	baseUri.setScheme("https");
            }
            baseUri.setHost(strBaseURL);
            String strPath = "";
            for(String up : URLParams){

                strPath = strPath.concat("/").concat(up);
            }
            baseUri.setPath(strPath);
            strURL = URLDecoder.decode(baseUri.build().toASCIIString(),"UTF-8");
        }catch (Exception e){
            e.printStackTrace();
        }
        return strURL;
    }


}
