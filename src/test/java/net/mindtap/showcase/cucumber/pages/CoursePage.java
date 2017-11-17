package net.mindtap.showcase.cucumber.pages;
import net.mindtap.showcase.cucumber.utils.commonUtil.JSONUtil;
import net.mindtap.showcase.cucumber.utils.commonUtil.Sync;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.annotations.findby.How;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.pages.PageObject;
import org.codehaus.groovy.runtime.powerassert.SourceText;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.mindtap.showcase.cucumber.utils.httpUtil.PropFileHandler;

import static org.assertj.core.api.Assertions.assertThat;
import org.openqa.selenium.WebElement;


public class CoursePage extends BasePage {

    @FindBy(how = How.XPATH, using = "//img[@title='Search']")
    public WebElementFacade imgSearch;

    @FindBy(how = How.XPATH, using = "//img[@title='RSS Feed']")
    public WebElementFacade imgRSSFeed;

    @FindBy(how = How.XPATH, using = "//img[@title='Full Book']")
    public WebElementFacade imgFullBook;

    @FindBy(how = How.XPATH, using = "//img[@title='Google Drive']")
    public WebElementFacade imgGoogleDrive;

    @FindBy(how = How.XPATH, using = "//input[@class='search']")
    public WebElementFacade txtSearch;

    @FindBy(how = How.ID, using = "nb_search_submit")
    public WebElementFacade imgSearchicon;

    @FindBy(how = How.XPATH, using = "//span[.='Go directly to page ']")
    public WebElementFacade chkResults;

    @FindBy(how = How.XPATH, using = "//div[contains(.,'Test 2')]/preceding-sibling::a")
    public WebElementFacade btnCloseAnnouncementTest2;


    @FindBy(how = How.XPATH, using = "//div[contains(.,'Test 1')]/preceding-sibling::a")
    public WebElementFacade btnCloseAnnouncementTest1;

    @FindBy(how = How.XPATH, using="//a[@title='Close App']")
    public WebElementFacade btnCloseApp;

    @FindBy(how=How.XPATH,using="//a[@id='ebook_right']/img")
    public WebElementFacade eleEbookRight;

    @FindBy(how=How.XPATH,using="//a[@id='ebook_left']/img")
    public WebElementFacade eleEbookLeft;

    @FindBy(how=How.XPATH,using="//*[@id='ebook_document']/div")
    public WebElementFacade eleEbookDocument;

    @FindBy(how=How.XPATH,using="//input[@value='Next Card']")
    public WebElementFacade btnNextCard;

    public void clickOn_tab(String tab_name) {

        List<WebElementFacade> total_event = findAll("//li[contains(@title,'" + tab_name + "')]");
        total_event.get(0).click();
    }



    public void clickOn_Activity(String activity_type) {
        List<WebElementFacade> element = findAll("//div[contains(@title,'" + activity_type + "')]");
        element.get(0).click();
    }

    public void verify_Media() {
        $("//div[@class='imageContainer']").click();
    }

    public void clickOn_Unit(String unit_name) {
        waitFor("//a[contains(text(),'"+unit_name+"')]");
        $("//a[contains(text(),'"+unit_name+"')]").click();
        System.out.println("User Clicked on "+unit_name);
    }

    public void clickOn_readingActivity(String activity_type) {
        Sync.waitForSeconds(5);
        waitFor("//a[contains(text(),'"+activity_type+"')]");
        $("//a[contains(text(),'"+activity_type+"')]").click();
    }

    public void clickOn_EnlargeImageIcon() {
        $("//img[contains(@title,'Enlarge Image')]").click();
    }

    public void verify_DockTitle(String title) {
        getDriver().switchTo().defaultContent();
        Assert.assertTrue($("//span[@class='appActionFrame_actionLabel' and contains(text(),'"+title+"')]").isDisplayed());
    }

