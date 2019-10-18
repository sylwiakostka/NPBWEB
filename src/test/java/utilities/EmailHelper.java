package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

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

    @FindBy(xpath = "//*[@class='zF']")
    List<WebElement> unreadEmails;

    @FindBy(xpath = "//table[@role='presentation']//div[@role='listitem']")
    WebElement mailContent;

    @FindBy(xpath = "//div[@id=':4']//div[@aria-label='Usuń']")
    WebElement deleteEmailButton;

    @FindBy(partialLinkText = "Zaloguj się")
    WebElement gmailLoginButton;


    public String openMail_and_readCode() {
        driver.get("https://mail.google.com/mail/u/0/?tab=km#inbox");
        gmailLoginButton.click();
        waitForPresenceOfElement(emailField);
        emailField.sendKeys(EMAIL);
        waitForPresenceOfElement(nextButton);
        nextButton.click();
        passwordField.clear();
        passwordField.sendKeys(PASSWORD);
        waitForPresenceOfElement(nextButton);
        nextButton.click();
        waitForPresenceOfElement(messageBody);

        waitForPresenceOfElements(unreadEmails);


        String emailTitle = "itaxi";


        for (int i = 0; i < unreadEmails.size(); i++) {
            if (unreadEmails.get(i).getText().equals(emailTitle)) {
                System.out.println("Yes we have got mail form " + emailTitle);
                unreadEmails.get(i).click();
                break;
            } else {
                System.out.println("No mail form " + emailTitle);
            }
        }

        String emailText = mailContent.getText();
        String firstStage = emailText.split(": ")[1].trim();
        String secondStage = firstStage.split("Odpowiedz")[0].trim();
        String code = secondStage;
        System.out.println(code);
        return code;
    }

}


