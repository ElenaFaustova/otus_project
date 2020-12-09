package pages;

import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static application.WebDriverFactory.wdChrome;
import static application.appManager.logger;

public class PageObject {

  public static WebDriverWait wait = new WebDriverWait(wdChrome, Duration.ofSeconds(20L));

  public static void refresfBrowser() {
    wdChrome.navigate().refresh();
    logger.info("Обновлена страница браузера.");
  }

  public static LoginPage loginPage = new LoginPage(wdChrome);
  public static MainPage mainPage = new MainPage(wdChrome);
  public static UserDetailsPage userDetailsPage = new UserDetailsPage(wdChrome);
}
