import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

import static application.WebDriverFactory.wdChrome;
import static application.AppManager.logger;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import static pages.PageObject.wait;

public class YandexTestBase {


  @BeforeEach
  public void startTest() {
    wdChrome.get("https://market.yandex.ru/");
    logger.info("Открыта страница Яндекс.Маркет.");
  }

  @AfterEach
  public void setDown() {
    if (wdChrome != null) {
      wdChrome.quit();
    }
  }

  public void clickOnLocatedElement(String locator) {
    wait.until(visibilityOfElementLocated(By.xpath(locator))).click();
  }

  public void findLocatedElement(String locator) {
    wait.until(visibilityOfElementLocated(By.xpath(locator)));
  }

  public void clickIfClickable(String locator) {
    wait.until(elementToBeClickable(wdChrome.findElement(By.cssSelector(locator)))).click();
  }

  public String getElementTitle(String locator) {
    return wait.until(visibilityOf(wdChrome.findElement(By.xpath(locator)))).getAttribute("title");
  }

  public void clickIfPresent(String locator) {
    wait.until(presenceOfElementLocated(By.xpath(locator))).click();
  }

  public void ifPresent(String locator) {
    wait.until(presenceOfElementLocated(By.xpath(locator)));
  }

  public File takeScreenShot() {
    return ((TakesScreenshot) wdChrome).getScreenshotAs(OutputType.FILE);
  }

  public void saveFile(File data) {
    String fileName = "target/" + System.currentTimeMillis() + ".png";
    try {
      FileUtils.copyFile(data, new File(fileName));
    } catch (IOException e) {
      logger.error(e);
    }
  }
}
