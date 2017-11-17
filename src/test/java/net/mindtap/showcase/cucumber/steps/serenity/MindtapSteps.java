package net.mindtap.showcase.cucumber.steps.serenity;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.engagemnet.report.validation.RegenerateTestData;
import org.apache.http.HttpResponse;

import net.mindtap.showcase.cucumber.pages.CoursePage;
import net.mindtap.showcase.cucumber.pages.EngagementReports;
import net.mindtap.showcase.cucumber.pages.InstructorResourceCenterPage;
import net.mindtap.showcase.cucumber.pages.JobFailureNotification;
import net.mindtap.showcase.cucumber.pages.LoginPage;
import net.mindtap.showcase.cucumber.utils.commonUtil.FileUtil;
import net.mindtap.showcase.cucumber.utils.httpUtil.GetUtil;
import net.mindtap.showcase.cucumber.utils.httpUtil.PostUtil;
import net.mindtap.showcase.cucumber.utils.httpUtil.URIUtil;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

/**
 * Created by abdul on 10/11/2016.
 */
public class MindtapSteps extends ScenarioSteps {

    LoginPage loginPage;
    CoursePage coursePage;
    InstructorResourceCenterPage instructorResourceCenterPage;
    EngagementReports engagementReports;
    JobFailureNotification notification = new JobFailureNotification();

    @Step
    public void login_into_app(String username, String password){
        loginPage.getDriver().manage().window().maximize();
        loginPage.open();
        loginPage.login(username,password);
    }
    
    @Step
    public void login_into_app(String username, String password,String course,String ISBN,String env){
        //loginPage.getDriver().manage().window().maximize();
        //loginPage.open();
        loginPage.getFinalUrl(username,password,course,ISBN,env);
    }
    @Step
    public void login_into_app(String username, String password,String course,String ISBN){
        login_into_app(username,password,course,ISBN,"int");
    }

    @Step
    public void fill_username_password(String username, String password){
//        loginPage.getDriver().manage().window().maximize();
        loginPage.open();
        loginPage.txtUsername.type(username);
        loginPage.txtPassword.type(password);
    }
    public void fill_username_password_in_stage(String username, String password) {
//        loginPage.g("https://s-c-login.cengage.com/");
        getDriver().navigate().to("https://s-c-login.cengage.com/");
        loginPage.txtUsername.type(username);
        loginPage.txtPassword.type(password);

    }

    @Step
    public void click_signin(){
        loginPage.btnSignIn.click();
    }

    @Step
    public void should_see_MindTap_course_menu(){
        getDriver().switchTo().window(getDriver().getWindowHandles().toArray()[1].toString());
        assertThat(coursePage.imgSearch.isDisplayed()).isTrue();
        assertThat(coursePage.imgFullBook.isDisplayed()).isTrue();
        assertThat(coursePage.imgRSSFeed.isDisplayed()).isTrue();
        assertThat(coursePage.imgGoogleDrive.isDisplayed()).isTrue();
    }

    @Step
    public void should_see_invalid_login_error_message(){
        assertThat(loginPage.elmntLoginErrorMessage.getText()).startsWith("The account credentials").endsWith("for assistance.");
    }

    @Step
    public void should_see_instructor_resource_center_heading(){
        assertThat(instructorResourceCenterPage.elmntInstructorResourceCenter.isDisplayed());
    }

    @Step
    public void construct_url(String baseURL, List<String> URLParams){
    	Serenity.setSessionVariable("BASE_URL").to(baseURL);
    	String courseURI=System.getProperty("CourseURI");
    	if(courseURI!=null){
    		URLParams.set(3, courseURI);
    	}
        String strURL = URIUtil.getURI(baseURL,null, URLParams).trim().replace(" ","%20");
        System.out.println("strURL"+strURL);
        Serenity.setSessionVariable("URL").to(strURL);
    }
    
    @Step
    public void construct_url(String baseURL,String scheme, List<String> URLParams){
    	Serenity.setSessionVariable("BASE_URL").to(baseURL);
    	String courseURI=System.getProperty("CourseURI");
    	if(courseURI!=null){
    		URLParams.set(3, courseURI);
    	}
        String strURL = URIUtil.getURI(baseURL,scheme, URLParams).trim().replace(" ","%20");
        System.out.println("strURL"+strURL);
        Serenity.setSessionVariable("URL").to(strURL);
    }

    @Step
    public void hit_get_request(List<String> Headers) throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, IOException {
        HttpResponse response = GetUtil.get(Headers);
        assertThat(response.getStatusLine().getStatusCode()).isNotNull();
        Serenity.setSessionVariable("response").to(response);
    }

    @Step
    public void should_see_status_code(int statusCode){
        HttpResponse response = Serenity.sessionVariableCalled("response");
        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(statusCode);
    }

    @Step
    public void click_search(){

        assertThat(coursePage.imgSearch.isDisplayed()).isTrue();
        coursePage.imgSearch.click();
        }

