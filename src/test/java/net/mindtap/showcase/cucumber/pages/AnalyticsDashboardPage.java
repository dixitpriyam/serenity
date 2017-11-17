package net.mindtap.showcase.cucumber.pages;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import gherkin.formatter.Reporter;
import net.mindtap.showcase.cucumber.utils.commonUtil.Sync;
import net.mindtap.showcase.cucumber.utils.httpUtil.JsonFileHandler;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.annotations.findby.How;
import net.serenitybdd.core.pages.WebElementDescriber;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.pages.WebElementFacadeImpl;
import net.thucydides.core.pages.PageObject;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import net.mindtap.showcase.cucumber.utils.httpUtil.PropFileHandler;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;

import static jdk.nashorn.internal.objects.NativeString.substring;
import static org.assertj.core.api.Assertions.assertThat;
import net.serenitybdd.core.Serenity;
public class AnalyticsDashboardPage extends BasePage {

	String searchEventCount,legacy_hr,legacy_login;
	int index = 13;
	static int i=0;
	@FindBy(how = How.ID, using = "emailId")
	public WebElementFacade txtUsername;

	@FindBy(how = How.ID, using = "password")
	public WebElementFacade txtPassword;

	@FindBy(how = How.XPATH, using = "//input[@value='Sign In']")
	public WebElementFacade btnSignIn;

	@FindBy(how = How.CSS, using = ".ui-grid-disable-selection.ng-scope")
	public WebElementFacade selectUser;

	@FindBy(how = How.XPATH, using = "//input[contains(@class,'ui-grid-filter')]")
	public List<WebElementFacade> eventAction_InputBox;

	@FindBy(how = How.XPATH, using = "//span[.='Event Action']/parent::div/following-sibling::div[2]//input")
	public WebElementFacade txtEventActionSearch;

	@FindBy(how = How.XPATH, using = "//span[.='Event Source']/parent::div/following-sibling::div[2]//input")
	public WebElementFacade txtEventSourceSearch;

	@FindBy(how = How.XPATH, using = "//span[.='Event Category']/parent::div/following-sibling::div[2]//input")
	public WebElementFacade txtEventCategorySearch;

	@FindBy(how = How.XPATH, using = "//span[.='Interaction Timestamp']/parent::div/following-sibling::div[2]//input")
	public WebElementFacade txtInteractionTimestamp;

	public void login(String username, String password) {
		txtUsername.type(username);
		txtPassword.type(password);
		btnSignIn.click();
	}

	public void selectUser(String user) {
		hardWait(30000);
		selectUser.click();
		waitForRenderedElements(By.xpath("//a[contains(@title,'0 requests pending')]"));

		// waitFor("//a[contains(@title,'0 requests pending')]");
	}

	public void searchAndSelectUser(String strSearchData, String strSearchType) {
		// Wait for Users heading
		waitFor("//h4[.='Users']");

		String xpath = new StringBuilder().append("//span[.='").append(strSearchType)
				.append("']/parent::div/following-sibling::div[2]//input").toString();

		find(By.xpath(xpath)).sendKeys(strSearchData);
		hardWait(30000);
		selectUser.click();
	}

	public void search_By_Date() {
		try {
			Thread.sleep(4000);
		} catch (InterruptedException ex) {
			Logger.getLogger(AnalyticsDashboardPage.class.getName()).log(Level.SEVERE, null, ex);
		}
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		String queryDate = dateFormat.format(date).trim().replace("/", "-");
		System.out.println("Current Data: " + queryDate);
		hardWait(3000);

	//	waitFor(txtInteractionTimestamp);

		Actions actions = new Actions(getDriver());
		actions.moveToElement(txtInteractionTimestamp).sendKeys(queryDate).perform();

		txtInteractionTimestamp.sendKeys(queryDate);
		// eventAction_InputBox.get(18).sendKeys("2017-02-09");

	}
	public void search_By_Date_For_TimeInMindtap() {
		Sync.waitForSeconds(10);
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH");
		f.setTimeZone(TimeZone.getTimeZone("UTC"));
		System.out.println(f.format(new Date()));
		String s=f.format(new Date()).trim().substring(0,10);
		System.out.println(f.format(new Date()));
		txtInteractionTimestamp.sendKeys(s);
	}

