package pages;


import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import utilities.SearchFromTables;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static utilities.NumberFormatter.changeToDecimalFormat;

public class MpkPage extends BasePage {
    public MpkPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[@class='btn small violet']//span[.='Dodaj']")
    private WebElement addMpkButton;

    @FindBy(xpath = "//div[@class='inputs']//h3[.='Dodaj']")
    private WebElement addMpkForm;

    @FindBy(id = "name")
    private WebElement nameField;

    @FindBy(id = "amountLimitGr")
    private WebElement maxAmountField;

    @FindBy(id = "alertLimitGr")
    private WebElement alertAmountField;

    @FindBy(id = "comment")
    private WebElement commentField;

    @FindBy(id = "button")
    private WebElement saveMpkButton;

    @FindBy(xpath = "//div[@class='notification green notification-enter-done']//p[.='Pomyślnie dodano']")
    private WebElement notificationSucessfullAdded;

    @FindBy(xpath = "//h3[contains(.,'Zarządzanie MPK - firma')]")
    private WebElement mpkHeader;

    @FindBy(xpath = "//div[@class='tabs']//a[@href='/manage/mpk']")
    private WebElement mpkButtonHeader;

    @FindBy(xpath = "//div[@class='tabs']//a[@href='/manage/profiles']")
    private WebElement profilesButtonHeader;

    @FindBy(xpath = "//div[@class='tabs']//a[@href='/manage/projects']")
    private WebElement projectsButtonHeader;

    @FindBy(xpath = "//div[@class='tabs']//a[@href='/manage/offices']")
    private WebElement officesButtonHeader;

    @FindBy(xpath = "//div[@class='notification red notification-enter-done']//p[.='Brak nazwy departamentu']")
    private WebElement notificationNoMpkName;

    @FindBy(xpath = "//div[@class='inputs delete']")
    private WebElement deleteMpkConfirmSection;

    @FindBy(xpath = "//button[@id='button']//span[.='Usuń']")
    private WebElement deleteMpkConfirmButton;

    @FindBy(xpath = "//div[@class='notification green notification-enter-done']//p[.='Pomyślnie usunięto']")
    private WebElement notificationSucessDeleted;

    @FindBy(xpath = "//select[@aria-label='rows per page']")
    private WebElement selectRowsPerPage;

    @FindBy(xpath = "//div[@class='notification green notification-enter-done']//p[.='Pomyślnie aktywowano']")
    private WebElement notificationSucessActivated;

    @FindBy(xpath = "//div[@class='inputs']//h3[.='Edytuj']")
    private WebElement editMpkForm;

    @FindBy(xpath = "//input[@placeholder='Wpisz...']")
    private WebElement mpkListToAssignEmployee;

    @FindBy(xpath = "//div[@class='notification green notification-enter-done']//p[.='Pomyślnie zapisano']")
    private WebElement notificationSucessSavedChanges;

    @FindBy(xpath = "//div[@class='notification green notification-enter-done']//p[.='Pomyślnie przypisano użytkowników']")
    private WebElement notificationSucessUserAssigned;

    @FindBy(xpath = "//div[@class='notification red notification-enter-done']//p[.='Nazwa departmanetu juz istnieje']")
    private WebElement notificationMpkExist;

    @FindBy(xpath = "//button[.='Następna']")
    private WebElement nextPageButton;

    @FindBy(xpath = "//button[.='Poprzednia']")
    private WebElement backPageButton;

    @FindBy(xpath = "//span[@class='-totalPages']")
    private WebElement totalPagesCount;

    @FindBy(xpath = "//div[@class='rt-thead -filters']//div[@class='rt-th'][1]//input")
    private WebElement nameSearchField;

    @FindBy(xpath = "//div[@class='rt-thead -filters']//div[@class='rt-th'][2]//input")
    private WebElement maxAmountSearchField;

    @FindBy(xpath = "//div[@class='rt-thead -filters']//div[@class='rt-th'][3]//input")
    private WebElement usedAmountSearchField;

