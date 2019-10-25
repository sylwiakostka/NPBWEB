package pages;

import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import utilities.DataManipulations;
import utilities.JsHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class OrderForEmployeePage extends BasePage {
    public OrderForEmployeePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "payBy-0")
    private WebElement paymentType_business_selectClass;

    @FindBy(id = "passengerName")
    private WebElement passengerNameField;

    @FindBy(id = "buildingNumber")
    private WebElement startAddressField;

    @FindBy(xpath = "//div[@class='single']//div[@class='reset']")
    private WebElement resetPassengerNameButton;

    @FindBy(xpath = "//div[@class='destination-basic']//div[@class='input']//input")
    private WebElement finalAddressField;

    @FindBy(className = "location")
    private WebElement locationButton;

    @FindBy(xpath = "//div[@class='destination-basic']//div[@class='input']//div[@class='reset']")
    private WebElement resetFinalAddressButton;

    @FindBy(xpath = "//div[@class='input start']//div[@class='reset']")
    private WebElement resetStartAddressButton;

    @FindBy(xpath = "//div[@class='project']//input[@type = 'search']")
    private WebElement projectField;

    @FindBy(name = "orderComment")
    private WebElement commentTextArea;

    @FindBy(xpath = "//div[@class='notifications']//p[.='Komentarz do zlecenia zbyt długi, maksymalna długość to 450 znaków']")
    private WebElement notificationsAboutTooLongComment;

    @FindBy(xpath = "//button[@class='btn yellow']/span[.='Zamawiam']")
    private WebElement orderButton;

    @FindBy(xpath = "//div[@class='password-modal success-order']")
    private WebElement confirmationOrderPopup;

    @FindBy(xpath = "//button[@class='btn yellow small']/span[.='OK']")
    private WebElement confirmationOrderPopupButton;

    @FindBy(xpath = "//ul[@class='summary-list']/li")
    private List<WebElement> listOfOrderedTaxis;

    @FindBy(id = "date1")
    private WebElement time_now;

    @FindBy(xpath = "//input[@class='react-datepicker-ignore-onclickoutside']")
    private WebElement futureOrderTimeField;


    @Step
    public OrderForEmployeePage verifyOrderForEmployeePage() {

        WebElement paymentType_business_ElemWithText = paymentType_business_selectClass.findElement(By.xpath("//label[@for='payBy-0']"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        String paymentType_business_text = (String) js.executeScript("return arguments[0].innerText", paymentType_business_ElemWithText);

        waitForPresenceOfElement(paymentType_business_ElemWithText);
        System.out.println(paymentType_business_ElemWithText);
        Assert.assertEquals(paymentType_business_text, "Płatność biznesowa");
        return this;
    }

    @Step
    public OrderForEmployeePage verifyOrderTaxiPageLabelNames() {
        List<String> expectedTexts = Arrays.asList("*Zamawiam taksówkę dla:", "Typ płatności:", "Płatność biznesowa", "Ilu pasażerów pojedzie z tą osobą:", "*Adres startowy:", "Adres docelowy:", "Na teraz!", "Na potem: ", "Projekt:", "Dodaj komentarz:");
        List<String> actualTexts = new ArrayList<String>();

        List<WebElement> labels = driver.findElements(By.xpath("//div[@class='input']//label | //div[@class='input start']//label"));
        for (WebElement label : labels) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            String text = (String) js.executeScript("return arguments[0].innerText", label);
            actualTexts.add(text);
        }
        System.out.println(actualTexts);
        Assert.assertEquals(expectedTexts.toString(), actualTexts.toString());
        return this;
    }

    @Step
    public OrderForEmployeePage setPassengerName(String passengerName) throws InterruptedException {
        passengerNameField.click();
        findElementFromUlListByTextAndClick(passengerName);
        Thread.sleep(1000);
        Assert.assertEquals(passengerNameField.getAttribute("value"), passengerName);
        return this;
    }

    @Step
    public String getPassengerName() {
        String passengerNameSelected = passengerNameField.getAttribute("value");
        return passengerNameSelected;
    }


    @Step
    public OrderForEmployeePage setIncorrectPassengerNameAndDelete(String passengerName) {
        passengerNameField.sendKeys(passengerName);
        passengerNameField.click();
        WebElement employeeList = driver.findElement(By.xpath("//ul[@class='open']/p"));
        Assert.assertEquals(employeeList.getText(), "Brak wyników!");
        passengerNameField.clear();
        return this;
    }

    @Step
    public OrderForEmployeePage setStartAddress(String startAddress) {
        startAddressField.sendKeys(startAddress);
        startAddressField.click();
        findElementFromUlListByTextAndClick(startAddress);
        Assert.assertEquals(startAddressField.getAttribute("value"), startAddress);
        return this;
    }

    @Step
    public OrderForEmployeePage setStartAddressByGeolocation() {
        locationButton.click();
        Assert.assertNotEquals(startAddressField.getAttribute("value"), null);
        return this;
    }

    @Step
    public String getStartAddress() {
        String startAddress = startAddressField.getAttribute("value");
        return startAddress;
    }

    @Step
    public String getFinalAddress() {
        String finalAddress = finalAddressField.getAttribute("value");
        return finalAddress;
    }


    @Step
    public OrderForEmployeePage setFinalAddress(String finalAddress) throws InterruptedException {
        Thread.sleep(2000);
        finalAddressField.sendKeys(finalAddress);
        finalAddressField.click();
        findElementFromUlListByTextAndClick(finalAddress);
        Assert.assertEquals(finalAddressField.getAttribute("value"), finalAddress);
        return this;
    }

    @Step
    public OrderForEmployeePage selectOrderTime_now() {
        new JsHelper(driver).scrollDownPage();
        if (!time_now.isSelected()) {
            time_now.click();
        }
        return this;
    }


    @Step
    public OrderForEmployeePage selectOrderTime_future_add_hours_from_now(int amountOfHours) {
        int indexMin = amountOfHours * 60 - 30;
        int x = indexMin / 10;
        new JsHelper(driver).scrollDownPage();
        WebElement time_future = driver.findElement(By.xpath("//label[@for='date2']"));
        time_future.click();
        WebElement calendar_hour = driver.findElement(By.xpath("//li[@class='react-datepicker__time-list-item']" + "[" + x + "]"));
        System.out.println(calendar_hour.getText());
        calendar_hour.click();
        return this;
    }

    @Step
    public OrderForEmployeePage selectOrderTime_future_add_minutes_from_now(int amountOfMinutes) {

        new JsHelper(driver).scrollDownPage();
        WebElement time_future = driver.findElement(By.xpath("//label[@for='date2']"));
        time_future.click();

        int indexMin = amountOfMinutes - 30;
        int x;
        if (indexMin > 30) {
            x = Math.round(indexMin / 10);
        } else {
            x = 1;
        }

        WebElement calendar_hour = driver.findElement(By.xpath("//li[@class='react-datepicker__time-list-item']" + "[" + x + "]"));
        System.out.println(calendar_hour.getText());
        calendar_hour.click();
        return this;
    }

    @Step
    public String getOrderTime() {
        String isTimeNowSelected = time_now.getAttribute("checked");
        String orderTimeText = null;
        if (isTimeNowSelected.equals("true")) {
            WebElement time_now_elem_with_text = driver.findElement(By.xpath("//label[@for='date1']"));
            orderTimeText = time_now_elem_with_text.getText();

        } else if (isTimeNowSelected.equals("false")) {
            new JsHelper(driver).scrollDownPage();
            orderTimeText = futureOrderTimeField.getText();
        }
        System.out.println(orderTimeText);
        return orderTimeText;
    }

    @Step
    public OrderForEmployeePage setProject_firstFromList() throws InterruptedException {
        projectField.click();
        WebElement firstProjectFromList = driver.findElement(By.xpath("//ul[@class='open']/li"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String firstProjectFromListName = (String) js.executeScript("return arguments[0].innerText", firstProjectFromList);
        firstProjectFromList.click();
        waitForTextToBePresentInElementValue(projectField, firstProjectFromListName);
        Thread.sleep(1000);
        String firstProjectNameSelected = projectField.getAttribute("value");
        Assert.assertEquals(firstProjectNameSelected, firstProjectFromListName);
        return this;
    }

    @Step
    public String getProjectName() {
        String projectNameSelected = projectField.getAttribute("value");
        return projectNameSelected;
    }

    @Step
    public OrderForEmployeePage addComment(String comment) {
        commentTextArea.sendKeys(comment);
        String commentAdded = commentTextArea.getText();
        Assert.assertEquals(commentAdded, comment);
        return this;
    }

    @Step
    public OrderForEmployeePage addTooLongComment() {
        int minChar = 451;
        int maxChar = 600;
        String generatedString = RandomStringUtils.randomAlphabetic(minChar, maxChar);
        commentTextArea.sendKeys(generatedString);
        return this;
    }

    @Step
    public String getComment() {
        String commentText = commentTextArea.getText();
        return commentText;
    }

    @Step
    public OrderForEmployeePage verifyNotificationsAboutTooLongComment() {
        waitForPresenceOfElement(notificationsAboutTooLongComment);
        Assert.assertTrue(notificationsAboutTooLongComment.isDisplayed());
        return this;
    }

    @Step
    public OrderForEmployeePage clickOrderButton() {
        orderButton.click();
        return this;
    }

    @Step
    public String getNumberOfTaxisOrdered() {
        int numberOfTaxisOrdered = listOfOrderedTaxis.size();
        String numberOfTaxisOrderedAsString = Integer.toString(numberOfTaxisOrdered);
        return numberOfTaxisOrderedAsString;
    }

    @Step
    public OrderForEmployeePage scrollDownPage() {
        new JsHelper(driver).scrollDownPage();
        return this;
    }

    @Step
    public OrderForEmployeePage verifyConfirmationOrderPopupWithAllInformation() {

        List<String> expectedTexts = Arrays.asList(getNumberOfTaxisOrdered(), getPassengerName(), getStartAddress(),getFinalAddress(), getOrderTime(), getComment());
        List<String> actualTexts = new ArrayList<String>();

        waitForPresenceOfElement(confirmationOrderPopup);
        WebElement header = confirmationOrderPopup.findElement(By.tagName("h3"));
        Assert.assertEquals(header.getText(), "Zamówienie zakończone powodzeniem!");


        List<WebElement> popupInfosFromOrder = confirmationOrderPopup.findElements(By.xpath("//ul/li/strong"));
        for (WebElement info : popupInfosFromOrder) {
            actualTexts.add(info.getText());
        }
        Assert.assertEquals(expectedTexts.toString(), actualTexts.toString());
        return this;
    }
    @Step
    public OrderForEmployeePage acceptConfirmationOrderPopupAndVerifyPage(){

        return this;
    }

}
