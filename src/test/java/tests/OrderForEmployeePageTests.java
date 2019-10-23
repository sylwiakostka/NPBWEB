package tests;

import org.testng.annotations.Test;
import pages.LoginPage;


public class OrderForEmployeePageTests extends BaseTests {

    @Test
    public void should_order_taxi() throws InterruptedException {
        new LoginPage(driver)
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC")
                .verify_dashboardPge_for_admin("ABC")
                .go_to_orderForEmployeePage()
                .verifyOrderForEmployeePage()
                .verifyOrderTaxiPageLabelNames()
                .setPassengerName("Zuzia Nowak")
                .setStartAddressByGeolocation()
                .setFinalAddress("Hoża 2, Warszawa")
                .scrollDownPage()
//                .selectOrderTime_now()
                .setProject_firstFromList()
                .addComment("nic a nic")
                .clickOrderButton()
                .verifyConfirmationOrderPopupWithAllInformation();
    }


}
