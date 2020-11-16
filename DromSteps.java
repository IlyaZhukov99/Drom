package DromPackage;


import com.codeborne.selenide.*;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DromSteps {
    private final Logger log = LoggerFactory.getLogger(DromSteps.class);
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
        log.info("Выбрали цену от " + priceFrom + " до " + priceTo);
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
        log.info("Выбрали тип топлива: " + fuel);
    }

    public void choiceDocuments() {
        Selenide.$(By.xpath("//div[@data-ftid=\"sales__filter_docs\"]")).$(By.tagName("select")).
                selectOption(doc);
        log.info("Выбрали состояние документов: ");
    }

    public void choiceColor() {
        $(By.className("css-1omqfef")).click();
        $(By.className("css-v5btcq")).click();
        $(By.className("css-17kbomo")).click();
        log.info("Выбрали цвет");
    }

    public void choicePhoto() {
        $(By.xpath("//label[@for=\"photo\"]")).click();
        log.info("Выбрали обязательность фото");
    }

    public void advansedSearch() {
        $(By.className("ezmft1z0")).click();
        log.info("Перешли в расширенный поиск");
    }

    public void choiceNotSold() {
        $(Selectors.byText("Непроданные")).click();
        log.info("Выбрали обязательность непроданного авто");
    }

    public void choiceTrade() {
        $(Selectors.byText("Возможен обмен")).click();
        log.info("Выбрали возможность обмена");
    }

    public void choiceRepair() {
        Selenide.$(By.xpath("//div[@data-ftid=\"sales__filter_damaged\"]")).$(By.tagName("select")).
                selectOption(repair);
        log.info("Состояние: " + repair);//
    }

    public void choiceVolume() {
        Selenide.$(By.xpath("//div[@data-ftid=\"sales__filter_volume-from\"]")).$(By.tagName("select")).
                selectOption(volume);
        log.info("Выбрали объем: " + volume);
    }

    public void buttonShowClick() {
        $(By.xpath("//button[@data-ftid=\"sales__filter_submit-button\"]")).click();
    }

    public void screenAndLink(FileWriter writer) throws IOException {
        ElementsCollection selenideElements = $$(By.xpath("//a[@data-ftid=\"bulls-list_bull\"]"));
        for (int i = 0; i < selenideElements.size(); i++) {
            if (selenideElements.get(i).$(By.className("css-1dkqnhk")).text().contains("Томск")) {
                selenideElements.get(i).click();
                log.info("Перешли на страницу Авто");
                WebDriverRunner.url();
                writeToFile(writer, WebDriverRunner.url());
                log.info("Записали в файл ссылку");
                Selenide.back();
                log.info("Вернулись на страницу с поиском");
            }
            }
        log.info("pizdec nahuy blyad`");
    }

    public static void writeToFile(FileWriter writer, String text) throws IOException {
        writer.write(text + "\n");
        writer.flush();
    }
}
