import config.ServerConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SampleTest {
  private static WebDriver driver;
  private Logger logger = LogManager.getLogger(SampleTest.class);
  ServerConfig cfg = ConfigFactory.create(ServerConfig.class);


  @BeforeEach
  public void setUP() {
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    logger.info("Драйвер поднят");

  }

  @Test
  public void openPage() {
    driver.get(cfg.url());
    logger.info("Открыта страница Отус");

    if (driver.findElement(By.xpath("//title[contains( text(),'Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям')]")).isEnabled()) {
      logger.info("Title появился");
    } else throw new AssertionError("Title не появился");
  }

  @AfterEach
  public void setDown() {
    if (driver != null) {
      driver.quit();
    }
  }
}
