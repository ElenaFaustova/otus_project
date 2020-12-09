package application;


import enums.Browsers;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebDriverFactory {

  public static WebDriver wdChrome = create(Browsers.CHROME.value());

  public static WebDriver create(String webDriverName) {
    if (webDriverName.equalsIgnoreCase(Browsers.CHROME.value())) {
      WebDriverManager.chromedriver().setup();
      return new ChromeDriver();
    } else if (webDriverName.equalsIgnoreCase(Browsers.FIREFOX.value())) {
      WebDriverManager.firefoxdriver().setup();
      return new FirefoxDriver();
    } else throw new AssertionError("Браузер не поддерживается");
  }

  public static WebDriver create(String webDriverName, String myOptions) {

    if (webDriverName.equalsIgnoreCase(Browsers.CHROME.value())) {
      WebDriverManager.chromedriver().setup();
      ChromeOptions options = new ChromeOptions();
      options.addArguments(myOptions);
      return new ChromeDriver(options);
    } else if (webDriverName.equalsIgnoreCase(Browsers.FIREFOX.value())) {
      WebDriverManager.firefoxdriver().setup();
      FirefoxOptions options = new FirefoxOptions();
      options.addArguments(myOptions);
      return new FirefoxDriver(options);
    } else throw new AssertionError("Браузер не поддерживается");
  }

}
