package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import pages.LoginPage;
import utilities.LogUsersDataProvider;


@Feature("LogIn Test")
public class LoginPageTests extends BaseTests {

    @Test
    public void should_verify_loginPage() {
        new LoginPage(driver)
                .verify_loginPage()
                .verify_benefitsSection();
    }

    @Test
    public void should_login_as_superAdmin_and_logout() throws InterruptedException {
        new LoginPage(driver)
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC")
                .verify_dashboardPge_for_admin("ABC")
                .logout();

    }

    @Test(dataProvider = "correctDataLogin", dataProviderClass = LogUsersDataProvider.class)
    public void should_login_as_employee_and_logout(String username, String password, String businessPartnerName)  {
        new LoginPage(driver)
                .verify_loginPage()
                .login_as_employee(username, password)
                .verify_dashboardPge_for_employee(businessPartnerName)
                .logout();

    }


    @Test(dataProvider = "correctDataFromExcel", dataProviderClass = LogUsersDataProvider.class)
    public void should_login_as_employee_and_logout_excel(String username, String password, String businessPartnerName)  {
        new LoginPage(driver)
                .verify_loginPage()
                .login_as_employee(username, password)
                .verify_dashboardPge_for_employee(businessPartnerName)
                .logout();

    }

    @Test
    public void should_cant_login_with_correct_login_and_without_password() {
        new LoginPage(driver)
                .verify_loginPage()
                .try_login_with_correct_login_and_without_password();

    }

    @Test
    public void should_cant_login_without_login_and_correct_password() {
        new LoginPage(driver)
                .verify_loginPage()
                .try_login_without_login_and_correct_password();
    }

    @Test
    public void should_cant_login_with_incorrect_login_and_correct_password() {
        new LoginPage(driver)
                .verify_loginPage()
                .try_login_with_incorrect_login_and_correct_password();
    }

    @Test
    public void should_cant_login_with_correct_login_and_incorrect_password() {
        new LoginPage(driver)
                .verify_loginPage()
                .try_login_with_correct_login_and_incorrect_password();
    }

    @Test
    public void should_cant_login_without_login_and_password() {
        new LoginPage(driver)
                .verify_loginPage()
                .try_login_without_login_and_password();
    }

}
