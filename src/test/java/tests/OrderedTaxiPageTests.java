package tests;

import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import pages.OrderForEmployeePage;


public class OrderedTaxiPageTests extends BaseTests {


    @Test
    public void go_to_orderedTaxiPage() throws InterruptedException {
        new LoginPage(driver)
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC")
                .verify_dashboardPge_for_admin("ABC")
                .go_to_orderedTaxiPage()
                .verifyOrderedTaxiPage();
    }

    @Test
    public void verify_future_order_on_orderedTaxiPage() throws InterruptedException {
        String projectName;
        String orderTime;
        String passengerName;
        String startAddress;
        String finalAddress;
        new LoginPage(driver)
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC")
                .verify_dashboardPge_for_admin("ABC")
                .go_to_orderForEmployeePage()
                .verifyOrderForEmployeePage()
                .verifyOrderTaxiPageLabelNames()
                .setPassengerName("Janusz Stary")
                .setStartAddress("Prosta 6, Warszawa")
                .scrollDownPage()
                .selectOrderTime_future_add_hours_from_now(4);
        passengerName = new OrderForEmployeePage(driver).getPassengerName();
        startAddress = new OrderForEmployeePage(driver).getStartAddress();
        finalAddress = new OrderForEmployeePage(driver).getFinalAddress();
        projectName = new OrderForEmployeePage(driver).getProjectName();
        orderTime = new OrderForEmployeePage(driver).getOrderTime();
        new OrderForEmployeePage(driver)
                .clickOrderButton()
                .verifyConfirmationOrderPopupWithOnlyRequiredInformation()
                .acceptConfirmationOrderPopupAndVerifyEmptyFieldsOnPageAfterOrder()
                .scrollUp();
        new DashboardPage(driver)
                .go_to_orderedTaxiPage()
                .verifyOrderedTaxiPage()
                .verifyIfOrderedTaxiSignIsPresentOnButton()
                .confirmFutureOrderIsPresent(passengerName, startAddress, orderTime, projectName, finalAddress);
    }


    @Test
    public void verify_now_order_on_orderedTaxiPage() throws InterruptedException {
        String projectName;
        String orderTime;
        String passengerName;
        String startAddress;
        String finalAddress;
        new LoginPage(driver)
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC")
                .verify_dashboardPge_for_admin("ABC")
                .go_to_orderForEmployeePage()
                .verifyOrderForEmployeePage()
                .verifyOrderTaxiPageLabelNames()
                .setPassengerName("Kuba Mors")
                .setStartAddress("Wernyhory 11, Warszawa")
                .scrollDownPage()
                .selectOrderTime_now();
        passengerName = new OrderForEmployeePage(driver).getPassengerName();
        startAddress = new OrderForEmployeePage(driver).getStartAddress();
        finalAddress = new OrderForEmployeePage(driver).getFinalAddress();
        projectName = new OrderForEmployeePage(driver).getProjectName();
        orderTime = new OrderForEmployeePage(driver).getOrderTime();
        new OrderForEmployeePage(driver)
                .clickOrderButton()
                .verifyConfirmationOrderPopupWithOnlyRequiredInformation()
                .acceptConfirmationOrderPopupAndVerifyEmptyFieldsOnPageAfterOrder()
                .scrollUp();
        new DashboardPage(driver)
                .go_to_orderedTaxiPage()
                .verifyOrderedTaxiPage()
                .verifyIfOrderedTaxiSignIsPresentOnButton()
                .confirmForNowOrderIsPresent(passengerName, startAddress, orderTime, projectName, finalAddress);
    }

    @Test
    public void verify_now_order_with_all_information_on_orderedTaxiPage() throws InterruptedException {
        String projectName;
        String orderTime;
        String passengerName;
        String startAddress;
        String finalAddress;
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
                .addComment("nic a nic");
        passengerName = new OrderForEmployeePage(driver).getPassengerName();
        startAddress = new OrderForEmployeePage(driver).getStartAddress();
        finalAddress = new OrderForEmployeePage(driver).getFinalAddress();
        projectName = new OrderForEmployeePage(driver).getProjectName();
        orderTime = new OrderForEmployeePage(driver).getOrderTime();
        new OrderForEmployeePage(driver)
                .clickOrderButton()
                .verifyConfirmationOrderPopupWithAllInformation()
                .acceptConfirmationOrderPopupAndVerifyEmptyFieldsOnPageAfterOrder()
                .scrollUp();
        new DashboardPage(driver)
                .go_to_orderedTaxiPage()
                .verifyOrderedTaxiPage()
                .verifyIfOrderedTaxiSignIsPresentOnButton()
                .confirmForNowOrderIsPresent(passengerName, startAddress, orderTime, projectName, finalAddress);
    }

