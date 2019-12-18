package pages;

import com.google.zxing.*;
import com.google.zxing.NotFoundException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import screenshots.CaptureScreen;
import utilities.JsHelper;
import utilities.NowSuchDriverException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DashboardPage extends BasePage {

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "dashboard")
    private WebElement dashboardSection;

    @FindBy(xpath = "//h3[.='Zamawiam taksówkę:']")
    private WebElement orderTaxiSection;

    @FindBy(className = "nav-items")
    private WebElement menuSection;

    @FindBy(xpath = "//div[@class='profile-open']")
    private WebElement profileAccount;

    @FindBy(xpath = "//button[@class='btn transparent small']//span[.='Wyloguj']")
    private WebElement logoutButton;

    @FindBy(xpath = "//div[@class='box-inner']//a[@href='/order']")
    private WebElement orderForEmployeeButton;

    @FindBy(xpath = "//div[@class='box-inner']//a[@href='/order/guest']")
    private WebElement orderForGuestButton;

    @FindBy(xpath = "//span[@class='company']")
    private WebElement companyName;

    @FindBy(xpath = "//a[@href='/manage']//span[.='Zarządzanie']")
    private WebElement manageButton;

    @FindBy(xpath = "//a[@href='/users']//span[.='Pracownicy']")
    private WebElement employeesButton;

    @FindBy(xpath = "//a[@href='/order']//span[.='Dla siebie']")
    private WebElement orderForMyselfButon;

    @FindBy(xpath = "//ul[@class='nav-items']//a[@href='/ordered']")
    private WebElement orderedTaxiButton;


