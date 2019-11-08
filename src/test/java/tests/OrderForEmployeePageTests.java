package tests;

import org.testng.annotations.Test;
import pages.LoginPage;

import java.util.Arrays;
import java.util.List;


public class OrderForEmployeePageTests extends BaseTests {

    @Test
    public void should_order_taxi_now() throws InterruptedException {
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
                .setProject_firstFromList()
                .addComment("nic a nic")
                .clickOrderButton()
                .verifyConfirmationOrderPopupWithAllInformation()
                .acceptConfirmationOrderPopupAndVerifyEmptyFieldsOnPageAfterOrder();
    }

    @Test
    public void should_order_taxi_future_today() throws InterruptedException {
        new LoginPage(driver)
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC")
                .verify_dashboardPge_for_admin("ABC")
                .go_to_orderForEmployeePage()
                .verifyOrderForEmployeePage()
                .verifyOrderTaxiPageLabelNames()
                .setPassengerName("Kuba Mors")
                .setStartAddress("Wałbrzyska 5, Warszawa")
                .addPassengers(5)
                .scrollDownPage()
                .selectOrderTime_future_add_hours_from_now(4)
                .clickOrderButton()
                .verifyConfirmationOrderPopupWithOnlyRequiredInformation()
                .acceptConfirmationOrderPopupAndVerifyEmptyFieldsOnPageAfterOrder();
    }

    @Test
    public void should_order_taxi_future_nextDay() throws InterruptedException {
        new LoginPage(driver)
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC")
                .verify_dashboardPge_for_admin("ABC")
                .go_to_orderForEmployeePage()
                .verifyOrderForEmployeePage()
                .verifyOrderTaxiPageLabelNames()
                .setPassengerName("Kuba Mors")
                .setStartAddress("Wałbrzyska 5, Warszawa")
                .scrollDownPage()
                .selectOrderTime_future_add_days_from_now(80)
                .clickOrderButton()
                .verifyConfirmationOrderPopupWithOnlyRequiredInformation()
                .acceptConfirmationOrderPopupAndVerifyEmptyFieldsOnPageAfterOrder();
    }

    @Test
    public void should_order_more_than_one_taxi_now() throws InterruptedException {
        List<String> employeesToOrderTaxi = Arrays.asList("Julek Angielski", "Kuba Mors", "Zuzia Nowak");
        new LoginPage(driver)
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC")
                .verify_dashboardPge_for_admin("ABC")
                .go_to_orderForEmployeePage()
                .verifyOrderForEmployeePage()
                .verifyOrderTaxiPageLabelNames()
                .orderMoreThanOneTaxi(employeesToOrderTaxi)
                .setStartAddressByGeolocation()
                .verifyIsFinalAddressLabelAbsent()
                .scrollDownPage()
                .clickOrderButton()
                .verifyConfirmationOrderPopupWithOnlyRequiredInformationAndMoreThanOneTaxi();
    }

    @Test
    public void should_cant_order_without_passengerName_and_address() throws InterruptedException {
        new LoginPage(driver)
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC")
                .verify_dashboardPge_for_admin("ABC")
                .go_to_orderForEmployeePage()
                .verifyOrderForEmployeePage()
                .verifyOrderTaxiPageLabelNames()
                .clickOrderButton()
                .verifyNotificationLackOfPassengerName()
                .verifyNotificationLackOfCityInAddress()
                .verifyNotificationLackOfStreetInAddress()
                .verifyNotificationLackOfCoordinates()
                .verifyNotificationLackOfBuildingNumberInAddress();
    }

    @Test
    public void should_cant_order_without_passengerName() throws InterruptedException {
        new LoginPage(driver)
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC")
                .verify_dashboardPge_for_admin("ABC")
                .go_to_orderForEmployeePage()
                .verifyOrderForEmployeePage()
                .verifyOrderTaxiPageLabelNames()
                .setStartAddressByGeolocation()
                .setProject_firstFromList()
                .addComment("teścik")
                .clickOrderButton()
                .verifyNotificationLackOfPassengerName();
    }

