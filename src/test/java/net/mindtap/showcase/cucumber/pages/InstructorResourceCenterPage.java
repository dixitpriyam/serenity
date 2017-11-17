package net.mindtap.showcase.cucumber.pages;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.annotations.findby.How;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;

public class InstructorResourceCenterPage extends PageObject {

    @FindBy(how = How.LINK_TEXT, using = "Manage Courses")
    public WebElementFacade lnkManageCourses;

    @FindBy(how = How.XPATH, using = "//h2[.='Instructor Resource Center']")
    public WebElementFacade elmntInstructorResourceCenter;

    @FindBy(how = How.LINK_TEXT, using = "Accept")
    public WebElementFacade lnkAccept;

    public void selectCourse(String course, String env){
//        find(By.linkText(course)).click();
        String new_url=null;
        String url=find(By.linkText(course)).getAttribute("href");
        System.out.println("Original URL: "+url);
        if (env.equals("int")) {
            new_url = url.replace("appdev.ng", "mindtap-" + env);
            System.out.println("new URL: "+new_url);
            String myCurrentDir = System.getProperty("user.dir");
            System.out.println(myCurrentDir);
            getDriver().navigate().to(new_url);
        }
        else if(env.equals("staging"))
        {
            System.out.println("staging");
            String myCurrentDir = System.getProperty("user.dir");
            System.out.println(myCurrentDir);
            getDriver().navigate().to(url);

        }


    }


}
