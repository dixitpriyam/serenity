package net.mindtap.showcase.cucumber;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features/mindtap-poc/", tags = {"@CAP-201"}, format = {"json:target/cucumber/report.json"})
public class Runner1 {

}