    public void clickOnAppByName(String appName) {
        String dockGroup;
        List<WebElementFacade> dockGroupImg = findAll("//div[contains(@class,'dockGroup')]/li/a/img");
        List<WebElementFacade> dockGroup1 = findAll("//div[@id='dockGroup1']/li");
        List<WebElementFacade> group = findAll("//div[contains(@class,'dockGroup')]/li/a");
        int i = 0, k = -1;
        for (int j = 0; j < dockGroupImg.size(); j++) {
            if (j < dockGroup1.size()) {
                dockGroup = "dockGroup1";
                i = j;
            } else {
                dockGroup = "dockGroup2";
                k++;
                i = k;
            }
            //System.out.println(elements("lbl_appName").get(j).getAttribute("title"));
            if (dockGroupImg.get(j).getAttribute("title").equalsIgnoreCase(appName)) {
                group.get(j).isDisplayed();
                evaluateJavascript("document.getElementById('" + dockGroup + "').childNodes[" + ((2 * i) + 1) + "].childNodes[0].click();");
                break;
            }
        }
        System.out.println("Instructor successfully Launch App From App Dock");
        System.out.println("successfully Launch "+appName+" App From App Dock");

    }

    public void verifyHeadingTitleAtTitleBar(String title) {
        assertThat($("div[id*=\"NB_App_DockItem\"]>div>h2>span").isDisplayed());
        System.out.println("Verified that app heading on app title bar");
        assertThat($("div[id*=\"NB_App_DockItem\"]>div>h2>span").getText().trim().contains(title));
        System.out.println("Headline at top off app is matched");

    }

    public void launchApp(String strAppName) {
        $("//img[@title='"+strAppName+"']").click();
    }

    public void launchCNowApp(String strAppName) {
        $("//li[@ref='CNOW.HW-preclass_ilrn_com']/a/img[@title='"+strAppName+"']").click();
    }
    public void verifyFullBookContent() {
        switchToDockIFrame();
        withTimeoutOf(1, TimeUnit.MINUTES).waitForPresenceOf(By.xpath("//div[@id='header']/h1"));
        assertThat($("//div[@id='header']/h1").isDisplayed()).isTrue();
        getDriver().switchTo().defaultContent();
        btnCloseApp.click();
    }

    public void verifyPageTittle() {
        assertThat($("//div[@class='logoMT']").isDisplayed());
    }

    public void clickOnTab(String strTab) {
        waitForAbsenceOf("//a[@title='Close App']");
        $("//li[contains(@title,'" + strTab + "')]").click();
    }

    public void launchReadingActivity(String strChapter, String strView) {
        clickOnTab(strView);
        clickOn_Unit(strChapter);
        clickOn_readingActivity(strChapter);
    }

    public boolean clickOnNextPage() throws InterruptedException {
        boolean isOVer = false;
        Sync.waitForSeconds(10);
        try {
            withAction().moveToElement(eleEbookRight).click().build().perform();
            isOVer = true;
            return isOVer;
        } catch (Exception e) {
            withAction().moveToElement(eleEbookLeft).click().build().perform();
            isOVer = true;
            return isOVer;
        }
    }


    public void verifyCourseBookContent() {
        switchToMainFrame();
        assertThat(eleEbookDocument.isDisplayed()).isTrue();
    }


    public void bookmarkThePage() {
        hardWait(4000);
        
         evaluateJavascript("document.getElementsByClassName('reader-bookmark bar_control nb_hidden rs_skip')[0].click();");
         evaluateJavascript("document.getElementsByClassName('reader-bookmark bar_control nb_hidden rs_skip')[0].click();");
         System.out.println("Successfully Book Mark Created");
         
    }