    @Step
    public void hit_post_request(String json_body, List<String> Headers) throws IOException, KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        HttpResponse response = PostUtil.post(json_body,Headers);
        Serenity.setSessionVariable("response").to(response);
    }

    @Step
    public void hit_post_request(String json_body, List<String> Headers,String EventAction,String  EventCategory) throws IOException, KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        HttpResponse response = PostUtil.post(json_body,Headers,EventAction,EventCategory);
        Serenity.setSessionVariable("response").to(response);
    }


    public void clickOn_tab(String tab_name) {
        coursePage.clickOn_tab(tab_name);
    }

    public void clickOn_Activity(String activity_type) {
        coursePage.clickOn_Activity(activity_type);
        coursePage.switchToMainFrame();
        coursePage.verify_Media();
        getDriver().navigate().refresh();
        coursePage.switchToMainFrame();
        coursePage.verify_Media();
    }

    public void clickOn_Unit(String unit_name) {
        coursePage.clickOn_Unit(unit_name);
    }

    public void clickOn_readingActivity(String activity_type) {
        coursePage.clickOn_readingActivity(activity_type);
    }

    public void clickOn_EnlargeImageIcon() {
        coursePage.switchToMainFrame();
        coursePage.clickOn_EnlargeImageIcon();
    }

    public void Verify_the_Enlarged_Image() {
        coursePage.verify_DockTitle("Enlarged View");
    }

    public void clickOnAppByName(String appName) {
        coursePage.clickOnAppByName(appName);
    }

    public void verifyHeadingTitleAtTitleBar(String title) {
        coursePage.verifyHeadingTitleAtTitleBar(title);
    }

    public void launchApp(String strAppName) {
        coursePage.launchApp(strAppName);
    }

    public void launchCNowApp(String strAppName) {
        coursePage.launchCNowApp(strAppName);
    }

    public void verifyFullBookContent() {
        coursePage.verifyFullBookContent();
    }

    public void verifyCoursePageTittle() {
        coursePage.verifyPageTittle();
    }

    public void clickOnTab(String strTab) {
        coursePage.clickOnTab(strTab);
    }

    public void launchReadingActivity(String strChapter, String strView) {
        coursePage.launchReadingActivity(strChapter,strView);
    }

    public void launchReadingViewActivity() throws InterruptedException {
       assertThat(coursePage.clickOnNextPage()).isTrue();
    }

    public void verifyCourseContent() {
        coursePage.verifyCourseBookContent();
    }


    public void bookmarkThePage() {
       coursePage.switchToMainFrame();
       coursePage.bookmarkThePage();
    }

    public void highlightTheParagraph() {
        coursePage.switchToMainFrame();
        coursePage.highlightSelection();
        coursePage.clickOnContextMenuColor();
    }

    public void removeHighlight() {
        coursePage.highlightSelection();
        coursePage.removeHighlight();
    }

    public void createaQuicknote() {
        coursePage.switchToMainFrame();
        coursePage.highlightSelection();
        coursePage.createaQuicknote();
    }

    public void removeaQuicknote() {
        coursePage.removeaQuicknote();
    }    

    @Step
    public void viewFlashCards() {
        coursePage.viewFlashCards();
    }

    @Step
    public void selectItemFromUserMenu(String strItem) {
        coursePage.selectItemFromUserMenu(strItem);
    }

    @Step
    public void veriftSystemRequirementPane() {
        coursePage.verifySystemRequirementPane();
    }

    @Step
    public void verifyGlossaryContent() {
        coursePage.verifyGlossaryContent();
    }

    @Step
    public void verifyGlossaryDefinition(String strGlossaryItem) {
        coursePage.verifyGlossaryDefinition(strGlossaryItem);
    }

    @Step
    public void spentTimeInMindtap(String strTime) {coursePage.spentTimeInMindtap(strTime);}

    @Step
    public void getEventsRIJson() {
        coursePage.getEventsRIJson();
    }


    @Step
    public void launch_Engagement_Report_URL() {
        coursePage.launch_Engagement_Report_URL();
    }

    public void verify_Engagement_Report() {
        coursePage.verify_Engagement_Report();
    }

    public void play_the_Video() {
        coursePage.switchToMainFrame();
        coursePage.play_the_Video();

    }

    public void clickOn_ApliaAddAssignment() {
        coursePage.switchToDockIFrame();
        coursePage.clickOn_ApliaAddAssignment();
    }


    public void getSsoToken(String strUsername, String strPassword) {
        loginPage.getSsoToken(strUsername,strPassword);
    }

    public void validateErrorMessage(String strErrorMessage) {
        engagementReports.validateErrorMessage(strErrorMessage);
    }

	public void compareEngagmentReport(String jsonFile) {
		try {
			engagementReports.compareEngagmentReport(FileUtil.fileRead(jsonFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

    public void navigatToPage(int pageNumber) {
        coursePage.navigatToPage(pageNumber);
    }

    public void readingPageCountAttribute() {
        coursePage.readingPageCountAttribute();
    }
    
    public void fetchJobStatus() {
		notification.fetchJobStatus();
	}
    
    public void fetchJobId() {
    	System.out.println("jobID");
    	String jobId = notification.fetchJobId();
    	System.out.println("jobID:"+jobId);
    	Serenity.setSessionVariable("JOB_ID").to(jobId);
    	System.out.println("done");
	}

	public void sendMailNotificationForFailure(String recipients,String jobName) {
		notification.notifyWithMail(recipients.split(","),jobName);
	}
    public void regenerateTestData() {
        RegenerateTestData.runRegenerateTestData();
    }
}

