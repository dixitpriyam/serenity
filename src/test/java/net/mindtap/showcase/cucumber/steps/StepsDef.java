package net.mindtap.showcase.cucumber.steps;

import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import com.engagemnet.report.validation.RegenerateTestData;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.mindtap.showcase.cucumber.pages.CoursePage;
import net.mindtap.showcase.cucumber.pages.InstructorResourceCenterPage;
import net.mindtap.showcase.cucumber.steps.serenity.MindtapSteps;
import net.serenitybdd.core.Serenity;

/**
 * Created by asiqa on 10/10/2016.
 */
public class StepsDef {

	InstructorResourceCenterPage instructorResourceCenterPage;
	CoursePage coursepage;
	@net.thucydides.core.annotations.Steps
	MindtapSteps mindtapSteps;

	@Given("^I Launch the snapshot with (.*) and (.*) into (.*) of (.*) ISBN$")
	public void I_login_to_mindtap_app(String userName, String password, String course, String ISBN) {
		mindtapSteps.login_into_app(userName.trim(), password.trim(), course.trim(), ISBN.trim());
	}
	@Given("^I Launch the snapshot with (.*) and (.*) into (.*) of (.*) ISBN on (.*)$")
	public void I_login_to_mindtap_app(String userName, String password, String course, String ISBN,String env) {
		mindtapSteps.login_into_app(userName.trim(), password.trim(), course.trim(), ISBN.trim(),env.trim());
	}

	@Given("I login into MindTap app with (.*) and (.*)")
	public void I_login_to_mindtap_app(String userName, String password) {
		mindtapSteps.login_into_app(userName, password);
	}

	@When("^I launch (.*) course on (.*) Environment$")
	public void iLaunchCourse(String course, String env) throws Throwable {
		instructorResourceCenterPage.lnkManageCourses.click();
		instructorResourceCenterPage.selectCourse(course, env);
	}

	@Then("^I should see MindTap course menus$")
	public void iShouldSeeMindTapCourseMenus() throws Throwable {
		mindtapSteps.should_see_MindTap_course_menu();
	}

	@Given("^I fill (.*) and (.*) in login form$")
	public void iFillAbdulCengageComAndPasswordInLoginForm(String username, String password) throws Throwable {
		mindtapSteps.fill_username_password(username, password);
	}

	@When("^I press Sign in button$")
	public void iPressSignInButton() throws Throwable {
		mindtapSteps.click_signin();
	}

	@Then("^I see invalid credentails message$")
	public void iSeeInvalidCredentailsMessage() throws Throwable {
		mindtapSteps.should_see_invalid_login_error_message();
	}

	@Then("^I see Instructor Resource Center page$")
	public void iSeeInstructorResourceCenterPage() throws Throwable {
		mindtapSteps.should_see_instructor_resource_center_heading();
	}

	@Then("^I see (\\d+) status code$")
	public void I_see_status_code(int statusCode) throws Throwable {
		mindtapSteps.should_see_status_code(statusCode);
	}

	@Given("^I construct a request with (.*) and following parameters : (.*)$")
	public void I_construct_URL_with_languagetool_org_host_and_following_parameters_api_v_languages(String host,
			List<String> URLParams) throws Throwable {
		mindtapSteps.construct_url(host, URLParams);
	}
	
	@Given("^I construct a request using base url (.*) and scheme (.*) and following parameters : (.*)$")
	public void I_construct_URL_with_host_and_schema_and_parameters(String host,String scheme,
			List<String> URLParams) throws Throwable {
		mindtapSteps.construct_url(host,scheme, URLParams);
	}

	@Given("^As a Mindtap user,I login with following (.*) and (.*)$")
	public void asAMindtapUserILoginWithFollowingUsernameAndPassword(String username, String password)
			throws Throwable {
		mindtapSteps.fill_username_password(username, password);
		mindtapSteps.click_signin();
	}

	@Given("^As a Mindtap user,I login in staging environment with following (.*) and (.*)$")
	public void asAMindtapUserILoginInStagingEnvironmentWithFollowingUsernameAndPassword(String username,
			String password) throws Throwable {
		mindtapSteps.fill_username_password_in_stage(username, password);
		mindtapSteps.click_signin();
	}

	@And("^I launch a (.*) on (.*) Environment$")
	public void iLaunchCourseInMindtap(String course, String env) throws Throwable {
		instructorResourceCenterPage.lnkManageCourses.click();
		instructorResourceCenterPage.selectCourse(course, env);
	}

	@When("^I click search from appdock$")
	public void iClickSearchFromAppdock() throws Throwable {
		mindtapSteps.click_search();
	}

	@And("^I enter (.*) in search box$")
	public void iEnterPage_noInSearchBox(String page_no) throws Throwable {
		coursepage.txtSearch.type(page_no);
		coursepage.imgSearchicon.click();
	}