    @Test
    public void try_order_adding_parts_of_address() throws InterruptedException {
        new LoginPage(driver)
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC")
                .verify_dashboardPge_for_admin("ABC")
                .go_to_orderForEmployeePage()
                .verifyOrderForEmployeePage()
                .verifyOrderTaxiPageLabelNames()
                .setPassengerName("Zuzia Nowak")
                .setProject_firstFromList()
                .addComment("teścik")
                .clickOrderButton()
                .verifyNotificationLackOfCityInAddress()
                .verifyNotificationLackOfStreetInAddress()
                .verifyNotificationLackOfCoordinates()
                .verifyNotificationLackOfBuildingNumberInAddress()

                .setStartAddress("Warszawa")
                .clickOrderButton()
                .verifyNotificationLackOfStreetInAddress()
                .verifyNotificationLackOfBuildingNumberInAddress()

                .setStartAddress("Praska, Warszawa")
                .clickOrderButton()
                .verifyNotificationLackOfBuildingNumberInAddress()

                .setStartAddress("Praska 1, Warszawa")
                .setFinalAddress("Jana Olbrachta 3, Warszawa")
                .clickOrderButton()
                .verifyConfirmationOrderPopupWithAllInformation()
                .acceptConfirmationOrderPopupAndVerifyEmptyFieldsOnPageAfterOrder();
    }

    @Test
    public void try_order_without_address_and_clicking_many_times_order_button() throws InterruptedException {
        new LoginPage(driver)
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC")
                .verify_dashboardPge_for_admin("ABC")
                .go_to_orderForEmployeePage()
                .verifyOrderForEmployeePage()
                .verifyOrderTaxiPageLabelNames()
                .setStartAddressByGeolocation()
                .clickManyTimesOrderButtonAndCantOrder_noRequiredInformation(3);
    }

    @Test
    public void try_to_add_to_long_comment() throws InterruptedException {
        new LoginPage(driver)
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC")
                .verify_dashboardPge_for_admin("ABC")
                .go_to_orderForEmployeePage()
                .verifyOrderForEmployeePage()
                .verifyOrderTaxiPageLabelNames()
                .setPassengerName("Julek Angielski")
                .setStartAddressByGeolocation()
                .setFinalAddress("Wałbrzyska 1, Warszawa")
                .selectOrderTime_future_add_minutes_from_now(80)
                .setProject_firstFromList()
                .addTooLongComment()
                .clickOrderButton()
                .verifyNotificationsAboutTooLongComment()
                .deleteTextFromCommentTextArea()
                .addComment("nowy, krótszy")
                .clickOrderButton()
                .verifyConfirmationOrderPopupWithAllInformation()
                .acceptConfirmationOrderPopupAndVerifyEmptyFieldsOnPageAfterOrder();
    }

    @Test
    public void try_to_add_incorrect_passenger_name_and_incorrect_addresses() throws InterruptedException {
        new LoginPage(driver)
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC")
                .verify_dashboardPge_for_admin("ABC")
                .go_to_orderForEmployeePage()
                .verifyOrderForEmployeePage()
                .verifyOrderTaxiPageLabelNames()
                .setIncorrectPassengerNameAndDelete("Anna Wanna")
                .setPassengerName("Janusz Stary")
                .setIncorrectStartAddressAndDelete("abcdefghijk")
                .setStartAddress("Hoża 4, Warszawa")
                .setIncorrectFinalAddressAndDelete("abcdefghijk")
                .setFinalAddress("Marszałkowska 10, Warszawa")
                .setProject_firstFromList()
                .addComment("jest ok")
                .clickOrderButton()
                .verifyConfirmationOrderPopupWithAllInformation()
                .acceptConfirmationOrderPopupAndVerifyEmptyFieldsOnPageAfterOrder();
    }

    @Test
    public void verify_can_change_order_time_from_future_to_now() throws InterruptedException {
        new LoginPage(driver)
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC")
                .verify_dashboardPge_for_admin("ABC")
                .go_to_orderForEmployeePage()
                .verifyOrderForEmployeePage()
                .verifyOrderTaxiPageLabelNames()
                .setPassengerName("Janusz Stary")
                .setStartAddressByGeolocation()
                .selectOrderTime_future_add_days_from_now(26)
                .selectOrderTime_now()
                .clickOrderButton()
                .verifyConfirmationOrderPopupWithOnlyRequiredInformation()
                .acceptConfirmationOrderPopupAndVerifyEmptyFieldsOnPageAfterOrder();
    }

    //WAŻNE! bedzie zmienione api pod wiecej opcji - aby sie wybrane opcje gdzies pokazywaly po zamowieniu, obecnie nigdzie nie ma

    @Test
    public void should_verify_and_order_with_more_options() throws InterruptedException {
        new LoginPage(driver)
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC")
                .verify_dashboardPge_for_admin("ABC")
                .go_to_orderForEmployeePage()
                .verifyOrderForEmployeePage()
                .verifyOrderTaxiPageLabelNames()
                .openMoreOptionsAndVerifyButtonText()
                .verifyMoreOptionsPageElements()
                .verifyIsEconomicTaxiActive()
                .selectPremiumTaxiAsActive()
                .verifyIsPremiumTaxiActive()
                .selectLargeTrunk()
                .selectSilentRide()
                .openFeeTableAndVerify()
                .acceptOptions();
    }

}
