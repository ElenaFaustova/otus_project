package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static application.WebDriverFactory.wdChrome;
import static application.AppManager.cfg;
import static application.AppManager.logger;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;
import static pages.PageObject.*;

public class MainPage {

  private WebDriver wd;

  public MainPage(WebDriver wd) {
    PageFactory.initElements(wd, this);
    this.wd = wdChrome;
  }

  @FindBy(xpath = "//*[@class='header2-menu__item-wrapper header2-menu__item-wrapper__username']")
  private WebElement userIcon;

  @FindBy(xpath = "//*[@href='/contacts/']")
  private WebElement contacts;

  @FindBy(xpath = "//*[@title='Выход']")
  private WebElement logout;

  public MainPage isPage() {
    wait.until(visibilityOf(userIcon)).isEnabled();
    logger.info("Пользователь авторизован и находится на главной странице.");
    return this;
  }

  public MainPage openContacts(){
    wait.until(visibilityOf(contacts)).click();
    return this;
  }

  public MainPage logout() {
    userIcon.click();
    wait.until(visibilityOf(logout)).click();
    return this;
  }

  public MainPage refreshLogin() {
    mainPage.logout();
    refresfBrowser();
    loginPage.isPage();
    loginPage.login(cfg.email(), cfg.password());
    mainPage.isPage();
    return this;
  }
}