    @FindBy(xpath = "//div[@class='rt-thead -filters']//div[@class='rt-th'][4]//input")
    private WebElement alertAmountSearchField;

    @FindBy(xpath = "//div[@class='rt-thead -filters']//div[@class='rt-th'][5]//input")
    private WebElement commentSearchField;


    @Step
    public MpkPage verify_MpkPage() throws InterruptedException {
        Thread.sleep(3000);
        waitForPresenceOfElement(mpkHeader);
        waitForPresenceOfElement(mpkButtonHeader);
        waitForPresenceOfElement(profilesButtonHeader);
        waitForPresenceOfElement(profilesButtonHeader);
        waitForPresenceOfElement(officesButtonHeader);
        Assert.assertTrue(mpkHeader.getText().contains("Zarządzanie MPK - firma"));
        Assert.assertEquals(mpkButtonHeader.getText(), "MPK");
        Assert.assertEquals(profilesButtonHeader.getText(), "Profile");
        Assert.assertEquals(projectsButtonHeader.getText(), "Projekty");
        Assert.assertEquals(officesButtonHeader.getText(), "Firma");
        return this;
    }

    @Step
    public MpkPage verify_mpk_tab_header_names() {
        List<String> expectedTexts = Arrays.asList("Nazwa", "Limit kwotowy", "Wykorzystano", "Alert gdy pozostało mniej niż", "Uwagi", "Opcje");
        List<String> actualTexts = new ArrayList<String>();

        List<WebElement> listOfHeaderNames = driver.findElements(By.xpath("//div[@class='rt-thead -header']//div[@role='row']/descendant::div[@class='rt-resizable-header-content']"));
        for (WebElement header : listOfHeaderNames) {
            System.out.println(header.getText());
            actualTexts.add(header.getText());
        }

        System.out.println("expected: " + expectedTexts.toString() + " " + "actual: " + actualTexts.toString());
        Assert.assertEquals(actualTexts.toString(), expectedTexts.toString());

        return this;
    }

    @Step
    public MpkPage verify_new_mpk_form_fields_name() {

        List<String> expectedTexts = Arrays.asList("Nazwa", "Limit kwotowy", "Alert gdy pozostało mniej niż", "Uwagi");
        List<String> actualTexts = new ArrayList<String>();

        List<WebElement> mpkFields = driver.findElements(By.xpath("//div[@class='inputs']//div[@class='input']"));
        for (WebElement field : mpkFields) {
            System.out.println(field.getText());
            actualTexts.add(field.getText());
        }

        System.out.println("expected: " + expectedTexts.toString() + " " + "actual: " + actualTexts.toString());
        Assert.assertEquals(actualTexts.toString(), expectedTexts.toString());

        return this;
    }


    @Step
    public MpkPage add_new_mpk(String mpkName, String maxAmount, String alertAmount, String comment) throws InterruptedException {
        waitForPresenceOfElement(addMpkButton);
        waitForElementToBeClickable(addMpkButton);
        Thread.sleep(10000);
        addMpkButton.click();
        waitForPresenceOfElement(addMpkForm);
        verify_new_mpk_form_fields_name();
        nameField.sendKeys(mpkName);
        maxAmountField.sendKeys(maxAmount);
        alertAmountField.sendKeys(alertAmount);
        commentField.sendKeys(comment);
        saveMpkButton.click();
        waitForPresenceOfElement(notificationSucessfullAdded);
        Assert.assertEquals(notificationSucessfullAdded.getText(), "Pomyślnie dodano");
        return this;
    }

