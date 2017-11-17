package com.engagemnet.report.validation;

import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PostCapInteactionEvents {
	private static String capeventmessages = System.getProperty("user.dir")
			+ "/src/test/resources/data/capeventmessages/cap-interaction-events.json";

	public static void main(String[] args) throws JsonProcessingException, IOException, KeyManagementException,
			KeyStoreException, NoSuchAlgorithmException {
		ObjectMapper mapper = new ObjectMapper();

		JsonNode root = mapper.readTree(new File(capeventmessages));

		Iterator<JsonNode> iterator = root.iterator();
		int i=0;
		ExecutorService executor = Executors.newFixedThreadPool(25);
		while (iterator.hasNext()){
			JsonNode node = iterator.next();
        	PostCapInteractionEventsThread worker = new PostCapInteractionEventsThread(mapper.writeValueAsString(node),i);
            executor.execute(worker);
            i++;       
          }
        executor.shutdown();
	}
}
