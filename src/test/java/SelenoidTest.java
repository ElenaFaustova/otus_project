import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

import static application.appManager.cfg;

public class SelenoidTest {
  protected WebDriver selenoidDriver;

  @SneakyThrows
  @BeforeEach
  public void initDriver() {
    DesiredCapabilities caps = new DesiredCapabilities();
    caps.setBrowserName("chrome");
    caps.setVersion("87.0");
    caps.setCapability("enableVNC", true);
    caps.setCapability("screenResolution", "1280x1024");

    selenoidDriver = new RemoteWebDriver(new URL(cfg.selenoidUrl()), caps);
  }

  @Test
  public void openOtusForSelenoid() {
    selenoidDriver.get(cfg.url());
  }

  @Test
  public void openYandexForSelenoid() {
    selenoidDriver.get("https://market.yandex.ru/");
  }
}
