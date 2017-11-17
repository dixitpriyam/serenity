package net.mindtap.showcase.cucumber;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;
import org.xml.sax.SAXException;

import com.engagemnet.report.validation.RegenerateTestData;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features/mindtap-poc/", tags = {"@CAP-155"}, format = {"json:target/cucumber/report.json"})
public class RunnerRegenerateTestData {

}
/*@RunWith(CucumberWithSerenity.class)
public class RunnerRegenerateTestData {
	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, TransformerException {
		new RegenerateTestData().main(args);
	}
}*/
