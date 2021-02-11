package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static application.WebDriverFactory.wdChrome;
import static application.AppManager.logger;
import static pages.PageObject.loginPage;
import static pages.PageObject.userDetailsPage;

@Log4j2
public class OtusSteps {

  @Given("^user opens main page$")
  public void openMainPage() {
    loginPage.open();
    if (wdChrome.findElement(By.xpath("//title[contains( text(),'Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям')]")).isEnabled()) {
      logger.info("Title появился");
    } else throw new AssertionError("Title не появился");
  }

  @Given("^user opens UserDetails page$")
  public void openUserDetailsPage() {
    userDetailsPage.open();
  }

  @When("^user fills login \"([^\"]*)\"$")
  public void fillLogin(String email) {
    loginPage.fillEmail(email);
  }

  @When("^user fills password \"([^\"]*)\"$")
  public void fillPass(String password) {
    loginPage.fillEmail(password);
  }

  @When("^user submits login")
  public void submitLogin() {
    loginPage.submitLogin();
  }

  @When("^user do login with email: \"([^\"]*)\" and password: \"([^\"]*)\"$")
  public void login(String email, String password) {
    loginPage.fillEmail(email);
    loginPage.fillEmail(password);
    loginPage.submitLogin();
  }

  @When("^user fills userFirstName \"([^\"]*)\"$")
  public void fillUserFirstName(String firstName) {
    userDetailsPage.fillFirstName(firstName);
  }

  @When("^user fills userLastName \"([^\"]*)\"$")
  public void fillUserLastName(String lastName) {
    userDetailsPage.fillLastName(lastName);
  }

  @When("^user submits user parameter changes")
  public void submitUserParamChanges(){
    userDetailsPage.saveForm();
  }

}