    @Step
    public MpkPage verify_is_mpk_added(String mpkName, String maxAmount, String alertAmount, String comment) throws InterruptedException {
        Thread.sleep(3000);
        List<WebElement> rows = driver.findElements(By.xpath("//div[@class='rt-tr-group']//div[@class='rt-td']"));
        for (WebElement row : rows) {
            if (row.getText().equals(mpkName)) {
                WebElement maxAmountRow = row.findElement(By.xpath("./following-sibling::div[1]"));
                WebElement alertRow = row.findElement(By.xpath("./following-sibling::div[3]"));
                WebElement commentRow = row.findElement(By.xpath("./following-sibling::div[4]"));
                WebElement optionsRow = row.findElement(By.xpath("./following-sibling::div[5]"));

                Assert.assertEquals(row.getText(), mpkName);
                Assert.assertEquals(maxAmountRow.getText(), changeToDecimalFormat(maxAmount));
                Assert.assertEquals(alertRow.getText(), changeToDecimalFormat(alertAmount));
                Assert.assertEquals(commentRow.getText(), comment);
                Assert.assertTrue(optionsRow.getText().contains("Edytuj"));
                Assert.assertTrue(optionsRow.getText().contains("Usuń"));
                Assert.assertFalse(optionsRow.getText().contains("Aktywuj"));
            }
        }
        return this;
    }

    @Step
    public MpkPage cant_add_mpk_without_data() throws InterruptedException {
        waitForPresenceOfElement(addMpkButton);
        Thread.sleep(10000);
        addMpkButton.click();
        waitForPresenceOfElement(addMpkForm);
        saveMpkButton.click();
        waitForPresenceOfElement(notificationNoMpkName);
        Assert.assertEquals(notificationNoMpkName.getText(), "Brak nazwy departamentu");
        return this;
    }

    @Step
    public MpkPage choose_appropriate_mpk_without_users_and_click_delete(String mpkName) throws InterruptedException {
        Thread.sleep(10000);
        List<WebElement> rows = driver.findElements(By.xpath("//div[@class='rt-tr-group']//div[@class='rt-td']"));
        for (WebElement row : rows) {
            if (row.getText().equals(mpkName)) {
                System.out.println(row.getText());
                WebElement deleteButton = row.findElement(By.xpath("./following-sibling::div[5]//span[.='Usuń']"));
                deleteButton.click();
            }
        }
        waitForPresenceOfElement(deleteMpkConfirmSection);
        System.out.println(deleteMpkConfirmSection.getText());
        Assert.assertEquals(deleteMpkConfirmSection.getText(), "Usuwanie\n" +
                "Czy na pewno chcesz usunąć MPK?");
        deleteMpkConfirmButton.click();
        waitForPresenceOfElement(notificationSucessDeleted);
        Assert.assertEquals(notificationSucessDeleted.getText(), "Pomyślnie usunięto");
        return this;
    }


    @Step
    public MpkPage choose_appropriate_mpk_without_users_and_click_edit(String mpkName) throws InterruptedException {
        Thread.sleep(10000);
        List<WebElement> rows = driver.findElements(By.xpath("//div[@class='rt-tr-group']//div[@class='rt-td']"));
        for (WebElement row : rows) {
            if (row.getText().equals(mpkName)) {
                System.out.println(row.getText());
                WebElement editButton = row.findElement(By.xpath("./following-sibling::div[5]//span[.='Edytuj']"));
                editButton.click();
            }
        }
        waitForPresenceOfElement(editMpkForm);
        Assert.assertEquals(nameField.getAttribute("value"), mpkName);
        return this;
    }


    @Step
    public MpkPage verify_if_deleted_mpk_isnt_on_list(String mpkName) {
        List<WebElement> rows = driver.findElements(By.xpath("//div[@class='rt-tr-group']//div[@class='rt-td']"));
        for (WebElement row : rows) {
            if (row.getText().equals(mpkName)) {
                System.out.println(row.getText());
                WebElement activeButton = row.findElement(By.xpath("./following-sibling::div[5]//span[.='Aktywuj']"));
                Assert.assertTrue(activeButton.isDisplayed());
                Assert.assertEquals(activeButton.getText(), "Aktywuj");
            }
        }
        return this;
    }


