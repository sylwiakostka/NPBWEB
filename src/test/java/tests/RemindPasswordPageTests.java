package tests;


import org.testng.annotations.Test;
import pages.LoginPage;
import pages.RemindPasswordPage;


public class RemindPasswordPageTests extends BaseTests {

    @Test
    public void should_verify_remindPasswordPage() throws InterruptedException {
        new LoginPage(driver)
                .go_to_remindPasswordPage()
                .verify_remindPasswordPage()
                .verify_benefitsSection();
    }

    @Test
    public void should_change_password_and_login_as_employee() throws InterruptedException {
        String emailEmployee = "haniakot90@gmail.com";
        new LoginPage(driver)
                .go_to_remindPasswordPage();
        String newPsw = new RemindPasswordPage(driver).remind_password_and_get_new(emailEmployee);
        System.out.println(newPsw);
        new LoginPage(driver)
                .login_with_new_password(newPsw,emailEmployee)
                .verify_dashboardPge_for_employee();
    }

    @Test
    public void should_introduce_wrong_email_and_cant_login() throws InterruptedException {
        new LoginPage(driver)
                .go_to_remindPasswordPage()
                .introduce_wrong_email();
    }

    @Test
    public void should_no_introduce_email_and_cant_login() throws InterruptedException {
        new LoginPage(driver)
                .go_to_remindPasswordPage()
                .empty_email();
    }

}
