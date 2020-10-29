import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static application.WebDriverFactory.wdChrome;
import static application.appManager.logger;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class YandexTest {

  WebDriverWait wait = new WebDriverWait(wdChrome, Duration.ofSeconds(10L));

  @Before
  public void startTest() {
    wdChrome.get("https://market.yandex.ru/");
    logger.info("Открыта страница Яндекс.Маркет.");
  }

  @Test
  public void compare() throws InterruptedException {
    wait.until(visibilityOfElementLocated(By.xpath("//*[@data-zone-name='category-link']//span[text()='Электроника']"))).click();
    logger.info("Произошел переход в раздел 'Электроника'");
    wait.until(visibilityOfElementLocated(By.xpath("//*[@data-zone-name='link']/a[text()='Смартфоны']"))).click();
    logger.info("Произошел переход в раздел 'Смартфоны'");
    wait.until(visibilityOfElementLocated(By.xpath("//*[@type='checkbox' and @name='Производитель Samsung']/../div/span"))).click();
    logger.info("Выбран фильтр по смартфонам Samsung");
    wait.until(visibilityOfElementLocated(By.xpath("//*[@type='checkbox' and @name='Производитель Xiaomi']/../div/span"))).click();
    logger.info("Выбран фильтр по смартфонам Xiaomi");
    wait.until(elementToBeClickable(wdChrome.findElement(By.cssSelector("[data-autotest-id='dprice']")))).click();
    logger.info("Список смартфонов отсортирован по возрастанию цены");
    // В данном случае считаю использование Thread.sleep оправданным, т.к. все плашки с товаром остаются видимыми и кликабельными в процессе сортировки и тест получает первый элемент до того, как сортировка завершена.
    // При этом в DOMе не происходит изменений, за которые можно зацепиться, чтобы поставить ожидание. Меняется значение data-autotest-id с dprice на aprice, но и оно происходит до завершения сортировки.
    Thread.sleep(7000L);
    String firstSamsung = wait.until(visibilityOf(wdChrome.findElement(By.xpath("//*[@data-zone-name='title']//span[contains( text(),'Samsung')]/..")))).getAttribute("title");
    logger.info(String.format("Найден первый в списке Samsung: %s", firstSamsung));
    wait.until(presenceOfElementLocated(By.xpath("//*[@title='" + firstSamsung + "']/../../../../div/div[contains(@aria-label,'сравнению')]"))).click();
    wait.until(visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Товар " + firstSamsung + " добавлен к сравнению')]")));
    logger.info(String.format("Добавлен в список сравнения: %s", firstSamsung));
    String firstXiomi = wait.until(visibilityOfElementLocated(By.xpath("//*[@data-zone-name='title']//span[contains( text(),'Xiaomi')]/.."))).getAttribute("title");
    logger.info(String.format("Найден первый в списке Xiaomi: %s", firstXiomi));
    wait.until(presenceOfElementLocated(By.xpath("//*[@title='" + firstXiomi + "']/../../../../div/div[contains(@aria-label,'сравнению')]"))).click();
    wait.until(visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Товар " + firstXiomi + " добавлен к сравнению')]")));
    logger.info(String.format("Добавлен в список сравнения: %s", firstXiomi));
    wait.until(visibilityOfElementLocated(By.xpath("//span[text()='Сравнить']"))).click();
    wait.until(presenceOfElementLocated(By.xpath("//title[contains( text(),'Сравнение товаров')]")));
    logger.info("Произошел переход на страницу сравнения");
    int goodsAmount = wdChrome.findElements(By.xpath("//*[@data-tid='a86a07a1 2d4d9fc1']")).size();
    if (goodsAmount == 2) {
      logger.info("В списке сравнения 2 товара");
    } else throw new AssertionError(String.format("В списке сравнения %s товар(-а,-ов)", goodsAmount));
  }

  @After
  public void setDown() {
    if (wdChrome != null) {
      wdChrome.quit();
    }
  }
}