//    @FindBy(xpath = "//a[@href='/ordered']//span[.='Zamówione taksówki']")
//    private WebElement orderedTaxiButton;

    @Step
    public DashboardPage verify_dashboardPge_for_employee() throws InterruptedException {
        waitForPresenceOfElement(dashboardSection);
        waitForPresenceOfElement(orderTaxiSection);
        waitForVisibilityOfElement(menuSection);

        List<String> expectedTexts = Arrays.asList("Dashboard", "Zamów taksówkę", "Zamówione taksówki", "Raporty i faktury", "Ustawienia");
        List<String> actualTexts = new ArrayList<String>();

        List<WebElement> menuButtonList = driver.findElements(By.xpath("//ul[@class='nav-items']//li"));
        Thread.sleep(1000);
        waitForPresenceOfElements(menuButtonList);
        waitForVisibilityOfElements(menuButtonList);
        for (WebElement element : menuButtonList) {
            actualTexts.add(element.getText());
        }
        Assert.assertEquals(actualTexts.toString(), expectedTexts.toString());

        Assert.assertTrue(orderForMyselfButon.isDisplayed());
        Assert.assertEquals(orderForMyselfButon.getText(), "DLA SIEBIE");

        return this;
    }


    @Step
    public DashboardPage verify_dashboardPge_for_admin(String businessPartnerName) throws InterruptedException {
        Thread.sleep(1000);
        waitForVisibilityOfElement(dashboardSection);
        waitForPresenceOfElement(orderTaxiSection);
        waitForPresenceOfElement(menuSection);

        List<String> expectedTexts = Arrays.asList("Dashboard", "Zamów taksówkę", "Zamówione taksówki", "Raporty i faktury", "Zarządzanie", "Pracownicy", "Ustawienia");
        List<String> actualTexts = new ArrayList<String>();

        List<WebElement> menuButtonList = driver.findElements(By.xpath("//ul[@class='nav-items']//li"));
        waitForPresenceOfElements(menuButtonList);
        waitForVisibilityOfElements(menuButtonList);
        for (WebElement element : menuButtonList) {
            actualTexts.add(element.getText());
        }
        Assert.assertEquals(actualTexts.toString(), expectedTexts.toString());

        Assert.assertTrue(orderForEmployeeButton.isDisplayed());
        Assert.assertTrue(orderForGuestButton.isDisplayed());
        Assert.assertEquals(orderForEmployeeButton.getText(), "DLA PRACOWNIKÓW");
        Assert.assertEquals(orderForGuestButton.getText(), "DLA GOŚCI");

        Assert.assertTrue(companyName.isDisplayed());
        Assert.assertEquals(companyName.getText(), businessPartnerName);


        return this;
    }

    @Step
    public void logout() {
        WebElement header_logo = driver.findElement(By.xpath("//div[@class='header-logo']"));
        header_logo.click();
        profileAccount.click();
        waitForPresenceOfElement(logoutButton);
        logoutButton.click();
        new LoginPage(driver).verify_loginPage();
    }

    @Step
    public MpkPage go_to_MpkPage() throws InterruptedException {
        Thread.sleep(1000);
        waitForPresenceOfElement(manageButton);
        waitForElementToBeClickable(manageButton);
        manageButton.click();
        return new MpkPage(driver);
    }

    @Step
    public DashboardPage verify_qr_code() throws IOException, NotFoundException {

        String imgPath = driver.findElement(By.xpath("//img[@class='qr']")).getAttribute("src");
        URL url = new URL(imgPath);
        BufferedImage buffImage = ImageIO.read(url);
        LuminanceSource luminanceSource = new BufferedImageLuminanceSource(buffImage);
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(luminanceSource));
        Result result = new MultiFormatReader().decode(binaryBitmap);
        String textInQrCode = result.getText();
        Assert.assertEquals(textInQrCode, "https://itaxi.pl/pobierz");
        return this;
    }

    @Step
    public DashboardPage verify_download_app_section_on_menu() {
        WebElement elementWithInfoAboutOrderInMobile = driver.findElement(By.xpath("//div[@class='side-nav-content']//p[contains(.,'Chcesz wygodnie zamawiać taksówki w swoim smartfonie?')]"));
        String infoAboutOrderInMobile = elementWithInfoAboutOrderInMobile.getText();

        WebElement elementWithLinkToDownloadPage = driver.findElement(By.xpath("//div[@class='side-nav-content']// a[.='POBIERZ APLIKACJĘ!']"));
        String urlToDownloadPage = elementWithLinkToDownloadPage.getAttribute("href");

        Assert.assertEquals(infoAboutOrderInMobile, "Chcesz wygodnie zamawiać taksówki w swoim smartfonie?\n" +
                "POBIERZ APLIKACJĘ!");
        Assert.assertEquals(urlToDownloadPage, "https://itaxi.pl/pobierz");

        elementWithLinkToDownloadPage.click();

        ArrayList<String> tabs = new ArrayList(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        waitForPresenceOfElement(driver.findElement(By.xpath("//h1[.='Wyślij sobie link za darmo']")));

        Assert.assertEquals(driver.getCurrentUrl(), "https://itaxi.pl/pobierz-aplikacje-smsem/");
        Assert.assertEquals(driver.getTitle(), "iTaxi - link SMS");

        driver.close();
        driver.switchTo().window(tabs.get(0));
        return this;
    }

    @Step
    public OrderForEmployeePage go_to_orderForEmployeePage() {
        orderForEmployeeButton.click();
        return new OrderForEmployeePage(driver);
    }


    @Step
    public OrderedTaxiPage go_to_orderedTaxiPage() throws InterruptedException {
        Thread.sleep(1000);
        orderedTaxiButton.click();
        return new OrderedTaxiPage(driver);
    }

    @Step
    public DashboardPage takeScreenshotOfMenu() throws IOException, NowSuchDriverException {
        waitForPresenceOfElement(menuSection);
        new CaptureScreen(driver).takeScreenshot(menuSection,"menu");
        return this;
    }

    @Step
    public DashboardPage compareScreenshotOfMenu() throws IOException {
        waitForPresenceOfElement(menuSection);
        new CaptureScreen(driver).compareImages("menu", menuSection);
        return this;
    }

    @Step
    public EmployeesPage go_to_EmployeesPage() throws InterruptedException {
        Thread.sleep(1000);
        employeesButton.click();
        return new EmployeesPage(driver);
    }



}