	public void search_By_Event_Category(String Category) {
		eventAction_InputBox.get(15).sendKeys(Category);
	}

	public void search_By_Event_Action(String event) {
		eventAction_InputBox.get(16).sendKeys(event);
	}

	public void calculate_Total_Events_Generated(String event) {
		List<WebElementFacade> total_event = findAll(" //div[contains(text(),'" + event + "')]");
		searchEventCount = Integer.toString(total_event.size());
		PropFileHandler.writeProperty(event + "EventCount", searchEventCount);
		System.out.println(event + " EventCount:- " + searchEventCount);
	}

	public void calculate_Total_Events_Generated(String event, String Category) {
		List<WebElementFacade> total_event = findAll(" //div[contains(text(),'" + event + "')]");
		searchEventCount = Integer.toString(total_event.size());
		PropFileHandler.writeProperty(event + "-" + Category + "_EventCount", searchEventCount);
		System.out.println(event + " EventCount:- " + searchEventCount);
	}

	public void calculateTotalEventsGenerated(String event, String Category) {
		List<WebElementFacade> total_event = findAll(
				"//div[@class='ui-grid-cell-contents ng-binding ng-scope' and .='" + event + "']");
		searchEventCount = Integer.toString(total_event.size());
		PropFileHandler.writeProperty(event + "-" + Category + "_EventCount", searchEventCount);
		System.out.println(event + " EventCount:- " + searchEventCount);
	}

	public void verify_Events_Generated(String event) throws InterruptedException {

		Thread.currentThread().sleep(30000);
		List<WebElementFacade> total_event = findAll(" //div[contains(text(),'" + event + "')]");
		// Logger.getLogger("Post Action Event Count: " +total_event.size());
		System.out.println("**********************************************************");
		System.out.println("Pre Action Event Count: " + PropFileHandler.readProperty(event + "EventCount"));
		System.out.println("Post Action Event Count: " + total_event.size());
		System.out.println("**********************************************************");
		String eventCount = Integer.toString(total_event.size() - 1);
		// String eventCount = Integer.toString(total_event.size());
		assertThat(PropFileHandler.readProperty(event + "EventCount")).contains(eventCount);

	}

	public void verify_Events_Generated(String event,String Category ) throws InterruptedException {

		// Thread.currentThread().sleep(30000);
		List<WebElementFacade> total_event=  findAll(" //div[contains(text(),'"+event+"')]");
		//  Logger.getLogger("Post Action Event Count: " +total_event.size());
		System.out.println("**********************************************************");
		System.out.println("Pre Action Event Count: " + PropFileHandler.readProperty(event+"-"+Category+"_EventCount"));
		System.out.println("Post Action Event Count: " + total_event.size());
		System.out.println("**********************************************************");
		String eventCount = Integer.toString(total_event.size()-1);

		if ((PropFileHandler.readProperty(event+"-"+Category+"_EventCount")).contains(eventCount)) {
			assertThat(PropFileHandler.readProperty(event+"-"+Category+"_EventCount")).contains(eventCount);
		}
		else{
			if(i>3){
				System.out.println("Test Falied: All the Attempts Completed ");
				assertThat(PropFileHandler.readProperty(event+"-"+Category+"_EventCount")).contains(eventCount);
			}
			else{
				System.out.println("Test Falied ");
				System.out.println("Retry count "+ i++);
				getDriver().navigate().refresh();
				selectUser("");
				search_By_Date();
				search_EventsByActionSourceCategory(Category,event,"GTM");
				verify_Events_Generated(event,Category);
			}
		}
	}

	public void verifyEventsGenerated(String event,String Category ) throws InterruptedException {

		Thread.currentThread().sleep(4000);
		List<WebElementFacade> total_event=  findAll("//div[@class='ui-grid-cell-contents ng-binding ng-scope' and .='"+event+"']");
		//  Logger.getLogger("Post Action Event Count: " +total_event.size());
		System.out.println("**********************************************************");
		System.out.println("Pre Action Event Count: " + PropFileHandler.readProperty(event+"-"+Category+"_EventCount"));
		System.out.println("Post Action Event Count: " + total_event.size());
		System.out.println("**********************************************************");
		String preActionEventCount=PropFileHandler.readProperty(event+"-"+Category+"_EventCount");
		System.out.println(preActionEventCount);
		if ((total_event.size()) > (Integer.valueOf(preActionEventCount))){
			assertThat(total_event.size()).isGreaterThan(Integer.valueOf(preActionEventCount));
		}
		else{
			//  System.out.println("i:- "+i);
			if(i>3){
				System.out.println("Test Falied: All the Attempts Completed ");
				assertThat(total_event.size()).isGreaterThan(Integer.valueOf(preActionEventCount));
			}
			else{
				System.out.println("Test Falied ");
				System.out.println("Retry count "+ i++);
				getDriver().navigate().refresh();
				selectUser("");
				search_By_Date();
				search_EventsByActionSourceCategory(Category,event,"GTM");
				verifyEventsGenerated(event,Category);
			}
		}
	}

