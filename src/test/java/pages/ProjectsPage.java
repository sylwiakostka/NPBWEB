package pages;

import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import utilities.DataFaker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static utilities.NumberFormatter.changeToDecimalFormat;

public class ProjectsPage extends BasePage {
    public ProjectsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h3[contains(.,'Zarządzanie projektami - firma')]")
    private WebElement projectsHeader;

    @FindBy(xpath = "//div[@class='rt-resizable-header-content']")
    List<WebElement> listOfProjectListHeaders;

    @FindBy(xpath = "//button[@class='btn small violet']")
    private WebElement addProjectButton;

    @FindBy(xpath = "//div[@class='inputs']/h3")
    private WebElement addProjectFormHeader;

    @FindBy(xpath = "//div[@class='input']")
    List<WebElement> listOfFormElementNames;

    @FindBy(id = "cb2")
    private WebElement isActiveInfo;

    @FindBy(id = "name")
    private WebElement projectNameFieldForm;

    @FindBy(id = "description")
    private WebElement projectDescriptionFieldForm;

    @FindBy(xpath = "//label[@for='cb2']")
    private WebElement isActiveCheckbox;

    @FindBy(id = "button")
    private WebElement saveButtonInForm;

    @FindBy(xpath = "//div[@class='notification red notification-enter-done']//p[.= 'Opis projektu zbyt długi, maksymalnie 250 znaków']")
    private WebElement notificationAboutTooLongDescriptionInForm;

    @FindBy(xpath = "//div[@class='notification red notification-enter-done']//p[.= 'Brak nazwy projektu']")
    private WebElement notificationAboutAbsenceOfProjectName;

    @FindBy(xpath = "//div[@class='close']")
    private WebElement closeFormButton;

    @FindBy(xpath = "//div[@class='rt-tr-group']//div[@class='rt-td']")
    List<WebElement> listOfRowsInProjectsPage;

    @FindBy(xpath = "//div[@class='notification green notification-enter-done']//p[.='Pomyślnie dodano']")
    private WebElement notificationSucessfullAdded;

    @FindBy(xpath = "//div[@class='inputs delete']/h3")
    private WebElement deleteFormHeader;

    @FindBy(xpath = "//div[@class='inputs delete']//strong")
    private WebElement deleteFormInfo;

    @FindBy(xpath = "//div[@class='buttons']//span[text()='Usuń']")
    private WebElement deleteFormButton;

    @FindBy(xpath = "//div[@class='notification green notification-enter-done']//p[.='Pomyślnie usunięto']")
    private WebElement notificationSucessfullDeleted;

    @FindBy(xpath = "//button[@class='btn yellow small']//span[text()='Zapisz']")
    private WebElement saveEditedFormButton;

    @FindBy(xpath = "//div[@class='inputs']/h3")
    private WebElement editFormHeader;

    @FindBy(xpath = "//div[@class='notification green notification-enter-done']//p[.='Pomyślnie zapisano']")
    private WebElement notificationSucessfullEdited;

    @FindBy(xpath = "//div[@class='rt-tr-group']//div[@class='rt-td'][1]")
    private List<WebElement> listOfRowsWithNamesInProjectsPage;

    @FindBy(xpath = "//div[@class='notification red notification-enter-done']//p[.= 'Projekt o takiej nazwie już istnieje']")
    private WebElement notificationAboutProjectNameRepetition;

    @FindBy(xpath = "//div[@class='rt-th no-search']/select")
    private WebElement isActiveSelector;

    @FindBy(xpath = "//div[@class='check-box']/input")
    private List<WebElement> listOfRowsWithIsActiveCheckbox;

    @Step
    public ProjectsPage verifyProjectsPageHeadersAndTitle() throws InterruptedException {

        Thread.sleep(3000);
        waitForPresenceOfElement(projectsHeader);
        waitForPresenceOfElements(listOfProjectListHeaders);
        Assert.assertTrue(projectsHeader.getText().contains("Zarządzanie projektami - firma"));

        List<String> expectedTexts = Arrays.asList("Nazwa", "Opis", "Czy aktywny?", "Opcje");
        List<String> actualTexts = new ArrayList<String>();
        for (WebElement header : listOfProjectListHeaders) {
            actualTexts.add(header.getText());
        }
        Assert.assertEquals(actualTexts.toString(), expectedTexts.toString());

        return this;
    }

