package net.mindtap.showcase.cucumber.utils.httpUtil;

import net.mindtap.showcase.cucumber.utils.commonUtil.YamlReader;
import net.serenitybdd.core.Serenity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

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
public class GetUtil {
    public static HttpResponse get(List<String> strHeaders) throws IOException, KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        String url = Serenity.sessionVariableCalled("URL").toString();
        HttpGet getMethod = new HttpGet(url);
        CloseableHttpClient client;
        if (url.contains("https")) {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, (certificate, authType) -> true).build();
            client = HttpClients.custom()
                    .setSSLContext(sslContext)
                    .setSSLHostnameVerifier(new NoopHostnameVerifier())
                    .build();
            for (String header : strHeaders) {
                if (header.contains("cengage-sso-token")) {
                    getMethod.setHeader("cengage-sso-token", Serenity.sessionVariableCalled("Ssotoken"));
                } else {
                    ArrayList<String> strkeyValue = YamlReader.getTestDataKeyValue(header);
                    System.out.println("KV"+strkeyValue);
                    for (String key : strkeyValue) {
                        String[] strheader = key.split(":");
                        getMethod.setHeader(strheader[0], strheader[1]);
                    }
                }
            }
        }
        else{
            client = HttpClientBuilder.create().build();
        }
        HttpResponse response = client.execute(getMethod);
        Serenity.setSessionVariable("responsebody").to(EntityUtils.toString(response.getEntity()));
        return response;
    }
}
