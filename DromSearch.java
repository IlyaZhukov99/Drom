package DromPackage;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class DromSearch {
    private final Logger log = LoggerFactory.getLogger(DromSearch.class);
    private static FileWriter writer;
    private static final String DROM_RU = "https://www.drom.ru/";
    private static WebDriver driver;

    @BeforeAll
    static void before1() throws IOException {
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--start-maximized");
        option.setCapability("takesScreenshot", false);
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver(option);
        WebDriverRunner.setWebDriver(driver);
        writer = new FileWriter("log\\auto.txt");
        }

    @Test
    void drom() throws IOException {
        open(DROM_RU);
        DromSteps dromSteps = new DromSteps();
        log.info("������� �����!!!");
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
    @Test
    void sendMails() {
        open("https://mail.ru/");
        log.info("����� ���������� ������");
        SendMail sendMail = new SendMail();
        sendMail.authorization();
        sendMail.goToSendMail();
        sendMail.setInformationAboutMail();
        sendMail.setItemsToMail();
        sendMail.setTxtToMail();
        sendMail.sendMessage();
    }

    @AfterAll
    static void after1() {
        driver.close();
        closeWebDriver();
    }
}
