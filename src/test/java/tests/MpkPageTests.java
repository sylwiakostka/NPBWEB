package tests;

import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.EmployeesPage;
import pages.LoginPage;
import pages.MpkPage;
import utilities.DataFaker;
import utilities.ManageDataProvider;

import java.util.List;
import java.util.Set;

public class MpkPageTests extends BaseTests {

    @Test
    public void should_verify_if_can_switch_mpk_list() throws InterruptedException {
        new LoginPage(driver)
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC")
                .go_to_MpkPage()
                .verify_MpkPage()
                .verify_if_next_and_back_buttons_are_active_and_clickable();

    }

    @Test
    public void should_verify_tab_headers() throws InterruptedException {
        new LoginPage(driver)
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC")
                .go_to_MpkPage()
                .verify_MpkPage()
                .verify_mpk_tab_header_names();
    }


    @Test(dataProvider = "correctDataToAddMPK", dataProviderClass = ManageDataProvider.class)
    public void should_add_new_mpk(String mpkName, String maxAmount, String alertAmount, String comment) throws InterruptedException {
        new LoginPage(driver)
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC")
                .go_to_MpkPage()
                .verify_MpkPage()
                .add_new_mpk(mpkName, maxAmount, alertAmount, comment)
                .verify_is_mpk_added(mpkName, maxAmount, alertAmount, comment);
    }

    @Test
    public void should_cant_add_new_mpk_without_data() throws InterruptedException {
        new LoginPage(driver)
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC")
                .go_to_MpkPage()
                .verify_MpkPage()
                .cant_add_mpk_without_data();
    }


    @Test
    public void should_cant_add_new_mpk_repeated_name() throws InterruptedException {
        new LoginPage(driver)
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC")
                .go_to_MpkPage()
                .verify_MpkPage();
        String activeMpk = new MpkPage(driver).getListOfActiveMpkAndSelectOne(7);
        String existingMpk = new MpkPage(driver).getListOfActiveMpkAndSelectOne(2);
        new MpkPage(driver)
                .choose_appropriate_mpk_with_users_and_click_edit(activeMpk)
                .edit_mpk_fields_and_try_to_add_existing_name(existingMpk);
    }


    @Test
    public void should_active_deleted_mpk() throws InterruptedException {
        new LoginPage(driver)
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC")
                .go_to_MpkPage()
                .verify_MpkPage()
                .show_amount_of_rows_per_page("100")
                .active_deleted_mpk(0);
    }

    @Test
    public void should_delete_mpk_without_users_added() throws InterruptedException {
        new LoginPage(driver)
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC")
                .go_to_EmployeesPage();
        List<String> usedMpkFromEmployeesPage = new EmployeesPage(driver).getListOfMpkInUse();
        new DashboardPage(driver)
                .go_to_MpkPage()
                .verify_MpkPage()
                .show_amount_of_rows_per_page("100");
        List<String> listOfActiveMpk = new MpkPage(driver).getListOfActiveMpk();
        String mpkToDelete = new MpkPage(driver).compareTwoListsAndTypeMpkWihoutUsersAndSelectOne(listOfActiveMpk, usedMpkFromEmployeesPage, 2);
        new MpkPage(driver)
                .choose_appropriate_mpk_without_users_and_click_delete(mpkToDelete)
                .verify_if_deleted_mpk_isnt_on_list(mpkToDelete);
    }


    @Test
    public void should_edit_mpk_with_users() throws InterruptedException {
        new LoginPage(driver)
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC")
                .go_to_EmployeesPage()
                .verifyEmployeesPage();
        String usedMpk = new EmployeesPage(driver).getMpkInUse();
        new DashboardPage(driver)
                .go_to_MpkPage()
                .verify_MpkPage();
        String activeMpk = new MpkPage(driver).getListOfActiveMpkAndSelectOne(7);
        String newMpkName = DataFaker.generate_fake_word();
        new MpkPage(driver)
                .choose_appropriate_mpk_with_users_and_click_edit(usedMpk)
                .edit_mpk_fields_and_verify_if_is_edited_with_users(newMpkName, "330", "250", "tak", activeMpk);
    }

    @Test
    public void should_edit_mpk_without_users() throws InterruptedException {
        new LoginPage(driver)
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC")
                .go_to_EmployeesPage()
                .verifyEmployeesPage();
        List<String> usedMpkFromEmployeesPage = new EmployeesPage(driver).getListOfMpkInUse();
        new DashboardPage(driver)
                .go_to_MpkPage()
                .verify_MpkPage()
                .show_amount_of_rows_per_page("100");
        List<String> listOfActiveMpk = new MpkPage(driver).getListOfActiveMpk();
        String mpkToEdit = new MpkPage(driver).compareTwoListsAndTypeMpkWihoutUsersAndSelectOne(listOfActiveMpk, usedMpkFromEmployeesPage, 2);
        String newMpkName = DataFaker.generate_fake_word();
        new MpkPage(driver)
                .choose_appropriate_mpk_without_users_and_click_edit(mpkToEdit)
                .edit_mpk_fields_and_verify_if_is_edited_without_users(newMpkName, "3330", "2600", "tak");
    }


    @Test
    public void verify_data_sort_in_columns() throws InterruptedException {
        new LoginPage(driver)
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC")
                .go_to_MpkPage()
                .verify_MpkPage()
                .sort_data_by_name("te")
                .sort_data_by_maxAmount("10")
                .sort_data_by_usedAmount("33")
                .sort_data_by_maxAlert("2")
                .sort_data_by_comments("x")
        ;
    }

    @Test
    public void should_delete_mpk_with_users() throws InterruptedException {
        new LoginPage(driver)
                .verify_loginPage()
                .login_as_superAdmin()
                .choose_business_partner_from_list("ABC")
                .go_to_EmployeesPage();
        String usedMpk = new EmployeesPage(driver).getMpkInUse();
        new DashboardPage(driver)
                .go_to_MpkPage()
                .verify_MpkPage()
                .show_amount_of_rows_per_page("100");
        String activeMpk = new MpkPage(driver).getListOfActiveMpkAndSelectOne(2);
        new MpkPage(driver)
                .choose_appropriate_mpk_with_users_and_click_delete(usedMpk, activeMpk)
                .verify_if_deleted_mpk_isnt_on_list(usedMpk);
    }


}
