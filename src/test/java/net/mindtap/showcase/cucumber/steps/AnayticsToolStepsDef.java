package net.mindtap.showcase.cucumber.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.mindtap.showcase.cucumber.steps.serenity.AnalyticsSteps;
import net.mindtap.showcase.cucumber.utils.httpUtil.PropFileHandler;
import net.thucydides.core.util.SystemEnvironmentVariables;

/**
 * Created by asiqa on 10/10/2016.
 */
public class AnayticsToolStepsDef {

    @net.thucydides.core.annotations.Steps
    AnalyticsSteps analyticsSteps;

    @Given("^As a Mindtap user,I launch the Analytics Diagnotic tool$")
    public void as_a_Mindtap_user_I_launch_the_Analytics_Diagnotic_tool() throws Throwable {
        analyticsSteps.launchTheAnalyticsDiagnoticTool();
    }

    @Given("^I launch query with following parameter (.*) , (.*) , (.*) , (.*) , (.*) , (.*)$")
    public void launch_query(String Environment, String QueryType, String QueryAttribute, String Parameter, String Tab,String ISBN){
        analyticsSteps.launch_query(Environment, QueryType,QueryAttribute,Parameter,Tab,ISBN);
    }
    @Given("^As a user, I launch ADT URL for staging environment with the following query string (.*) , (.*) , (.*) , (.*) , (.*)$")
    public void asAUserILaunchADTURLInStagingEnvironmentWithTheFollowingQueryStringQueryTypeQueryAttributeParameterTabISBN( String strQueryType, String strQueryAttribute, String strParameter, String strTab,String strISBN){
        String strEnvironment= SystemEnvironmentVariables.createEnvironmentVariables().getProperty("adt.environment");
        analyticsSteps.launch_query_in_staging_environment(strEnvironment, strQueryType,strQueryAttribute,strParameter,strTab,strISBN);
    }


    @When("^I select the (.*)$")
    public void i_secect_the_Instructor(String user) throws Throwable {
        analyticsSteps.secect_the_Instructor(user);
    }

    @Then("^I Search By Date$")
    public void i_Search_By_Date() throws Throwable {
        analyticsSteps.search_By_Date();
    }

    @Then("^I Search By (.*) Event Category$")
    public void i_Search_By_Event_Category(String Category ) throws Throwable {
        analyticsSteps.search_By_Event_Category(Category);
    }

    @Then("^I Search By (.*) Event Action$")
    public void search_By_Event_Action(String event) throws Throwable {
        analyticsSteps.search_By_Event_Action(event);
    }

    @Then("^I Calculate Total (.*) Events Generated$")
    public void calculate_Total_Events_Generated(String event) throws Throwable {
        analyticsSteps.calculate_Total_Events_Generated(event);
    }

    @Then("^I Calculate Total Generated for (.*) Events under (.*) Event Category$")
    public void calculate_Total_Events_Generated(String event, String Category) throws Throwable {
        analyticsSteps.calculate_Total_Events_Generated(event,Category);
    }

    @And("^I Verify (.*) Events Generated")
    public void verify_Events_Generated(String event) throws Throwable {
        analyticsSteps.verify_Events_Generated(event);
    }


    @And("^I verify The Events Details like (.*) , (.*) , (.*)$")
    public void verify_The_Events_Details(String strEventSource, String strEventAction, String strValue) throws Throwable {
        PropFileHandler.writeProperty("eventValue",strValue);
        analyticsSteps.verify_The_Events_Details(strEventSource, strEventAction, strValue);
    }
    @Then("^I Verify events Generated for (.*) Events under (.*) Event Category$")
    public void verify_Events_Generated(String event, String EventCategory) throws Throwable {
        analyticsSteps.verify_Events_Generated(event,EventCategory);
    }


    @Then("^I create Json file$")
    public void createJsonFile() throws Throwable {
        analyticsSteps.createJsonFile();
    }


    @Then("^I create Json file for (.*) Events under (.*) Event Category$")
    public void createJsonFile(String EventAction, String EventCategory) throws Throwable {
        analyticsSteps.createJsonFile(EventAction,EventCategory);
    }

