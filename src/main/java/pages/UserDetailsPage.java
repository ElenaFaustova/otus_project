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

public class UserDetailsPage {

  private WebDriver wd;

  public UserDetailsPage(WebDriver wd) {
    PageFactory.initElements(wd, this);
    this.wd = wdChrome;
  }

  @FindBy(xpath = "//*[@class='title__text' and contains(text(), 'Личный кабинет')]")
  private WebElement title;

  @FindBy(xpath = "//*[@name='fname']")
  private WebElement firstName;

  @FindBy(xpath = "//*[@name='lname']")
  private WebElement lastName;

  @FindBy(xpath = "//*[@name='fname_latin']")
  private WebElement firstNameLatin;

  @FindBy(xpath = "//*[@name='lname_latin']")
  private WebElement lastNameLatin;

  @FindBy(xpath = "//*[@name='blog_name']")
  private WebElement blogName;

  @FindBy(xpath = "//*[@name='date_of_birth']")
  private WebElement birthDateField;

  @FindBy(xpath = "//*[@data-view='month current']")
  private WebElement birthMonthCurrent;

  @FindBy(xpath = "//*[@data-view='year prev']")
  private WebElement birthYearPrev;

  @FindBy(xpath = "//*[@data-view='month' and contains(text(), 'Июнь')]")
  private WebElement birthMonth;

  @FindBy(xpath = "//*[@data-view='day' and contains(text(), '3')]")
  private WebElement birthDay;

  @FindBy(xpath = "//*[@name='country']/../..")
  private WebElement countryField;

  @FindBy(xpath = "//*[@name='country']/../..//*[@data-value='1' and @title='Россия']")
  private WebElement country;

  @FindBy(xpath = "//*[@name='city']/../..")
  private WebElement cityField;

  @FindBy(xpath = "//*[@name='city']/../..//*[@data-value='37' and @title='Арзамас']")
  private WebElement city;

  @FindBy(xpath = "//*[@name='english_level']/../..")
  private WebElement englishLevel;

  @FindBy(xpath = "//*[@name='english_level']/../..//*[@data-value='5' and @title='Выше среднего (Upper Intermediate)']")
  private WebElement englishUpper;

  @FindBy(xpath = "//*[@name='contact-0-service']/../div")
  private WebElement contact0;

  @FindBy(xpath = "//*[@type='text' and @name='contact-0-value']")
  private WebElement fieldContact0;

  @FindBy(xpath = "//*[@name='contact-1-service']/../div")
  private WebElement contact1;

  @FindBy(xpath = "//*[@type='text' and @name='contact-1-value']")
  private WebElement fieldContact1;

  @FindBy(xpath = "//*[@type='button' and contains(text(), 'Добавить')]")
  private WebElement addContact;

  @FindBy(xpath = "//*[@data-value='facebook' and @title='Facebook']")
  private WebElement contactFacebook;

  @FindBy(xpath = "//*[@name='contact-1-service']/../..//*[@data-value='vk' and @title='VK']")
  private WebElement contactVk;

  @FindBy(xpath = "//*[@name='continue']")
  private WebElement save;

  public UserDetailsPage open() {
    wdChrome.get(cfg.urlUserDetails());
    getTitle();
    return this;
  }

  public UserDetailsPage getTitle() {
    wait.until(visibilityOf(title));
    logger.info("Произошел переход в Личный кабинет.");
    return this;
  }


  public UserDetailsPage fillFirstName(String name) {
    wait.until(visibilityOf(firstName)).clear();
    firstName.sendKeys(name);
    return this;
  }

  public UserDetailsPage fillLastName(String surname) {
    wait.until(visibilityOf(lastName)).clear();
    lastName.sendKeys(surname);
    return this;
  }

  public UserDetailsPage fillFirstNameLatin(String fNameLatin) {
    wait.until(visibilityOf(firstNameLatin)).clear();
    firstNameLatin.sendKeys(fNameLatin);
    return this;
  }

  public UserDetailsPage fillLastNameLatin(String lNameLatin) {
    wait.until(visibilityOf(lastNameLatin)).clear();
    lastNameLatin.sendKeys(lNameLatin);
    return this;
  }

  public UserDetailsPage fillBlogName(String bName) {
    wait.until(visibilityOf(blogName)).clear();
    blogName.sendKeys(bName);
    return this;
  }

  public UserDetailsPage fillBirthDate() {
    wait.until(visibilityOf(birthDateField)).clear();
    birthDateField.click();
    wait.until(visibilityOf(birthMonthCurrent)).click();
    wait.until(visibilityOf(birthYearPrev)).click();
    wait.until(visibilityOf(birthMonth)).click();
    wait.until(visibilityOf(birthDay)).click();
    return this;
  }

  public UserDetailsPage fillCountry() {
    countryField.click();
    wait.until(visibilityOf(country)).click();
    return this;
  }

  public UserDetailsPage fillCity() {
    cityField.click();
    wait.until(visibilityOf(city)).click();
    return this;
  }

  public UserDetailsPage fillEnglishLevel() {
    englishLevel.click();
    wait.until(visibilityOf(englishUpper)).click();
    return this;
  }

  public UserDetailsPage fillContact0(String contactZero) {
    contact0.click();
    wait.until(visibilityOf(contactFacebook)).click();
    fieldContact0.clear();
    fieldContact0.sendKeys(contactZero);
    return this;
  }

  public UserDetailsPage fillContact1(String contactOne) {
    addContact.click();
    wait.until(visibilityOf(contact1)).click();
    wait.until(visibilityOf(contactVk)).click();
    fieldContact1.clear();
    fieldContact1.sendKeys(contactOne);
    return this;
  }

  public UserDetailsPage saveForm() {
    logger.info("Происходит сохранение измененных данных.");
    save.click();
    return this;
  }

  public String getFirstName() {
    return wait.until(visibilityOf(firstName)).getAttribute("value");
  }

  public String getLastName() {
    return wait.until(visibilityOf(lastName)).getAttribute("value");
  }

  public String getFirstNameLatin() {
    return wait.until(visibilityOf(firstNameLatin)).getAttribute("value");
  }

  public String getLastNameLatin() {
    return wait.until(visibilityOf(lastNameLatin)).getAttribute("value");
  }

  public String getBlogName() {
    return wait.until(visibilityOf(blogName)).getAttribute("value");
  }

  public String get0contact() {
    return wait.until(visibilityOf(fieldContact0)).getAttribute("value");
  }

  public String get1contact() {
    return wait.until(visibilityOf(fieldContact1)).getAttribute("value");
  }

}
