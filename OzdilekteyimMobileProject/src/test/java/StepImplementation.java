import com.thoughtworks.gauge.Step;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class StepImplementation extends BaseTest {

    Logger logger= LogManager.getLogger(StepImplementation.class);

    @Step("<time> saniye kadar bekle")
    public void waitForseconds(int time) throws InterruptedException {
        Thread.sleep(time*1000);
    }

    @Step("id <id> li elementi bul ve <text> alanını kontrol et")
    public void textAreacontrolWithid(String id,String text) {
        Assert.assertTrue("Element text değerini içermiyor", appiumDriver.findElement(By.id(id)).getText().contains(text));

    }

    @Step("xpath <xpath> li elementi bul ve <text> alanını kontrol et")
    public void textAreacontrolWithxpath(String xpath,String text) {
        Assert.assertTrue("Element text değerini içermiyor", appiumDriver.findElement(By.xpath(xpath)).getText().contains(text));
        logger.info("Sayfa kontrol işlemi doğrulandı");
    }

    @Step("id <id> li emente tıkla")
    public void clickByid(String id){
        appiumDriver.findElement(By.id(id)).click();

    }

    @Step("xpath <xpath> li emente tıkla")
    public void clickByxpath(String xpath){
        appiumDriver.findElement(By.xpath(xpath)).click();

    }

    @Step("Sayfayı yukarı kaydır")
    public void swipeUp() {
        final int ANIMATION_TIME = 200; // ms
        final int PRESS_TIME = 250; // ms
        int edgeBorder = 10; // better avoid edges
        PointOption pointOptionStart, pointOptionEnd;
        // init screen variables
        Dimension dims = appiumDriver.manage().window().getSize();
        // init start point = center of screen
        pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);
        pointOptionEnd = PointOption.point(dims.width / 2, edgeBorder);
        for (int i = 0; i < 2; i++) {
            new TouchAction(appiumDriver)
                    .press(pointOptionStart)
                    // a bit more reliable when we add small wait
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                    .moveTo(pointOptionEnd)
                    .release().perform();
        }
    }

    @Step("Elementler <xpath> arasından rasgele bir tanesini seç ve tıkla")
    public void clickRandomElement(String xpath){
        Random random = new Random();
        List<MobileElement> products = appiumDriver.findElements(By.xpath(xpath));
        int index = random.nextInt(products.size());
        products.get(index).click();
    }

    @Step("id <id> li ementi bul ve <text> değerini yaz")
    public void sendkeysByid(String id,String text){

        appiumDriver.findElement(By.id(id)).sendKeys(text);
    }

}