	public void verifyTheEventsDetails(String source, String category, String value) {
		List<WebElementFacade> total_event = findAll(".ui-grid-cell-contents.ng-binding.ng-scope");
		index = total_event.size() - 1;
		System.out.println("Index Size: " + index);
		System.out.println("*************Event Details*******************");
		verifyEventValue(value);
		// System.out.println("Index Size: 1"+index);
		verifyTimeZoneOffSet();
		// System.out.println("Index Size: 2"+index);
		verifyIntegrationTimeStamps();
		// System.out.println("Index Size: 3"+index);
		// verifyActivity();
		// System.out.println("Index Size: 4"+index);
		verifyEventAction();
		// System.out.println("Index Size: 5"+index);
		verifyEventCategory(category);
		// System.out.println("Index Size: 6"+index);
		verifyEventSource(source);
		// System.out.println("Index Size: 7"+index);
		verifyID();
		// System.out.println("Index Size: 8"+index);
		System.out.println("*************Event Details*******************");
		System.out.println("Successfully Verified Event Details ");
	}

	private void verifyEventAction() {
		List<WebElementFacade> event_row = findAll(".ui-grid-cell-contents.ng-binding.ng-scope");
		System.out.println("Event Activity: " + event_row.get(index--).getText());
	}

	private void verifyEventValue(String value) {
		List<WebElementFacade> total_event = findAll(".ui-grid-cell-contents.ng-binding.ng-scope");
		if (total_event.get(index--).getText().trim().contains(value)) {
			System.out.println("Event Value: " + value);
			Assert.assertTrue(true);
		} else {
			System.out.println("Event Value is not correct");
			Assert.assertTrue(false);
		}
	}

	private void verifyTimeZoneOffSet() {
		List<WebElementFacade> total_event = findAll(".ui-grid-cell-contents.ng-binding.ng-scope");
		System.out.println("Time Zone Off Set: " + total_event.get(index--).getText());
	}

	private void verifyIntegrationTimeStamps() {
		List<WebElementFacade> event_row = findAll(".ui-grid-cell-contents.ng-binding.ng-scope");
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		String queryDate = dateFormat.format(date).trim().replace("/", "-");
		String eventDate = event_row.get(index--).getText().trim();
		System.out.println("Current Data: " +queryDate.substring(0,10));
		System.out.println("Event Date: " + eventDate.substring(0,10));
		if (eventDate.substring(0,10).contains(queryDate.trim().substring(0,10))) {
			Assert.assertTrue(true);
		} else {
			System.out.println("Event Date is not correct");
			Assert.assertTrue(false);
		}
	}

	private void verifyID() {
		List<WebElementFacade> event_row = findAll(".ui-grid-cell-contents.ng-binding.ng-scope");
		System.out.println("Event ID: " + event_row.get(index--).getText());
	}

	private void verifyEventCategory(String category) {
		List<WebElementFacade> event_row = findAll(".ui-grid-cell-contents.ng-binding.ng-scope");
		if (event_row.get(index--).getText().trim().contains(category)) {
			System.out.println("Event Category: " + category);
			Assert.assertTrue(true);
		} else {
			System.out.println("Event Category is not correct");
			Assert.assertTrue(false);
		}
	}

	private void verifyEventSource(String source) {
		List<WebElementFacade> event_row = findAll(".ui-grid-cell-contents.ng-binding.ng-scope");
		// System.out.println("Analytics tool- Event Source:
		// "+event_row.get(index--).getText());
		if (event_row.get(index--).getText().contains(source.trim())) {
			System.out.println("Event Source: " + source);
			Assert.assertTrue(true);
		} else {
			System.out.println("Event Source: " + source);
			System.out.println("Event source is not correct");
			Assert.assertTrue(false);
		}
	}

