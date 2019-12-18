package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.util.ArrayList;
import java.util.List;


public class EmailHelper extends BasePage {

    public static final String EMAIL = System.getenv("EMAIL_USERNAME");
    public static final String PASSWORD = System.getenv("EMAIL_PASSWORD");
//    String host = "pop.gmail.com";//change accordingly
//    String mailStoreType = "pop3";



    public EmailHelper(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "identifierId")
    WebElement emailField;

    @FindBy(xpath = "//span[.='Dalej']")
    WebElement nextButton;

    @FindBy(name = "password")
    WebElement passwordField;

    @FindBy(tagName = "tbody")
    WebElement messageBody;

    @FindBy(xpath = "//*[@class='zA zE']//span[@class='bqe']")
    List<WebElement> unreadEmailTitles;

    @FindBy(xpath = "//table[@role='presentation']//div[@role='listitem']")
    List<WebElement> mailsContent;

    @FindBy(xpath = "//div[@id=':4']//div[@aria-label='Usuń']")
    WebElement deleteEmailButton;

    @FindBy(partialLinkText = "Zaloguj się")
    WebElement gmailLoginButton;


    public String openMail_and_readCode(String email, String password) {

        driver.get("https://mail.google.com/mail/u/0/?tab=km#inbox");
        gmailLoginButton.click();
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(2));
        waitForVisibilityOfElement(driver.findElement(By.id("headingText")));
        waitForPresenceOfElement(emailField);
        emailField.sendKeys(email);
        waitForPresenceOfElement(nextButton);
        nextButton.click();
        passwordField.clear();
        passwordField.sendKeys(password);
        waitForPresenceOfElement(nextButton);
        try {
            nextButton.click();
        } catch (StaleElementReferenceException ex) {
            nextButton.click();
        }
        waitForPresenceOfElement(messageBody);

        waitForPresenceOfElements(unreadEmailTitles);


        String emailTitle = "iTaxi.pl - resetowanie hasła. Kod weryfikacyjny";


        for (int i = 0; i < unreadEmailTitles.size(); i++) {
            if (unreadEmailTitles.get(i).getText().equals(emailTitle)) {
                System.out.println("Yes we have got mail with title " + emailTitle);
                unreadEmailTitles.get(i).click();
                break;
            } else {
                System.out.println("No mail form " + emailTitle);
            }
        }

        int listSize = mailsContent.size()-1;
        String lastFromListText = mailsContent.get(listSize).getText();
        String firstStage = lastFromListText.split(": ")[1].trim();
        String secondStage = firstStage.split("Pozdrawiamy")[0].trim();
        String code = secondStage;
        System.out.println(code);
        return code;
    }

}