    @Test
    public void verify_future_order_with_all_information_on_orderedTaxiPage() throws InterruptedException {
        String projectName;
        String orderTime;
        String passengerName;
        String startAddress;
        String finalAddress;
        new LoginPage(driver)
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC")
                .verify_dashboardPge_for_admin("ABC")
                .go_to_orderForEmployeePage()
                .verifyOrderForEmployeePage()
                .verifyOrderTaxiPageLabelNames()
                .setPassengerName("Kuba Mors")
                .setStartAddress("Prosta 1, Warszawa")
                .setFinalAddress("Wspólna 1, Warszawa")
                .scrollDownPage()
                .selectOrderTime_future_add_minutes_from_now(150)
                .setProject_firstFromList()
                .addComment("nic a nic");
        passengerName = new OrderForEmployeePage(driver).getPassengerName();
        startAddress = new OrderForEmployeePage(driver).getStartAddress();
        finalAddress = new OrderForEmployeePage(driver).getFinalAddress();
        projectName = new OrderForEmployeePage(driver).getProjectName();
        orderTime = new OrderForEmployeePage(driver).getOrderTime();
        new OrderForEmployeePage(driver)
                .clickOrderButton()
                .verifyConfirmationOrderPopupWithAllInformation()
                .acceptConfirmationOrderPopupAndVerifyEmptyFieldsOnPageAfterOrder()
                .scrollUp();
        new DashboardPage(driver)
                .go_to_orderedTaxiPage()
                .verifyOrderedTaxiPage()
                .verifyIfOrderedTaxiSignIsPresentOnButton()
                .confirmFutureOrderIsPresent(passengerName, startAddress, orderTime, projectName, finalAddress);
    }

    @Test(dependsOnMethods = {"verify_future_order_with_all_information_on_orderedTaxiPage", "verify_now_order_with_all_information_on_orderedTaxiPage"})
    public void should_search_by_passengerName_and_address() throws InterruptedException {
        new LoginPage(driver)
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC")
                .verify_dashboardPge_for_admin("ABC")
                .go_to_orderedTaxiPage()
                .verifyOrderedTaxiPage()
                .searchByPassengerName("Kuba")
                .clearSearchField()
                .searchByStartAddress("Prosta");
    }

    @Test
    public void should_search_by_not_existing_order_value() throws InterruptedException {
        new LoginPage(driver)
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC")
                .verify_dashboardPge_for_admin("ABC")
                .go_to_orderedTaxiPage()
                .verifyOrderedTaxiPage()
                .searchNotExistingOrderValue("xxxxxxxxxxxxxxxx");
    }

    @Test
    public void should_cancel_now_order() throws InterruptedException {
        String projectName;
        String orderTime;
        String passengerName;
        String startAddress;
        String finalAddress;
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
                .addComment("nic a nic");
        passengerName = new OrderForEmployeePage(driver).getPassengerName();
        startAddress = new OrderForEmployeePage(driver).getStartAddress();
        finalAddress = new OrderForEmployeePage(driver).getFinalAddress();
        projectName = new OrderForEmployeePage(driver).getProjectName();
        orderTime = new OrderForEmployeePage(driver).getOrderTime();
        new OrderForEmployeePage(driver)
                .clickOrderButton()
                .verifyConfirmationOrderPopupWithAllInformation()
                .acceptConfirmationOrderPopupAndVerifyEmptyFieldsOnPageAfterOrder()
                .scrollUp();
        new DashboardPage(driver)
                .go_to_orderedTaxiPage()
                .verifyOrderedTaxiPage()
                .verifyIfOrderedTaxiSignIsPresentOnButton()
                .chooseOrderForNowAnClickCancelButton(passengerName, startAddress, orderTime, projectName, finalAddress)
                .confirmOrderCancel();
    }

