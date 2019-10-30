package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MoreOptionsPage extends BasePage {
    public MoreOptionsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='inputs']/h3")
    private WebElement header;

    @FindBy(xpath = "//p[@class='additional-info-box']")
    private WebElement additionalInfo;

    @FindBy(xpath = "//div[@class='car-class-info input']/p")
    private WebElement additionalFeeInfo;

    @FindBy(xpath = "//label[@for='cb3']")
    private WebElement silentRideCheckbox;

    @FindBy(xpath = "//label[@for='cb4']")
    private WebElement largeTrunkCheckbox;

    @FindBy(xpath = "//div[@class='car-class input']//p[.='Luksusowa']")
    private WebElement premiumTaxiIcon;

    @FindBy(xpath = "//div[@class='car-class input']//p[.='Popularna']")
    private WebElement economicTaxiIcon;

    @FindBy(xpath = "//a[@class='more']")
    private WebElement checkFeeTableButton;

    @FindBy(xpath = "//button[@class='btn yellow small']/span")
    private WebElement acceptButton;


    @Step
    public MoreOptionsPage verifyMoreOptionsPageElements() {
        Assert.assertEquals(header.getText(), "Opcje dodatkowe");
        Assert.assertEquals(additionalInfo.getText(), "Jeżeli chcesz zamówić busa, limuzynę, autokar lub inny transport niestandardowy, napisz do nas na cob@itaxi.pl lub zadzwoń na 22 439 00 30");
        Assert.assertEquals(additionalFeeInfo.getText(), "Taksówka luksusowa: dopłata 40.00zł");
        Assert.assertEquals(silentRideCheckbox.getText(), "Cichy przejazd");
        Assert.assertEquals(largeTrunkCheckbox.getText(), "Duży bagażnik");
        Assert.assertEquals(premiumTaxiIcon.getText(), "Luksusowa");
        Assert.assertEquals(economicTaxiIcon.getText(), "Popularna");
        Assert.assertEquals(checkFeeTableButton.getText(), "Sprawdź tabelę opłat");
        Assert.assertEquals(acceptButton.getText(), "WYBIERZ");


        List<String> expectedTexts = Arrays.asList("Popularna", "Luksusowa");
        List<String> actualTexts = new ArrayList<String>();
        List<WebElement> taxiTypes = driver.findElements(By.xpath("//div[@class='car-class input']//p"));
        for (WebElement type : taxiTypes) {
            actualTexts.add(type.getText());
        }
        System.out.println(actualTexts);
        Assert.assertEquals(expectedTexts.toString(), actualTexts.toString());

        return this;
    }

    @Step
    public MoreOptionsPage verifyIsEconomicTaxiActive() {
        WebElement economicTaxiIsActive = driver.findElement(By.xpath("//div[@class='car-class input']//p[.='Popularna']/.."));
        String economicTaxiClassName = economicTaxiIsActive.getAttribute("class");
        Assert.assertEquals("cclass active",economicTaxiClassName);
        return this;
    }


    @Step
    public MoreOptionsPage verifyIsPremiumTaxiActive() {
        WebElement premiumTaxiIsActive = driver.findElement(By.xpath("//div[@class='car-class input']//p[.='Luksusowa']/.."));
        String premiumTaxiClassName = premiumTaxiIsActive.getAttribute("class");
        Assert.assertEquals("cclass active", premiumTaxiClassName);
        return this;
    }

    @Step
    public MoreOptionsPage verifyIsEcpnomicTaxiActive() {
        WebElement premiumTaxiIsActive = driver.findElement(By.xpath("//div[@class='car-class input']//p[.='Popularna']/.."));
        String premiumTaxiClassName = premiumTaxiIsActive.getAttribute("class");
        Assert.assertEquals("cclass active", premiumTaxiClassName);
        return this;
    }

    @Step
    public MoreOptionsPage selectPremiumTaxiAsActive() {
        premiumTaxiIcon.click();
        return this;
    }

    @Step
    public MoreOptionsPage selectEconomicTaxiAsActive() {
        economicTaxiIcon.click();
        return this;
    }

    @Step
    public MoreOptionsPage selectLargeTrunk() {
        largeTrunkCheckbox.click();
        return this;
    }

    @Step
    public MoreOptionsPage selectSilentRide() {
        silentRideCheckbox.click();
        return this;
    }

    @Step
    public MoreOptionsPage openFeeTableAndVerify() throws InterruptedException {
        checkFeeTableButton.click();
        ArrayList<String> tabs = new ArrayList (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        Assert.assertEquals(driver.getCurrentUrl(), "https://itaxi.pl/cennik_biznesowy.pdf");

        driver.close();
        driver.switchTo().window(tabs.get(0));
        verifyMoreOptionsPageElements();

        return this;
    }

    @Step
    public OrderForEmployeePage acceptOptions() {
        waitForVisibilityOfElement(acceptButton);
        acceptButton.click();
        return new OrderForEmployeePage(driver);
    }


}

