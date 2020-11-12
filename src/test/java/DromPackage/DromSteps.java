package DromPackage;


import com.codeborne.selenide.*;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.FileWriter;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DromSteps {
    private final Logger log = LoggerFactory.getLogger(DromSearch.class);
    private final String priceFrom = "100000";
    private final String priceTo = "300000";
    private final String model = "Toyota";
    private final String doc = "В порядке";
    private final String year = "2010";
    private final String transmission = "Автомат";
    private final String fuel = "Бензин";
    private final String volume = "1.5";
    private final String repair = "Не требуется ремонт";

    public void goToTomsk() {
        $(By.xpath("//a[@href=\"https://auto.drom.ru/cities/\"]")).click();
//      $(By.xpath("//a[@href=\"https://auto.drom.ru/region70/\"]")).click();
        $(By.xpath("//a[@href=\"https://www.drom.ru/my_region/?go=https%3A%2F%2Ftomsk.drom.ru%2Fauto%2F\"]")).click();
        log.info("Перешли в Томск");
    }

    public void choiceModel() {
        $(By.xpath("//button[@tabindex=\"-1\"]")).click();
        $(By.linkText(model)).click();
        log.info("Выбрали модель " + model);
    }

    public void choicePrice() {
        $(By.xpath("//input[@data-ftid=\"sales__filter_price-from\"]")).sendKeys(priceFrom);
        $(By.xpath("//input[@data-ftid=\"sales__filter_price-to\"]")).sendKeys(priceTo);
        log.info("Выбрали цену в пределах от " + priceFrom + " до " + priceTo);
    }

    public void choiceYear() {
        Selenide.$(By.xpath("//div[@data-ftid=\"sales__filter_year-to\"]")).$(By.tagName("select")).
                selectOption(year);
        log.info("Выбрали " + year + " год");
    }

    public void choiceTransmission() {
        Selenide.$(By.xpath("//div[@data-ftid=\"sales__filter_transmission\"]")).$(By.tagName("select")).
                selectOption(transmission);
        log.info("Выбрали коробку " + transmission);
    }

    public void choiceFuel() {
        Selenide.$(By.xpath("//div[@data-ftid=\"sales__filter_fuel-type\"]")).$(By.tagName("select")).
                selectOption(fuel);
        log.info("Выбрали тип топлива" + fuel);
    }

    public void choiceDocuments() {
        Selenide.$(By.xpath("//div[@data-ftid=\"sales__filter_docs\"]")).$(By.tagName("select")).
                selectOption(doc);
        log.info("Выбрали состояние документов");
    }

    public void choiceColor() {
        $(By.className("css-1omqfef")).click();
        $(By.className("css-v5btcq")).click();
        $(By.className("css-17kbomo")).click();
        log.info("Выбрали цвета");
    }

    public void choicePhoto() {
        $(By.xpath("//label[@for=\"photo\"]")).click();
        log.info("Выбрали пометку С Фото");
    }

    public void advansedSearch() {
        $(By.className("ezmft1z0")).click();
        log.info("Открыли расширенный поиск");
    }

    public void choiceNotSold() {
        $(Selectors.byText("Непроданные")).click();
        log.info("Выбрали пометку Непроданные");
    }

    public void choiceTrade() {
        $(Selectors.byText("Возможен обмен")).click();
        log.info("Выбрали возможность обмена");
    }

    public void choiceRepair() {
        Selenide.$(By.xpath("//div[@data-ftid=\"sales__filter_damaged\"]")).$(By.tagName("select")).
                selectOption(repair);
        log.info("Выбрали состояние: " + repair);//
    }

    public void choiceVolume() {
        Selenide.$(By.xpath("//div[@data-ftid=\"sales__filter_volume-from\"]")).$(By.tagName("select")).
                selectOption(volume);
        log.info("Выбрали объем двигателя: " + volume);
    }

    public void buttonShowClick() {
        $(By.xpath("//button[@data-ftid=\"sales__filter_submit-button\"]")).click();
    }

    public void screenAndLink(FileWriter writer) throws IOException {
        ElementsCollection selenideElements = $$(By.xpath("//a[@data-ftid=\"bulls-list_bull\"]"));
        for (int i = 0; i < selenideElements.size(); i++) {
            if (selenideElements.get(i).$(By.className("css-1dkqnhk")).text().contains("Томск")) {
                selenideElements.get(i).click();
                log.info("Перешел на страницу машины");
                WebDriverRunner.url();
                writeToFile(writer, WebDriverRunner.url());
                Selenide.back();
                log.info("Вернулся на страницу поиска");
            }
            log.info("Переход к следующему элементу цикла");
        }
        log.info("pizdec nahuy blyad`");
    }

    public static void writeToFile(FileWriter writer, String text) throws IOException {
        writer.write(text + "\n");
        writer.flush();
    }
}
