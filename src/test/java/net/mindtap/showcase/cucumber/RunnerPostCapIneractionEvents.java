package net.mindtap.showcase.cucumber;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import com.engagemnet.report.validation.PostCapInteactionEvents;
import com.fasterxml.jackson.core.JsonProcessingException;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
public class RunnerPostCapIneractionEvents {
	public static void main(String[] args) throws KeyManagementException, JsonProcessingException, KeyStoreException, NoSuchAlgorithmException, IOException {
		new PostCapInteactionEvents().main(args);
	}

}