    @Step
    public MpkPage choose_appropriate_mpk_with_users_and_click_edit(String mpkName) throws InterruptedException {
        Thread.sleep(10000);
        List<WebElement> rows = driver.findElements(By.xpath("//div[@class='rt-tr-group']//div[@class='rt-td']"));
        for (WebElement row : rows) {
            if (row.getText().equals(mpkName)) {
                WebElement editButton = row.findElement(By.xpath("./following-sibling::div[5]//span[.='Edytuj']"));
                System.out.println("znaleziono row z mpk");
                editButton.click();
            }
        }
        waitForPresenceOfElement(editMpkForm);
        Assert.assertEquals(nameField.getAttribute("value"), mpkName);
        return this;
    }


    @Step
    public MpkPage choose_appropriate_mpk_and_click_edit(String mpkName) throws InterruptedException {
        Thread.sleep(10000);
        List<WebElement> rows = driver.findElements(By.xpath("//div[@class='rt-tr-group']//div[@class='rt-td']"));
        for (WebElement row : rows) {
            if (row.getText().equals(mpkName)) {
                WebElement editButton = row.findElement(By.xpath("./following-sibling::div[5]//span[.='Edytuj']"));
                editButton.click();
            }
        }
        waitForPresenceOfElement(editMpkForm);
        Assert.assertEquals(nameField.getAttribute("value"), mpkName);
        return this;
    }


    @Step
    public MpkPage show_amount_of_rows_per_page(String numberOfRowsOnPage) {
        Select listRowsPerPage = new Select(selectRowsPerPage);
        listRowsPerPage.selectByValue(numberOfRowsOnPage);
        return this;
    }

    @Step
    public MpkPage active_deleted_mpk(int index) {
        List<WebElement> unactiveMpkNames = driver.findElements(By.xpath("//div[@class='rt-tr-group']//div[contains(@style,'background')]/div[1]"));
        WebElement rowWithIndex = unactiveMpkNames.get(index);
        String mpkName = rowWithIndex.getText();
        System.out.println(mpkName);

        WebElement optionsRow = rowWithIndex.findElement(By.xpath("./following-sibling::div[5]"));
        Assert.assertEquals(optionsRow.getText(), "Aktywuj");
        optionsRow.click();
        waitForPresenceOfElement(notificationSucessActivated);
        Assert.assertEquals(notificationSucessActivated.getText(), "Pomyślnie aktywowano");

        WebElement editButton = optionsRow.findElement(By.xpath("//div[@class='options']//span[.='Edytuj']"));
        Assert.assertTrue(editButton.isDisplayed());
        Assert.assertEquals(editButton.getText(), "Edytuj");

        WebElement deleteButton = optionsRow.findElement(By.xpath("//div[@class='options']//span[.='Usuń']"));
        Assert.assertTrue(deleteButton.isDisplayed());
        Assert.assertEquals(deleteButton.getText(), "Usuń");

        return this;
    }

    @Step
    public MpkPage edit_mpk_fields_and_verify_if_is_edited_with_users(String newMpkName, String newMaxAmount, String newAlertAmount, String newComment, String newMpkToAssignEmployee) throws InterruptedException {
        waitForPresenceOfElement(editMpkForm);

        List<String> expectedTexts = Arrays.asList("Nazwa", "Limit kwotowy", "Alert gdy pozostało mniej niż", "Uwagi", "Wybierz do jakiego MPK ich przypisać:");
        List<String> actualTexts = new ArrayList<String>();

        List<WebElement> labelNames = driver.findElements(By.xpath("//div[@class='input']/label"));
        for (WebElement label : labelNames) {
            actualTexts.add(label.getText());
        }

        Assert.assertEquals(actualTexts.toString(), expectedTexts.toString());

        nameField.clear();
        nameField.sendKeys(newMpkName);

        maxAmountField.clear();
        maxAmountField.sendKeys(changeToDecimalFormat(newMaxAmount));

        alertAmountField.clear();
        alertAmountField.sendKeys(changeToDecimalFormat(newAlertAmount));

        commentField.clear();
        commentField.sendKeys(newComment);

        mpkListToAssignEmployee.sendKeys(newMpkToAssignEmployee);
        mpkListToAssignEmployee.click();
        Thread.sleep(1000);
        WebElement firstElementFromList = driver.findElement(By.xpath("//div[@class='select-search']//ul[@class='open']//li[1]"));
        firstElementFromList.click();
        Thread.sleep(1000);
        waitForPresenceOfElement(saveMpkButton);
        saveMpkButton.click();
        waitForPresenceOfElement(notificationSucessSavedChanges);
        waitForPresenceOfElement(notificationSucessUserAssigned);

        Assert.assertEquals(notificationSucessSavedChanges.getText(), "Pomyślnie zapisano");


        verify_is_mpk_edited(newMpkName, changeToDecimalFormat(newMaxAmount), changeToDecimalFormat(newAlertAmount), newComment);
        return this;
    }