    @Step
    public ProjectsPage clickAddProjectButtonAndVerifyForm() {
        addProjectButton.click();
        waitForVisibilityOfElement(addProjectFormHeader);
        Assert.assertTrue(addProjectFormHeader.getText().equals("Dodaj"));

        List<String> expectedTexts = Arrays.asList("Nazwa", "Opis", "Czy aktywny?");
        List<String> actualTexts = new ArrayList<String>();
        for (WebElement header : listOfFormElementNames) {
            actualTexts.add(header.getText());
        }
        Assert.assertEquals(actualTexts.toString(), expectedTexts.toString());

        String isActive = isActiveInfo.getAttribute("checked");
        Assert.assertEquals(isActive, "true");

        return this;
    }

    @Step
    public ProjectsPage clickSaveButtonOnAddForm() {
        saveButtonInForm.click();
        return this;
    }

    @Step
    public ProjectsPage fillActiveProjectForm() {
        projectNameFieldForm.sendKeys(DataFaker.generate_fake_word());
        projectDescriptionFieldForm.sendKeys(DataFaker.generate_text_with_range_of_characters(10, 20));
        return this;
    }

    @Step
    public ProjectsPage fillUnactiveProjectForm() {
        projectNameFieldForm.sendKeys(DataFaker.generate_fake_word());
        projectDescriptionFieldForm.sendKeys(DataFaker.generate_text_with_range_of_characters(10, 20));
        isActiveCheckbox.click();
        return this;
    }

    @Step
    public ProjectsPage fillActiveProjectFormWithSpecialSigns() {
        projectNameFieldForm.sendKeys(DataFaker.generate_fake_word_with_only_special_signs());
        projectDescriptionFieldForm.sendKeys(DataFaker.generate_fake_word_with_only_special_signs() + " " + DataFaker.generate_fake_word_with_only_special_signs());
        return this;
    }

    @Step
    public ProjectsPage fillUnactiveProjectFormWith250CharsInDescription() {
        projectNameFieldForm.sendKeys(DataFaker.generate_fake_word());
        projectDescriptionFieldForm.sendKeys(DataFaker.generate_text_with_exact_amount_of_characters(250));
        isActiveCheckbox.click();
        return this;
    }

    @Step
    public ProjectsPage tryToAddActiveProjectWithMoreThan250CharsInDescription() {
        projectNameFieldForm.sendKeys(DataFaker.generate_fake_word());
        projectDescriptionFieldForm.sendKeys(DataFaker.generate_text_with_range_of_characters(300, 400));
        saveButtonInForm.click();
        waitForVisibilityOfElement(notificationAboutTooLongDescriptionInForm);
        Assert.assertEquals("Opis projektu zbyt długi, maksymalnie 250 znaków", notificationAboutTooLongDescriptionInForm.getText());
        return this;
    }

    @Step
    public ProjectsPage tryToAddActiveProjectWithoutProjectName() {
        saveButtonInForm.click();
        waitForVisibilityOfElement(notificationAboutAbsenceOfProjectName);
        Assert.assertEquals("Brak nazwy projektu", notificationAboutAbsenceOfProjectName.getText());
        return this;
    }


    @Step
    public ProjectsPage fillFormAndCloseWithCrossButton() {
        projectNameFieldForm.sendKeys(DataFaker.generate_fake_word());
        projectDescriptionFieldForm.sendKeys(DataFaker.generate_text_with_range_of_characters(10, 20));
        closeFormButton.click();
        return this;
    }

    @Step
    public ProjectsPage closeFormWithCrossButton() {
        try {
            closeFormButton.click();
        } catch (StaleElementReferenceException ex) {
            closeFormButton.click();
        }
        return this;
    }

    @Step
    public ProjectsPage verifyIsActiveProjectAdded(String projectName, String description) throws InterruptedException {
        Thread.sleep(3000);
        Assert.assertTrue(isProjectPresent(projectName));
        for (WebElement row : listOfRowsInProjectsPage) {
            if (row.getText().equals(projectName)) {
                WebElement descriptionRow = row.findElement(By.xpath("./following-sibling::div[1]"));
                WebElement isActiveRow = row.findElement(By.xpath("./following-sibling::div[2]//input"));
                WebElement optionsRow = row.findElement(By.xpath("./following-sibling::div[3]"));

                Assert.assertEquals(row.getText(), projectName);
                Assert.assertEquals(descriptionRow.getText(), description);
                Assert.assertEquals(isActiveRow.getAttribute("checked"), "true");

                Assert.assertTrue(optionsRow.getText().contains("Edytuj"));
                Assert.assertTrue(optionsRow.getText().contains("Usuń"));

            }
        }
        return this;
    }

