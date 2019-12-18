package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class OrderedTaxiPage extends BasePage {
    public OrderedTaxiPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(tagName = "h2")
    private WebElement header;

    @FindBy(xpath = "//ul[@class='ordered-list']/li")
    private List<WebElement> orderList;

    @FindBy(xpath = "//ul[@class='ordered-list']/p/strong")
    private WebElement emptyOrderListInfo;

    @FindBy(xpath = "//div[@class='who']//span[@class='name']")
    private List<WebElement> passengerNameList;

    @FindBy(xpath = "/p[@class='status']/following-sibling::p[contains(text(),'Adres startowy')]")
    private List<WebElement> startAddressesList;

    @FindBy(xpath = "/p[@class='status']/following-sibling::p[contains(text(),'Adres docelowy')]")
    private List<WebElement> finalAddressesList;

    @FindBy(xpath = "/p[@class='status']/following-sibling::p[contains(text(),'Projekt')]")
    private List<WebElement> projectList;

    @FindBy(xpath = "//div[@class='data-inner']/h4")
    private List<WebElement> orderTimeList;

    @FindBy(xpath = "//div[@class='search']//input")
    private WebElement searchField;

    @FindBy(xpath = "//button[@class='btn yellow small']/span[.='Odwołaj!']")
    private WebElement cancelOrderButton;

    @FindBy(xpath = "//div[@class='password-modal']//h2")
    private WebElement cancelOrderConfirmationPopup;

    @FindBy(xpath = "//div[@class='password-modal']//span[.='TAK']")
    private WebElement confirmCancelationButton;

    @FindBy(xpath = "//div[@class='password-modal']//span[.='NIE']")
    private WebElement denyCancelationButton;

    @FindBy(xpath = "//div[@class='notification green notification-enter-done']//p[.='Anulowano przejazd']")
    private WebElement notificationOrderCanceled;


    @Step
    public OrderedTaxiPage verifyOrderedTaxiPage() {
        waitForVisibilityOfElement(header);
        Assert.assertEquals("Zamówione taksówki", header.getText());
        return this;
    }

    @Step
    public OrderedTaxiPage verifyIfOrderedTaxiSignIsPresentOnButton() {
        WebElement elementWithPulseIcon = driver.findElement(By.xpath("//a[@href='/ordered']//div[@class='img']/div"));
        waitForAtributeOfElement(elementWithPulseIcon, "class", "pulse");
        String elementClassName = elementWithPulseIcon.getAttribute("class");
        Assert.assertEquals(elementClassName, "pulse");
        return this;
    }

    @Step
    public OrderedTaxiPage verifyIfOrderedTaxiSignIsAbsenteOnButton() {
        WebElement elementWithPulseIcon = driver.findElement(By.xpath("//a[@href='/ordered']//div[@class='img']/div"));
        String elementClassName = elementWithPulseIcon.getAttribute("class");
        Assert.assertEquals(elementClassName, "");
        return this;
    }

    @Step
    private boolean verifyIfOrderIsPresent_future(String passengerName, String startAddress, String orderTime, String projectName, String finalAddress,String comment, String isLargeTruncSelected, String carClass) {
        waitForVisibilityOfElements(orderList);

        for (WebElement order : orderList) {
            if (order.getText().contains(passengerName) && order.getText().contains(startAddress) && order.getText().contains(prepareOrderTimeToCompareInListDetails_future(orderTime)) && order.getText().contains(projectName)
                    && order.getText().contains(finalAddress)&& order.getText().contains(comment) && order.getText().contains(isLargeTruncSelected) && order.getText().contains(carClass))
                System.out.println("order: "+order);
            {
                return true;
            }
        }
        System.out.println("nie ma zlecenia");
        return false;
    }

    @Step
    private boolean verifyIfOrderIsPresent_now(String passengerName, String startAddress, String orderTime, String projectName, String finalAddress, String comment, String isLargeTruncSelected, String carClass) {
        waitForVisibilityOfElements(orderList);
        for (WebElement order : orderList) {
            System.out.println(order.getText());
            if (order.getText().contains(passengerName) && order.getText().contains(startAddress) && order.getText().contains(prepareOrderTimeToCompareInListDetails_now(orderTime)) && order.getText().contains(projectName) && order.getText().contains(finalAddress)
                    && order.getText().contains(comment) && order.getText().contains(isLargeTruncSelected) && order.getText().contains(carClass) ) {
                return true;
            }
        }
        System.out.println("nie ma zlecenia");
        return false;
    }

    @Step
    public OrderedTaxiPage chooseFutureOrderAndClickCancelButton(String passengerName, String startAddress, String orderTime, String projectName, String finalAddress) {
        for (WebElement order : orderList) {
            if (order.getText().contains(passengerName) && order.getText().contains(startAddress) && order.getText().contains(prepareOrderTimeToCompareInListDetails_future(orderTime)) && order.getText().contains(projectName) && order.getText().contains(finalAddress)) {
                order.findElement(By.xpath("//button[@class='btn yellow small']/span[.='Odwołaj!']")).click();
            } else {
                System.out.println("brak zlecenia");
            }
        }
        return this;
    }

    @Step
    public OrderedTaxiPage chooseOrderForNowAnClickCancelButton(String passengerName, String startAddress, String orderTime, String projectName, String finalAddress) {
        for (WebElement order : orderList) {
            if (order.getText().contains(passengerName) && order.getText().contains(startAddress) && order.getText().contains(prepareOrderTimeToCompareInListDetails_now(orderTime)) && order.getText().contains(projectName) && order.getText().contains(finalAddress)) {
                order.findElement(By.xpath("//button[@class='btn yellow small']/span[.='Odwołaj!']")).click();
            } else {
                System.out.println("brak zlecenia");
            }
        }
        return this;
    }

    @Step
    public OrderedTaxiPage confirmOrderCancel() {
        Assert.assertEquals(cancelOrderConfirmationPopup.getText(), "Czy na pewno chcesz anulować przejazd?");
        confirmCancelationButton.click();
        waitForVisibilityOfElement(notificationOrderCanceled);
        Assert.assertTrue(notificationOrderCanceled.isDisplayed());
        return this;
    }

    @Step
    public OrderedTaxiPage denyOrderCancel() {
        Assert.assertEquals(cancelOrderConfirmationPopup.getText(), "Czy na pewno chcesz anulować przejazd?");
        denyCancelationButton.click();
        return this;
    }


    @Step
    public OrderedTaxiPage confirmForNowOrderIsPresent(String passengerName, String startAddress, String orderTime, String projectName, String finalAddress, String comment, String isLargTruncSelected, String carClass) {
        Assert.assertTrue(verifyIfOrderIsPresent_now(passengerName, startAddress, orderTime, projectName, finalAddress,comment, isLargTruncSelected, carClass));
        return this;
    }

    @Step
    public OrderedTaxiPage confirmFutureOrderIsPresent(String passengerName, String startAddress, String orderTime, String projectName, String finalAddress,String comment, String isLargTruncSelected, String carClass) {
        Assert.assertTrue(verifyIfOrderIsPresent_future(passengerName, startAddress, orderTime, projectName, finalAddress,comment, isLargTruncSelected, carClass));
        return this;
    }

    @Step
    private String prepareOrderTimeToCompareInListDetails_future(String orderTime) {

        String dataOrderWithoutComma = orderTime.replaceAll(",", "");

        StringBuilder reversedNameBuilder = new StringBuilder();
        StringBuilder subNameBuilder = new StringBuilder();

        for (int i = 0; i < dataOrderWithoutComma.length(); i++) {
            char currentChar = dataOrderWithoutComma.charAt(i);
            if (currentChar != ' ') {
                subNameBuilder.append(currentChar);
            } else {
                reversedNameBuilder.insert(0, currentChar + subNameBuilder.toString());
                subNameBuilder.setLength(0);
            }
        }

        String changedDataFromOrder = reversedNameBuilder.insert(0, subNameBuilder.toString()).toString();
        return changedDataFromOrder.replaceAll(" ", "");
    }

    @Step
    private String prepareOrderTimeToCompareInListDetails_now(String orderTime) {
        return orderTime.split("!")[0].trim().toUpperCase();
    }

    @Step
    public OrderedTaxiPage searchByPassengerName(String passengerName) throws InterruptedException {
        waitForVisibilityOfElement(searchField);
        searchField.sendKeys(passengerName);
        Thread.sleep(2000);
        for (WebElement name : passengerNameList) {
            Assert.assertTrue(name.getText().contains(passengerName));
        }
        return this;
    }

    @Step
    public OrderedTaxiPage searchByStartAddress(String startAddress) throws InterruptedException {
        searchField.sendKeys(startAddress);
        Thread.sleep(2000);
        for (WebElement address : startAddressesList) {
            Assert.assertTrue(address.getText().contains(startAddress));
        }
        return this;
    }

    @Step
    public OrderedTaxiPage clearSearchField() {
        while (searchField.getAttribute("value").length() > 0) {
            searchField.sendKeys(Keys.BACK_SPACE);
        }
        return this;
    }

    @Step
    public OrderedTaxiPage searchNotExistingOrderValue(String notExistingOrderValue) throws InterruptedException {
        searchField.sendKeys(notExistingOrderValue);
        Thread.sleep(2000);
        Assert.assertEquals(emptyOrderListInfo.getText(), "Brak wyników!");
        return this;
    }

    @Step
    public OrderForEmployeePage chooseOrderAndClickEditOrderButton(String passengerName, String startAddress, String orderTime, String projectName, String finalAddress) {
        waitForVisibilityOfElements(orderList);
        try {
            for (WebElement order : orderList) {
                if (order.getText().contains(passengerName) && order.getText().contains(startAddress) && order.getText().contains(prepareOrderTimeToCompareInListDetails_future(orderTime)) && order.getText().contains(projectName) && order.getText().contains(finalAddress)) {
                    int index = orderList.indexOf(order) + 1;
                    driver.findElement(By.xpath("//ul[@class='ordered-list']/li" + "[" + index + "]" + "//a[@class='btn transparent small']/span[.='Zmień']")).click();
                } else {
                    System.out.println("brak zlecenia");
                }
            }
        } catch (StaleElementReferenceException ex) {
            for (WebElement order : orderList) {
                if (order.getText().contains(passengerName) && order.getText().contains(startAddress) && order.getText().contains(prepareOrderTimeToCompareInListDetails_future(orderTime)) && order.getText().contains(projectName) && order.getText().contains(finalAddress)) {
                    int index = orderList.indexOf(order);
                    System.out.println(index);
                    order.findElement(By.xpath("//a[@class='btn transparent small']/span[.='Zmień']" + "[" + index + "]")).click();
                } else {
                    System.out.println("brak zlecenia");
                }
            }
        }
        return new OrderForEmployeePage(driver);
    }


}



