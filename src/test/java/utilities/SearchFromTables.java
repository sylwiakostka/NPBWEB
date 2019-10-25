package utilities;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pages.BasePage;

import java.util.ArrayList;
import java.util.List;

public class SearchFromTables extends BasePage {

    public SearchFromTables(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='rt-noData']")
    private WebElement noResultInfo;


    public void sort_data_by(String dataToSearch, List<WebElement> listOfRowsRequired, WebElement fieldToSearch) throws InterruptedException {

        fieldToSearch.sendKeys(dataToSearch);

        List<String> listWithNames = new ArrayList<String>();

        try {
            for (WebElement row : listOfRowsRequired) {
                listWithNames.add(row.getText());
            }
        } catch (org.openqa.selenium.StaleElementReferenceException ex) {
        }

        System.out.println("list size :" + listWithNames.size());

        if (listWithNames.size() > 0) {
            for (String element : listWithNames) {
                System.out.println("List to compare: " + listWithNames);
                Assert.assertTrue(element.contains(dataToSearch));
            }

        } else {
            waitForPresenceOfElement(noResultInfo);
            Assert.assertEquals(noResultInfo.getText(), "Brak wyników!");
            System.out.println("Brak wyników!");
        }

        Thread.sleep(2000);
        while (fieldToSearch.getAttribute("value").length() > 0) {
            fieldToSearch.sendKeys(Keys.BACK_SPACE);
        }

    }

    public void sort_selecting_from_list(String dataToSelect, List<WebElement> rowsWithData, WebElement selectList) throws InterruptedException {

        Select select = new Select(selectList);
        Assert.assertFalse(select.isMultiple());
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "Pokaż wszystkie");
        select.selectByVisibleText(dataToSelect);

        List<String> listWithDays = new ArrayList<String>();

        try {
            for (WebElement row : rowsWithData) {
                listWithDays.add(row.getText());
            }
        } catch (org.openqa.selenium.StaleElementReferenceException ex) {
        }

        System.out.println("list size :" + listWithDays.size());

        if (listWithDays.size() > 0) {
            for (String element : listWithDays) {
                System.out.println("List to compare: " + listWithDays);
                Assert.assertEquals(element, dataToSelect);
            }
        } else {
            waitForPresenceOfElement(noResultInfo);
            Assert.assertEquals(noResultInfo.getText(), "Brak wyników!");
            System.out.println("Brak wyników!");
        }
        try {
            select.selectByValue("all");

        } catch (org.openqa.selenium.StaleElementReferenceException ex) {

        }

    }
}