    @And("^I search and select a user with (.*) in (.*)$")
    public void iSelectTheInstructor(String strSearchData,String strSearchType) throws Throwable {
        analyticsSteps.searchAndSelectUser(strSearchData, strSearchType);
    }

    @When("^I search events by (.*) and (.*)$")
    public void iSearchEventsByEventCategoryEventAction(String strEventCategory,String strEventAction) throws Throwable {
        analyticsSteps.search_By_Date();
        analyticsSteps.search_By_Event_Category(strEventCategory);
        analyticsSteps.search_By_Event_Action(strEventAction);
    }

    @Then("^I shoud be able to see and retrieve the total (.*)$")
    public void iShoudBeAbleToSeeAndRetrieveTheEventCount(String strEvent) throws Throwable {
        analyticsSteps.calculate_Total_Events_Generated(strEvent);
    }

    @Given("^As a user, I launch ADT URL with the following query string (.*) , (.*) , (.*) , (.*) , (.*)$")
    public void asAUserILaunchADTURLWithTheFollowingQueryStringQueryTypeQueryAttributeParameterTabISBN(String strQueryType,String strQueryAttribute,String strParameter,String strTab,String strISBN) throws Throwable {
        String strEnvironment= SystemEnvironmentVariables.createEnvironmentVariables().getProperty("adt.environment");
        analyticsSteps.launch_query(strEnvironment, strQueryType,strQueryAttribute,strParameter,strTab,strISBN);
    }

    @When("^I search events with the following parameter (.*),(.*),(.*)$")
    public void iSearchEventsWithTheFollowingParameterEventCategoryEventActionEventSource(String strEventCategory,String strEventAction,String strEventSource) throws Throwable {
        analyticsSteps.search_By_Date();
        analyticsSteps.search_EventCategoryEventActionEventSource(strEventCategory,strEventAction,strEventSource);

    }
    @When("^I search events with the following parameters for timeinmindtap (.*),(.*),(.*)$")
    public void iSearchEventsWithTheFollowingParameterForTimeinmindtapEventCategoryEventActionEventSource(String strEventCategory,String strEventAction,String strEventSource) throws Throwable {
        analyticsSteps.search_By_Date_For_TimeInMindtap();
        analyticsSteps.search_EventCategoryEventActionEventSource(strEventCategory,strEventAction,strEventSource);

    }


    @Then("^I calculate total events generated for (.*) Events under (.*) Event Category$")
    public void iCalculateTotalEventsGeneratedForEventActionEventsUnderEventCategoryEventCategory(String strEventAction,String strEventCategoty) throws Throwable {
        analyticsSteps.calculateTotalEventsGenerated(strEventAction,strEventCategoty);
    }

    @Then("^I verify events generated for (.*) Events under (.*) Event Category$")
    public void iVerifyEventsGeneratedForEventActionEventsUnderEventCategoryEventCategory(String strEventAction,String strEventCategory) throws Throwable {
        analyticsSteps.verifyEventsGenerated(strEventAction,strEventCategory);
    }

    @And("^I verify time in mindtap events generated for (.*) Events under (.*) Event Category$")
    public void iVerifyTimeInMindtapEventsGeneratedForEventActionEventsUnderEventCategoryEventCategory(String strEventAction,String strEventCategory) throws Throwable {
        analyticsSteps.verifyTimeInMindtapEventsGenerated(strEventAction,strEventCategory);
    }


    @Then("^I calculate total time-in-tap events generated for (.*) Events under (.*) Event Category$")
    public void iCalculateTotalTimeInTapEventsGeneratedForEventActionEventsUnderEventCategoryEventCategory(String strEventAction,String strEventCategoty) throws Throwable {
        analyticsSteps.calculateTotalTimeInTapEventsGenerated(strEventAction,strEventCategoty);
    }

    @Given("^As a user, I Get SSO Token for (.*) from ADT$")
    public void get_SSO_Token(String User) throws Throwable {
        analyticsSteps.get_SSO_Token(User);
    }
    @Then("^I Validate legacy report with new report$")
    public void launch_Engagement_Report_URL() throws Throwable {
        analyticsSteps.validate_legacy_report_with_new_report();

    }


}
