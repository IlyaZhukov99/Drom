package DromPackage;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.$;

public class SendMail {
    private final Logger log = LoggerFactory.getLogger(SendMail.class);
    private final String recipient = "zhukov.ilya1999";
    private final String topic = "Cars";
    private final String login = "zhukov.ilya1999";
    private final String pass = "Qwas232425";
    private final String docToSend = "C:\\Users\\user\\Desktop\\Drom-master\\Drom-master\\log\\auto.txt";

    public void authorization() {
        $(By.id("mailbox:login-input")).sendKeys(login);
        $(By.className("mailbox__icon_next")).click();
        $(By.id("mailbox:password-input")).sendKeys(pass);
        $(By.xpath("//input[@value=\"������ ������\"]")).click();
    }
    public void goToSendMail() {
        $(By.xpath("//a[@href=\"/compose/\"]")).shouldBe(Condition.visible).click();
        log.info("������� � ��������� ������ ���������");
    }
    public void setInformationAboutMail() {
        $(By.xpath("//input[@tabindex=\"100\"]")).shouldBe(Condition.visible).sendKeys(recipient);
        $(By.xpath("//input[@name=\"Subject\"]")).sendKeys(topic);
        log.info("����� ���������� � ����������");
    }
    public void setItemsToMail() {
        SelenideElement addFile = $(By.xpath("//input[@type=\"file\"]"));
        addFile.sendKeys(docToSend);
        log.info("��������� ����� � ������");
    }
    public void setTxtToMail (){
        $(By.className("cke_widget_editable")).sendKeys("�������� ���������� ����");
        log.info("����� �������");
    }
    public void sendMessage() {
        $(Selectors.byTitle("���������")).click();
        log.info("��������� ���������");
    }
}