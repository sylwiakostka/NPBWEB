package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;


public class EmployeesPage extends BasePage {
    public EmployeesPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h3[contains(text(),'Zarządzanie użytkownikami - firma')]")
    private WebElement header;

    @Step
    public EmployeesPage verifyEmployeesPage() throws InterruptedException {
        Thread.sleep(1000);
        waitForVisibilityOfElement(header);
        Assert.assertTrue(header.isDisplayed());
        Assert.assertEquals(header.getText(), "Zarządzanie użytkownikami - firma ABC");
        return this;
    }

    @Step
    public String getMpkInUse() {
        waitForVisibilityOfElement(driver.findElement(By.xpath("//div[@class='rt-table']")));
        List<WebElement> rows = driver.findElements(By.xpath("//div[@class='rt-tr-group']//div[@class='rt-td']/following-sibling::div[6]/span"));
        List<String> list = new ArrayList<String>();
        for (WebElement row : rows) {
            try {
                list.add(row.getText());
            } catch (StaleElementReferenceException ex) {
                list.add(row.getText());
            }
        }
        int listSize = list.size();
        String mpk = null;
        if (listSize > 0) {
            mpk = list.get(listSize - 1);
        } else System.out.println("Nie ma mpk z pracownikami");
        System.out.println("mpk in use:" + mpk);
        return mpk;
    }

    @Step
    public List<String> getListOfMpkInUse() {
        waitForVisibilityOfElement(driver.findElement(By.xpath("//div[@class='rt-table']")));
        List<WebElement> rows = driver.findElements(By.xpath("//div[@class='rt-tr-group']//div[@class='rt-td']/following-sibling::div[6]/span"));
        List<String> list = new ArrayList<String>();
        for (WebElement row : rows) {
            try {
                list.add(row.getText());
            } catch (StaleElementReferenceException ex) {
                list.add(row.getText());
            }
        }
        return list;
    }

}