    public void highlightSelection() {
        hardWait(4000);
        String firstParagraphId = null;
        boolean paraCheck = false;
        while (!paraCheck) {
            List<WebElement> firstParagraph = executeJavascriptWithReturnelement("return document.getElementById('ebook_document').querySelectorAll('p')");
            // Code to select the first Paragraph which is visible
            for (int i = 0; i < firstParagraph.size(); i++) {
                if (firstParagraph.get(i).isDisplayed() && !(firstParagraph.get(i).getAttribute("id").equals(""))) {
                    checkFontSizer(firstParagraph.get(i));
                    firstParagraphId = firstParagraph.get(i).getAttribute("id");
                    PropFileHandler.writeProperty("ParagraphName", firstParagraph.get(i).getText());
                    paraCheck = true;
                    break;
                }
            }
        }
//        wait.hardWait(1);
//        scrollDown(element("paragraphUnhighlighted"));
//        firstParagraphId = element("paragraphUnhighlighted").getAttribute("id");
//        System.out.println("paragraph Unhighlighted");
//        highlightParaId = true;
//        if (firstParagraphId.contains("")) {
//            scrollDown(element("paragraphHlighted"));
//            firstParagraphId = element("paragraphHlighted").getAttribute("id");
//            System.out.println("paragraph Hlighted");
//        }
        System.out.println("Selected paragraph ID :" + firstParagraphId);
        
        // Clear ranges if already created
        evaluateJavascript("function test(){if(document.selection)" + " document.selection.empty();"
                + "else if(window.getSelection)" + " window.getSelection().removeAllRanges();}");

       

        // Adding Range (first child to last child of paragraph) /
        try {
            evaluateJavascript("var range = document.createRange();range.setStart(document.getElementById('"
                    + firstParagraphId + "').firstChild,0);range.setEnd(document.getElementById('" + firstParagraphId
                    + "').lastChild, document.getElementById('" + firstParagraphId
                    + "').lastChild.length);window.getSelection().addRange(range);");
        } catch (Exception e) {

        }
        
        // simulate mouse release to fire mouseup event /
        evaluateJavascript("var elem = document.getElementById('" + firstParagraphId
                + "');if( document.createEvent ) { var evObj = document.createEvent('MouseEvents');evObj.initEvent( 'mouseup', true, false );elem.dispatchEvent(evObj);} else if( document.createEventObject ) {elem.fireEvent('onmouseup');}");
        // Hard wait /
        
//            if (highlightParaId) {
//                scrollDown(element("paragraphUnhighlighted"));
//            } else {
//                scrollDown(element("paragraphHlighted"));
//            }
        // Change property so that pop is being displayed /
        // driver.executeScript("document.getElementsByClassName('selectionOpts
        // clui_contextmenu')[0].style.display = 'block';");

        // Return the paragraph selected /
//        highlightParaId = firstParagraphId;
//            String text = (driver.findElement(By.id(firstParagraphId))).getText();
//            return text;
    }

    public void clickOnContextMenuColor() {
        $(".selectionOpts.nbreader_contextmenu>.plain>.clMenuDisplayContextTextSelected>.highlightMenuOptions>.pink").click();
        System.out.println("User clicked on context Menu Color");
    }
    
    
    public void checkFontSizer(WebElement para) {
        boolean result = true;
        WebElement fontSizeMedium=$("//div[@id ='fontSizer']/a[3]");
        WebElement fontSizeLarge=$("//div[@id ='fontSizer']/a[2]");
        WebElement fontSizeSmall=$("//div[@id ='fontSizer']/a[1]");
        fontSizeMedium.click();
     //   hoverClick(element("fontSizeMedium"));
        String check = fontSizeMedium.getAttribute("class");
        System.out.println("font size medium text: " + check);
        result = result && fontSizeMedium.getAttribute("class").equals("fontSizeMedium fontSelected");
        System.out.println("\npara font size is = " + para.getCssValue("font-size") + "\n");
        result = result && para.getCssValue("font-size").equals("14.5");
        fontSizeLarge.click();
     //   hoverClick(element("fontSizeLarge"));
        result = result && fontSizeLarge.getAttribute("class").equals("fontSizeLarge fontSelected");
        System.out.println("\npara font size is = " + para.getCssValue("font-size") + "\n");
        result = result && para.getCssValue("font-size").equals("16.5");
        fontSizeSmall.click();
    //   hoverClick(element("fontSizeSmall"));
        
        result = result && fontSizeSmall.getAttribute("class").equals("fontSizeSmall fontSelected");
        System.out.println("\npara font size is = " + para.getCssValue("font-size"));
        result = result && para.getCssValue("font-size").equals("12.5");
    }

