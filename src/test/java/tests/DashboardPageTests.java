package tests;


import com.google.zxing.NotFoundException;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.io.IOException;

public class DashboardPageTests extends BaseTests {


    @Test
    public void should_verify_qr_code_and_download_app_section() throws IOException, NotFoundException {
        new LoginPage(driver)
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC")
                .verify_dashboardPge_for_admin("ABC")
                .verify_qr_code()
                .verify_download_app_section_on_menu();

    }
}
