package net.mindtap.showcase.cucumber.pages;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import gherkin.formatter.Reporter;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.annotations.findby.How;
import net.serenitybdd.core.pages.WebElementDescriber;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.pages.WebElementFacadeImpl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

/**
 * Created by qainfotech on 07-02-2017.
 */
public class BasePage extends PageObject {

	public List<WebElementFacade> elements(String replacement) {

		return $("//li/a[contains(.,'" + replacement + "')]");
	}

	public void switchToDefaultContent() {
		getDriver().switchTo().defaultContent();
	}
	public void switchToMainFrame() {
		WebElementFacade frame = $("//iframe[contains(@id,'_NB_Main_IFrame')]");
		getDriver().switchTo().frame(frame);
	}

	public void switchToDockIFrame() {
		WebElementFacade frame = $("//iframe[contains(@id,'NB_Dock_IFrame')]");
		getDriver().switchTo().frame(frame);
	}

	public void hardWait(int milisec) {
		try {
			Thread.currentThread().sleep(milisec);
		} catch (InterruptedException ex) {
			Logger.getLogger(CoursePage.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	protected List<WebElement> executeJavascriptWithReturnelement(String script) {
		return (List<WebElement>) ((JavascriptExecutor) getDriver()).executeScript(script);
	}

}
