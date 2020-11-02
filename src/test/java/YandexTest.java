import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static application.WebDriverFactory.wdChrome;
import static application.appManager.logger;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class YandexTest extends YandexTestBase {


  private String firstMark = "Samsung";
  private String secondMark = "Xiaomi";
  private String locatorElectronica = "//*[@data-zone-name='category-link']//span[text()='Электроника']";
  private String locatorSmartphone = "//*[@data-zone-name='link']/a[text()='Смартфоны']";
  private String locatorFilterSamsung = "//*[@type='checkbox' and @name='Производитель Samsung']/../div/span";
  private String locatorFilterXiomi = "//*[@type='checkbox' and @name='Производитель Xiaomi']/../div/span";
  private String cssSelectorOrderByPrice = "[data-autotest-id='dprice']";
  private String locatorCompare = "//span[text()='Сравнить']";
  private String locatorTitleComparePage = "//title[contains( text(),'Сравнение товаров')]";
  private String locatorElementsOnComparePage = "//*[@data-tid='a86a07a1 2d4d9fc1']";

  private String locatorFirstSmartphone(String mark) {
    return "//*[@data-zone-name='title']//span[contains( text(),'" + mark + "')]/..";
  }

  private String locatorAddToCompare(String smartphone) {
    return "//*[@title='" + smartphone + "']/../../../../div/div[contains(@aria-label,'сравнению')]";
  }

  private String locatorSmartphoneAdded(String smartphone) {
    return "//*[contains(text(), 'Товар " + smartphone + " добавлен к сравнению')]";
  }


  @Test
  public void compare() throws InterruptedException {
    clickOnLocatedElement(locatorElectronica);
    logger.info("Произошел переход в раздел 'Электроника'");
    clickOnLocatedElement(locatorSmartphone);
    logger.info("Произошел переход в раздел 'Смартфоны'");

    clickOnLocatedElement(locatorFilterSamsung);
    logger.info("Выбран фильтр по смартфонам Samsung");
    clickOnLocatedElement(locatorFilterXiomi);
    logger.info("Выбран фильтр по смартфонам Xiaomi");

    clickIfClickable(cssSelectorOrderByPrice);
    logger.info("Список смартфонов отсортирован по возрастанию цены");
    // В данном случае считаю использование Thread.sleep оправданным, т.к. все плашки с товаром остаются видимыми и кликабельными в процессе сортировки и тест получает первый элемент до того, как сортировка завершена.
    // При этом в DOMе не происходит изменений, за которые можно зацепиться, чтобы поставить ожидание. Меняется значение data-autotest-id с dprice на aprice, но и оно происходит до завершения сортировки.
    Thread.sleep(7000L);

    String firstSamsung = getElementTitle(locatorFirstSmartphone(firstMark));
    logger.info(String.format("Найден первый в списке Samsung: %s", firstSamsung));
    clickIfPresent(locatorAddToCompare(firstSamsung));
    findLocatedElement(locatorSmartphoneAdded(firstSamsung));
    logger.info(String.format("Добавлен в список сравнения: %s", firstSamsung));

    String firstXiomi = getElementTitle(locatorFirstSmartphone(secondMark));
    logger.info(String.format("Найден первый в списке Xiaomi: %s", firstXiomi));
    clickIfPresent(locatorAddToCompare(firstXiomi));
    findLocatedElement(locatorSmartphoneAdded(firstXiomi));
    logger.info(String.format("Добавлен в список сравнения: %s", firstXiomi));

    clickOnLocatedElement(locatorCompare);
    ifPresent(locatorTitleComparePage);
    logger.info("Произошел переход на страницу сравнения");
    saveFile(takeScreenShot());
    int goodsAmount = wdChrome.findElements(By.xpath(locatorElementsOnComparePage)).size();
    assertEquals(2, goodsAmount);
  }
}