    @Test
    public void should_cancel_future_order() throws InterruptedException {
        String projectName;
        String orderTime;
        String passengerName;
        String startAddress;
        String finalAddress;
        new LoginPage(driver)
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC")
                .verify_dashboardPge_for_admin("ABC")
                .go_to_orderForEmployeePage()
                .verifyOrderForEmployeePage()
                .verifyOrderTaxiPageLabelNames()
                .setPassengerName("Kuba Mors")
                .setStartAddress("Prosta 1, Warszawa")
                .setFinalAddress("Wspólna 1, Warszawa")
                .scrollDownPage()
                .selectOrderTime_future_add_minutes_from_now(150)
                .setProject_firstFromList()
                .addComment("nic a nic");
        passengerName = new OrderForEmployeePage(driver).getPassengerName();
        startAddress = new OrderForEmployeePage(driver).getStartAddress();
        finalAddress = new OrderForEmployeePage(driver).getFinalAddress();
        projectName = new OrderForEmployeePage(driver).getProjectName();
        orderTime = new OrderForEmployeePage(driver).getOrderTime();
        new OrderForEmployeePage(driver)
                .clickOrderButton()
                .verifyConfirmationOrderPopupWithAllInformation()
                .acceptConfirmationOrderPopupAndVerifyEmptyFieldsOnPageAfterOrder()
                .scrollUp();
        new DashboardPage(driver)
                .go_to_orderedTaxiPage()
                .verifyOrderedTaxiPage()
                .verifyIfOrderedTaxiSignIsPresentOnButton()
                .chooseFutureOrderAndClickCancelButton(passengerName, startAddress, orderTime, projectName, finalAddress)
                .confirmOrderCancel();
    }

    @Test
    public void should_dont_cancel_now_order() throws InterruptedException {
        String projectName;
        String orderTime;
        String passengerName;
        String startAddress;
        String finalAddress;
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
                .addComment("nic a nic");
        passengerName = new OrderForEmployeePage(driver).getPassengerName();
        startAddress = new OrderForEmployeePage(driver).getStartAddress();
        finalAddress = new OrderForEmployeePage(driver).getFinalAddress();
        projectName = new OrderForEmployeePage(driver).getProjectName();
        orderTime = new OrderForEmployeePage(driver).getOrderTime();
        new OrderForEmployeePage(driver)
                .clickOrderButton()
                .verifyConfirmationOrderPopupWithAllInformation()
                .acceptConfirmationOrderPopupAndVerifyEmptyFieldsOnPageAfterOrder()
                .scrollUp();
        new DashboardPage(driver)
                .go_to_orderedTaxiPage()
                .verifyOrderedTaxiPage()
                .verifyIfOrderedTaxiSignIsPresentOnButton()
                .confirmForNowOrderIsPresent(passengerName, startAddress, orderTime, projectName, finalAddress)
                .chooseOrderForNowAnClickCancelButton(passengerName, startAddress, orderTime, projectName, finalAddress)
                .denyOrderCancel()
                .confirmForNowOrderIsPresent(passengerName, startAddress, orderTime, projectName, finalAddress);
    }