    @Step
    public MpkPage edit_mpk_fields_and_verify_if_is_edited_without_users(String newMpkName, String newMaxAmount, String newAlertAmount, String newComment) throws InterruptedException {

        waitForPresenceOfElement(editMpkForm);
        List<String> expectedTexts = Arrays.asList("Nazwa", "Limit kwotowy", "Alert gdy pozostało mniej niż", "Uwagi");
        List<String> actualTexts = new ArrayList<String>();

        List<WebElement> labelNames = driver.findElements(By.xpath("//div[@class='input']/label"));
        for (WebElement label : labelNames) {
            actualTexts.add(label.getText());
        }

        Assert.assertEquals(actualTexts.toString(), expectedTexts.toString());

        nameField.clear();
        nameField.sendKeys(newMpkName);

        maxAmountField.clear();
        maxAmountField.sendKeys(changeToDecimalFormat(newMaxAmount));

        alertAmountField.clear();
        alertAmountField.sendKeys(changeToDecimalFormat(newAlertAmount));

        commentField.clear();
        commentField.sendKeys(newComment);

        waitForPresenceOfElement(saveMpkButton);
        saveMpkButton.click();
        waitForPresenceOfElement(notificationSucessSavedChanges);
        waitForPresenceOfElement(notificationSucessUserAssigned);

        Assert.assertEquals(notificationSucessSavedChanges.getText(), "Pomyślnie zapisano");


        verify_is_mpk_edited(newMpkName, changeToDecimalFormat(newMaxAmount), changeToDecimalFormat(newAlertAmount), newComment);
        return this;
    }


    @Step
    public MpkPage edit_mpk_fields_and_try_to_add_existing_name(String newMpkName) {

        nameField.clear();
        nameField.sendKeys(newMpkName);

        waitForPresenceOfElement(saveMpkButton);
        saveMpkButton.click();
        waitForPresenceOfElement(notificationSucessSavedChanges);

        Assert.assertEquals(notificationMpkExist.getText(), "Nazwa departmanetu juz istnieje");

        WebElement close = driver.findElement(By.xpath("//div[@class='modal modal_after-open']//div[@class='close']"));
        try {
            close.click();
        } catch (StaleElementReferenceException exception) {
            close.click();
        }
        Assert.assertTrue(isMpkPresent(newMpkName));

        return this;
    }

    private boolean isMpkPresent(String mpkName) {
        boolean isMpkPresent = false;
        List<WebElement> rows = driver.findElements(By.xpath("//div[@class='rt-tr-group']//div[@class='rt-td']"));
        waitForVisibilityOfElements(rows);
        for (WebElement row : rows) {
            if (row.getText().equals(mpkName)) {
                isMpkPresent = true;
            }
        }
        return isMpkPresent;
    }


