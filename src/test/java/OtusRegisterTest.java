import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static application.WebDriverFactory.wdChrome;
import static application.AppManager.cfg;
import static application.AppManager.logger;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static pages.PageObject.*;

public class OtusRegisterTest {

  private String firstName = "Елена";
  private String lastName = "Фаустова";
  private String firstNameLatin = "Elena";
  private String lastNameLatin = "Faustova";
  private String blogName = "EF";
  private String facebook = "My contact on Facebook";
  private String vk = "My contact on VK";

  @Test
  public void changeUserDetails() {
    loginPage.open();
    loginPage.login(cfg.email(), cfg.password());
    mainPage.isPage();

    userDetailsPage.open();
    userDetailsPage.fillFirstName(firstName);
    userDetailsPage.fillLastName(lastName);
    userDetailsPage.fillFirstNameLatin(firstNameLatin);
    userDetailsPage.fillLastNameLatin(lastNameLatin);
    userDetailsPage.fillBlogName(blogName);
    userDetailsPage.fillBirthDate();
    userDetailsPage.fillCountry();
    userDetailsPage.fillCity();
    userDetailsPage.fillEnglishLevel();
    userDetailsPage.fillContact0(facebook);
    userDetailsPage.fillContact1(vk);
    userDetailsPage.saveForm();

    mainPage.refreshLogin();
    userDetailsPage.open();

    logger.info("Личные данные: " + userDetailsPage.getFirstName() + " " + userDetailsPage.getLastName()
            + ", на латинском: " + userDetailsPage.getFirstNameLatin() + " " + userDetailsPage.getLastNameLatin()
            + ". Имя в блоге: " + userDetailsPage.getBlogName()
            + ". Контактные данные: FB: " + userDetailsPage.get0contact()
            + ", VK: " + userDetailsPage.get1contact());
    assertEquals(firstName, userDetailsPage.getFirstName());
    assertEquals(lastName, userDetailsPage.getLastName());
    assertEquals(firstNameLatin, userDetailsPage.getFirstNameLatin());
    assertEquals(lastNameLatin, userDetailsPage.getLastNameLatin());
    assertEquals(blogName, userDetailsPage.getBlogName());
    assertEquals(facebook, userDetailsPage.get0contact());
    assertEquals(vk, userDetailsPage.get1contact());
  }

  @AfterEach
  public void setDown() {
    if (wdChrome != null) {
      wdChrome.quit();
    }
  }
}
