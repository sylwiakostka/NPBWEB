package tests;

import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import pages.ProfilesPage;
import utilities.ManageDataProvider;

import java.util.List;

public class ProfilesPageTests extends BaseTests {

    @Test
    public void should_verify_tab_headers() throws InterruptedException {
        new LoginPage(driver)
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC")
                .go_to_MpkPage()
                .verify_MpkPage()
                .go_to_ProfilesPage()
                .verify_profilesPage()
                .verify_profiles_tab_header_names()
                .scroll_right();
        new DashboardPage(driver).logout();
    }

    @Test
    public void should_verify_data_sort_in_columns() throws InterruptedException {
        new LoginPage(driver)
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC")
                .go_to_MpkPage()
                .verify_MpkPage()
                .go_to_ProfilesPage()
                .verify_profilesPage()
                .sort_from_list_by_days("PON-PIA")
                .sort_data_by_name("2")
                .sort_data_by_orderAmount("30")
                .sort_data_by_maxAmount("10")
                .sort_data_by_maxAlert("2")
                .sort_data_by_maxTariff("5")
                .sort_data_by_maxUsersNumber("1");

    }


    @Test
    public void should_verify_if_can_switch_profiles_list() throws InterruptedException {
        new LoginPage(driver)
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC")
                .go_to_MpkPage()
                .verify_MpkPage()
                .go_to_ProfilesPage()
                .verify_profilesPage()
                .verify_if_next_and_back_buttons_are_active_and_clickable();
    }

    @Test(dataProvider = "correctDataToAddProfiles", dataProviderClass = ManageDataProvider.class)
    public void should_add_new_profile(String profileName, String timeFrom, String timeTo, String maxAmount, String maxOrders,
                                       String alertAmount, String orderDays, String maxTariff, String carClass, String comment) throws InterruptedException {
        new LoginPage(driver)
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC")
                .go_to_MpkPage()
                .verify_MpkPage()
                .go_to_ProfilesPage()
                .verify_profilesPage()
                .add_new_profile(profileName, timeFrom, timeTo, maxAmount, maxOrders, alertAmount, orderDays, maxTariff, carClass, comment)
                .verify_is_profile_added(profileName, timeFrom, timeTo, maxAmount, maxOrders, alertAmount, orderDays, maxTariff, carClass);

    }

    @Test
    public void should_cant_add_new_profile_without_data_with_incorrect_limits_and_with_incorrect_hours() throws InterruptedException {
        new LoginPage(driver)
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC")
                .go_to_MpkPage()
                .verify_MpkPage()
                .go_to_ProfilesPage()
                .verify_profilesPage()
                .cant_add_profile_without_data()
                .cant_add_new_profile_with_incorrect_limits_AmountAlertWithoutMaxAmount("limity", "50")
                .cant_add_new_profile_with_incorrect_hours_finishTimeBeforeStartTime("godz", "03:00", "01:30");
    }

    @Test
    public void should_delete_profile_without_users_added() throws InterruptedException {
        new LoginPage(driver)
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC")
                .go_to_MpkPage()
                .verify_MpkPage()
                .go_to_ProfilesPage()
                .verify_profilesPage()
                .choose_profile_without_users_click_delete_and_verify_is_not_on_list(2);
    }

    @Test
    public void should_delete_profile_with_users_added() throws InterruptedException {
        new LoginPage(driver)
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC")
                .go_to_MpkPage()
                .verify_MpkPage()
                .go_to_ProfilesPage()
                .verify_profilesPage()
                .choose_appropriate_profile_with_users_and_click_delete_and_verify_is_not_on_list(1);
    }

    @Test
    public void should_try_to_delete_profile_with_users_added_without_set_new_profile() throws InterruptedException {
        new LoginPage(driver)
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC")
                .go_to_MpkPage()
                .verify_MpkPage()
                .go_to_ProfilesPage()
                .verify_profilesPage()
                .choose_appropriate_profile_with_users_and_click_delete_without_set_new_profile(0);
    }

    @Test
    public void should_compare_list_of_mpk_on_page_with_list_during_editing_and_deleting_profile() throws InterruptedException {
        new LoginPage(driver)
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC")
                .go_to_MpkPage()
                .verify_MpkPage()
                .go_to_ProfilesPage()
                .verify_profilesPage();
        List<String> listOfProfiles = new ProfilesPage(driver).getListOfExistingProfiles();
        new ProfilesPage(driver).choose_appropriate_profile_with_users_and_compare_lists_on_form_and_on_page(0,listOfProfiles);
    }


    @Test
    public void should_edit_profile_without_users_added() throws InterruptedException {
        new LoginPage(driver)
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC")
                .go_to_MpkPage()
                .verify_MpkPage()
                .go_to_ProfilesPage()
                .verify_profilesPage()
                .choose_appropriate_profile_without_users_and_click_edit(1)
                .edit_profile_fields_and_verify_if_is_edited("zazaz5", "08:00", "17:00", "40", "6", "20", "PON-SOB", "22", "Premium", "abce");
    }


    @Test
    public void should_edit_profile_with_users_added() throws InterruptedException {
        new LoginPage(driver)
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC")
                .go_to_MpkPage()
                .verify_MpkPage()
                .go_to_ProfilesPage()
                .verify_profilesPage()
                .choose_appropriate_profile_with_users_and_click_edit(1)
                .edit_profile_fields_and_verify_if_is_edited("11", "07:00", "20:00", "60", "5", "20", "PON-SOB", "22", "Premium", "abce");
    }


}