    @Step
    public ProjectsPage verifyIsUnactiveProjectAdded(String projectName, String description) throws InterruptedException {
        Thread.sleep(3000);
        Assert.assertTrue(isProjectPresent(projectName));
        for (WebElement row : listOfRowsInProjectsPage) {
            if (row.getText().equals(projectName)) {
                WebElement descriptionRow = row.findElement(By.xpath("./following-sibling::div[1]"));
                WebElement isActiveRow = row.findElement(By.xpath("./following-sibling::div[2]//input"));
                WebElement optionsRow = row.findElement(By.xpath("./following-sibling::div[3]"));

                Assert.assertEquals(row.getText(), projectName);
                Assert.assertEquals(descriptionRow.getText(), description);
                Assert.assertNull(isActiveRow.getAttribute("checked"));

                Assert.assertTrue(optionsRow.getText().contains("Edytuj"));
                Assert.assertTrue(optionsRow.getText().contains("Usuń"));
            }
        }
        return this;
    }

    @Step
    public String getProjectNameFromForm() {
        return projectNameFieldForm.getAttribute("value");
    }

    @Step
    public String getDescriptionFromForm() {
        return projectDescriptionFieldForm.getAttribute("value");
    }

    @Step
    public ProjectsPage verifyIsSuccesfullAddedNotificationDisplayed() {
        waitForVisibilityOfElement(notificationSucessfullAdded);
        Assert.assertEquals("Pomyślnie dodano", notificationSucessfullAdded.getText());
        return this;
    }


    private boolean isProjectPresent(String projectName) {
        boolean isProjectPresent = false;
        waitForVisibilityOfElements(listOfRowsInProjectsPage);
        for (WebElement row : listOfRowsInProjectsPage) {
            if (row.getText().equals(projectName)) {
                isProjectPresent = true;
            }
        }
        return isProjectPresent;
    }

    @Step
    public ProjectsPage verifyIsProjectNoAtMainList(String projectName) {
        Assert.assertFalse(isProjectPresent(projectName));
        return this;
    }

    @Step
    public ProjectsPage selectActiveProjectAndClickDelete(int indexOfProject) {
        List<WebElement> isActiveRowsOnProjectsPage_activeProjects = driver.findElements(By.xpath("//div[@class='check-box']/input[@checked]"));
        WebElement selectedProject = isActiveRowsOnProjectsPage_activeProjects.get(indexOfProject);
        WebElement deleteButtonInRow = selectedProject.findElement(By.xpath("./following::div[1]//span[text()='Usuń']"));
        waitForVisibilityOfElement(deleteButtonInRow);
        try {
            deleteButtonInRow.click();
        } catch (StaleElementReferenceException ex) {
            deleteButtonInRow.click();
        }
        return this;
    }

    @Step
    public ProjectsPage selectUnactiveProjectAndClickDelete(int indexOfProject) {
        List<WebElement> isActiveRowsOnProjectsPage_unactiveProjects = driver.findElements(By.xpath("//div[@class='check-box']/input[not(@checked)]"));
        WebElement selectedProject = isActiveRowsOnProjectsPage_unactiveProjects.get(indexOfProject);
        WebElement deleteButtonInRow = selectedProject.findElement(By.xpath("./following::div[1]//span[text()='Usuń']"));
        waitForVisibilityOfElement(deleteButtonInRow);
        try {
            deleteButtonInRow.click();
        } catch (StaleElementReferenceException ex) {
            deleteButtonInRow.click();
        }
        return this;
    }


    @Step
    public String getNameOfProjectActiveToDeleteOrEdit(int indexOfProject) {
        List<WebElement> isActiveRowsOnProjectsPage_activeProjects = driver.findElements(By.xpath("//div[@class='check-box']/input[@checked]"));
        WebElement selectedProject = isActiveRowsOnProjectsPage_activeProjects.get(indexOfProject);
        WebElement projectNameRow = selectedProject.findElement(By.xpath("./preceding::div[2]"));
        String projectName = projectNameRow.getText();
        return projectName;
    }

    @Step
    public String getNameOfProjectUnactiveToDeleteOrEdit(int indexOfProject) {
        List<WebElement> isActiveRowsOnProjectsPage_unactiveProjects = driver.findElements(By.xpath("//div[@class='check-box']/input[not(@checked)]"));
        WebElement selectedProject = isActiveRowsOnProjectsPage_unactiveProjects.get(indexOfProject);
        WebElement projectNameRow = selectedProject.findElement(By.xpath("./preceding::div[2]"));
        String projectName = projectNameRow.getText();
        return projectName;
    }

    @Step
    public ProjectsPage verifyDeleteFormAndDeleteProject() {
        Assert.assertEquals("Usuwanie", deleteFormHeader.getText());
        Assert.assertEquals("Czy na pewno chcesz usunąć projekt?", deleteFormInfo.getText());
        deleteFormButton.click();
        return this;
    }

