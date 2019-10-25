package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import utilities.SearchFromTables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ProfilesPage extends BasePage {
    public ProfilesPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[@class='-totalPages']")
    private WebElement totalPagesCount;

    @FindBy(xpath = "//h3[contains(.,'Zarządzanie profilami - firma')]")
    private WebElement profilesHeader;

    @FindBy(xpath = "//button[.='Następna']")
    private WebElement nextPageButton;

    @FindBy(xpath = "//button[.='Poprzednia']")
    private WebElement backPageButton;

    @FindBy(xpath = "//select[@aria-label='rows per page']")
    private WebElement selectRowsPerPage;

    @FindBy(xpath = "//div[@class='notification green notification-enter-done']//p[.='Pomyślnie dodano']")
    private WebElement notificationSucessfullAdded;

    @FindBy(xpath = "//button[.='Dodaj']")
    private WebElement addNewProfileButton;

    @FindBy(xpath = "//div[@class='inputs']//h3[.='Dodaj']")
    private WebElement addProfileForm;

    @FindBy(id = "name")
    private WebElement nameField;

    @FindBy(xpath = "//div[@class='flex']/descendant::input[1]")
    private WebElement timeFromField;

    @FindBy(xpath = "//div[@class='flex']/descendant::input[2]")
    private WebElement timeToField;

    @FindBy(id = "maxTotalAmountGr")
    private WebElement maxTotalAmountField;

    @FindBy(id = "maxOrders")
    private WebElement maxOrdersField;

    @FindBy(id = "warnAmountGr")
    private WebElement alertAmountField;

    @FindBy(id = "orderDays")
    private WebElement orderDaysSelect;

    @FindBy(id = "maxTariffGr")
    private WebElement maxTariffField;

    @FindBy(id = "carClass")
    private WebElement carClassSelect;

    @FindBy(id = "commentForCC")
    private WebElement commentForCCField;

    @FindBy(xpath = "//button[.='Zapisz']")
    private WebElement saveProfileButton;

    @FindBy(xpath = "//div[@class='notification red notification-enter-done']//p[.='Pusta nazwa profilu']")
    private WebElement notificationNoProfileName;

    @FindBy(xpath = "//div[@class='inputs delete']")
    private WebElement deleteProfileConfirmSection;

    @FindBy(xpath = "//button[@id='button']//span[.='Usuń']")
    private WebElement deleteProfileConfirmButton;

    @FindBy(xpath = "//div[@class='notification green notification-enter-done']//p[.='Pomyślnie usunięto']")
    private WebElement notificationSucessDeleted;

    @FindBy(xpath = "//input[@placeholder='Wpisz...']")
    private WebElement profileListToAssignEmployee;

    @FindBy(xpath = "//div[@class='inputs']//h3[.='Edytuj']")
    private WebElement editProfleForm;

    @FindBy(xpath = "//div[@class='notification green notification-enter-done']//p[.='Pomyślnie zapisano']")
    private WebElement notificationSucessSavedChanges;

    @FindBy(xpath = "//div[@class='notification red notification-enter-done']//p[.='Godzina początku aktywności profilu większa niż godzina końca aktywności']")
    private WebElement notificationFinishTimeBeforeStartTime;

    @FindBy(xpath = "//div[@class='notification red notification-enter-done']//p[.='Kwota ostrzeżenia nie może występować bez limitu kwoty i musi być mniejsza od tego limitu']")
    private WebElement notificationAmountAlertWithoutMaxAmount;

    @FindBy(xpath = "//div[@class='modal modal_after-open']//div[@class='close']")
    private WebElement closeSection;

    @FindBy(xpath = "//div[@class='rt-thead -filters']//div[@class='rt-th'][1]//input")
    private WebElement nameSearchField;

    @FindBy(xpath = "//div[@class='rt-thead -filters']//div[@class='rt-th'][2]//input")
    private WebElement orderAmountSearchField;

    @FindBy(xpath = "//div[@class='rt-thead -filters']//div[@class='rt-th'][3]//input")
    private WebElement maxAmountSearchField;

    @FindBy(xpath = "//div[@class='rt-thead -filters']//div[@class='rt-th'][4]//input")
    private WebElement alertAmountSearchField;

    @FindBy(xpath = "//div[@class='rt-thead -filters']//div[@class='rt-th'][5]//input")
    private WebElement maxTariffSearchField;

    @FindBy(xpath = "//div[@class='rt-thead -filters']//div[@class='rt-th'][6]//input")
    private WebElement usersNumberSearchField;


    @Step
    public ProfilesPage verify_profilesPage() throws InterruptedException {
        Thread.sleep(3000);
        waitForPresenceOfElement(profilesHeader);
        Assert.assertTrue(profilesHeader.getText().contains("Zarządzanie profilami - firma "));
        return this;
    }

    @Step
    public ProfilesPage verify_profiles_tab_header_names() {

        List<String> expectedTexts = Arrays.asList("Nazwa", "Limit godzinowy", "Dni tygodnia", "Limit ilościowy", "Limit kwotowy", "Alert gdy zostało mniej niż", "Maksymalna taryfa", "Liczba użytkowników", "Klasa samochodu", "Opcje");
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
    public ProfilesPage verify_if_next_and_back_buttons_are_active_and_clickable() throws InterruptedException {
        Thread.sleep(2000);
        int pageCount = Integer.parseInt(totalPagesCount.getText());
        System.out.println(pageCount);
        if (pageCount > 1) {
            Assert.assertTrue(nextPageButton.isEnabled());
            Assert.assertFalse(backPageButton.isEnabled());
            nextPageButton.click();
            Assert.assertTrue(backPageButton.isEnabled());
            Assert.assertFalse(nextPageButton.isEnabled());
            backPageButton.click();
        } else {
            Assert.assertFalse(nextPageButton.isEnabled());
            Assert.assertFalse(backPageButton.isEnabled());
        }
        return this;
    }

    @Step
    public ProfilesPage scroll_left() {
        WebElement element = driver.findElement(By.xpath("//div[@class='options']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
        return this;
    }

    @Step
    public ProfilesPage scroll_right() {
        WebElement element = driver.findElement(By.xpath("//div[.='Nazwa']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
        return this;
    }

    @Step
    public ProfilesPage show_amount_of_rows_per_page(String numberOfRowsOnPage) {
        Select listRowsPerPage = new Select(selectRowsPerPage);
        listRowsPerPage.selectByValue(numberOfRowsOnPage);
        return this;
    }

    @Step
    public ProfilesPage add_new_profile(String profileName, String timeFrom, String timeTo, String maxAmount, String maxOrders,
                                        String alertAmount, String orderDays, String maxTariff, String carClass, String comment) throws InterruptedException {
        waitForPresenceOfElement(addNewProfileButton);
        addNewProfileButton.click();
        waitForPresenceOfElement(addProfileForm);
        verify_new_profile_form_fields_name();
        nameField.sendKeys(profileName);

        timeFromField.click();
        waitForPresenceOfElement(driver.findElement(By.xpath("//div[@class='react-datepicker-time__header']")));
        try {
            List<WebElement> timeList = driver.findElements(By.xpath("//ul[@class='react-datepicker__time-list']/li"));
            for (WebElement time : timeList) {
                if (time.getText().equals(timeFrom)) {
                    time.click();
                }
            }
        } catch (org.openqa.selenium.StaleElementReferenceException ex) {
            List<WebElement> timeList = driver.findElements(By.xpath("//ul[@class='react-datepicker__time-list']/li"));
            for (WebElement time : timeList) {
                if (time.getText().equals(timeFrom)) {
                    time.click();
                }
            }
        }

        timeToField.click();
        waitForPresenceOfElement(driver.findElement(By.xpath("//div[@class='react-datepicker-time__header']")));
        try {
            List<WebElement> timeList2 = driver.findElements(By.xpath("//ul[@class='react-datepicker__time-list']/li"));
            for (WebElement time : timeList2) {
                if (time.getText().equals(timeTo)) {
                    time.click();
                }
            }
        } catch (org.openqa.selenium.StaleElementReferenceException ex) {
            List<WebElement> timeList2 = driver.findElements(By.xpath("//ul[@class='react-datepicker__time-list']/li"));
            for (WebElement time : timeList2) {
                if (time.getText().equals(timeTo)) {
                    time.click();
                }
            }
        }

        maxTotalAmountField.sendKeys(maxAmount);
        maxOrdersField.sendKeys(maxOrders);
        alertAmountField.sendKeys(alertAmount);

        Select orderDaysList = new Select(orderDaysSelect);
        orderDaysList.selectByVisibleText(orderDays);

        maxTariffField.sendKeys(maxTariff);

        Select carClassList = new Select(carClassSelect);
        carClassList.selectByVisibleText(carClass);

        commentForCCField.sendKeys(comment);

        saveProfileButton.click();

        waitForPresenceOfElement(notificationSucessfullAdded);
        Assert.assertEquals(notificationSucessfullAdded.getText(), "Pomyślnie dodano");
        return this;
    }


    @Step
    public ProfilesPage verify_new_profile_form_fields_name() {

        List<String> expectedTexts = Arrays.asList("Nazwa", "Limit godzin", "Od", "Do", "Limit kwotowy", "Limit ilościowy", "Alert gdy zostało mniej niż", "Dni tygodnia", "Maksymalna taryfa", "Klasa samochodu", "Komentarz dla CC");
        List<String> actualTexts = new ArrayList<String>();

        List<WebElement> profleFields = driver.findElements(By.xpath("//div[@class='inputs']//div[@class='input']//label"));
        for (WebElement field : profleFields) {
            System.out.println(field.getText());
            actualTexts.add(field.getText());
        }

        System.out.println("expected: " + expectedTexts.toString() + " " + "actual: " + actualTexts.toString());
        Assert.assertEquals(actualTexts.toString(), expectedTexts.toString());

        return this;
    }

    @Step
    public ProfilesPage verify_is_profile_added(String profileName, String timeFrom, String timeTo, String maxAmount, String maxOrders,
                                                String alertAmount, String orderDays, String maxTariff, String carClass) throws InterruptedException {
        Thread.sleep(3000);
        List<WebElement> rows = driver.findElements(By.xpath("//div[@class='rt-tr-group']//div[@class='rt-td']"));
        for (WebElement row : rows) {
            if (row.getText().equals(profileName)) {
                WebElement orderTimeRow = row.findElement(By.xpath("./following-sibling::div[1]"));
                WebElement orderDaysRow = row.findElement(By.xpath("./following-sibling::div[2]"));
                WebElement maxOrdersRow = row.findElement(By.xpath("./following-sibling::div[3]"));
                WebElement maxAmountRow = row.findElement(By.xpath("./following-sibling::div[4]"));
                WebElement alertRow = row.findElement(By.xpath("./following-sibling::div[5]"));
                WebElement maxTariffRow = row.findElement(By.xpath("./following-sibling::div[6]"));
                WebElement carClassRow = row.findElement(By.xpath("./following-sibling::div[8]"));
                WebElement optionsRow = row.findElement(By.xpath("./following-sibling::div[9]"));

                String orderTime = timeFrom + " - " + timeTo;

                Assert.assertEquals(row.getText(), profileName);
                Assert.assertEquals(orderTimeRow.getText(), orderTime);
                Assert.assertEquals(orderDaysRow.getText(), orderDays);
                Assert.assertEquals(maxOrdersRow.getText(), maxOrders);
                Assert.assertEquals(maxAmountRow.getText(), maxAmount);
                Assert.assertEquals(alertRow.getText(), alertAmount);
                Assert.assertEquals(maxTariffRow.getText(), maxTariff);
//                Assert.assertEquals(carClassRow.getText(), carClass);

                Assert.assertTrue(optionsRow.getText().contains("Edytuj"));
                Assert.assertTrue(optionsRow.getText().contains("Usuń"));
                Assert.assertFalse(optionsRow.getText().contains("Aktywuj"));
            }
        }
        return this;
    }


    @Step
    public ProfilesPage cant_add_profile_without_data() throws InterruptedException {
        Thread.sleep(1000);
        waitForPresenceOfElement(addNewProfileButton);
        addNewProfileButton.click();
        waitForPresenceOfElement(addProfileForm);
        verify_new_profile_form_fields_name();
        saveProfileButton.click();
        waitForPresenceOfElement(notificationNoProfileName);
        Assert.assertEquals(notificationNoProfileName.getText(), "Pusta nazwa profilu");
        new Actions(driver).moveToElement(closeSection).perform();
        closeSection.click();
        return this;
    }

    @Step
    public ProfilesPage choose_appropriate_profile_without_users_and_click_delete(String profileName) throws InterruptedException {
        Thread.sleep(2000);
        List<WebElement> rows = driver.findElements(By.xpath("//div[@class='rt-tr-group']//div[@class='rt-td']"));
        for (WebElement row : rows) {
            if (row.getText().equals(profileName)) {
                System.out.println(row.getText());
                WebElement deleteButton = row.findElement(By.xpath("./following-sibling::div[9]//span[.='Usuń']"));
                deleteButton.click();
            }
        }
        waitForPresenceOfElement(deleteProfileConfirmSection);
        System.out.println(deleteProfileConfirmSection.getText());
        Assert.assertEquals(deleteProfileConfirmSection.getText(), "Usuwanie\n" +
                "Czy na pewno chcesz usunąć profil?");
        deleteProfileConfirmButton.click();
        waitForPresenceOfElement(notificationSucessDeleted);
        Assert.assertEquals(notificationSucessDeleted.getText(), "Pomyślnie usunięto");
        return this;
    }

    @Step
    public ProfilesPage choose_appropriate_profile_with_users_and_click_delete(String profileName, String newProfileToAssignEmployee) throws InterruptedException {
        Thread.sleep(2000);
        List<WebElement> rows = driver.findElements(By.xpath("//div[@class='rt-tr-group']//div[@class='rt-td']"));
        for (WebElement row : rows) {
            if (row.getText().equals(profileName)) {
                System.out.println(row.getText());
                WebElement deleteButton = row.findElement(By.xpath("./following-sibling::div[9]//span[.='Usuń']"));
                deleteButton.click();
            }
        }
        waitForPresenceOfElement(deleteProfileConfirmSection);
        System.out.println(deleteProfileConfirmSection.getText());
        Assert.assertEquals(deleteProfileConfirmSection.getText(), "Usuwanie\n" +
                "Uwaga! Ten profil ma przypisanych użytkowników!\n" +
                "Wybierz do jakiego profilu ich przypisać:");
        profileListToAssignEmployee.sendKeys(newProfileToAssignEmployee);
        profileListToAssignEmployee.click();
        Thread.sleep(1000);
        WebElement firstElementFromList = driver.findElement(By.xpath("//div[@class='select-search']//ul[@class='open']//li[1]"));
        firstElementFromList.click();
        Thread.sleep(1000);
        deleteProfileConfirmButton.click();
        waitForPresenceOfElement(notificationSucessDeleted);
        Assert.assertEquals(notificationSucessDeleted.getText(), "Pomyślnie usunięto");
        return this;
    }


    @Step
    public ProfilesPage verify_if_deleted_profile_isnt_on_list(String profileName) {
        List<WebElement> rows = driver.findElements(By.xpath("//div[@class='rt-tr-group']//div[@class='rt-td']"));
        for (WebElement row : rows) {
            Assert.assertNotEquals(row.getText(), profileName);
        }
        return this;
    }

    @Step
    public ProfilesPage edit_profile_fields_and_verify_if_is_edited(String profileName, String timeFrom, String timeTo, String maxAmount, String maxOrders,
                                                                    String alertAmount, String orderDays, String maxTariff, String carClass, String comment, String newProfileToAssignEmployee) throws InterruptedException {
        waitForPresenceOfElement(editProfleForm);
        nameField.clear();
        nameField.sendKeys(profileName);

        timeFromField.clear();
        timeFromField.click();
        waitForPresenceOfElement(driver.findElement(By.xpath("//div[@class='react-datepicker-time__header']")));
        try {
            List<WebElement> timeList = driver.findElements(By.xpath("//ul[@class='react-datepicker__time-list']/li"));
            for (WebElement time : timeList) {
                if (time.getText().equals(timeFrom)) {
                    time.click();
                }
            }
        } catch (org.openqa.selenium.StaleElementReferenceException ex) {
            List<WebElement> timeList = driver.findElements(By.xpath("//ul[@class='react-datepicker__time-list']/li"));
            for (WebElement time : timeList) {
                if (time.getText().equals(timeFrom)) {
                    time.click();
                }
            }
        }

        timeToField.clear();
        timeToField.click();
        waitForPresenceOfElement(driver.findElement(By.xpath("//div[@class='react-datepicker-time__header']")));
        try {
            List<WebElement> timeList2 = driver.findElements(By.xpath("//ul[@class='react-datepicker__time-list']/li"));
            for (WebElement time : timeList2) {
                if (time.getText().equals(timeTo)) {
                    time.click();
                }
            }
        } catch (org.openqa.selenium.StaleElementReferenceException ex) {
            List<WebElement> timeList2 = driver.findElements(By.xpath("//ul[@class='react-datepicker__time-list']/li"));
            for (WebElement time : timeList2) {
                if (time.getText().equals(timeTo)) {
                    time.click();
                }
            }
        }
        maxTotalAmountField.clear();
        maxTotalAmountField.sendKeys(maxAmount);

        maxOrdersField.clear();
        maxOrdersField.sendKeys(maxOrders);

        alertAmountField.clear();
        alertAmountField.sendKeys(alertAmount);

        Select orderDaysList = new Select(orderDaysSelect);
        orderDaysList.selectByVisibleText(orderDays);

        maxTariffField.clear();
        maxTariffField.sendKeys(maxTariff);

        Select carClassList = new Select(carClassSelect);
        carClassList.selectByVisibleText(carClass);

        commentForCCField.clear();
        commentForCCField.sendKeys(comment);

        profileListToAssignEmployee.sendKeys(newProfileToAssignEmployee);
        profileListToAssignEmployee.click();
        Thread.sleep(1000);
        WebElement firstElementFromList = driver.findElement(By.xpath("//div[@class='select-search']//ul[@class='open']//li[1]"));
        firstElementFromList.click();
        Thread.sleep(1000);
        waitForPresenceOfElement(saveProfileButton);
        saveProfileButton.click();

        waitForPresenceOfElement(notificationSucessSavedChanges);
        Assert.assertEquals(notificationSucessSavedChanges.getText(), "Pomyślnie zapisano");

        verify_is_profile_edited(profileName, timeFrom, timeTo, maxAmount, maxOrders, alertAmount, orderDays, maxTariff, carClass);

        return this;

    }

    @Step
    public ProfilesPage choose_appropriate_profile_without_users_and_click_edit(String profileName) throws InterruptedException {
        Thread.sleep(1000);
        List<WebElement> rows = driver.findElements(By.xpath("//div[@class='rt-tr-group']//div[@class='rt-td']"));
        for (WebElement row : rows) {
            if (row.getText().equals(profileName)) {
                WebElement editButton = row.findElement(By.xpath("./following-sibling::div[9]//span[.='Edytuj']"));
                editButton.click();
            }
        }
        waitForPresenceOfElement(editProfleForm);
        Assert.assertEquals(nameField.getAttribute("value"), profileName);
        return this;
    }

    @Step
    public ProfilesPage verify_is_profile_edited(String profileName, String timeFrom, String timeTo, String maxAmount, String maxOrders,
                                                 String alertAmount, String orderDays, String maxTariff, String carClass) throws InterruptedException {
        Thread.sleep(2000);
        List<WebElement> rows = driver.findElements(By.xpath("//div[@class='rt-tr-group']//div[@class='rt-td']"));
        for (WebElement row : rows) {
            if (row.getText().equals(profileName)) {
                WebElement orderTimeRow = row.findElement(By.xpath("./following-sibling::div[1]"));
                WebElement orderDaysRow = row.findElement(By.xpath("./following-sibling::div[2]"));
                WebElement maxOrdersRow = row.findElement(By.xpath("./following-sibling::div[3]"));
                WebElement maxAmountRow = row.findElement(By.xpath("./following-sibling::div[4]"));
                WebElement alertRow = row.findElement(By.xpath("./following-sibling::div[5]"));
                WebElement maxTariffRow = row.findElement(By.xpath("./following-sibling::div[6]"));
                WebElement carClassRow = row.findElement(By.xpath("./following-sibling::div[8]"));
                WebElement optionsRow = row.findElement(By.xpath("./following-sibling::div[9]"));

                String orderTime = timeFrom + " - " + timeTo;

                Assert.assertEquals(row.getText(), profileName);
                Assert.assertEquals(orderTimeRow.getText(), orderTime);
                Assert.assertEquals(orderDaysRow.getText(), orderDays);
                Assert.assertEquals(maxOrdersRow.getText(), maxOrders);
                Assert.assertEquals(maxAmountRow.getText(), maxAmount);
                Assert.assertEquals(alertRow.getText(), alertAmount);
                Assert.assertEquals(maxTariffRow.getText(), maxTariff);
//                Assert.assertEquals(carClassRow.getText(), carClass);

                Assert.assertTrue(optionsRow.getText().contains("Edytuj"));
                Assert.assertTrue(optionsRow.getText().contains("Usuń"));
                Assert.assertFalse(optionsRow.getText().contains("Aktywuj"));
            }
        }
        return this;
    }

    @Step
    public List<String> take_profile_names_from_main_table() throws InterruptedException {
        Thread.sleep(1000);
        List<String> rowsListWithNames = new ArrayList<String>();
        List<WebElement> rowsWithName = driver.findElements(By.xpath("//div[@class='rt-tr-group']/descendant::div[2]"));
        for (WebElement rowWithName : rowsWithName) {
            rowsListWithNames.add(rowWithName.getText());
        }
        System.out.println("main table list: " + rowsListWithNames);
        return rowsListWithNames;
    }

    @Step
    public List<String> take_profile_names_from_edit_or_delete_selectList() {
        profileListToAssignEmployee.click();
        List<String> selectListWithNames = new ArrayList<String>();
        List<WebElement> listWithNames = driver.findElements(By.xpath("//div[@class='select-search']//ul//li"));
        for (WebElement name : listWithNames) {
            selectListWithNames.add(name.getText());
        }
        System.out.println("select list: " + selectListWithNames);
        return selectListWithNames;
    }

    @Step
    public ProfilesPage compare_list_of_profiles_in_delete_section(String profileName) throws InterruptedException {
        List<String> profile_names_from_main_table = take_profile_names_from_main_table();

        Thread.sleep(1000);
        List<WebElement> rows = driver.findElements(By.xpath("//div[@class='rt-tr-group']//div[@class='rt-td']"));
        for (WebElement row : rows) {
            if (row.getText().equals(profileName)) {
                System.out.println(row.getText());
                WebElement deleteButton = row.findElement(By.xpath("./following-sibling::div[9]//span[.='Usuń']"));
                deleteButton.click();
            }
        }
        waitForPresenceOfElement(deleteProfileConfirmSection);
        Assert.assertEquals(deleteProfileConfirmSection.getText(), "Usuwanie\n" +
                "Uwaga! Ten profil ma przypisanych użytkowników!\n" +
                "Wybierz do jakiego profilu ich przypisać:");


        List<String> profile_names_from_edit_or_delete_list = take_profile_names_from_edit_or_delete_selectList();

        while (profile_names_from_main_table.contains(profileName)) {
            profile_names_from_main_table.remove(profileName);
        }
        System.out.println("list after remove to compare: " + profile_names_from_main_table);

        Assert.assertEquals(profile_names_from_main_table.toString(), profile_names_from_edit_or_delete_list.toString());

        closeSection.click();

        return this;
    }

    @Step
    public ProfilesPage compare_list_of_profiles_in_edit_section(String profileName) throws InterruptedException {
        List<String> profile_names_from_main_table = take_profile_names_from_main_table();

        Thread.sleep(1000);
        List<WebElement> rows = driver.findElements(By.xpath("//div[@class='rt-tr-group']//div[@class='rt-td']"));
        for (WebElement row : rows) {
            if (row.getText().equals(profileName)) {
                WebElement editButton = row.findElement(By.xpath("./following-sibling::div[9]//span[.='Edytuj']"));
                editButton.click();
            }
        }
        waitForPresenceOfElement(editProfleForm);
        Assert.assertEquals(nameField.getAttribute("value"), profileName);

        List<String> profile_names_from_edit_or_delete_list = take_profile_names_from_edit_or_delete_selectList();

        while (profile_names_from_main_table.contains(profileName)) {
            profile_names_from_main_table.remove(profileName);
        }
        System.out.println("list after remove to compare: " + profile_names_from_main_table);

        Assert.assertEquals(profile_names_from_main_table.toString(), profile_names_from_edit_or_delete_list.toString());

        new Actions(driver).moveToElement(closeSection).perform();
        closeSection.click();

        return this;
    }


    @Step
    public ProfilesPage cant_add_new_profile_with_incorrect_hours_finishTimeBeforeStartTime(String
                                                                                                    profileName, String timeFrom, String timeTo) throws InterruptedException {
        Thread.sleep(1000);
        waitForPresenceOfElement(addNewProfileButton);
        addNewProfileButton.click();
        waitForPresenceOfElement(addProfileForm);
        verify_new_profile_form_fields_name();
        nameField.sendKeys(profileName);

        timeFromField.click();
        waitForPresenceOfElement(driver.findElement(By.xpath("//div[@class='react-datepicker-time__header']")));
        try {
            List<WebElement> timeList = driver.findElements(By.xpath("//ul[@class='react-datepicker__time-list']/li"));
            for (WebElement time : timeList) {
                if (time.getText().equals(timeFrom)) {
                    time.click();
                }
            }
        } catch (org.openqa.selenium.StaleElementReferenceException ex) {
            List<WebElement> timeList = driver.findElements(By.xpath("//ul[@class='react-datepicker__time-list']/li"));
            for (WebElement time : timeList) {
                if (time.getText().equals(timeFrom)) {
                    time.click();
                }
            }
        }

        timeToField.click();
        waitForPresenceOfElement(driver.findElement(By.xpath("//div[@class='react-datepicker-time__header']")));
        try {
            List<WebElement> timeList2 = driver.findElements(By.xpath("//ul[@class='react-datepicker__time-list']/li"));
            for (WebElement time : timeList2) {
                if (time.getText().equals(timeTo)) {
                    time.click();
                }
            }
        } catch (org.openqa.selenium.StaleElementReferenceException ex) {
            List<WebElement> timeList2 = driver.findElements(By.xpath("//ul[@class='react-datepicker__time-list']/li"));
            for (WebElement time : timeList2) {
                if (time.getText().equals(timeTo)) {
                    time.click();
                }
            }
        }

        saveProfileButton.click();

        waitForPresenceOfElement(notificationFinishTimeBeforeStartTime);
        Assert.assertEquals(notificationFinishTimeBeforeStartTime.getText(), "Godzina początku aktywności profilu większa niż godzina końca aktywności");

        new Actions(driver).moveToElement(closeSection).perform();
        Thread.sleep(15000);
        closeSection.click();
        return this;
    }

    @Step
    public ProfilesPage cant_add_new_profile_with_incorrect_limits_AmountAlertWithoutMaxAmount(String profileName, String alertAmount) throws InterruptedException {
        Thread.sleep(1000);
        waitForPresenceOfElement(addNewProfileButton);

        addNewProfileButton.click();
        waitForPresenceOfElement(addProfileForm);
        verify_new_profile_form_fields_name();
        nameField.sendKeys(profileName);

        alertAmountField.sendKeys(alertAmount);

        saveProfileButton.click();

        waitForPresenceOfElement(notificationAmountAlertWithoutMaxAmount);
        Assert.assertEquals(notificationAmountAlertWithoutMaxAmount.getText(), "Kwota ostrzeżenia nie może występować bez limitu kwoty i musi być mniejsza od tego limitu");

        new Actions(driver).moveToElement(closeSection).perform();
        Thread.sleep(15000);
        closeSection.click();
        return this;
    }



    @Step
    public ProfilesPage sort_data_by_name(String nameToSearch) throws InterruptedException {
        List<WebElement> rowsWithName = driver.findElements(By.xpath("//div[@class='rt-tr-group']/descendant::div[2]"));
        new SearchFromTables(driver).sort_data_by(nameToSearch, rowsWithName, nameSearchField);
        return this;
    }

    @Step
    public ProfilesPage sort_data_by_orderAmount(String orderAmountToSearch) throws InterruptedException {
        List<WebElement> rowsWithOrderAmount = driver.findElements(By.xpath("//div[@class='rt-tr-group']/descendant::div[5]"));
        new SearchFromTables(driver).sort_data_by(orderAmountToSearch, rowsWithOrderAmount, orderAmountSearchField);
        return this;
    }

    @Step
    public ProfilesPage sort_data_by_maxAmount(String maxAmountToSearch) throws InterruptedException {
        List<WebElement> rowsWithMaxAmount = driver.findElements(By.xpath("//div[@class='rt-tr-group']/descendant::div[6]"));
        new SearchFromTables(driver).sort_data_by(maxAmountToSearch, rowsWithMaxAmount, maxAmountSearchField);
        return this;
    }

    @Step
    public ProfilesPage sort_data_by_maxAlert(String alertAmountToSearch) throws InterruptedException {
        List<WebElement> rowsWithMaxAlert = driver.findElements(By.xpath("//div[@class='rt-tr-group']/descendant::div[7]"));
        new SearchFromTables(driver).sort_data_by(alertAmountToSearch, rowsWithMaxAlert, alertAmountSearchField);
        return this;
    }

    @Step
    public ProfilesPage sort_data_by_maxTariff(String tariffAmountToSearch) throws InterruptedException {
        List<WebElement> rowsWithMaxTariff = driver.findElements(By.xpath("//div[@class='rt-tr-group']/descendant::div[8]"));
        new SearchFromTables(driver).sort_data_by(tariffAmountToSearch, rowsWithMaxTariff, maxTariffSearchField);
        return this;
    }

    @Step
    public ProfilesPage sort_data_by_maxUsersNumber(String maxUsersNumberToSearch) throws InterruptedException {
        List<WebElement> rowsWithMaxUsersNumber = driver.findElements(By.xpath("//div[@class='rt-tr-group']/descendant::div[9]"));
        new SearchFromTables(driver).sort_data_by(maxUsersNumberToSearch, rowsWithMaxUsersNumber, usersNumberSearchField);
        return this;
    }

    @Step
    public ProfilesPage sort_from_list_by_days(String days) throws InterruptedException {
        List<WebElement> rowsWithDays = driver.findElements(By.xpath("//div[@class='rt-tr-group']/descendant::div[4]"));
        WebElement selectListWithDays = driver.findElement(By.xpath("//div[@class='rt-th no-search'][2]/descendant::select"));
        new SearchFromTables(driver).sort_selecting_from_list(days, rowsWithDays, selectListWithDays);
        return this;
    }

    @Step
    public ProfilesPage sort_from_list_by_carClass(String cassClass) throws InterruptedException {
        List<WebElement> rowsWithCarClass = driver.findElements(By.xpath("//div[@class='rt-tr-group']/descendant::div[10]"));
        WebElement selectListWithCarClass = driver.findElement(By.xpath("//div[@class='rt-th no-search'][3]/descendant::select"));
        new SearchFromTables(driver).sort_selecting_from_list(cassClass, rowsWithCarClass, selectListWithCarClass);
        return this;
    }



}
