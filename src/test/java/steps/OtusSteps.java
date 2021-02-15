package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static application.AppManager.cfg;
import static application.WebDriverFactory.wdChrome;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static pages.PageObject.*;

@Log4j2
public class OtusSteps {

  @Given("^user opens main page")
  public void openMainPage() {
    loginPage.open();
    assertTrue(wdChrome.findElement(By.xpath("//title[contains( text(),'Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям')]")).isEnabled(),
            "Title не появился");
  }

  @Given("^user opens RegisterForm")
  public void openRegForm() {
    loginPage.openRegisterForm();
  }

  @When("^user fills login \"([^\"]*)\"$")
  public void fillLogin(String email) {
    loginPage.fillEmail(email);
  }

  @When("^user fills password \"([^\"]*)\"$")
  public void fillPass(String password) {
    loginPage.fillPassword(password);
  }

  @When("^user submits login")
  public void submitLogin() {
    loginPage.submitLogin();
  }

  @When("^user do login with email: \"([^\"]*)\" and password: \"([^\"]*)\"$")
  public void login(String email, String password) {
    loginPage.openRegisterForm();
    loginPage.fillEmail(email);
    loginPage.fillPassword(password);
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
  public void submitUserParamChanges() {
    userDetailsPage.saveForm();
  }

  @When("^user opens contacts")
  public void clickContacts() {
    mainPage.openContacts();
  }

  @Then("^user checks that text 'Реквизиты' exists")
  public void checkPhone() {
    wdChrome.findElement(By.xpath("//div[contains(text(), 'Реквизиты')]")).isEnabled();
  }
}
