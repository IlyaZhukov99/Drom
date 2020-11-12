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
    private final String doc = "Â ïîðÿäêå";
    private final String year = "2010";
    private final String transmission = "Àâòîìàò";
    private final String fuel = "Áåíçèí";
    private final String volume = "1.5";
    private final String repair = "Íå òðåáóåòñÿ ðåìîíò";

    public void goToTomsk() {
        $(By.xpath("//a[@href=\"https://auto.drom.ru/cities/\"]")).click();
//      $(By.xpath("//a[@href=\"https://auto.drom.ru/region70/\"]")).click();
        $(By.xpath("//a[@href=\"https://www.drom.ru/my_region/?go=https%3A%2F%2Ftomsk.drom.ru%2Fauto%2F\"]")).click();
        System.out.println("Ïåðåøëè â Òîìñê");
    }

    public void choiceModel() {
        $(By.xpath("//button[@tabindex=\"-1\"]")).click();
        $(By.linkText(model)).click();
        System.out.println("Âûáðàëè ìîäåëü " + model);
    }

    public void choicePrice() {
        $(By.xpath("//input[@data-ftid=\"sales__filter_price-from\"]")).sendKeys(priceFrom);
        $(By.xpath("//input[@data-ftid=\"sales__filter_price-to\"]")).sendKeys(priceTo);
        System.out.println("Âûáðàëè öåíó â ïðåäåëàõ îò " + priceFrom + " äî " + priceTo);
    }

    public void choiceYear() {
        Selenide.$(By.xpath("//div[@data-ftid=\"sales__filter_year-to\"]")).$(By.tagName("select")).
                selectOption(year);
        System.out.println("Âûáðàëè " + year + " ãîä");
    }

    public void choiceTransmission() {
        Selenide.$(By.xpath("//div[@data-ftid=\"sales__filter_transmission\"]")).$(By.tagName("select")).
                selectOption(transmission);
        System.out.println("Âûáðàëè êîðîáêó " + transmission);
    }

    public void choiceFuel() {
        Selenide.$(By.xpath("//div[@data-ftid=\"sales__filter_fuel-type\"]")).$(By.tagName("select")).
                selectOption(fuel);
        System.out.println("Âûáðàëè òèï òîïëèâà" + fuel);
    }

    public void choiceDocuments() {
        Selenide.$(By.xpath("//div[@data-ftid=\"sales__filter_docs\"]")).$(By.tagName("select")).
                selectOption(doc);
        System.out.println("Âûáðàëè ñîñòîÿíèå äîêóìåíòîâ");
    }

    public void choiceColor() {
        $(By.className("css-1omqfef")).click();
        $(By.className("css-v5btcq")).click();
        $(By.className("css-17kbomo")).click();
        System.out.println("Âûáðàëè öâåòà");
    }

    public void choicePhoto() {
        $(By.xpath("//label[@for=\"photo\"]")).click();
        System.out.println("Âûáðàëè ïîìåòêó Ñ Ôîòî");
    }

    public void advansedSearch() {
        $(By.className("ezmft1z0")).click();
        System.out.println("Îòêðûëè ðàñøèðåííûé ïîèñê");
    }

    public void choiceNotSold() {
        $(Selectors.byText("Íåïðîäàííûå")).click();
        System.out.println("Âûáðàëè ïîìåòêó Íåïðîäàííûå");
    }

    public void choiceTrade() {
        $(Selectors.byText("Âîçìîæåí îáìåí")).click();
        System.out.println("Âûáðàëè âîçìîæíîñòü îáìåíà");
    }

    public void choiceRepair() {
        Selenide.$(By.xpath("//div[@data-ftid=\"sales__filter_damaged\"]")).$(By.tagName("select")).
                selectOption(repair);
        System.out.println("Âûáðàëè ñîñòîÿíèå: " + repair);//
    }

    public void choiceVolume() {
        Selenide.$(By.xpath("//div[@data-ftid=\"sales__filter_volume-from\"]")).$(By.tagName("select")).
                selectOption(volume);
        System.out.println("Âûáðàëè îáúåì äâèãàòåëÿ: " + volume);
    }

    public void buttonShowClick() {
        $(By.xpath("//button[@data-ftid=\"sales__filter_submit-button\"]")).click();
    }

    public void screenAndLink(FileWriter writer) throws IOException {
        ElementsCollection selenideElements = $$(By.xpath("//a[@data-ftid=\"bulls-list_bull\"]"));
        for (int i = 0; i < selenideElements.size(); i++) {
            if (selenideElements.get(i).$(By.className("css-1dkqnhk")).text().contains("Òîìñê")) {
                selenideElements.get(i).click();
                System.out.println("Ïåðåøåë íà ñòðàíèöó ìàøèíû");
                WebDriverRunner.url();
                writeToFile(writer, WebDriverRunner.url());
                Selenide.back();
                System.out.println("Âåðíóëñÿ íà ñòðàíèöó ïîèñêà");
            }
            System.out.println("Ïåðåõîä ê ñëåäóþùåìó ýëåìåíòó öèêëà");
        }
        System.out.println("pizdec nahuy blyad`");
    }

    public static void writeToFile(FileWriter writer, String text) throws IOException {
        writer.write(text + "\n");
        writer.flush();
    }
}
