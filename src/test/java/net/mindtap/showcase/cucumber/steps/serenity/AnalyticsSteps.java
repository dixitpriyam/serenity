package net.mindtap.showcase.cucumber.steps.serenity;

import com.google.common.io.Resources;
import net.mindtap.showcase.cucumber.utils.httpUtil.PropFileHandler;
import net.mindtap.showcase.cucumber.utils.httpUtil.URIUtil;
import net.mindtap.showcase.cucumber.pages.CoursePage;
import net.mindtap.showcase.cucumber.pages.InstructorResourceCenterPage;
import net.mindtap.showcase.cucumber.pages.LoginPage;
import net.mindtap.showcase.cucumber.pages.AnalyticsDashboardPage;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.apache.commons.io.Charsets;

import javax.jws.soap.SOAPBinding;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by asiqa on 10/11/2016.
 */
public class AnalyticsSteps extends ScenarioSteps {

    LoginPage loginPage;
    CoursePage coursePage;
    InstructorResourceCenterPage instructorResourceCenterPage;
    AnalyticsDashboardPage AnalyticsDashboardPage;
    @Step
    public void login_into_app(String username, String password) {
        loginPage.getDriver().manage().window().maximize();
        loginPage.open();
        loginPage.login(username, password);
    }

    @Step
    public void launchTheAnalyticsDiagnoticTool() {
//        loginPage.getDriver().manage().window().maximize();
        loginPage.openAt("https://analytics-tools.cengage.info/diagnostictool/#/home");
    }

    public void launch_query(String Environment, String QueryType, String QueryAttribute, String Parameter, String Tab, String ISBN) {

        StringBuilder sb = new StringBuilder();
        sb.append("https://analytics-tools.cengage.info/diagnostictool/#/");
        sb.append(QueryType).append("/");
        sb.append(Tab).append("/");
        sb.append(Environment).append("/");
        sb.append("uri/mindtap:mindtap-int.cengage.com:course:isbn:");
        sb.append(ISBN);
        sb.append(":course-key:").append(Parameter);
       
        System.out.println("Search Query : " + sb);
        PropFileHandler.writeProperty("courseKey",Parameter);
        PropFileHandler.writeProperty("coreTextISBN",ISBN);
        getDriver().navigate().to(sb.toString());
      //  loginPage.openAt(sb.toString());
    }
    public void launch_query_in_staging_environment(String Environment, String QueryType, String QueryAttribute, String Parameter, String Tab, String ISBN) {

        StringBuilder sb = new StringBuilder();
        sb.append("https://analytics-tools.cengage.info/diagnostictool/#/");
        sb.append(QueryType).append("/");
        sb.append(Tab).append("/");
        sb.append(Environment).append("/");
        sb.append("uri/mindtap:mindtap-staging.cengage.com:course:isbn:");
        sb.append(ISBN);
        sb.append(":course-key:").append(Parameter);

        System.out.println("Search Query : " + sb);
        PropFileHandler.writeProperty("courseKey",Parameter);
        PropFileHandler.writeProperty("coreTextISBN",ISBN);
        getDriver().navigate().to(sb.toString());
        //  loginPage.openAt(sb.toString());
    }

    public void secect_the_Instructor(String user) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            System.out.println(" Thread.sleep(5000); Failed");
            Logger.getLogger(AnalyticsSteps.class.getName()).log(Level.SEVERE, null, ex);
        }
        AnalyticsDashboardPage.selectUser(user);
    }

    public void searchAndSelectUser(String strSearchData, String strSearchType) {
        AnalyticsDashboardPage.searchAndSelectUser(strSearchData,strSearchType);
    }

    public void search_By_Date() {
        AnalyticsDashboardPage.search_By_Date();
    }
    public void search_By_Date_For_TimeInMindtap() {
        AnalyticsDashboardPage.search_By_Date_For_TimeInMindtap();
    }

    public void search_By_Event_Category(String Category) {
        PropFileHandler.writeProperty("eventCategory",Category);
        AnalyticsDashboardPage.search_By_Event_Category( Category);
    }

    public void search_By_Event_Action(String event) {
        PropFileHandler.writeProperty("eventAction",event);
        AnalyticsDashboardPage.search_By_Event_Action( event);
    }

    public void calculate_Total_Events_Generated(String event) {
        AnalyticsDashboardPage.calculate_Total_Events_Generated( event);
    }

    public void calculate_Total_Events_Generated(String event,String Category) {
        AnalyticsDashboardPage.calculate_Total_Events_Generated(event,Category);
    }

    public void calculateTotalEventsGenerated(String strEventAction,String strEventCategory) {
        AnalyticsDashboardPage.calculateTotalEventsGenerated(strEventAction,strEventCategory);
    }

    public void verify_Events_Generated(String event) throws InterruptedException {
        AnalyticsDashboardPage.verify_Events_Generated(event);
    }
    public void verify_Events_Generated(String event,String Category) throws InterruptedException {
        AnalyticsDashboardPage.verify_Events_Generated(event,Category);
    }

    public void verifyEventsGenerated(String strEventAction,String strEventCategory) throws InterruptedException {
        AnalyticsDashboardPage.verifyEventsGenerated(strEventAction,strEventCategory);
    }

    @Step
    public void verify_The_Events_Details(String source, String category, String value) {
        AnalyticsDashboardPage.verifyTheEventsDetails(source,category,value);
    }
    @Step
    public void createJsonFile() throws  Throwable{
        AnalyticsDashboardPage.createJsonFile();
    }

    @Step
    public void createJsonFile(String EventAction, String EventCategory) throws  Throwable{
        AnalyticsDashboardPage.createJsonFile(EventAction,EventCategory);
    }

    @Step
    public void search_EventCategoryEventActionEventSource(String strEventCategory,String strEventAction,String strEventSource){
        AnalyticsDashboardPage.search_EventsByActionSourceCategory(strEventCategory,strEventAction,strEventSource);
    }

    @Step
    public void verifyTimeInMindtapEventsGenerated(String strEventAction, String strEventCategory) throws InterruptedException {
        AnalyticsDashboardPage.verifyTimeInMindtapEventsGenerated(strEventAction,strEventCategory);

    }

    @Step
    public void calculateTotalTimeInTapEventsGenerated(String strEventAction, String strEventCategory) throws InterruptedException {
        AnalyticsDashboardPage.calculateTotalTimeInTapEventsGenerated(strEventAction,strEventCategory);

    }

    public void get_SSO_Token(String UserGUID) {
        AnalyticsDashboardPage.get_SSO_Token(UserGUID);
    }


    public void validate_legacy_report_with_new_report() {
        AnalyticsDashboardPage.validate_legacy_report_with_new_report();
    }

}