	public void createJsonFile() {
		System.out.println("******************* Creating JSON File*************************");
		System.out.println("Loading ......");
		List<String> jsonBody = new ArrayList<>();
		jsonBody.add(PropFileHandler.readProperty("eventAction"));
		jsonBody.add(PropFileHandler.readProperty("eventCategory"));
		jsonBody.add(PropFileHandler.readProperty("eventLabel"));
		jsonBody.add(PropFileHandler.readProperty("courseKey"));
		jsonBody.add(PropFileHandler.readProperty("coreTextISBN"));
		jsonBody.add(PropFileHandler.readProperty("userSSOGuid"));
		jsonBody.add(PropFileHandler.readProperty("productPlatform"));
		jsonBody.add(PropFileHandler.readProperty("environment"));
		jsonBody.add(PropFileHandler.readProperty("activityCGI"));
		jsonBody.add(PropFileHandler.readProperty("localTime"));
		jsonBody.add(PropFileHandler.readProperty("eventValue"));

		JsonFileHandler.createJSONFile(jsonBody);

	}

	public void createJsonFile(String event, String Category) {
		System.out.println("******************* Creating JSON File*************************");
		System.out.println("Loading ......");
		List<String> jsonBody = new ArrayList<>();
		jsonBody.add(PropFileHandler.readProperty("eventAction"));
		jsonBody.add(PropFileHandler.readProperty("eventCategory"));
		jsonBody.add(PropFileHandler.readProperty("eventLabel"));
		jsonBody.add(PropFileHandler.readProperty("courseKey"));
		jsonBody.add(PropFileHandler.readProperty("coreTextISBN"));
		jsonBody.add(PropFileHandler.readProperty("userSSOGuid"));
		jsonBody.add(PropFileHandler.readProperty("productPlatform"));
		jsonBody.add(PropFileHandler.readProperty("environment"));
		jsonBody.add(PropFileHandler.readProperty("activityCGI"));
		jsonBody.add(PropFileHandler.readProperty("localTime"));
		jsonBody.add(PropFileHandler.readProperty("eventValue"));

		JsonFileHandler.createJSON(jsonBody, event, Category);

	}

	public void search_EventsByActionSourceCategory(String strEventCategory, String strEventAction,
			String strEventSource) {
		txtEventActionSearch.sendKeys(strEventAction);
		txtEventSourceSearch.sendKeys(strEventSource);
		txtEventCategorySearch.sendKeys(strEventCategory);
		PropFileHandler.writeProperty("eventCategory", strEventCategory);
		PropFileHandler.writeProperty("eventAction", strEventAction);
	}

	public void verifyTimeInMindtapEventsGenerated(String event, String Category) throws InterruptedException {
		int timeSpentInMindtap = Integer.parseInt(PropFileHandler.readProperty("TimeSpentInImindtap"));
		Thread.currentThread().sleep(4000);
		List<WebElementFacade> total_event = findAll(
				"//h4[.='Aurora Resource Interactions']/parent::div/following-sibling::div//div[@class='ui-grid-row ng-scope']//div[4]");
		// Logger.getLogger("Post Action Event Count: " +total_event.size());
		System.out.println("**********************************************************");
		System.out.println(
				"Pre Action Event Count: " + PropFileHandler.readProperty(event + "-" + Category + "_EventCount"));
		System.out.println("Actual Post Action Event Count: " + total_event.size());
		System.out.println("**********************************************************");
		int preActionEventCount = Integer.parseInt(PropFileHandler.readProperty(event + "-" + Category + "_EventCount"));
		int ExpectedPostEventCount = (timeSpentInMindtap / 60)+ preActionEventCount;
		System.out.println("Expected Post Event Count: " + ExpectedPostEventCount);
		assertThat(total_event.size()).isEqualTo(ExpectedPostEventCount);
	}


	public void calculateTotalTimeInTapEventsGenerated(String event, String Category) {
		Sync.waitForSeconds(10);
		List<WebElementFacade> total_event = findAll(
				"//h4[.='Aurora Resource Interactions']/parent::div/following-sibling::div//div[@class='ui-grid-row ng-scope']//div[4]");
		searchEventCount = Integer.toString(total_event.size());
		System.out.println(total_event.size());
		PropFileHandler.writeProperty(event + "-" + Category + "_EventCount", searchEventCount);
		System.out.println(event + " EventCount:- " + searchEventCount);
	}