    @Test
    public void should_dont_cancel_future_order() throws InterruptedException {
        String projectName;
        String orderTime;
        String passengerName;
        String startAddress;
        String finalAddress;
        new LoginPage(driver)
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC")
                .verify_dashboardPge_for_admin("ABC")
                .go_to_orderForEmployeePage()
                .verifyOrderForEmployeePage()
                .verifyOrderTaxiPageLabelNames()
                .setPassengerName("Kuba Mors")
                .setStartAddress("Prosta 6, Warszawa")
                .setFinalAddress("Wspólna 11, Warszawa")
                .scrollDownPage()
                .selectOrderTime_future_add_minutes_from_now(300)
                .setProject_firstFromList()
                .addComment("nic a nic");
        passengerName = new OrderForEmployeePage(driver).getPassengerName();
        startAddress = new OrderForEmployeePage(driver).getStartAddress();
        finalAddress = new OrderForEmployeePage(driver).getFinalAddress();
        projectName = new OrderForEmployeePage(driver).getProjectName();
        orderTime = new OrderForEmployeePage(driver).getOrderTime();
        new OrderForEmployeePage(driver)
                .clickOrderButton()
                .verifyConfirmationOrderPopupWithAllInformation()
                .acceptConfirmationOrderPopupAndVerifyEmptyFieldsOnPageAfterOrder()
                .scrollUp();
        new DashboardPage(driver)
                .go_to_orderedTaxiPage()
                .verifyOrderedTaxiPage()
                .verifyIfOrderedTaxiSignIsPresentOnButton()
                .confirmFutureOrderIsPresent(passengerName, startAddress, orderTime, projectName, finalAddress)
                .chooseFutureOrderAndClickCancelButton(passengerName, startAddress, orderTime, projectName, finalAddress)
                .denyOrderCancel()
                .confirmFutureOrderIsPresent(passengerName, startAddress, orderTime, projectName, finalAddress);
    }

    @Test
    public void should_edit_future_order() throws InterruptedException {
        String projectName;
        String orderTime;
        String passengerName;
        String startAddress;
        String finalAddress;
        new LoginPage(driver)
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC")
                .verify_dashboardPge_for_admin("ABC")
                .go_to_orderForEmployeePage()
                .verifyOrderForEmployeePage()
                .verifyOrderTaxiPageLabelNames()
                .setPassengerName("Kuba Mors")
                .setStartAddress("Prosta 1, Warszawa")
                .setFinalAddress("Hoża 1, Warszawa")
                .scrollDownPage()
                .selectOrderTime_future_add_hours_from_now(3)
                .setProject_firstFromList()
                .addComment("od klatki I")
                .clickOrderButton();
        passengerName = new OrderForEmployeePage(driver).getPassengerName();
        startAddress = new OrderForEmployeePage(driver).getStartAddress();
        finalAddress = new OrderForEmployeePage(driver).getFinalAddress();
        projectName = new OrderForEmployeePage(driver).getProjectName();
        orderTime = new OrderForEmployeePage(driver).getOrderTime();
        new OrderForEmployeePage(driver)
                .verifyConfirmationOrderPopupWithAllInformation()
                .acceptConfirmationOrderPopupAndVerifyEmptyFieldsOnPageAfterOrder()
                .scrollUp();
        new DashboardPage(driver)
                .go_to_orderedTaxiPage()
                .verifyOrderedTaxiPage()
                .verifyIfOrderedTaxiSignIsPresentOnButton()
                .confirmFutureOrderIsPresent(passengerName, startAddress, orderTime, projectName, finalAddress)
                .chooseOrderAndClickEditOrderButton(passengerName, startAddress, orderTime, projectName, finalAddress)
                .verifyOrderTaxiPageLabelNames()
                .verifyOrderForEmployeePage()
                .editOrder_verifyIfDataIsCorrect(passengerName, startAddress, orderTime, projectName, finalAddress)
                .editPassengerName("Zuzia Nowak")
                .editStartAddress("Hoża 15, Warszawa")
                .editFinalAddress("Al. Jerozolimskie 154, Warszawa")
                .selectOrderTime_future_add_days_from_now(3)
                .editComment("po edycji");
        passengerName = new OrderForEmployeePage(driver).getPassengerName();
        startAddress = new OrderForEmployeePage(driver).getStartAddress();
        finalAddress = new OrderForEmployeePage(driver).getFinalAddress();
        projectName = new OrderForEmployeePage(driver).getProjectName();
        orderTime = new OrderForEmployeePage(driver).getOrderTime();
        new OrderForEmployeePage(driver)
                .clickSaveEditedOrderButton()
                .editing_verifyConfirmationOrderPopupWithAllInformation()
                .acceptConfirmationOrderPopupAndVerifyEmptyFieldsOnPageAfterOrder()
                .scrollUp();
        new DashboardPage(driver)
                .go_to_orderedTaxiPage()
                .verifyOrderedTaxiPage()
                .verifyIfOrderedTaxiSignIsAbsenteOnButton()
                .confirmFutureOrderIsPresent(passengerName, startAddress, orderTime, projectName, finalAddress);
    }

}
