package net.mindtap.showcase.cucumber.utils.httpUtil;

import net.mindtap.showcase.cucumber.utils.commonUtil.FileUtil;
import net.mindtap.showcase.cucumber.utils.commonUtil.YamlReader;
import net.serenitybdd.core.Serenity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by abdul on 2/7/2017.
 */
public class PostUtil {
    public static HttpResponse post(String json, List<String> Headers) throws IOException, KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        CloseableHttpClient client;
        String json_string = FileUtil.fileRead(json);
        System.out.println("Json Body :-"+json_string);
        String url = Serenity.sessionVariableCalled("URL").toString();
        StringEntity requestEntity = new StringEntity(
                json_string,
                ContentType.APPLICATION_JSON);
        HttpPost postMethod = new HttpPost(url);
        for(String header : Headers){
            ArrayList<String> strkeyValue=YamlReader.getTestDataKeyValue(header);
            for(String key: strkeyValue) {
                String[] strheader = key.split(":");
                postMethod.setHeader(strheader[0], strheader[1]);
            }
        }
        postMethod.setEntity(requestEntity);
        if (url.contains("https")) {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, (certificate, authType) -> true).build();
            client = HttpClients.custom()
                    .setSSLContext(sslContext)
                    .setSSLHostnameVerifier(new NoopHostnameVerifier())
                    .build();
        }
        else{
            client = HttpClientBuilder.create().build();
        }
        HttpResponse response = client.execute(postMethod);
        return response;
    }

    public static HttpResponse post(String json, List<String> Headers,String event,String  Category) throws IOException, KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        CloseableHttpClient client;
        String json_string = "["+PropFileHandler.readProperty(Category.toUpperCase()+event.toUpperCase()+"_JsonBody")+"]";
        System.out.println("Json Body :-"+json_string);
        String url = Serenity.sessionVariableCalled("URL").toString();
        StringEntity requestEntity = new StringEntity(
                json_string,
                ContentType.APPLICATION_JSON);
        HttpPost postMethod = new HttpPost(url);
        for(String header : Headers){
            ArrayList<String> strkeyValue=YamlReader.getTestDataKeyValue(header);
            for(String key: strkeyValue) {
                String[] strheader = key.split(":");
                postMethod.setHeader(strheader[0], strheader[1]);
            }
        }
        postMethod.setEntity(requestEntity);
        if (url.contains("https")) {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, (certificate, authType) -> true).build();
            client = HttpClients.custom()
                    .setSSLContext(sslContext)
                    .setSSLHostnameVerifier(new NoopHostnameVerifier())
                    .build();
        }
        else{
            client = HttpClientBuilder.create().build();
        }
        HttpResponse response = client.execute(postMethod);
        return response;
    }
}
