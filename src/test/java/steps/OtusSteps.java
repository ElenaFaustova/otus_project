package steps;

import cucumber.api.java.en.And;
import lombok.extern.log4j.Log4j2;

import static pages.PageObject.loginPage;

@Log4j2
public class OtusSteps {

  @And("^user opens main page$")
  public void openMainPage() {
    System.out.println("Тут должна была открыться главная страница");
    loginPage.open();
  }


}
