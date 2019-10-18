package tests;

import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import utilities.ManageDataProvider;

public class ProfilesPageTests extends BaseTests {

    @Test
    public void should_verify_tab_headers() throws InterruptedException {
        new LoginPage(driver)
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("HH")
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
                .choose_business_partner_from_list("HH")
                .go_to_MpkPage()
                .verify_MpkPage()
                .go_to_ProfilesPage()
                .verify_profilesPage()
                .sort_from_list_by_days("Pon - pt")
                .sort_data_by_name("2")
                .sort_data_by_orderAmount("30")
                .sort_data_by_maxAmount("10")
                .sort_data_by_maxAlert("2")
                .sort_data_by_maxTariff("5")
                .sort_data_by_maxUsersNumber("1");

    }


    @Test
    public void should_compare_profile_list_in_delete_and_edit_section() throws InterruptedException {
        new LoginPage(driver)
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("HH")
                .go_to_MpkPage()
                .verify_MpkPage()
                .go_to_ProfilesPage()
                .verify_profilesPage()
                .show_amount_of_rows_per_page("100")
                .compare_list_of_profiles_in_delete_section("2,2 zł za km")
                .compare_list_of_profiles_in_edit_section("Bez limitów")
        ;
    }


    @Test
    public void should_verify_if_can_switch_profiles_list() throws InterruptedException {
        new LoginPage(driver)
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("HH")
                .go_to_MpkPage()
                .verify_MpkPage()
                .go_to_ProfilesPage()
                .verify_profilesPage()
                .verify_if_next_and_back_buttons_are_active_and_clickable();
        new DashboardPage(driver).logout();
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
                .choose_appropriate_profile_without_users_and_click_delete("pop")
                .verify_if_deleted_profile_isnt_on_list("pop");
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
                .choose_appropriate_profile_with_users_and_click_delete("luk", "kul")
                .verify_if_deleted_profile_isnt_on_list("luk");
    }

    @Test
    public void should_edit_profile() throws InterruptedException {
        new LoginPage(driver)
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC")
                .go_to_MpkPage()
                .verify_MpkPage()
                .go_to_ProfilesPage()
                .verify_profilesPage()
                .choose_appropriate_profile_without_users_and_click_edit("kawakonfa")
                .edit_profile_fields_and_verify_if_is_edited("abc", "09:00", "17:00", "40", "6", "", "Pon - pt", "", "Luksusowa", "abce", "war");

    }


    //dodac sprawdzanie listy profili to przepisania - tylko profile dla danej firmy

}