    @Step
    public ProjectsPage verifyIsSuccesfullDeletedNotificationDisplayed() {
        waitForVisibilityOfElement(notificationSucessfullDeleted);
        Assert.assertEquals("Pomyślnie usunięto", notificationSucessfullDeleted.getText());
        return this;
    }

    @Step
    public ProjectsPage selectActiveProjectAndClickEdit(int indexOfProject) {
        List<WebElement> isActiveRowsOnProjectsPage_activeProjects = driver.findElements(By.xpath("//div[@class='check-box']/input[@checked]"));
        WebElement selectedProject = isActiveRowsOnProjectsPage_activeProjects.get(indexOfProject);
        WebElement editButtonInRow = selectedProject.findElement(By.xpath("./following::div[1]//span[text()='Edytuj']"));
        waitForVisibilityOfElement(editButtonInRow);
        try {
            editButtonInRow.click();
        } catch (StaleElementReferenceException ex) {
            editButtonInRow.click();
        }
        return this;
    }

    @Step
    public ProjectsPage selectUnactiveProjectAndClickEdit(int indexOfProject) {
        List<WebElement> isActiveRowsOnProjectsPage_unactiveProjects = driver.findElements(By.xpath("//div[@class='check-box']/input[not(@checked)]"));
        WebElement selectedProject = isActiveRowsOnProjectsPage_unactiveProjects.get(indexOfProject);
        WebElement editButtonInRow = selectedProject.findElement(By.xpath("./following::div[1]//span[text()='Edytuj']"));
        waitForVisibilityOfElement(editButtonInRow);
        try {
            editButtonInRow.click();
        } catch (StaleElementReferenceException ex) {
            editButtonInRow.click();
        }
        return this;
    }


    @Step
    public ProjectsPage verifyEditFormInActiveProject() {
        waitForVisibilityOfElement(editFormHeader);
        Assert.assertTrue(editFormHeader.getText().equals("Edytuj"));

        while (projectNameFieldForm.getAttribute("value").length() > 0) {
            projectNameFieldForm.sendKeys(Keys.BACK_SPACE);
        }
        while (projectDescriptionFieldForm.getAttribute("value").length() > 0) {
            projectDescriptionFieldForm.sendKeys(Keys.BACK_SPACE);
        }

        List<String> expectedTexts = Arrays.asList("Nazwa", "Opis", "Czy aktywny?");
        List<String> actualTexts = new ArrayList<String>();
        for (WebElement header : listOfFormElementNames) {
            actualTexts.add(header.getText());
        }
        Assert.assertEquals(actualTexts.toString(), expectedTexts.toString());

        String isActive = isActiveInfo.getAttribute("checked");
        Assert.assertEquals(isActive, "true");
        return this;
    }

    @Step
    public ProjectsPage verifyEditFormInUnactiveProject() {
        waitForVisibilityOfElement(editFormHeader);
        Assert.assertTrue(editFormHeader.getText().equals("Edytuj"));

        while (projectNameFieldForm.getAttribute("value").length() > 0) {
            projectNameFieldForm.sendKeys(Keys.BACK_SPACE);
        }
        while (projectDescriptionFieldForm.getAttribute("value").length() > 0) {
            projectDescriptionFieldForm.sendKeys(Keys.BACK_SPACE);
        }

        List<String> expectedTexts = Arrays.asList("Nazwa", "Opis", "Czy aktywny?");
        List<String> actualTexts = new ArrayList<String>();
        for (WebElement header : listOfFormElementNames) {
            actualTexts.add(header.getText());
        }
        Assert.assertEquals(actualTexts.toString(), expectedTexts.toString());

        String isActive = isActiveInfo.getAttribute("checked");
        Assert.assertNull(isActive);
        return this;
    }

    @Step
    public ProjectsPage editAllDataInProject() {
        while (projectNameFieldForm.getAttribute("value").length() > 0) {
            projectNameFieldForm.sendKeys(Keys.BACK_SPACE);
        }

        while (projectDescriptionFieldForm.getAttribute("value").length() > 0) {
            projectDescriptionFieldForm.sendKeys(Keys.BACK_SPACE);
        }

        projectNameFieldForm.sendKeys(DataFaker.generate_fake_word());
        projectDescriptionFieldForm.sendKeys(DataFaker.generate_fake_word());
        isActiveCheckbox.click();
        return this;
    }

    @Step
    public ProjectsPage fillFormWithProjectName(String projectName) {
        while (projectNameFieldForm.getAttribute("value").length() > 0) {
            projectNameFieldForm.sendKeys(Keys.BACK_SPACE);
        }

        projectNameFieldForm.sendKeys(projectName);
        return this;
    }