	@Then("^I should see search results$")
	public void iShouldSeeSearchResults() throws Throwable {
		assertThat(coursepage.chkResults.isDisplayed()).isTrue();
	}

	@When("^I hit a POST request with (.*) and following headers: (.*)$")
	public void iHitAPOSTRequest(String body, List<String> Headers) throws Throwable {
		System.out.println("Json File Name: " + body);
		System.out.println("Header: " + Headers);
		mindtapSteps.hit_post_request(body, Headers);
	}

	@When("^I hit a POST request for (.*) Events under (.*) Category with (.*) and following headers: (.*)$")
	public void iHitAPOSTRequest(String EventAction, String EventCategory, String body, List<String> Headers)
			throws Throwable {
		System.out.println("Json File Name: " + body);
		System.out.println("Header: " + Headers);
		mindtapSteps.hit_post_request(body, Headers, EventAction, EventCategory);
	}

	@When("^I search a page with (.*)$")
	public void iSearchForPage_no(String page_no) throws Throwable {
		mindtapSteps.click_search();
		coursepage.txtSearch.type(page_no);
		coursepage.imgSearchicon.click();
	}

	@Then("^I navigate to unit view tab$")
	public void navigate_to_unit_view_tab() {
		mindtapSteps.clickOn_tab("Unit View");
	}

	@And("^I Clicked on (.*) Activity")
	public void clickOn_Activity(String activity_type) {
		mindtapSteps.clickOn_Activity(activity_type);

	}

	@And("^I select (.*) unit$")
	public void clickOn_Unit(String unit_name) {
		mindtapSteps.clickOn_Unit(unit_name);

	}

	@And("^I select (.*) reading activity$")
	public void clickOn_readingActivity(String activity_type) {
		mindtapSteps.clickOn_readingActivity(activity_type);

	}

	@And("^I Enlarge the Image$")
	public void clickOn_EnlargeImageIcon() {
		mindtapSteps.clickOn_EnlargeImageIcon();

	}

	@And("^I Verified the Enlarged Image$")
	public void Verify_the_Enlarged_Image() {
		mindtapSteps.Verify_the_Enlarged_Image();
	}

	@And("^I Launch (.*) App and Verify (.*) title$")
	public void Verify_the_Enlarged_Image(String AppName, String Title) {
		mindtapSteps.clickOnAppByName(AppName);
		mindtapSteps.verifyHeadingTitleAtTitleBar(Title);

	}

	@Then("^I Refresh the page$")
	public void refresh_the_page() {
		getDriver().navigate().refresh();
	}

	@When("^I select (.*) app from app dock$")
	public void iSelectFullBookAppFromAppDock(String strAppName) throws Throwable {
		mindtapSteps.launchApp(strAppName);
	}

	@When("^I launch (.*) app from app dock$")
	public void iLaunchCNowAppFromAppDock(String strAppName) throws Throwable {
		mindtapSteps.launchCNowApp(strAppName);
	}

	@Then("^I should see the book content$")
	public void iShouldSeeTheBookContent() throws Throwable {
		mindtapSteps.verifyFullBookContent();
	}

	@Given("^I am on course page$")
	public void iAmOnCoursePage() throws Throwable {
		mindtapSteps.verifyCoursePageTittle();
	}

	@When("^I select a (.*) from (.*)$")
	public void iSelectAChapterFromView(String strChapter, String strView) throws Throwable {
		mindtapSteps.launchReadingActivity(strChapter, strView);
	}

	@Then("^I should be able to view and read the content$")
	public void iShouldBeAbleToViewAndReadTheContent() throws Throwable {
		mindtapSteps.verifyCourseContent();
		mindtapSteps.launchReadingViewActivity();
	}

	@And("^I Bookmark the page$")
	public void bookmarkThePage() {
		mindtapSteps.bookmarkThePage();
	}

	@And("^I Highlight the Paragraph$")
	public void highlightTheParagraph() {
		mindtapSteps.highlightTheParagraph();
	}

	@And("^I Remove Highlight$")
	public void removeHighlight() {
		mindtapSteps.removeHighlight();
	}

	@And("^I create a QUICKNOTE$")
	public void createaQuicknote() {
		mindtapSteps.createaQuicknote();
	}

	@And("^I Delete a QUICKNOTE$")
	public void removeaQuicknote() {
		mindtapSteps.removeaQuicknote();
	}

	@Then("^I should see created flashcards$")
	public void iShouldSeeCreatedFlashcards() throws Throwable {
		mindtapSteps.viewFlashCards();
	}

