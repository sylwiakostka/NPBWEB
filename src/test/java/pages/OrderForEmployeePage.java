package pages;

import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import utilities.DataManipulations;
import utilities.JsHelper;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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

    @FindBy(xpath = "//button[@class='btn yellow small']/span[.='Wybierz']")
    private WebElement orderButton;

    @FindBy(xpath = "//div[@class='password-modal success-order']")
    private WebElement confirmationOrderPopup;

    @FindBy(xpath = "//button[@class='btn yellow small']/span[.='OK']")
    private WebElement confirmationOrderPopupButton;

    @FindBy(xpath = "//ul[@class='summary-list']/li")
    private List<WebElement> listOfOrderedTaxis;

    @FindBy(xpath = "//label[@for='date1']")
    private WebElement time_now_radio;

    @FindBy(id = "date1")
    private WebElement timeNow_ifIsChecked;

    @FindBy(xpath = "//input[@class='react-datepicker-ignore-onclickoutside']")
    private WebElement futureOrderTimeField;

    @FindBy(xpath = "//div[@class='react-datepicker__input-container']/input")
    private WebElement futureOrderTimeFieldToGetValue;

    @FindBy(name = "fellowPassenger")
    private WebElement addPassengersSelectList;

    @FindBy(className = "add")
    private WebElement addNextTaxi;

    @FindBy(className = "destination-map")
    private WebElement map;

    @FindBy(xpath = "//div[@class='notification red notification-enter-done']//p[.='Brak imienia i nazwiska klienta']")
    private WebElement lackOfPassengerNameNotification;

    @FindBy(xpath = "//div[@class='notification red notification-enter-done']//p[.='Brak miasta w adresie początkowym']")
    private WebElement lackOfCityInAddress;

    @FindBy(xpath = "//div[@class='notification red notification-enter-done']//p[.='Brak ulicy w adresie początkowym']")
    private WebElement lackOfStreetInAddress;

    @FindBy(xpath = "//div[@class='notification red notification-enter-done']//p[.='Brak współrzędnych w adresie początkowym']")
    private WebElement lackOfCoordinatesInAddress;

    @FindBy(xpath = "//div[@class='notifications']//p[.='Brak numeru budunku w adresie początkowym']")
    private WebElement lackOfBuildingNumberInAddress;

    @FindBy(className = "access")
    private WebElement infoAboutWriteUs;

    @FindBy (className = "more")
    private WebElement moreOptionsButton;

    @Step
    public OrderForEmployeePage verifyOrderForEmployeePage() {

        WebElement paymentType_business_ElemWithText = paymentType_business_selectClass.findElement(By.xpath("//label[@for='payBy-0']"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        String paymentType_business_text = (String) js.executeScript("return arguments[0].innerText", paymentType_business_ElemWithText);

        waitForPresenceOfElement(paymentType_business_ElemWithText);
        waitForVisibilityOfElement(map);
        Assert.assertEquals(paymentType_business_text, "Płatność biznesowa");
        Assert.assertTrue(map.isDisplayed());
        Assert.assertEquals(infoAboutWriteUs.getText(), "W przypadku problemów bądź wątpliwości, napisz do nas: cob@itaxi.pl");
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
    public OrderForEmployeePage setIncorrectPassengerNameAndDelete(String incorrectPassengerName) {
        passengerNameField.sendKeys(incorrectPassengerName);
        passengerNameField.click();
        WebElement employeeList = driver.findElement(By.xpath("//ul[@class='open']/p"));
        Assert.assertEquals(employeeList.getText(), "Brak wyników!");
        while (passengerNameField.getAttribute("value").length() > 0) {
            passengerNameField.sendKeys(Keys.BACK_SPACE);
        }
        return this;
    }

    @Step
    public OrderForEmployeePage setIncorrectStartAddressAndDelete(String incorrectStartAddress) {
        startAddressField.sendKeys(incorrectStartAddress);
        startAddressField.click();
        WebElement addressList = driver.findElement(By.xpath("//ul[@class='open']/p"));
        waitForVisibilityOfElement(addressList);
        Assert.assertEquals(addressList.getText(), "Brak wyników!");
        while (startAddressField.getAttribute("value").length() > 0) {
            startAddressField.sendKeys(Keys.BACK_SPACE);
        }
        return this;

    }

    @Step
    public OrderForEmployeePage setIncorrectFinalAddressAndDelete(String incorrectFinalAddress) {
        finalAddressField.sendKeys(incorrectFinalAddress);
        waitForElementToBeClickable(finalAddressField);
        finalAddressField.click();
        WebElement addressList = driver.findElement(By.xpath("//ul[@class='open']/p"));
        waitForVisibilityOfElement(addressList);
        Assert.assertEquals(addressList.getText(), "Brak wyników!");
        while (finalAddressField.getAttribute("value").length() > 0) {
            finalAddressField.sendKeys(Keys.BACK_SPACE);
        }
        return this;
    }


    @Step
    public OrderForEmployeePage setStartAddress(String startAddress) throws InterruptedException {
        Thread.sleep(2000);
        while (startAddressField.getAttribute("value").length() > 0) {
            startAddressField.sendKeys(Keys.BACK_SPACE);
        }
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
        finalAddressField.click();
        finalAddressField.sendKeys(finalAddress);
        finalAddressField.click();
        findElementFromUlListByTextAndClick(finalAddress);
        Assert.assertEquals(finalAddressField.getAttribute("value"), finalAddress);
        return this;
    }

    @Step
    public OrderForEmployeePage selectOrderTime_now() {
        new JsHelper(driver).scrollDownPage();
        time_now_radio.click();
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
        String isTimeNowSelected = timeNow_ifIsChecked.getAttribute("checked");
        System.out.println(isTimeNowSelected);
        String orderTimeText;

        if (isTimeNowSelected != null && isTimeNowSelected.equals("true")) {
            WebElement time_now_elem_with_text = driver.findElement(By.xpath("//label[@for='date1']"));
            orderTimeText = time_now_elem_with_text.getText();
        } else {
            new JsHelper(driver).scrollDownPage();
            orderTimeText = futureOrderTimeFieldToGetValue.getAttribute("value");
        }
        System.out.println(orderTimeText);
        return orderTimeText;
    }

    @Step
    public OrderForEmployeePage setProject_firstFromList() throws InterruptedException {
        waitForElementToBeClickable(projectField);
        projectField.click();
        WebElement firstProjectFromList = driver.findElement(By.xpath("//ul[@class='open']/li"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String firstProjectFromListName = (String) js.executeScript("return arguments[0].innerText", firstProjectFromList);
        firstProjectFromList.click();
        Thread.sleep(1000);
        waitForTextToBePresentInElementValue(projectField, firstProjectFromListName);
        String firstProjectNameSelected = projectField.getAttribute("value");
        Assert.assertEquals(firstProjectNameSelected, firstProjectFromListName);
        return this;
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
    public OrderForEmployeePage clickOrderButton() throws InterruptedException {
        Thread.sleep(1000);
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

        List<String> expectedTexts = Arrays.asList(getNumberOfTaxisOrdered(), getPassengerName(), getStartAddress(), getFinalAddress(), getOrderTime(), getComment());
        List<String> actualTexts = new ArrayList<String>();

        waitForPresenceOfElement(confirmationOrderPopup);
        WebElement header = confirmationOrderPopup.findElement(By.tagName("h3"));
        Assert.assertEquals(header.getText(), "Zamówienie zakończone powodzeniem!");


        List<WebElement> popupInfosFromOrder = confirmationOrderPopup.findElements(By.xpath("//ul/li/strong"));
        for (WebElement info : popupInfosFromOrder) {
            actualTexts.add(info.getText());
        }

        System.out.println("Expected: " + expectedTexts);
        System.out.println("Actual: " + actualTexts);

        Assert.assertEquals(actualTexts.toString(), expectedTexts.toString());
        return this;
    }

    @Step
    public OrderForEmployeePage verifyConfirmationOrderPopupWithOnlyRequiredInformation() {
        List<String> expectedTexts = Arrays.asList(getNumberOfTaxisOrdered(), getPassengerName(), getStartAddress(), getOrderTime());
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
    public OrderForEmployeePage acceptConfirmationOrderPopupAndVerifyEmptyFieldsOnPageAfterOrder() {
        confirmationOrderPopupButton.click();
        Assert.assertTrue(passengerNameField.getText().isEmpty());
        Assert.assertTrue(startAddressField.getText().isEmpty());
        return this;
    }


    @Step
    public OrderForEmployeePage addPassengers(int amountOfPassengers) {
        Select amountOfPassengersList = new Select(addPassengersSelectList);
        if (amountOfPassengers >= 3) {
            amountOfPassengersList.selectByVisibleText("3");
        } else {
            String amountOfPassengerString = Integer.toString(amountOfPassengers);
            amountOfPassengersList.selectByVisibleText(amountOfPassengerString);
        }
        return this;
    }

    @Step
    public OrderForEmployeePage orderMoreThanOneTaxi(List<String> employees) throws InterruptedException {

        int listSize = employees.size();

        List<String> passengerNames = new ArrayList<>();
        for (int i = 0; i < listSize; i++) {
            passengerNames.add(employees.get(i));
        }
        System.out.println(passengerNames);

        for (int i = 0; i < listSize; i++) {
            if (i < listSize - 1) {
                addNextTaxi.click();
                List<WebElement> passengerNameFields = driver.findElements(By.id("passengerName"));
                WebElement fieldToFill = passengerNameFields.get(i);

                fieldToFill.click();
                fieldToFill.click();
                Thread.sleep(1000);

                findElementFromUlListByTextAndClick(employees.get(i));
                Thread.sleep(1000);

                Assert.assertEquals(fieldToFill.getAttribute("value"), employees.get(i));

            } else {
                List<WebElement> passengerNameFields = driver.findElements(By.id("passengerName"));
                WebElement fieldToFill = passengerNameFields.get(i);

                fieldToFill.click();
                fieldToFill.click();
                Thread.sleep(1000);

                findElementFromUlListByTextAndClick(employees.get(i));
                Thread.sleep(1000);

                Assert.assertEquals(fieldToFill.getAttribute("value"), employees.get(i));
            }
        }
        return this;
    }

    @Step
    public List<String> getPassengerNames() {
        List<String> passengerNames = new ArrayList<>();
        List<WebElement> passengerNameFields = driver.findElements(By.id("passengerName"));
        for (WebElement passengerName : passengerNameFields) {
            String text = passengerName.getAttribute("value");
            passengerNames.add(text);

        }
        return passengerNames;
    }


    @Step
    public OrderForEmployeePage verifyConfirmationOrderPopupWithOnlyRequiredInformationAndMoreThanOneTaxi() {
        List<String> expectedTexts = new ArrayList<>();
        expectedTexts.add(getNumberOfTaxisOrdered());
        for (int i = 0; i < getPassengerNames().size(); i++) {
            expectedTexts.add(getPassengerNames().get(i));
        }

        expectedTexts.add(getStartAddress());
        expectedTexts.add(getOrderTime());
        System.out.println("Expected: " + expectedTexts);

        List<String> actualTexts = new ArrayList<String>();

        waitForPresenceOfElement(confirmationOrderPopup);
        WebElement header = confirmationOrderPopup.findElement(By.tagName("h3"));
        Assert.assertEquals(header.getText(), "Zamówienie zakończone powodzeniem!");

        List<WebElement> popupInfosFromOrder = confirmationOrderPopup.findElements(By.xpath("//ul/li/strong"));
        for (WebElement info : popupInfosFromOrder) {
            actualTexts.add(info.getText());
        }
        System.out.println("Actual: " + actualTexts);

        return this;
    }

    @Step
    public OrderForEmployeePage verifyNotificationLackOfPassengerName() {
        waitForPresenceOfElement(lackOfPassengerNameNotification);
        Assert.assertTrue(lackOfPassengerNameNotification.isDisplayed());
        return this;
    }

    @Step
    public OrderForEmployeePage verifyNotificationLackOfCityInAddress() {
        waitForPresenceOfElement(lackOfCityInAddress);
        Assert.assertTrue(lackOfCityInAddress.isDisplayed());
        return this;
    }

    @Step
    public OrderForEmployeePage verifyNotificationLackOfStreetInAddress() {
        waitForPresenceOfElement(lackOfStreetInAddress);
        Assert.assertTrue(lackOfStreetInAddress.isDisplayed());
        return this;
    }

    @Step
    public OrderForEmployeePage verifyNotificationLackOfCoordinates() {
        waitForPresenceOfElement(lackOfCoordinatesInAddress);
        Assert.assertTrue(lackOfCoordinatesInAddress.isDisplayed());
        return this;
    }

    @Step
    public OrderForEmployeePage verifyNotificationLackOfBuildingNumberInAddress() {
        waitForPresenceOfElement(lackOfBuildingNumberInAddress);
        Assert.assertTrue(lackOfBuildingNumberInAddress.isDisplayed());
        return this;
    }

    @Step
    public OrderForEmployeePage clickManyTimesOrderButtonAndCantOrder_noRequiredInformation(int howManyClick) {
        for (int i = 0; i < howManyClick; i++) {
            orderButton.click();
        }

        List<WebElement> notifications = driver.findElements(By.className("notifications"));
        waitForVisibilityOfElements(notifications);
        Assert.assertTrue(notifications.size() > 0);
        return this;
    }

    @Step
    public OrderForEmployeePage deleteTextFromCommentTextArea() {
        while (commentTextArea.getText().length() > 0) {
            commentTextArea.sendKeys(Keys.BACK_SPACE);
        }
        return this;
    }

    @Step
    public MoreOptionsPage openMoreOptionsAndVerifyButtonText(){
        Assert.assertEquals(moreOptionsButton.getText(),"Więcej opcji");
        moreOptionsButton.click();
        return new MoreOptionsPage(driver);
    }

}