    @Step
    public ProjectsPage fillFormWithDescription(String description) {

        while (projectDescriptionFieldForm.getAttribute("value").length() > 0) {
            projectDescriptionFieldForm.sendKeys(Keys.BACK_SPACE);
        }

        projectDescriptionFieldForm.sendKeys(description);
        return this;
    }


    @Step
    public ProjectsPage clickSaveButtonOnEditForm() {
        saveEditedFormButton.click();
        return this;
    }

    @Step
    public ProjectsPage verifyIsSuccesfullEditedNotificationDisplayed() {
        waitForVisibilityOfElement(notificationSucessfullEdited);
        Assert.assertEquals("Pomyślnie zapisano", notificationSucessfullEdited.getText());
        return this;
    }


    @Step
    public ProjectsPage verifyIsTooLongDescriptionInFormNotificationDisplayed() {
        waitForVisibilityOfElement(notificationAboutTooLongDescriptionInForm);
        Assert.assertEquals("Opis projektu zbyt długi, maksymalnie 250 znaków", notificationAboutTooLongDescriptionInForm.getText());
        return this;
    }

    @Step
    public ProjectsPage verifyIsAbsenceOfProjectNameNotificationDisplayed() {
        waitForVisibilityOfElement(notificationAboutAbsenceOfProjectName);
        Assert.assertEquals("Brak nazwy projektu", notificationAboutAbsenceOfProjectName.getText());
        return this;
    }


    @Step
    public String getProjectNameFromMainList(int index) {
        String nameOfSelectedProject = listOfRowsWithNamesInProjectsPage.get(index).getText();
        return nameOfSelectedProject;
    }

    @Step
    public ProjectsPage verifyIsProjectNameRepetitionNotificationDisplayed() {
        waitForVisibilityOfElement(notificationAboutProjectNameRepetition);
        Assert.assertEquals("Projekt o takiej nazwie już istnieje", notificationAboutProjectNameRepetition.getText());
        return this;
    }

    @Step
    public ProjectsPage verifyActiveProjectSelector() {
        Select showActiveSelector = new Select(isActiveSelector);
        WebElement firstSelectedOptions = showActiveSelector.getFirstSelectedOption();
        Assert.assertEquals("Pokaż wszystkie", firstSelectedOptions.getText());

        List<String> expectedTexts = Arrays.asList("Pokaż wszystkie", "Aktywne", "Nieaktywne");
        List<String> actualTexts = new ArrayList<String>();

        List<WebElement> listOfOptions = showActiveSelector.getOptions();
        for (WebElement option : listOfOptions) {
            actualTexts.add(option.getText());
        }
        Assert.assertEquals(actualTexts.toString(), expectedTexts.toString());
        return this;
    }

    @Step
    public ProjectsPage selectOnlyActiveProjects() {
        waitForVisibilityOfElement(isActiveSelector);
        Select showActiveSelector = new Select(isActiveSelector);
        try {
            showActiveSelector.selectByVisibleText("Aktywne");
        } catch (StaleElementReferenceException ex) {
            showActiveSelector.selectByVisibleText("Aktywne");
        }
        return this;
    }

    @Step
    public ProjectsPage selectOnlyUnactiveProjects() {
        Select showActiveSelector = new Select(isActiveSelector);
        showActiveSelector.selectByVisibleText("Nieaktywne");
        return this;
    }


    private boolean isShowActiveProjectSelected() throws InterruptedException {
        boolean isProjectPresent = false;
        Thread.sleep(500);
        List<String> checkboxStatuses = new ArrayList<String>();
        for (WebElement row : listOfRowsWithIsActiveCheckbox) {
            checkboxStatuses.add(row.getAttribute("checked"));
        }
        System.out.println(checkboxStatuses);

        for (int i = 1; i < checkboxStatuses.size(); i++) {
            if (checkboxStatuses.get(i).equals("true")) {
                isProjectPresent = true;
            }
           else if (checkboxStatuses.get(i).equals("null")) {
                return false;
            }
        }

        System.out.println(isProjectPresent);
        return isProjectPresent;
    }

    @Step
    public ProjectsPage verifyIsOnlyActiveProjectsSelected() throws InterruptedException {
        Assert.assertTrue(isShowActiveProjectSelected());
        return this;
    }

    @Step
    public ProjectsPage verifyIsOnlyUnactiveProjectsSelected() throws InterruptedException {
        Assert.assertFalse(isShowActiveProjectSelected());
        return this;
    }


}