	@When("^I select (.*) from user menu$")
	public void iSelectSystemCheckFromUserMenu(String strItem) throws Throwable {
		mindtapSteps.selectItemFromUserMenu(strItem);
	}

	@Then("^I should see system requirement pane$")
	public void iShouldSeeSystemRequirementPane() throws Throwable {
		mindtapSteps.veriftSystemRequirementPane();
	}

	@Then("^I should see the glossary content$")
	public void iShouldSeeTheglossaryContent() throws Throwable {
		mindtapSteps.verifyGlossaryContent();
	}

	@Then("^I should see the definition of glossary term (.*) in the chapter contents$")
	public void iShouldSeeTheDefinitionOfGlossaryItem(String strGlossaryItem) throws Throwable {
		mindtapSteps.verifyCourseContent();
		mindtapSteps.verifyGlossaryDefinition(strGlossaryItem);
	}

	@Then("^I spent (.*) in mindtap page$")
	public void iSpentSomeTimeInMindtapPage(String strTime) throws Throwable {
		mindtapSteps.spentTimeInMindtap(strTime);
	}

	@And("^I extract events details from resource interaction$")
	public void iExtractEventsDetailsFromResourceInteraction() throws Throwable {
		mindtapSteps.getEventsRIJson();
	}


	@Then("^I (.*) from mindtap page$")
	public void iLogoutFromMindtapPage(String logout) throws Throwable {
		mindtapSteps.selectItemFromUserMenu(logout);

	}

	@When("^I launch Engagement Report URL$")
	public void launch_Engagement_Report_URL() throws Throwable {
		mindtapSteps.launch_Engagement_Report_URL();

	}

	@When("^I Should see the Engagement Report$")
	public void verify_Engagement_Report() throws Throwable {
		mindtapSteps.verify_Engagement_Report();

	}

	@When("^I Play the Video$")
	public void play_the_Video() {

		mindtapSteps.play_the_Video();

	}

	@And("^I select add assigment button in aplia$")
	public void iSelectAddAssigmentButtonInAplia() throws Throwable {
		mindtapSteps.clickOn_ApliaAddAssignment();
	}

	@And("^I fetch SSO token for the (.*) with (.*)$")
	public void iFetchSSOTokenForTheUsernameWithPassword(String strUsername, String strPassword) throws Throwable {
		mindtapSteps.getSsoToken(strUsername.trim(), strPassword.trim());
	}

	@And("^I hit a GET request with following headers: (.*)$")
	public void iHitAGETRequestWithFollowingHeaders(List<String> strHeaders) throws Throwable {
		mindtapSteps.hit_get_request(strHeaders);
	}

	@Then("^I should see (.*) in response body$")
	public void iShouldSeeNotAnInstructorErrorInResponseBody(String strErrorMessage) throws Throwable {
		// HttpResponse response= Serenity.sessionVariableCalled("response");
		// System.out.println(EntityUtils.toString(response.getEntity()));
		mindtapSteps.validateErrorMessage(strErrorMessage);
	}

	@Then("^I should see engagement report json in response body same as in (.*) file$")
	public void iCompareEngagementReportWithSchemaReport(String jsonFile) throws Throwable {
		mindtapSteps.compareEngagmentReport(jsonFile);
	}

	@Then("^I navigate to (\\d+) page$")
	public void navigatToPage(int pageNumber) throws Throwable {
		mindtapSteps.navigatToPage(pageNumber);
	}

	//
	@Then("^I verify readingPageCount attribute present in Networklogs$")
	public void verifyReadingPageCountAttribute() throws Throwable {
		mindtapSteps.readingPageCountAttribute();
	}

	@When("^I hit a GET request$")
	public void ihitAGetRequest() throws Throwable {
		String url = Serenity.sessionVariableCalled("URL");
		String jobId = Serenity.sessionVariableCalled("JOB_ID");
		if (jobId != null) {
			url = url.replaceAll("\\{id\\}", jobId);
			Serenity.setSessionVariable("URL").to(url);
			System.out.println(url);
		}
		mindtapSteps.hit_get_request(Arrays.asList(new String[0]));
	}

	@And("^I fetch job status$")
	public void fetchJobStatus() {
		mindtapSteps.fetchJobStatus();
	}

	@And("^I fetch job id$")
	public void fetchJobId() {
		Serenity.setSessionVariable("JOB_ID").to(null);
		mindtapSteps.fetchJobId();
	}

	@Then("^I send Mail Notification For (.*)$")
	public void sendMailNotificationForFailure(String jobName) throws Throwable {
		mindtapSteps.sendMailNotificationForFailure(System.getProperty("RECIPIENTS"),jobName);
	}
	@Given("^Regenerate Test Data$")
	public void regenerateTestData(){
		System.out.println("Test");
		mindtapSteps.regenerateTestData();
	}
}