    public void removeHighlight() {
        $(".init_removeHighlight").click();
        System.out.println("User clicked on Remove Highlight Option");

    }

    public void createaQuicknote() {
        $("//span[@id='add']").click();
        $(".initialFocusElement").sendKeys("Test");
        $(".create.adminButton").click();

        System.out.println("User create a Quicknote");
    }

    public void removeaQuicknote() {
        $("(//div[contains(@class,'QuickNote')])[1]").click();
        $(".delete.adminButton.leftButton").click();
        getAlert().accept();
        System.out.println("User Remove a Quicknote");
    }    

    public void viewFlashCards() {
        switchToDockIFrame();
        assertThat($("//div[@class='line card_div']").isDisplayed()).isTrue();
        btnNextCard.click();
    }

    public void selectItemFromUserMenu(String strItem) {
        Sync.waitForSeconds(3);
        getDriver().switchTo().defaultContent();
        $("//a[@class='user-menu-link tb_item']").click();
        $("//div[@style='display: block;']/a[.='"+strItem+"']").click();
    }

    public void verifySystemRequirementPane() {
        switchToDockIFrame();
        assertThat($("//div[@class='content']").isDisplayed()).isTrue();
        Sync.waitForSeconds(10);
    }

    public void verifyGlossaryContent() {
        switchToDockIFrame();
        withTimeoutOf(1, TimeUnit.MINUTES).waitForPresenceOf(By.xpath("//div[@id='termdefList']"));
        assertThat($("//div[@id='termdefList']").isDisplayed()).isTrue();
        getDriver().switchTo().defaultContent();

        btnCloseApp.click();
    }

    public void verifyGlossaryDefinition(String strGlossaryItem) {
        clickOn_GlossaryItem(strGlossaryItem);
    }

    public void clickOn_GlossaryItem(String strGlossaryItem) {
        waitFor("//span[.='"+strGlossaryItem+"']/parent::span[contains(@class,'glossary nbannotation nbid')]");
        $("//span[.='"+strGlossaryItem+"']/parent::span[contains(@class,'glossary nbannotation nbid')]").click();
        assertThat($("//span[.='"+strGlossaryItem+"']/parent::span[contains(@class,'glossaryPopover ')]").isDisplayed()).isTrue();
    }

    public void spentTimeInMindtap(String strTime) {
        int timeSpentInMindtap=Integer.parseInt(strTime);
        PropFileHandler.writeProperty("TimeSpentInImindtap", strTime);
        Sync.waitForSeconds(timeSpentInMindtap);
    }

    public void getEventsRIJson() {
        JSONUtil.createJsonForEventsInRILog();
    }

    public void launch_Engagement_Report_URL() {
        String CourseURI=System.getProperty("CourseURI");
        String SSOToken=PropFileHandler.readProperty("SSOToken");
        String url="https://analytics-cdn.cengage.com/engagement-report/staging/?ssoToken="+SSOToken+"&userRole=INSTRUCTOR&courseUri="+CourseURI+"#instructor-dashboard";
        System.out.println("CourseURI:- "+CourseURI);
        System.out.println("SSOToken:- " +SSOToken);
        System.out.println("url:- "+url);
        getDriver().navigate().to(url);
    }

    public void verify_Engagement_Report() {
        Assert.assertTrue($("//div[@class='aa']").isDisplayed());
        System.out.println("Verified Engagement report generated");
    }

    public void play_the_Video() {
        evaluateJavascript("document.getElementsByTagName('video')[0].play();");
        hardWait(120000);
    }

    public void clickOn_ApliaAddAssignment() {
        $("//span[.='Add Assignment']").click();

    }

    public void navigatToPage(int pageNumber) {
        switchToDefaultContent();
        switchToDockIFrame();
        System.out.println("Navigate to "+pageNumber+" page");
        while((pageNumber--)>0){
        $("//img[@alt='Next Page']").click();}
    }

    public void readingPageCountAttribute() {
        assertThat(PropFileHandler.readProperty("READINGVIEW_JsonBody").contains("readingPageCount"));
        System.out.println("Verified reading Page Count Attribute Present in Network logs calls");
    }
}
