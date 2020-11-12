package DromPackage;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.FileWriter;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class DromSearch {
    private static FileWriter writer;
    private static final String URL = "https://www.drom.ru/";

    @BeforeAll
    static void before1() throws IOException {

        writer = new FileWriter("log\\auto.txt");
        Selenide.open(URL);
    }

    @Test
    void drom() throws IOException  {
        DromSteps dromSteps = new DromSteps();
        dromSteps.goToTomsk();
        dromSteps.choiceModel();
        dromSteps.choicePrice();
        dromSteps.choicePhoto();
        dromSteps.choiceTransmission();
        dromSteps.choiceYear();
        dromSteps.choiceFuel();
        dromSteps.choiceNotSold();
        dromSteps.choiceVolume();
        dromSteps.advansedSearch();
        dromSteps.choiceRepair();
        dromSteps.choiceColor();
        dromSteps.choiceDocuments();
        dromSteps.choiceTrade();
        dromSteps.buttonShowClick();
        dromSteps.screenAndLink(writer);
    }

    @AfterAll
    static void after1() {
        closeWebDriver();
    }
}
