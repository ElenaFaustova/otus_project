package steps;

import cucumber.api.java.en.And;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static application.WebDriverFactory.wdChrome;
import static application.appManager.logger;
import static pages.PageObject.loginPage;
import static pages.PageObject.userDetailsPage;

@Log4j2
public class OtusSteps {

  @And("^user opens main page$")
  public void openMainPage() {
    loginPage.open();
    if (wdChrome.findElement(By.xpath("//title[contains( text(),'Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям')]")).isEnabled()) {
      logger.info("Title появился");
    } else throw new AssertionError("Title не появился");
  }

  @And("^user opens UserDetails page$")
  public void openUserDetailsPage() {
    userDetailsPage.open();
  }

  @And("^user fills login \"([^\"]*)\"$")
  public void fillLogin(String email) {
    loginPage.fillEmail(email);
  }

  @And("^user fills password \"([^\"]*)\"$")
  public void fillPass(String password) {
    loginPage.fillEmail(password);
  }

  @And("^user submits login")
  public void submitLogin() {
    loginPage.submitLogin();
  }

  @And("^user do login with email: \"([^\"]*)\" and password: \"([^\"]*)\"$")
  public void login(String email, String password) {
    loginPage.fillEmail(email);
    loginPage.fillEmail(password);
    loginPage.submitLogin();
  }

  @And("^user fills userFirstName \"([^\"]*)\"$")
  public void fillUserFirstName(String firstName) {
    userDetailsPage.fillFirstName(firstName);
  }

  @And("^user fills userLastName \"([^\"]*)\"$")
  public void fillUserLastName(String lastName) {
    userDetailsPage.fillLastName(lastName);
  }

  @And("^user submits user parameter changes")
  public void submitUserParamChanges(){
    userDetailsPage.saveForm();
  }

}