    @Step
    public MpkPage choose_appropriate_mpk_with_users_and_click_delete(String mpkName, String newMpkToAssignEmployee) throws InterruptedException {
        Thread.sleep(2000);
        List<WebElement> rows = driver.findElements(By.xpath("//div[@class='rt-tr-group']//div[@class='rt-td']"));
        for (WebElement row : rows) {
            if (row.getText().equals(mpkName)) {
                System.out.println(row.getText());
                WebElement deleteButton = row.findElement(By.xpath("./following-sibling::div[5]//span[.='Usuń']"));
                deleteButton.click();
            }
        }
        waitForPresenceOfElement(deleteMpkConfirmSection);
        System.out.println(deleteMpkConfirmSection.getText());
        Assert.assertEquals(deleteMpkConfirmSection.getText(), "Usuwanie\n" +
                "Uwaga! Ten MPK ma przypisanych użytkowników!\n" +
                "Wybierz do jakiego MPK ich przypisać:");
        mpkListToAssignEmployee.sendKeys(newMpkToAssignEmployee);
        mpkListToAssignEmployee.click();
        Thread.sleep(1000);
        WebElement firstElementFromList = driver.findElement(By.xpath("//div[@class='select-search']//ul[@class='open']//li[1]"));
        firstElementFromList.click();
        Thread.sleep(1000);
        deleteMpkConfirmButton.click();
        waitForPresenceOfElement(notificationSucessDeleted);
        Assert.assertEquals(notificationSucessDeleted.getText(), "Pomyślnie usunięto");
        return this;
    }

    @Step
    public MpkPage verify_if_next_and_back_buttons_are_active_and_clickable() throws InterruptedException {
        Thread.sleep(2000);
        int pageCount = Integer.parseInt(totalPagesCount.getText());
        System.out.println(pageCount);
        if (pageCount > 1) {
            Assert.assertTrue(nextPageButton.isEnabled());
            Assert.assertFalse(backPageButton.isEnabled());
            nextPageButton.click();
            Assert.assertTrue(backPageButton.isEnabled());
            backPageButton.click();
        } else {
            Assert.assertFalse(nextPageButton.isEnabled());
            Assert.assertFalse(backPageButton.isEnabled());
        }

        return this;
    }

    @Step
    public ProfilesPage go_to_ProfilesPage() {
        profilesButtonHeader.click();
        return new ProfilesPage(driver);

    }

    @Step
    public ProjectsPage go_to_ProjectsPage() {
        projectsButtonHeader.click();
        return new ProjectsPage(driver);

    }

    @Step
    public MpkPage verify_is_mpk_edited(String newMpkName, String newMaxAmount, String newAlertAmount, String newComment) throws InterruptedException {
        Thread.sleep(3000);
        List<WebElement> rows = driver.findElements(By.xpath("//div[@class='rt-tr-group']//div[@class='rt-td']"));
        for (WebElement row : rows) {
            if (row.getText().equals(newMpkName)) {
                WebElement maxAmountRow = row.findElement(By.xpath("./following-sibling::div[1]"));
                WebElement alertRow = row.findElement(By.xpath("./following-sibling::div[3]"));
                WebElement commentRow = row.findElement(By.xpath("./following-sibling::div[4]"));
                WebElement optionsRow = row.findElement(By.xpath("./following-sibling::div[5]"));

                Assert.assertEquals(row.getText(), newMpkName);
                Assert.assertEquals(maxAmountRow.getText(), newMaxAmount);
                Assert.assertEquals(alertRow.getText(), newAlertAmount);
                Assert.assertEquals(commentRow.getText(), newComment);
                Assert.assertTrue(optionsRow.getText().contains("Edytuj"));
                Assert.assertTrue(optionsRow.getText().contains("Usuń"));
                Assert.assertFalse(optionsRow.getText().contains("Aktywuj"));
            }
        }

        return this;
    }

    @Step
    public MpkPage sort_data_by_name(String nameToSearch) throws InterruptedException {
        List<WebElement> rowsWithName = driver.findElements(By.xpath("//div[@class='rt-tr-group']/descendant::div[2]"));
        new SearchFromTables(driver).sort_data_by(nameToSearch, rowsWithName, nameSearchField);
        return this;
    }

