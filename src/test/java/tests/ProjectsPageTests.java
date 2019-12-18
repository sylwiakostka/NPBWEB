package tests;

import org.testng.annotations.Test;
import pages.*;

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
                .clickAddProjectButtonAndVerifyForm()
                .fillActiveProjectForm();
        projectName = projectsPage.getProjectNameFromForm();
        description = projectsPage.getDescriptionFromForm();
        projectsPage
                .clickSaveButtonOnForm()
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
                .clickAddProjectButtonAndVerifyForm()
                .fillUnactiveProjectForm();
        projectName = projectsPage.getProjectNameFromForm();
        description = projectsPage.getDescriptionFromForm();
        projectsPage
                .clickSaveButtonOnForm()
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
                .clickAddProjectButtonAndVerifyForm()
                .fillActiveProjectFormWithSpecialSigns();
        projectName = projectsPage.getProjectNameFromForm();
        description = projectsPage.getDescriptionFromForm();
        projectsPage
                .clickSaveButtonOnForm()
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
                .clickAddProjectButtonAndVerifyForm()
                .fillUnactiveProjectFormWith250CharsInDescription();
        projectName = projectsPage.getProjectNameFromForm();
        description = projectsPage.getDescriptionFromForm();
        projectsPage
                .clickSaveButtonOnForm()
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
                .verifyProjectsPageHeadersAndTitle();
        projectName = projectsPage.getNameOfProjectActiveToDelete(indexOfProject);
        projectsPage.selectActiveProjectAndClickDelete(indexOfProject)
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
                .verifyProjectsPageHeadersAndTitle();
        projectName = projectsPage.getNameOfProjectUnactiveToDelete(indexOfProject);
        System.out.println(projectName);
        projectsPage
                .selectUnactiveProjectAndClickDelete(indexOfProject)
                .verifyDeleteFormAndDeleteProject()
                .verifyIsSuccesfullDeletedNotificationDisplayed()
                .verifyIsProjectNoAtMainList(projectName);
    }
}
