package tests;

import org.testng.annotations.Test;
import pages.*;


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
        String comment;
        String carClass;
        String isLargeTruncSelected;
        new LoginPage(driver)
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC");
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage
                .verify_dashboardPge_for_admin("ABC")
                .go_to_orderForEmployeePage();
        OrderForEmployeePage orderForEmployeePage = new OrderForEmployeePage(driver);
        orderForEmployeePage
                .verifyOrderForEmployeePage()
                .verifyOrderTaxiPageLabelNames()
                .setPassengerName("Janusz Stary")
                .setStartAddress("Prosta 6, Warszawa")
                .scrollDownPage()
                .selectOrderTime_future_add_hours_from_now(4);
        MoreOptionsPage moreOptionsPage = new MoreOptionsPage(driver);
        boolean isSilentRideUse = moreOptionsPage.isSilentRideUse();
        boolean isLargeTrunkUse = moreOptionsPage.isLargeTrunkUse();
        boolean isPremiumTaxiUse = moreOptionsPage.isPremiumTaxiUse();
        passengerName = new OrderForEmployeePage(driver).getPassengerName();
        startAddress = new OrderForEmployeePage(driver).getStartAddress();
        finalAddress = new OrderForEmployeePage(driver).getFinalAddress();
        projectName = new OrderForEmployeePage(driver).getProjectName();
        orderTime = new OrderForEmployeePage(driver).getOrderTime();
        comment = new OrderForEmployeePage(driver).getCommentToCompareInOrderedTaxiPage(isSilentRideUse);
        isLargeTruncSelected = new OrderForEmployeePage(driver).getIfIsLargeTruncSelected(isLargeTrunkUse);
        carClass = new OrderForEmployeePage(driver).getCarClass(isPremiumTaxiUse);
        orderForEmployeePage
                .clickOrderButton()
                .verifyConfirmationOrderPopupWithOnlyRequiredInformation()
                .acceptConfirmationOrderPopupAndVerifyEmptyFieldsOnPageAfterOrder()
                .scrollUp();
        dashboardPage
                .go_to_orderedTaxiPage();
        OrderedTaxiPage orderedTaxiPage = new OrderedTaxiPage(driver);
        orderedTaxiPage
                .verifyOrderedTaxiPage()
                .verifyIfOrderedTaxiSignIsPresentOnButton()
                .confirmFutureOrderIsPresent(passengerName, startAddress, orderTime, projectName, finalAddress, comment, isLargeTruncSelected, carClass);
    }


    @Test
    public void verify_now_order_on_orderedTaxiPage() throws InterruptedException {
        String projectName;
        String orderTime;
        String passengerName;
        String startAddress;
        String finalAddress;
        String comment;
        String isLargeTruncSelected;
        String carClass;
        new LoginPage(driver)
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC");
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage
                .verify_dashboardPge_for_admin("ABC")
                .go_to_orderForEmployeePage();
        OrderForEmployeePage orderForEmployeePage = new OrderForEmployeePage(driver);
        orderForEmployeePage
                .verifyOrderForEmployeePage()
                .verifyOrderTaxiPageLabelNames()
                .setPassengerName("Kuba Mors")
                .setStartAddress("Wernyhory 11, Warszawa")
                .scrollDownPage()
                .selectOrderTime_now();
        MoreOptionsPage moreOptionsPage = new MoreOptionsPage(driver);
        boolean isLargeTrunkUse = moreOptionsPage.isLargeTrunkUse();
        boolean isPremiumTaxiUse = moreOptionsPage.isPremiumTaxiUse();
        passengerName = new OrderForEmployeePage(driver).getPassengerName();
        startAddress = new OrderForEmployeePage(driver).getStartAddress();
        finalAddress = new OrderForEmployeePage(driver).getFinalAddress();
        projectName = new OrderForEmployeePage(driver).getProjectName();
        orderTime = new OrderForEmployeePage(driver).getOrderTime();
        comment = new OrderForEmployeePage(driver).getComment();
        isLargeTruncSelected = new OrderForEmployeePage(driver).getIfIsLargeTruncSelected(isLargeTrunkUse);
        carClass = new OrderForEmployeePage(driver).getIfIsLargeTruncSelected(isPremiumTaxiUse);
        orderForEmployeePage
                .clickOrderButton()
                .verifyConfirmationOrderPopupWithOnlyRequiredInformation()
                .acceptConfirmationOrderPopupAndVerifyEmptyFieldsOnPageAfterOrder()
                .scrollUp();
        dashboardPage
                .go_to_orderedTaxiPage();
        OrderedTaxiPage orderedTaxiPage = new OrderedTaxiPage(driver);
        orderedTaxiPage
                .verifyOrderedTaxiPage()
                .verifyIfOrderedTaxiSignIsPresentOnButton()
                .confirmForNowOrderIsPresent(passengerName, startAddress, orderTime, projectName, finalAddress, comment, isLargeTruncSelected, carClass);
    }


    @Test
    public void verify_now_order_with_all_information_on_orderedTaxiPage() throws InterruptedException {
        String projectName;
        String orderTime;
        String passengerName;
        String startAddress;
        String finalAddress;
        String comment;
        String isLargeTruncSelected;
        String carClass;
        new LoginPage(driver)
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC");
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage
                .verify_dashboardPge_for_admin("ABC")
                .go_to_orderForEmployeePage();
        OrderForEmployeePage orderForEmployeePage = new OrderForEmployeePage(driver);
        orderForEmployeePage
                .verifyOrderForEmployeePage()
                .verifyOrderTaxiPageLabelNames()
                .setPassengerName("Zuzia Nowak")
                .setStartAddressByGeolocation()
                .setFinalAddress("Hoża 2, Warszawa")
                .scrollDownPage()
                .setProject_firstFromList()
                .addComment("nic a nic");
        MoreOptionsPage moreOptionsPage = new MoreOptionsPage(driver);
        boolean isLargeTrunkUse = moreOptionsPage.isLargeTrunkUse();
        boolean isPremiumTaxiUse = moreOptionsPage.isPremiumTaxiUse();
        passengerName = new OrderForEmployeePage(driver).getPassengerName();
        startAddress = new OrderForEmployeePage(driver).getStartAddress();
        finalAddress = new OrderForEmployeePage(driver).getFinalAddress();
        projectName = new OrderForEmployeePage(driver).getProjectName();
        orderTime = new OrderForEmployeePage(driver).getOrderTime();
        comment = new OrderForEmployeePage(driver).getComment();
        isLargeTruncSelected = new OrderForEmployeePage(driver).getIfIsLargeTruncSelected(isLargeTrunkUse);
        carClass = new OrderForEmployeePage(driver).getIfIsLargeTruncSelected(isPremiumTaxiUse);
        orderForEmployeePage
                .clickOrderButton()
                .verifyConfirmationOrderPopupWithAllInformation()
                .acceptConfirmationOrderPopupAndVerifyEmptyFieldsOnPageAfterOrder()
                .scrollUp();
        dashboardPage
                .go_to_orderedTaxiPage();
        OrderedTaxiPage orderedTaxiPage = new OrderedTaxiPage(driver);
        orderedTaxiPage
                .verifyOrderedTaxiPage()
                .verifyIfOrderedTaxiSignIsPresentOnButton()
                .confirmForNowOrderIsPresent(passengerName, startAddress, orderTime, projectName, finalAddress, comment, isLargeTruncSelected, carClass);
    }

    @Test
    public void verify_future_order_with_all_information_on_orderedTaxiPage() throws InterruptedException {
        String projectName;
        String orderTime;
        String passengerName;
        String startAddress;
        String finalAddress;
        String comment;
        String carClass;
        String isLargeTruncSelected;
        new LoginPage(driver)
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC");
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage
                .verify_dashboardPge_for_admin("ABC")
                .go_to_orderForEmployeePage();
        OrderForEmployeePage orderForEmployeePage = new OrderForEmployeePage(driver);
        orderForEmployeePage
                .verifyOrderForEmployeePage()
                .verifyOrderTaxiPageLabelNames()
                .setPassengerName("Kuba Mors")
                .setStartAddress("Prosta 1, Warszawa")
                .setFinalAddress("Wspólna 1, Warszawa")
                .scrollDownPage()
                .selectOrderTime_future_add_minutes_from_now(150)
                .setProject_firstFromList()
                .addComment("nic a nic");
        MoreOptionsPage moreOptionsPage = new MoreOptionsPage(driver);
        boolean isSilentRideUse = moreOptionsPage.isSilentRideUse();
        boolean isLargeTrunkUse = moreOptionsPage.isLargeTrunkUse();
        boolean isPremiumTaxiUse = moreOptionsPage.isPremiumTaxiUse();
        passengerName = new OrderForEmployeePage(driver).getPassengerName();
        startAddress = new OrderForEmployeePage(driver).getStartAddress();
        finalAddress = new OrderForEmployeePage(driver).getFinalAddress();
        projectName = new OrderForEmployeePage(driver).getProjectName();
        orderTime = new OrderForEmployeePage(driver).getOrderTime();
        comment = new OrderForEmployeePage(driver).getCommentToCompareInOrderedTaxiPage(isSilentRideUse);
        isLargeTruncSelected = new OrderForEmployeePage(driver).getIfIsLargeTruncSelected(isLargeTrunkUse);
        carClass = new OrderForEmployeePage(driver).getCarClass(isPremiumTaxiUse);
        orderForEmployeePage
                .clickOrderButton()
                .verifyConfirmationOrderPopupWithAllInformation()
                .acceptConfirmationOrderPopupAndVerifyEmptyFieldsOnPageAfterOrder()
                .scrollUp();
        dashboardPage
                .go_to_orderedTaxiPage();
        OrderedTaxiPage orderedTaxiPage = new OrderedTaxiPage(driver);
        orderedTaxiPage
                .verifyOrderedTaxiPage()
                .verifyIfOrderedTaxiSignIsPresentOnButton()
                .confirmFutureOrderIsPresent(passengerName, startAddress, orderTime, projectName, finalAddress, comment, isLargeTruncSelected, carClass);
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
                .choose_business_partner_from_list("ABC");
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage
                .verify_dashboardPge_for_admin("ABC")
                .go_to_orderForEmployeePage();
        OrderForEmployeePage orderForEmployeePage = new OrderForEmployeePage(driver);
        orderForEmployeePage
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
        orderForEmployeePage
                .clickOrderButton()
                .verifyConfirmationOrderPopupWithAllInformation()
                .acceptConfirmationOrderPopupAndVerifyEmptyFieldsOnPageAfterOrder()
                .scrollUp();
        dashboardPage
                .go_to_orderedTaxiPage();
        OrderedTaxiPage orderedTaxiPage = new OrderedTaxiPage(driver);
        orderedTaxiPage
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
                .choose_business_partner_from_list("ABC");
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage
                .verify_dashboardPge_for_admin("ABC")
                .go_to_orderForEmployeePage();
        OrderForEmployeePage orderForEmployeePage = new OrderForEmployeePage(driver);
        orderForEmployeePage
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
        orderForEmployeePage
                .clickOrderButton()
                .verifyConfirmationOrderPopupWithAllInformation()
                .acceptConfirmationOrderPopupAndVerifyEmptyFieldsOnPageAfterOrder()
                .scrollUp();
        dashboardPage
                .go_to_orderedTaxiPage();
        OrderedTaxiPage orderedTaxiPage = new OrderedTaxiPage(driver);
        orderedTaxiPage
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
        String comment;
        String isLargeTruncSelected;
        String carClass;
        new LoginPage(driver)
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC");
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage
                .verify_dashboardPge_for_admin("ABC")
                .go_to_orderForEmployeePage();
        OrderForEmployeePage orderForEmployeePage = new OrderForEmployeePage(driver);
        orderForEmployeePage
                .verifyOrderForEmployeePage()
                .verifyOrderTaxiPageLabelNames()
                .setPassengerName("Zuzia Nowak")
                .setStartAddressByGeolocation()
                .setFinalAddress("Hoża 2, Warszawa")
                .scrollDownPage()
                .setProject_firstFromList()
                .addComment("nic a nic");
        MoreOptionsPage moreOptionsPage = new MoreOptionsPage(driver);
        boolean isLargeTrunkUse = moreOptionsPage.isLargeTrunkUse();
        boolean isPremiumTaxiUse = moreOptionsPage.isPremiumTaxiUse();
        passengerName = new OrderForEmployeePage(driver).getPassengerName();
        startAddress = new OrderForEmployeePage(driver).getStartAddress();
        finalAddress = new OrderForEmployeePage(driver).getFinalAddress();
        projectName = new OrderForEmployeePage(driver).getProjectName();
        orderTime = new OrderForEmployeePage(driver).getOrderTime();
        comment = new OrderForEmployeePage(driver).getComment();
        isLargeTruncSelected = new OrderForEmployeePage(driver).getIfIsLargeTruncSelected(isLargeTrunkUse);
        carClass = new OrderForEmployeePage(driver).getIfIsLargeTruncSelected(isPremiumTaxiUse);
        orderForEmployeePage
                .clickOrderButton()
                .verifyConfirmationOrderPopupWithAllInformation()
                .acceptConfirmationOrderPopupAndVerifyEmptyFieldsOnPageAfterOrder()
                .scrollUp();
        dashboardPage
                .go_to_orderedTaxiPage();
        OrderedTaxiPage orderedTaxiPage = new OrderedTaxiPage(driver);
        orderedTaxiPage
                .verifyOrderedTaxiPage()
                .verifyIfOrderedTaxiSignIsPresentOnButton()
                .confirmForNowOrderIsPresent(passengerName, startAddress, orderTime, projectName, finalAddress, comment, isLargeTruncSelected, carClass)
                .chooseOrderForNowAnClickCancelButton(passengerName, startAddress, orderTime, projectName, finalAddress)
                .denyOrderCancel()
                .confirmForNowOrderIsPresent(passengerName, startAddress, orderTime, projectName, finalAddress, comment, isLargeTruncSelected, carClass);
    }

    @Test
    public void should_dont_cancel_future_order() throws InterruptedException {
        String projectName;
        String orderTime;
        String passengerName;
        String startAddress;
        String finalAddress;
        String comment;
        String carClass;
        String isLargeTruncSelected;
        new LoginPage(driver)
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC");
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage
                .verify_dashboardPge_for_admin("ABC")
                .go_to_orderForEmployeePage();
        OrderForEmployeePage orderForEmployeePage = new OrderForEmployeePage(driver);
        orderForEmployeePage
                .verifyOrderForEmployeePage()
                .verifyOrderTaxiPageLabelNames()
                .setPassengerName("Kuba Mors")
                .setStartAddress("Prosta 6, Warszawa")
                .setFinalAddress("Wspólna 11, Warszawa")
                .scrollDownPage()
                .selectOrderTime_future_add_minutes_from_now(300)
                .setProject_firstFromList()
                .addComment("nic a nic");
        MoreOptionsPage moreOptionsPage = new MoreOptionsPage(driver);
        boolean isSilentRideUse = moreOptionsPage.isSilentRideUse();
        boolean isLargeTrunkUse = moreOptionsPage.isLargeTrunkUse();
        boolean isPremiumTaxiUse = moreOptionsPage.isPremiumTaxiUse();
        passengerName = new OrderForEmployeePage(driver).getPassengerName();
        startAddress = new OrderForEmployeePage(driver).getStartAddress();
        finalAddress = new OrderForEmployeePage(driver).getFinalAddress();
        projectName = new OrderForEmployeePage(driver).getProjectName();
        orderTime = new OrderForEmployeePage(driver).getOrderTime();
        comment = new OrderForEmployeePage(driver).getCommentToCompareInOrderedTaxiPage(isSilentRideUse);
        isLargeTruncSelected = new OrderForEmployeePage(driver).getIfIsLargeTruncSelected(isLargeTrunkUse);
        carClass = new OrderForEmployeePage(driver).getCarClass(isPremiumTaxiUse);
        orderForEmployeePage
                .clickOrderButton()
                .verifyConfirmationOrderPopupWithAllInformation()
                .acceptConfirmationOrderPopupAndVerifyEmptyFieldsOnPageAfterOrder()
                .scrollUp();
        dashboardPage
                .go_to_orderedTaxiPage();
        OrderedTaxiPage orderedTaxiPage = new OrderedTaxiPage(driver);
        orderedTaxiPage
                .verifyOrderedTaxiPage()
                .verifyIfOrderedTaxiSignIsPresentOnButton()
                .confirmFutureOrderIsPresent(passengerName, startAddress, orderTime, projectName, finalAddress, comment, isLargeTruncSelected, carClass)
                .chooseFutureOrderAndClickCancelButton(passengerName, startAddress, orderTime, projectName, finalAddress)
                .denyOrderCancel()
                .confirmFutureOrderIsPresent(passengerName, startAddress, orderTime, projectName, finalAddress, comment, isLargeTruncSelected, carClass);
    }

    @Test
    public void should_edit_future_order() throws InterruptedException {
        String projectName;
        String orderTime;
        String passengerName;
        String startAddress;
        String finalAddress;
        String comment;
        String carClass;
        String isLargeTruncSelected;
        new LoginPage(driver)
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC");
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage
                .verify_dashboardPge_for_admin("ABC")
                .go_to_orderForEmployeePage();
        OrderForEmployeePage orderForEmployeePage = new OrderForEmployeePage(driver);
        orderForEmployeePage
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
        MoreOptionsPage moreOptionsPage = new MoreOptionsPage(driver);
        boolean isSilentRideUse = moreOptionsPage.isSilentRideUse();
        boolean isLargeTrunkUse = moreOptionsPage.isLargeTrunkUse();
        boolean isPremiumTaxiUse = moreOptionsPage.isPremiumTaxiUse();
        passengerName = new OrderForEmployeePage(driver).getPassengerName();
        startAddress = new OrderForEmployeePage(driver).getStartAddress();
        finalAddress = new OrderForEmployeePage(driver).getFinalAddress();
        projectName = new OrderForEmployeePage(driver).getProjectName();
        orderTime = new OrderForEmployeePage(driver).getOrderTime();
        comment = new OrderForEmployeePage(driver).getCommentToCompareInOrderedTaxiPage(isSilentRideUse);
        isLargeTruncSelected = new OrderForEmployeePage(driver).getIfIsLargeTruncSelected(isLargeTrunkUse);
        carClass = new OrderForEmployeePage(driver).getCarClass(isPremiumTaxiUse);
        System.out.println("from test case: "+passengerName+" "+startAddress+" "+finalAddress+" "+projectName+" "+orderTime+" "+comment+" "+isLargeTruncSelected+" "+carClass);
        orderForEmployeePage
                .verifyConfirmationOrderPopupWithAllInformation()
                .acceptConfirmationOrderPopupAndVerifyEmptyFieldsOnPageAfterOrder()
                .scrollUp();
        dashboardPage
                .go_to_orderedTaxiPage();
        OrderedTaxiPage orderedTaxiPage = new OrderedTaxiPage(driver);
        orderedTaxiPage
                .verifyOrderedTaxiPage()
                .verifyIfOrderedTaxiSignIsPresentOnButton()
                .confirmFutureOrderIsPresent(passengerName, startAddress, orderTime, projectName, finalAddress, comment, isLargeTruncSelected, carClass)
                .chooseOrderAndClickEditOrderButton(passengerName, startAddress, orderTime, projectName, finalAddress)
                .verifyOrderForEmployeePage()
                .editOrder_verifyIfDataIsCorrect_future(passengerName, startAddress, orderTime, projectName, finalAddress)
                .editPassengerName("Zuzia Nowak")
                .editStartAddress("Hoża 15, Warszawa")
                .editFinalAddress("Al. Jerozolimskie 154, Warszawa")
                .selectOrderTime_future_add_days_from_now(7)
                .editComment("po edycji");
        orderForEmployeePage
                .clickSaveEditedOrderButton()
                .editing_verifyConfirmationOrderPopupWithAllInformation()
                .acceptConfirmationOrderPopupAndVerifyEmptyFieldsOnPageAfterOrder()
                .scrollUp();
        dashboardPage
                .go_to_orderedTaxiPage();
        orderedTaxiPage
                .verifyOrderedTaxiPage()
                .confirmFutureOrderIsPresent(passengerName, startAddress, orderTime, projectName, finalAddress, comment, isLargeTruncSelected, carClass);
    }


    @Test
    public void verify_now_order_with_all_information_on_orderedTaxiPage_and_all_more_options() throws InterruptedException {
        String projectName;
        String orderTime;
        String passengerName;
        String startAddress;
        String finalAddress;
        String comment;
        String carClass;
        String isLargeTruncSelected;
        new LoginPage(driver)
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC");
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage
                .verify_dashboardPge_for_admin("ABC")
                .go_to_orderForEmployeePage();
        OrderForEmployeePage orderForEmployeePage = new OrderForEmployeePage(driver);
        orderForEmployeePage
                .verifyOrderForEmployeePage()
                .verifyOrderTaxiPageLabelNames()
                .setPassengerName("Julek Angielski")
                .setStartAddressByGeolocation()
                .setFinalAddress("Hassa 2, Warszawa");
        boolean isStartAddressAdded = new OrderForEmployeePage(driver).isStartAddressAdded();
        orderForEmployeePage.openMoreOptionsAndVerifyButtonText();
        MoreOptionsPage moreOptionsPage = new MoreOptionsPage(driver);
        moreOptionsPage
                .verifyMoreOptionsPageElements()
                .verifyIsEconomicTaxiActive()
                .selectPremiumTaxiAsActive()
                .verifyIsPremiumTaxiActive(isStartAddressAdded)
                .selectLargeTrunk()
                .selectSilentRide()
                .openFeeTableAndVerify()
                .acceptOptions()
                .scrollDownPage()
                .setProject_firstFromList()
                .addComment("jest oki222");
        boolean isSilentRideUse = moreOptionsPage.isSilentRideUse();
        boolean isLargeTrunkUse = moreOptionsPage.isLargeTrunkUse();
        boolean isPremiumTaxiUse = moreOptionsPage.isPremiumTaxiUse();
        passengerName = new OrderForEmployeePage(driver).getPassengerName();
        startAddress = new OrderForEmployeePage(driver).getStartAddress();
        finalAddress = new OrderForEmployeePage(driver).getFinalAddress();
        projectName = new OrderForEmployeePage(driver).getProjectName();
        orderTime = new OrderForEmployeePage(driver).getOrderTime();
        comment = new OrderForEmployeePage(driver).getCommentToCompareInOrderedTaxiPage(isSilentRideUse);
        isLargeTruncSelected = new OrderForEmployeePage(driver).getIfIsLargeTruncSelected(isLargeTrunkUse);
        carClass = new OrderForEmployeePage(driver).getCarClass(isPremiumTaxiUse);
        orderForEmployeePage
                .clickOrderButton()
                .verifyConfirmationOrderPopupWithAllInformation()
                .acceptConfirmationOrderPopupAndVerifyEmptyFieldsOnPageAfterOrder()
                .scrollUp();
        dashboardPage
                .go_to_orderedTaxiPage();
        OrderedTaxiPage orderedTaxiPage = new OrderedTaxiPage(driver);
        orderedTaxiPage
                .verifyOrderedTaxiPage()
                .verifyIfOrderedTaxiSignIsPresentOnButton()
                .confirmForNowOrderIsPresent(passengerName, startAddress, orderTime, projectName, finalAddress, comment, isLargeTruncSelected, carClass);
    }

    @Test
    public void verify_future_order_with_all_information_on_orderedTaxiPage_and_all_more_options() throws InterruptedException {
        String projectName;
        String orderTime;
        String passengerName;
        String startAddress;
        String finalAddress;
        String comment;
        String carClass;
        String isLargeTruncSelected;
        new LoginPage(driver)
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC");
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage
                .verify_dashboardPge_for_admin("ABC")
                .go_to_orderForEmployeePage();
        OrderForEmployeePage orderForEmployeePage = new OrderForEmployeePage(driver);
        orderForEmployeePage
                .verifyOrderForEmployeePage()
                .verifyOrderTaxiPageLabelNames()
                .setPassengerName("Kuba Mors")
                .setStartAddressByGeolocation()
                .setFinalAddress("Hassa 2, Warszawa");
        boolean isStartAddressAdded = new OrderForEmployeePage(driver).isStartAddressAdded();
        orderForEmployeePage
                .openMoreOptionsAndVerifyButtonText();
        MoreOptionsPage moreOptionsPage = new MoreOptionsPage(driver);
        moreOptionsPage
                .verifyMoreOptionsPageElements()
                .verifyIsEconomicTaxiActive()
                .selectPremiumTaxiAsActive()
                .verifyIsPremiumTaxiActive(isStartAddressAdded)
                .selectLargeTrunk()
                .selectSilentRide()
                .openFeeTableAndVerify()
                .acceptOptions()
                .scrollDownPage()
                .selectOrderTime_future_add_hours_from_now(3)
                .setProject_firstFromList()
                .addComment("od klatki I");
        boolean isSilentRideUse = moreOptionsPage.isSilentRideUse();
        boolean isLargeTrunkUse = moreOptionsPage.isLargeTrunkUse();
        boolean isPremiumTaxiUse = moreOptionsPage.isPremiumTaxiUse();
        passengerName = new OrderForEmployeePage(driver).getPassengerName();
        startAddress = new OrderForEmployeePage(driver).getStartAddress();
        finalAddress = new OrderForEmployeePage(driver).getFinalAddress();
        projectName = new OrderForEmployeePage(driver).getProjectName();
        orderTime = new OrderForEmployeePage(driver).getOrderTime();
        comment = new OrderForEmployeePage(driver).getCommentToCompareInOrderedTaxiPage(isSilentRideUse);
        isLargeTruncSelected = new OrderForEmployeePage(driver).getIfIsLargeTruncSelected(isLargeTrunkUse);
        carClass = new OrderForEmployeePage(driver).getCarClass(isPremiumTaxiUse);
        orderForEmployeePage
                .clickOrderButton()
                .verifyConfirmationOrderPopupWithAllInformation()
                .acceptConfirmationOrderPopupAndVerifyEmptyFieldsOnPageAfterOrder()
                .scrollUp();
        dashboardPage
                .go_to_orderedTaxiPage()
                .verifyOrderedTaxiPage()
                .verifyIfOrderedTaxiSignIsPresentOnButton()
                .confirmFutureOrderIsPresent(passengerName, startAddress, orderTime, projectName, finalAddress, comment, isLargeTruncSelected, carClass);
    }
}
