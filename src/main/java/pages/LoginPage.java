package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static application.WebDriverFactory.wdChrome;
import static application.appManager.cfg;
import static application.appManager.logger;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;
import static pages.PageObject.wait;

public class LoginPage {

  private WebDriver wd;

  public LoginPage(WebDriver wd) {
    PageFactory.initElements(wd, this);
    this.wd = wdChrome;
  }

  @FindBy(xpath = "//*[@data-modal-id='new-log-reg' and contains(text(), 'Вход')]")
  private WebElement enter;

  @FindBy(xpath = "//*[@type='text' and @name='email' and @placeholder='Электронная почта']")
  private WebElement email;

  @FindBy(xpath = "//*[@name='password']")
  private WebElement password;

  @FindBy(xpath = "//*[@data-type='modal-form']//*[@type='submit' and contains(text(), 'Войти')]")
  private WebElement submit;


  public LoginPage open() {
    wdChrome.get(cfg.url());
    return this;
  }

  private LoginPage openRegisterForm() {
    wait.until(visibilityOf(enter)).click();
    return this;
  }

  private LoginPage fillEmail(String text) {
    wait.until(visibilityOf(email)).sendKeys(text);
    return this;
  }

  private LoginPage fillPassword(String text) {
    password.sendKeys(text);
    return this;
  }

  private LoginPage submitLogin() {
    submit.click();
    return this;
  }

  public LoginPage login(String email, String password) {
    openRegisterForm();
    fillEmail(email);
    fillPassword(password);
    submitLogin();
    return this;
  }

  public LoginPage isPage() {
    wait.until(visibilityOf(enter)).isEnabled();
    logger.info("Пользователь находится на странице авторизации.");
    return this;
  }

}