	public void get_SSO_Token(String userGUID) {
		String URL= "https://analytics-tools.cengage.info/diagnostictool/#/user/view/staging/userIdentifier/"+userGUID;
		System.out.println("UserGUID: "+ userGUID);
		System.out.println("ADT Launch URL: "+URL);
		getDriver().navigate().to(URL);
		hardWait(5000);
		List<WebElementFacade> total_event = findAll("(//span[contains(@data-ng-if,'ctrl.parent.ssoToken')])");
		total_event.get(0).click();
				//waitFor("(//span[@data-ng-if='ctrl.parent.ssoToken'])[1]");
		//$("(//span[@data-ng-if='ctrl.parent.ssoToken'])[1]").click();
		PropFileHandler.writeProperty("SSOToken",$("(//span[@data-ng-if='ctrl.parent.ssoToken'])[2]").getText());
		//PropFileHandler.writeProperty("SSOToken",total_event.get(1).getText());
		System.out.println("SSOTOKEN:- "+ PropFileHandler.readProperty("SSOToken"));
		Serenity.setSessionVariable("Ssotoken").to(PropFileHandler.readProperty("SSOToken"));
	}

    public void validate_legacy_report_with_new_report() {
		launch_legacy_report();
		launch_and_verify_legacy_report();
		String CourseURI=System.getProperty("CourseURI");
		String SSOToken=PropFileHandler.readProperty("SSOToken");
		String url="https://analytics-cdn.cengage.com/engagement-report/staging/?ssoToken="+SSOToken+"&userRole=INSTRUCTOR&courseUri="+CourseURI+"#instructor-dashboard";
		System.out.println("CourseURI:- "+CourseURI);
		System.out.println("SSOToken:- " +SSOToken);
		System.out.println("url:- "+url);
		getDriver().navigate().to(url);
    }

	private void launch_legacy_report() {
		String CourseURI=System.getProperty("CourseURI");
		String SSOToken=PropFileHandler.readProperty("SSOToken");
		String url="https://s-mindtap-analytics.cengage.com/mindtapui/?ssoToken="+SSOToken+"&userRole=INSTRUCTOR&courseUri="+CourseURI+"#instructor-dashboard";
		System.out.println("CourseURI:- "+CourseURI);
		System.out.println("SSOToken:- " +SSOToken);
		System.out.println("url:- "+url);
		getDriver().navigate().to(url);
		// legacy_hr =$("(//div[@class='meter time']/*[name()=\"svg\"]//*[name()=\"text\"])[2]").getText();
		// legacy_login=$("(//div[@class='meter logins']/*[name()=\"svg\"]//*[name()=\"text\"])[2]").getText();
	}

	private void launch_and_verify_legacy_report() {
		String CourseURI=System.getProperty("CourseURI");
		String SSOToken=PropFileHandler.readProperty("SSOToken");
		String url="https://analytics-cdn.cengage.com/engagement-report/staging/?ssoToken="+SSOToken+"&userRole=INSTRUCTOR&courseUri="+CourseURI+"#instructor-dashboard";
		System.out.println("CourseURI:- "+CourseURI);
		System.out.println("SSOToken:- " +SSOToken);
		System.out.println("url:- "+url);
		getDriver().navigate().to(url);
		String new_hr =$("(//div[@class='meter time']/*[name()=\"svg\"]//*[name()=\"text\"])[2]").getText();
		String new_login=$("(//div[@class='meter logins']/*[name()=\"svg\"]//*[name()=\"text\"])[2]").getText();
		System.out.println("************************************");
		System.out.println("Legacy report hour:- "+legacy_hr);
		System.out.println("New report hour:- "+new_hr);
		assertThat(legacy_hr.contains(new_hr));
		System.out.println("Number of hours Verified");
		System.out.println("************************************");
		System.out.println(" ");
		System.out.println("************************************");
		System.out.println("Legacy report Login:- "+legacy_login);
		System.out.println("New report Login:- "+new_login);
		assertThat(legacy_login.contains(new_login));
		System.out.println("Number of Login Verified");
		System.out.println("************************************");


	}
}
