package tests;

import org.testng.annotations.Test;
import pages.*;
import utilities.DataFaker;

public class ProjectsPageTests extends BaseTests {

    @Test
    public void should_add_active_project() throws InterruptedException {
        String projectName;
        String description;
        LoginPage loginPage = new LoginPage(driver);
        loginPage
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC");
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage
                .go_to_MpkPage();
        MpkPage mpkPage = new MpkPage(driver);
        mpkPage
                .verify_MpkPage()
                .go_to_ProjectsPage();
        ProjectsPage projectsPage = new ProjectsPage(driver);
        projectsPage
                .verifyProjectsPageHeadersAndTitle()
                .verifyActiveProjectSelector()
                .clickAddProjectButtonAndVerifyForm()
                .fillActiveProjectForm();
        projectName = projectsPage.getProjectNameFromForm();
        description = projectsPage.getDescriptionFromForm();
        projectsPage
                .clickSaveButtonOnAddForm()
                .verifyIsSuccesfullAddedNotificationDisplayed()
                .verifyIsActiveProjectAdded(projectName, description);
    }

    @Test
    public void should_add_unactive_project() throws InterruptedException {
        String projectName;
        String description;
        LoginPage loginPage = new LoginPage(driver);
        loginPage
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC");
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage
                .go_to_MpkPage();
        MpkPage mpkPage = new MpkPage(driver);
        mpkPage
                .verify_MpkPage()
                .go_to_ProjectsPage();
        ProjectsPage projectsPage = new ProjectsPage(driver);
        projectsPage
                .verifyProjectsPageHeadersAndTitle()
                .verifyActiveProjectSelector()
                .clickAddProjectButtonAndVerifyForm()
                .fillUnactiveProjectForm();
        projectName = projectsPage.getProjectNameFromForm();
        description = projectsPage.getDescriptionFromForm();
        projectsPage
                .clickSaveButtonOnAddForm()
                .verifyIsSuccesfullAddedNotificationDisplayed()
                .verifyIsUnactiveProjectAdded(projectName, description);
    }

    @Test
    public void should_add_active_project_with_special_signs_in_name_and_description() throws InterruptedException {
        String projectName;
        String description;
        LoginPage loginPage = new LoginPage(driver);
        loginPage
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC");
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage
                .go_to_MpkPage();
        MpkPage mpkPage = new MpkPage(driver);
        mpkPage
                .verify_MpkPage()
                .go_to_ProjectsPage();
        ProjectsPage projectsPage = new ProjectsPage(driver);
        projectsPage
                .verifyProjectsPageHeadersAndTitle()
                .verifyActiveProjectSelector()
                .clickAddProjectButtonAndVerifyForm()
                .fillActiveProjectFormWithSpecialSigns();
        projectName = projectsPage.getProjectNameFromForm();
        description = projectsPage.getDescriptionFromForm();
        projectsPage
                .clickSaveButtonOnAddForm()
                .verifyIsSuccesfullAddedNotificationDisplayed()
                .verifyIsActiveProjectAdded(projectName, description);
    }

    @Test
    public void should_add_unactive_project_with_250_signs_in_description() throws InterruptedException {
        String projectName;
        String description;
        LoginPage loginPage = new LoginPage(driver);
        loginPage
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC");
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage
                .go_to_MpkPage();
        MpkPage mpkPage = new MpkPage(driver);
        mpkPage
                .verify_MpkPage()
                .go_to_ProjectsPage();
        ProjectsPage projectsPage = new ProjectsPage(driver);
        projectsPage
                .verifyProjectsPageHeadersAndTitle()
                .verifyActiveProjectSelector()
                .clickAddProjectButtonAndVerifyForm()
                .fillUnactiveProjectFormWith250CharsInDescription();
        projectName = projectsPage.getProjectNameFromForm();
        description = projectsPage.getDescriptionFromForm();
        projectsPage
                .clickSaveButtonOnAddForm()
                .verifyIsSuccesfullAddedNotificationDisplayed()
                .verifyIsUnactiveProjectAdded(projectName, description);
    }

    @Test
    public void should_can_not_add_active_project_with_more_than_250_signs_in_description() throws InterruptedException {
        String projectName;
        LoginPage loginPage = new LoginPage(driver);
        loginPage
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC");
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage
                .go_to_MpkPage();
        MpkPage mpkPage = new MpkPage(driver);
        mpkPage
                .verify_MpkPage()
                .go_to_ProjectsPage();
        ProjectsPage projectsPage = new ProjectsPage(driver);
        projectsPage
                .verifyProjectsPageHeadersAndTitle()
                .verifyActiveProjectSelector()
                .clickAddProjectButtonAndVerifyForm()
                .tryToAddActiveProjectWithMoreThan250CharsInDescription();
        projectName = projectsPage.getProjectNameFromForm();
        projectsPage
                .closeFormWithCrossButton()
                .verifyIsProjectNoAtMainList(projectName);
    }

