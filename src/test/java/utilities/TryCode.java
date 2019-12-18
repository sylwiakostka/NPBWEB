package utilities;

import org.openqa.selenium.WebElement;
import pages.MoreOptionsPage;

import java.util.*;


public class TryCode {
    public static void main(String[] args) {

        boolean isProjectPresent = false;
        List<String> checkboxStatuses = new ArrayList<String>();
        checkboxStatuses.add("true");
        checkboxStatuses.add("true");
        checkboxStatuses.add("true");

        System.out.println(checkboxStatuses);
        for (int i = 1; i < checkboxStatuses.size(); i++) {
            if (checkboxStatuses.get(i).equals("true")) {
                isProjectPresent = true;
            }
        }
        System.out.println(isProjectPresent);

    }

}
