import application.WebDriverFactory;
import config.ServerConfig;
import enums.Browsers;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BrowserTest {

  private String webDriverChrome = Browsers.CHROME.value();
  private String webDriverFfox = Browsers.FIREFOX.value();
  private Logger logger = LogManager.getLogger(BrowserTest.class);
  ServerConfig cfg = ConfigFactory.create(ServerConfig.class);

  @Test
  public void openPageChrome() {
    WebDriver driver = WebDriverFactory.create(webDriverChrome, "--window-size=1000,500");
    logger.info("Поднят драйвер " + webDriverChrome);
    driver.get(cfg.url());
    logger.info("Открыта страница Отус");

    if (driver.findElement(By.xpath("//title[contains( text(),'Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям')]")).isEnabled()) {
      logger.info("Title появился");
    } else throw new AssertionError("Title не появился");

    if (driver != null) {
      driver.quit();
    }
  }

  @Test
  public void openPageFfox() {
    WebDriver driver = WebDriverFactory.create(webDriverFfox);
    logger.info("Поднят драйвер " + webDriverFfox);
    driver.get(cfg.url());
    logger.info("Открыта страница Отус");

    if (driver.findElement(By.xpath("//title[contains( text(),'Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям')]")).isEnabled()) {
      logger.info("Title появился");
    } else throw new AssertionError("Title не появился");

    if (driver != null) {
      driver.quit();
    }
  }
}
