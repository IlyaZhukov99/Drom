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
    private final String doc = "� �������";
    private final String year = "2010";
    private final String transmission = "�������";
    private final String fuel = "������";
    private final String volume = "1.5";
    private final String repair = "�� ��������� ������";

    public void goToTomsk() {
        $(By.xpath("//a[@href=\"https://auto.drom.ru/cities/\"]")).click();
//      $(By.xpath("//a[@href=\"https://auto.drom.ru/region70/\"]")).click();
        $(By.xpath("//a[@href=\"https://www.drom.ru/my_region/?go=https%3A%2F%2Ftomsk.drom.ru%2Fauto%2F\"]")).click();
        log.info("������� � �����");
    }

    public void choiceModel() {
        $(By.xpath("//button[@tabindex=\"-1\"]")).click();
        $(By.linkText(model)).click();
        log.info("������� ������ " + model);
    }

    public void choicePrice() {
        $(By.xpath("//input[@data-ftid=\"sales__filter_price-from\"]")).sendKeys(priceFrom);
        $(By.xpath("//input[@data-ftid=\"sales__filter_price-to\"]")).sendKeys(priceTo);
        log.info("������� ���� �� " + priceFrom + " �� " + priceTo);
    }

    public void choiceYear() {
        Selenide.$(By.xpath("//div[@data-ftid=\"sales__filter_year-to\"]")).$(By.tagName("select")).
                selectOption(year);
        log.info("������� " + year + " ���");
    }

    public void choiceTransmission() {
        Selenide.$(By.xpath("//div[@data-ftid=\"sales__filter_transmission\"]")).$(By.tagName("select")).
                selectOption(transmission);
        log.info("������� ������� " + transmission);
    }

    public void choiceFuel() {
        Selenide.$(By.xpath("//div[@data-ftid=\"sales__filter_fuel-type\"]")).$(By.tagName("select")).
                selectOption(fuel);
        log.info("������� ��� �������: " + fuel);
    }

    public void choiceDocuments() {
        Selenide.$(By.xpath("//div[@data-ftid=\"sales__filter_docs\"]")).$(By.tagName("select")).
                selectOption(doc);
        log.info("������� ��������� ����������: ");
    }

    public void choiceColor() {
        $(By.className("css-1omqfef")).click();
        $(By.className("css-v5btcq")).click();
        $(By.className("css-17kbomo")).click();
        log.info("������� ����");
    }

    public void choicePhoto() {
        $(By.xpath("//label[@for=\"photo\"]")).click();
        log.info("������� �������������� ����");
    }

    public void advansedSearch() {
        $(By.className("ezmft1z0")).click();
        log.info("������� � ����������� �����");
    }

    public void choiceNotSold() {
        $(Selectors.byText("�����������")).click();
        log.info("������� �������������� ������������ ����");
    }

    public void choiceTrade() {
        $(Selectors.byText("�������� �����")).click();
        log.info("������� ����������� ������");
    }

    public void choiceRepair() {
        Selenide.$(By.xpath("//div[@data-ftid=\"sales__filter_damaged\"]")).$(By.tagName("select")).
                selectOption(repair);
        log.info("���������: " + repair);//
    }

    public void choiceVolume() {
        Selenide.$(By.xpath("//div[@data-ftid=\"sales__filter_volume-from\"]")).$(By.tagName("select")).
                selectOption(volume);
        log.info("������� �����: " + volume);
    }

    public void buttonShowClick() {
        $(By.xpath("//button[@data-ftid=\"sales__filter_submit-button\"]")).click();
    }

    public void screenAndLink(FileWriter writer) throws IOException {
        ElementsCollection selenideElements = $$(By.xpath("//a[@data-ftid=\"bulls-list_bull\"]"));
        for (int i = 0; i < selenideElements.size(); i++) {
            if (selenideElements.get(i).$(By.className("css-1dkqnhk")).text().contains("�����")) {
                selenideElements.get(i).click();
                log.info("������� �� �������� ����");
                WebDriverRunner.url();
                writeToFile(writer, WebDriverRunner.url());
                log.info("�������� � ���� ������");
                Selenide.back();
                log.info("��������� �� �������� � �������");
            }
            }
        log.info("pizdec nahuy blyad`");
    }

    public static void writeToFile(FileWriter writer, String text) throws IOException {
        writer.write(text + "\n");
        writer.flush();
    }
}
