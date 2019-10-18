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

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);

    }

    public static final String EMAIL = System.getenv("EMAIL_USERNAME");
    public static final String SUPER_ADMIN_LOGIN = System.getenv("SUPER_ADMIN_LOGIN");
    public static final String SUPER_ADMIN_PASSWORD = System.getenv("SUPER_ADMIN_PASSWORD");


    @FindBy(id = "login")
    private WebElement usernameField;

    @FindBy(id = "pass")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@id='button']//span[.='Zaloguj']")
    private WebElement loginButton;

    @FindBy(xpath = "//a[@class='remind']")
    private WebElement remindButton;

    @FindBy(xpath = "//h3[.='Logowanie']")
    private WebElement headerLogin;

    @FindBy(className = "benefits")
    private WebElement benefitsSection;

    @FindBy(xpath = "//h2[.='Wybierz partnera biznesowego']")
    private WebElement chooseBusinesPartnerSection;

    @FindBy(xpath = "//input[@placeholder='Wpisz aby wyszukać...']")
    private WebElement typeBusinesPartnerField;

    @FindBy(xpath = "//button[@id='button']//span[.='Wybierz']")
    private WebElement chooseBusinesPartnerButton;

    @FindBy(xpath = "//div[@class='notifications']//p[.='Zalogowano poprawnie.']")
    private WebElement correctLoginNotifications;

    @FindBy(xpath = "//p[.='Podaj hasło']")
    private WebElement errorPasswordInfo;

    @FindBy(xpath = "//p[.='Podaj prawidłowy login']")
    private WebElement errorLoginInfo;

    @FindBy(xpath = "//div[@class='notifications']//p[.='Błędny login lub hasło']")
    private WebElement incorrectLoginAndPasswordNotifications;


    @Step
    public LoginPage verify_loginPage() {
        waitForVisibilityOfElement(headerLogin);
        Assert.assertEquals(headerLogin.getText(), "LOGOWANIE");
        waitForVisibilityOfElement(benefitsSection);
        return this;
    }

    @Step
    public LoginPage login_as_superAdmin() {
        waitForPresenceOfElement(usernameField);
        waitForPresenceOfElement(passwordField);
        usernameField.clear();
        usernameField.sendKeys(SUPER_ADMIN_LOGIN);
        passwordField.clear();
        passwordField.sendKeys(SUPER_ADMIN_PASSWORD);
        loginButton.click();
        waitForPresenceOfElement(correctLoginNotifications);
        Assert.assertEquals(correctLoginNotifications.getText(), "Zalogowano poprawnie.");
        return this;
    }

    @Step
    public DashboardPage login_as_employee(String login, String password) {
        waitForPresenceOfElement(usernameField);
        waitForPresenceOfElement(passwordField);
        usernameField.clear();
        usernameField.sendKeys(login);
        passwordField.clear();
        passwordField.sendKeys(password);
        loginButton.click();
        waitForPresenceOfElement(correctLoginNotifications);
        Assert.assertEquals(correctLoginNotifications.getText(), "Zalogowano poprawnie.");
        return new DashboardPage(driver);
    }

    @Step
    public DashboardPage choose_business_partner_from_list(String businessPartnerName) {
        waitForPresenceOfElement(chooseBusinesPartnerSection);
        typeBusinesPartnerField.sendKeys(businessPartnerName);
        WebElement firstCompany = driver.findElement(By.xpath("//label[@for='radio-0']"));
        firstCompany.click();
        chooseBusinesPartnerButton.click();
        return new DashboardPage(driver);
    }

    @Step
    public LoginPage verify_benefitsSection() {
        List<String> expectedTexts = Arrays.asList("Oszczędność czasu dla działów administracji i księgowości", "Narzędzie do kontroli kosztów", "Możliwość zarządzania przejazdami pracowników na wielu płaszczyznach", "Możliwość nadania wielu limitów", "Podgląd na bieżące wykorzystanie", "Rozbudowane raportowanie", "Wszystkie faktury w jednym miejscu", "Kanał informacji o bieżących akcjach i promocjach", "Bezpieczeństwo danych");
        List<String> actualTexts = new ArrayList<>();

        WebElement benefitsList = driver.findElement(By.xpath("//div[@class='benefits']//ul"));
        List<WebElement> elementsFromList = benefitsList.findElements(By.tagName("li"));
        for (WebElement element : elementsFromList) {
            actualTexts.add(element.getText());
        }
        Assert.assertEquals(expectedTexts.toString(), actualTexts.toString());
        return this;
    }

    @Step
    public RemindPasswordPage go_to_remindPasswordPage() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        waitForPresenceOfElement(remindButton);
        waitForElementToBeClickable(remindButton);
        remindButton.click();
        return new RemindPasswordPage(driver);
    }

    @Step
    public DashboardPage login_with_new_password(String newPassword) {
        waitForPresenceOfElement(usernameField);
        waitForPresenceOfElement(passwordField);
        usernameField.sendKeys(EMAIL);
        usernameField.sendKeys(newPassword);
        loginButton.click();
        return new DashboardPage(driver);
    }

    @Step
    public LoginPage try_login_without_login_and_password()  {
        waitForPresenceOfElement(usernameField);
        waitForPresenceOfElement(passwordField);
        usernameField.clear();
        passwordField.clear();
        loginButton.click();
        waitForPresenceOfElement(errorLoginInfo);
        waitForPresenceOfElement(errorPasswordInfo);
        Assert.assertTrue(errorLoginInfo.isDisplayed());
        Assert.assertTrue(errorPasswordInfo.isDisplayed());
        Assert.assertEquals(errorLoginInfo.getText(), "Podaj prawidłowy login");
        Assert.assertEquals(errorPasswordInfo.getText(), "Podaj hasło");
        verify_loginPage();
        return this;
    }

    @Step
    public LoginPage try_login_with_correct_login_and_incorrect_password()  {
        waitForPresenceOfElement(usernameField);
        waitForPresenceOfElement(passwordField);
        usernameField.clear();
        passwordField.clear();
        usernameField.sendKeys("jolakama666@gmail.com");
        passwordField.sendKeys("123");
        loginButton.click();
        waitForPresenceOfElement(errorPasswordInfo);
        Assert.assertTrue(errorPasswordInfo.isDisplayed());
        Assert.assertEquals(errorPasswordInfo.getText(), "Podaj hasło");
        verify_loginPage();
        return this;
    }

    @Step
    public LoginPage try_login_with_incorrect_login_and_correct_password()  {
        waitForPresenceOfElement(usernameField);
        waitForPresenceOfElement(passwordField);
        usernameField.clear();
        passwordField.clear();
        usernameField.sendKeys("jolakama666@gmail");
        passwordField.sendKeys("123456");
        loginButton.click();
        waitForPresenceOfElement(incorrectLoginAndPasswordNotifications);
        Assert.assertEquals(incorrectLoginAndPasswordNotifications.getText(), "Błędny login lub hasło");
        verify_loginPage();
        return this;
    }

    @Step
    public LoginPage try_login_with_correct_login_and_without_password() {
        waitForPresenceOfElement(usernameField);
        waitForPresenceOfElement(passwordField);
        usernameField.clear();
        passwordField.clear();
        usernameField.sendKeys("jolakama666@gmail.pl");
        loginButton.click();
        waitForPresenceOfElement(errorPasswordInfo);
        Assert.assertTrue(errorPasswordInfo.isDisplayed());
        Assert.assertEquals(errorPasswordInfo.getText(), "Podaj hasło");
        verify_loginPage();
        return this;
    }

    @Step
    public LoginPage try_login_without_login_and_correct_password() {
        waitForPresenceOfElement(usernameField);
        waitForPresenceOfElement(passwordField);
        usernameField.clear();
        passwordField.clear();
        passwordField.sendKeys("123456");
        loginButton.click();
        waitForPresenceOfElement(errorLoginInfo);
        Assert.assertTrue(errorLoginInfo.isDisplayed());
        Assert.assertEquals(errorLoginInfo.getText(), "Podaj prawidłowy login");
        verify_loginPage();
        return this;
    }

}