    @Test
    public void should_can_not_add_active_project_without_name() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC");
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage
                .go_to_MpkPage();
        MpkPage mpkPage = new MpkPage(driver);
        mpkPage
                .verify_MpkPage()
                .go_to_ProjectsPage();
        ProjectsPage projectsPage = new ProjectsPage(driver);
        projectsPage
                .verifyProjectsPageHeadersAndTitle()
                .verifyActiveProjectSelector()
                .clickAddProjectButtonAndVerifyForm()
                .tryToAddActiveProjectWithoutProjectName();
        projectsPage
                .closeFormWithCrossButton();
    }

    @Test
    public void should_can_not_add_active_project_clicking_close_form_button_without_save() throws InterruptedException {
        String projectName;
        LoginPage loginPage = new LoginPage(driver);
        loginPage
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC");
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage
                .go_to_MpkPage();
        MpkPage mpkPage = new MpkPage(driver);
        mpkPage
                .verify_MpkPage()
                .go_to_ProjectsPage();
        ProjectsPage projectsPage = new ProjectsPage(driver);
        projectsPage
                .verifyProjectsPageHeadersAndTitle()
                .verifyActiveProjectSelector()
                .clickAddProjectButtonAndVerifyForm()
                .fillFormAndCloseWithCrossButton();
        projectName = projectsPage.getProjectNameFromForm();
        projectsPage
                .verifyIsProjectNoAtMainList(projectName);
    }

    @Test
    public void should_delete_active_project() throws InterruptedException {
        String projectName;
        LoginPage loginPage = new LoginPage(driver);
        loginPage
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC");
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage
                .go_to_MpkPage();
        MpkPage mpkPage = new MpkPage(driver);
        mpkPage
                .verify_MpkPage()
                .go_to_ProjectsPage();
        ProjectsPage projectsPage = new ProjectsPage(driver);
        int indexOfProject = 1;
        projectsPage
                .verifyProjectsPageHeadersAndTitle()
                .verifyActiveProjectSelector();
        projectName = projectsPage.getNameOfProjectActiveToDeleteOrEdit(indexOfProject);
        projectsPage
                .selectActiveProjectAndClickDelete(indexOfProject)
                .verifyDeleteFormAndDeleteProject()
                .verifyIsSuccesfullDeletedNotificationDisplayed()
                .verifyIsProjectNoAtMainList(projectName);
    }

    @Test
    public void should_delete_unactive_project() throws InterruptedException {
        String projectName;
        LoginPage loginPage = new LoginPage(driver);
        loginPage
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC");
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage
                .go_to_MpkPage();
        MpkPage mpkPage = new MpkPage(driver);
        mpkPage
                .verify_MpkPage()
                .go_to_ProjectsPage();
        ProjectsPage projectsPage = new ProjectsPage(driver);
        int indexOfProject = 1;
        projectsPage
                .verifyProjectsPageHeadersAndTitle()
                .verifyActiveProjectSelector();
        projectName = projectsPage.getNameOfProjectUnactiveToDeleteOrEdit(indexOfProject);
        System.out.println(projectName);
        projectsPage
                .selectUnactiveProjectAndClickDelete(indexOfProject)
                .verifyDeleteFormAndDeleteProject()
                .verifyIsSuccesfullDeletedNotificationDisplayed()
                .verifyIsProjectNoAtMainList(projectName);
    }


    @Test
    public void should_edit_active_project_all_data() throws InterruptedException {
        String projectNameToEdit;
        String projectNameAfterEdit;
        String projectDescriptionAfterEdit;
        LoginPage loginPage = new LoginPage(driver);
        loginPage
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC");
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage
                .go_to_MpkPage();
        MpkPage mpkPage = new MpkPage(driver);
        mpkPage
                .verify_MpkPage()
                .go_to_ProjectsPage();
        ProjectsPage projectsPage = new ProjectsPage(driver);
        int indexOfProject = 1;
        projectsPage
                .verifyProjectsPageHeadersAndTitle()
                .verifyActiveProjectSelector();
        projectNameToEdit = projectsPage.getNameOfProjectActiveToDeleteOrEdit(indexOfProject);
        projectsPage
                .selectActiveProjectAndClickEdit(indexOfProject)
                .verifyEditFormInActiveProject()
                .editAllDataInProject();
        projectNameAfterEdit = projectsPage.getProjectNameFromForm();
        projectDescriptionAfterEdit = projectsPage.getDescriptionFromForm();
        projectsPage
                .clickSaveButtonOnEditForm()
                .verifyIsSuccesfullEditedNotificationDisplayed()
                .verifyIsUnactiveProjectAdded(projectNameAfterEdit, projectDescriptionAfterEdit)
                .verifyIsProjectNoAtMainList(projectNameToEdit);
    }

    @Test
    public void should_edit_unactive_project_all_data() throws InterruptedException {
        String projectNameToEdit;
        String projectNameAfterEdit;
        String projectDescriptionAfterEdit;
        LoginPage loginPage = new LoginPage(driver);
        loginPage
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC");
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage
                .go_to_MpkPage();
        MpkPage mpkPage = new MpkPage(driver);
        mpkPage
                .verify_MpkPage()
                .go_to_ProjectsPage();
        ProjectsPage projectsPage = new ProjectsPage(driver);
        int indexOfProject = 1;
        projectsPage
                .verifyProjectsPageHeadersAndTitle()
                .verifyActiveProjectSelector();
        projectNameToEdit = projectsPage.getNameOfProjectUnactiveToDeleteOrEdit(indexOfProject);
        projectsPage
                .selectUnactiveProjectAndClickEdit(indexOfProject)
                .verifyEditFormInUnactiveProject()
                .editAllDataInProject();
        projectNameAfterEdit = projectsPage.getProjectNameFromForm();
        projectDescriptionAfterEdit = projectsPage.getDescriptionFromForm();
        projectsPage
                .clickSaveButtonOnEditForm()
                .verifyIsSuccesfullEditedNotificationDisplayed()
                .verifyIsActiveProjectAdded(projectNameAfterEdit, projectDescriptionAfterEdit)
                .verifyIsProjectNoAtMainList(projectNameToEdit);
    }

    @Test
    public void should_try_to_edit_active_project_with_more_than_250_signs_in_description_and_without_project_name() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC");
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage
                .go_to_MpkPage();
        MpkPage mpkPage = new MpkPage(driver);
        mpkPage
                .verify_MpkPage()
                .go_to_ProjectsPage();
        ProjectsPage projectsPage = new ProjectsPage(driver);
        int indexOfProject = 1;
        projectsPage
                .verifyProjectsPageHeadersAndTitle()
                .verifyActiveProjectSelector()
                .selectActiveProjectAndClickEdit(indexOfProject)
                .verifyEditFormInActiveProject()
                .fillFormWithProjectName("")
                .fillFormWithDescription(DataFaker.generate_text_with_exact_amount_of_characters(300))
                .clickSaveButtonOnEditForm()
                .closeFormWithCrossButton()
                .verifyIsAbsenceOfProjectNameNotificationDisplayed()
                .verifyIsTooLongDescriptionInFormNotificationDisplayed();

    }

    @Test
    public void should_try_to_add_project_with_repeated_name() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC");
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage
                .go_to_MpkPage();
        MpkPage mpkPage = new MpkPage(driver);
        mpkPage
                .verify_MpkPage()
                .go_to_ProjectsPage();
        ProjectsPage projectsPage = new ProjectsPage(driver);
        int indexOfProject = 1;
        projectsPage
                .verifyProjectsPageHeadersAndTitle()
                .verifyActiveProjectSelector();
        String existingProjectName = projectsPage.getProjectNameFromMainList(indexOfProject);
        projectsPage
                .selectActiveProjectAndClickEdit(indexOfProject)
                .verifyEditFormInActiveProject()
                .fillFormWithProjectName(existingProjectName)
                .clickSaveButtonOnEditForm()
                .verifyIsProjectNameRepetitionNotificationDisplayed()
                .closeFormWithCrossButton();
    }

    @Test
    public void should_sort_by_active_projects() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC");
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage
                .go_to_MpkPage();
        MpkPage mpkPage = new MpkPage(driver);
        mpkPage
                .verify_MpkPage()
                .go_to_ProjectsPage();
        ProjectsPage projectsPage = new ProjectsPage(driver);
        projectsPage
                .verifyProjectsPageHeadersAndTitle()
                .verifyActiveProjectSelector()
                .selectOnlyActiveProjects()
                .verifyIsOnlyActiveProjectsSelected();
    }

    @Test
    public void should_sort_by_unactive_projects() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC");
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage
                .go_to_MpkPage();
        MpkPage mpkPage = new MpkPage(driver);
        mpkPage
                .verify_MpkPage()
                .go_to_ProjectsPage();
        ProjectsPage projectsPage = new ProjectsPage(driver);
        projectsPage
                .verifyProjectsPageHeadersAndTitle()
                .verifyActiveProjectSelector()
                .selectOnlyUnactiveProjects()
                .verifyIsOnlyUnactiveProjectsSelected();
    }
}