    @Step
    public MpkPage sort_data_by_maxAmount(String maxAmountToSearch) throws InterruptedException {
        List<WebElement> rowsWithMaxAmount = driver.findElements(By.xpath("//div[@class='rt-tr-group']/descendant::div[3]"));
        new SearchFromTables(driver).sort_data_by(maxAmountToSearch, rowsWithMaxAmount, maxAmountSearchField);
        return this;
    }

    @Step
    public MpkPage sort_data_by_usedAmount(String usedAmountToSearch) throws InterruptedException {
        List<WebElement> rowsWithUsedAmount = driver.findElements(By.xpath("//div[@class='rt-tr-group']/descendant::div[4]"));
        new SearchFromTables(driver).sort_data_by(usedAmountToSearch, rowsWithUsedAmount, usedAmountSearchField);
        return this;
    }

    @Step
    public MpkPage sort_data_by_maxAlert(String alertAmountToSearch) throws InterruptedException {
        List<WebElement> rowsWithMaxAlert = driver.findElements(By.xpath("//div[@class='rt-tr-group']/descendant::div[5]"));
        new SearchFromTables(driver).sort_data_by(alertAmountToSearch, rowsWithMaxAlert, alertAmountSearchField);
        return this;
    }

    @Step
    public MpkPage sort_data_by_comments(String commetsToSearch) throws InterruptedException {
        List<WebElement> rowsWithComments = driver.findElements(By.xpath("//div[@class='rt-tr-group']/descendant::div[6]"));
        new SearchFromTables(driver).sort_data_by(commetsToSearch, rowsWithComments, alertAmountSearchField);
        return this;
    }

    @Step
    public String getFirstMpkOnList() {
        List<WebElement> rowsWithName = driver.findElements(By.xpath("//div[@class='rt-tr-group']/descendant::div[2]"));
        waitForVisibilityOfElements(rowsWithName);
        List<String> list = new ArrayList<String>();
        for (WebElement row : rowsWithName) {
            list.add(row.getText());
        }
        int listSize = list.size();
        String mpk = null;
        if (listSize > 0) {
            mpk = list.get(0);
        } else System.out.println("Nie ma mpk");
        System.out.println("mpk from mpkList " + mpk);
        return mpk;
    }

    @Step
    public String getListOfActiveMpkAndSelectOne(int indexOfMpk) {
        List<WebElement> rowsWithName = driver.findElements(By.xpath(" //span[contains(text(),'Edytuj')]/preceding::div[@class='rt-tr-group']//div[@role='gridcell'][1]"));
        waitForVisibilityOfElements(rowsWithName);
        List<String> list = new ArrayList<String>();
        for (WebElement row : rowsWithName) {
            list.add(row.getText());
        }
        int listSize = list.size();
        String mpk = null;
        if (listSize > 0) {
            mpk = list.get(indexOfMpk);
        } else System.out.println("Nie ma aktywnego mpk");
        System.out.println("mpk from mpkList " + mpk);
        return mpk;
    }

    @Step
    public List<String> getListOfActiveMpk() {
        List<WebElement> rowsWithName = driver.findElements(By.xpath(" //span[contains(text(),'Edytuj')]/preceding::div[@class='rt-tr-group']//div[@role='gridcell'][1]"));
        waitForVisibilityOfElements(rowsWithName);
        List<String> list = new ArrayList<String>();
        for (WebElement row : rowsWithName) {
            list.add(row.getText());
        }
        return list;
    }

    @Step
    public String compareTwoListsAndTypeMpkWihoutUsersAndSelectOne(List<String> listFromMpPage, List<String> listFromEmployeesPage, int indexOfMpk) {

        ArrayList<String> list3 = new ArrayList<String>(listFromMpPage);
        list3.retainAll(listFromEmployeesPage);
        listFromMpPage.removeAll(list3);
        System.out.println(listFromMpPage);

        int listSize = listFromMpPage.size();
        String mpk = null;
        if (listSize > 0) {
            mpk = listFromMpPage.get(indexOfMpk);
        } else System.out.println("Nie ma aktywnego mpk");
        System.out.println("mpk from mpkList " + mpk);
        return mpk;

    }

}






