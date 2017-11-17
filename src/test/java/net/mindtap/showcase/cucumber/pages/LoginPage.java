package net.mindtap.showcase.cucumber.pages;

import java.io.IOException;
import java.util.ArrayList;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import net.mindtap.showcase.cucumber.utils.commonUtil.FileUtil;
import net.mindtap.showcase.cucumber.utils.commonUtil.YamlReader;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.annotations.findby.How;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;


public class LoginPage extends PageObject {

    @FindBy(how = How.ID, using = "emailId")
    public WebElementFacade txtUsername;

    @FindBy(how = How.ID, using = "password")
    public WebElementFacade txtPassword;

    @FindBy(how = How.XPATH, using = "//*[@value='Sign In']")
    public WebElementFacade btnSignIn;

    @FindBy(how = How.XPATH, using = "//div[@id='columnMain']//div[@class='errormsg']")
    public WebElementFacade elmntLoginErrorMessage;


    public void login(String username,String password){
        txtUsername.type(username);
        txtPassword.type(password);
        btnSignIn.click();
    }
    
    
    
    /**
     * @param userId
     * @param password
     * @param courseKey
     * @param titleIsbn
     * @return finalUrl
     */
    public String getFinalUrl(String userId, String password,String courseKey, String titleIsbn,String env) {
		CloseableHttpClient client;
		String url = "http://kt-ws.cengage.com/ssows/rest/getToken";
		String json_string = "{\"uid\":\""+userId+"\",\"password\":\""+password+"\"}";
		HttpPost postMethod = new HttpPost(url);
		postMethod.setHeader("Content-type", "application/json");
		postMethod.setEntity(new StringEntity(json_string, ContentType.APPLICATION_JSON));
		client = HttpClientBuilder.create().build();

		HttpResponse response;
		String jsonResponse;
		Object parse;
		JSONObject jsonObject = null;
		JSONParser parser = new JSONParser();
		
		try {
			response = client.execute(postMethod);
			jsonResponse = EntityUtils.toString(response.getEntity());
			parse = parser.parse(jsonResponse);
			jsonObject = (JSONObject)parse;
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
	
       String token = (String) jsonObject.get("token");
       String returnString = "http://mindtap-"+env+".cengage.com/nb/service/launch?token="
    		   +token+"&eISBN="+titleIsbn+"&courseKey="+courseKey;
        System.out.println("Snapshot URL:- "+returnString);
		getDriver().navigate().to(returnString);
		//openAt(returnString);
        return returnString;
	}


	public String getSsoToken(String strUsername, String strPassword) {
		CloseableHttpClient client;
		String url = "http://kt-ws.cengage.com/ssows/rest/getToken";
		String json_string = "{\"uid\":\""+strUsername+"\",\"password\":\""+strPassword+"\"}";
		HttpPost postMethod = new HttpPost(url);
		postMethod.setHeader("Content-type", "application/json");
		postMethod.setEntity(new StringEntity(json_string, ContentType.APPLICATION_JSON));
		client = HttpClientBuilder.create().build();
		HttpResponse response;
		String jsonResponse;
		Object parse;
		JSONObject jsonObject = null;
		JSONParser parser = new JSONParser();

		try {
			response = client.execute(postMethod);
			jsonResponse = EntityUtils.toString(response.getEntity());
			parse = parser.parse(jsonResponse);
			jsonObject = (JSONObject)parse;
		} catch (Exception e) {
			e.printStackTrace();
		}
		String token = (String) jsonObject.get("token");
		System.out.println(token);
		Serenity.setSessionVariable("Ssotoken").to(token);
		return token;
	}
}
