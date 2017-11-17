package com.engagemnet.report.validation;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class PostCapInteractionEventsThread implements Runnable {

	private String jsonPayload;
	private int i;
	
	public PostCapInteractionEventsThread(String payload, int i){
		this.jsonPayload = payload;
		this.i = i;
	}
	
	private String postUtil() {
		CloseableHttpClient client;
		String url = "https://u4k8v1b3s8.execute-api.us-east-1.amazonaws.com/staging/resource-interactions";
		HttpPost postMethod = new HttpPost(url);
		postMethod.setHeader("Content-type", "application/json");
		postMethod.setHeader("x-api-key", "lEehx310I43uKk8vbcgoL7ElmrF3ufsg6SOAk0JQ");
		postMethod.setEntity(new StringEntity("[" + jsonPayload + "]", ContentType.APPLICATION_JSON));
		client = HttpClientBuilder.create().build();

		HttpResponse response;
		String jsonResponse = null;

		try {
			response = client.execute(postMethod);
			jsonResponse = EntityUtils.toString(response.getEntity());
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(i);
		return jsonResponse;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		postUtil();
		

	}

}
