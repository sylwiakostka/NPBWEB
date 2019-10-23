package utilities;

import org.openqa.selenium.WebDriver;
import pages.BasePage;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DataManipulations extends BasePage {

    public DataManipulations(WebDriver driver) {
        super(driver);
    }

    public SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy,HH:mm");
    public Date currentDate = new Date();
    // convert date to calendar
    public Calendar calendar = Calendar.getInstance();


    public String addHourToCurrentDate(int amountOfHours) {
        // manipulate date
        calendar.setTime(currentDate);
        calendar.add(Calendar.HOUR, amountOfHours);
        Date currentDatePlusHours = calendar.getTime();
        String newDate = dateFormat.format(currentDatePlusHours);
        System.out.println(newDate);
        return newDate;

    }

    public String addMinutesToCurrentDate(int amountOfMinutes) {
        calendar.setTime(currentDate);
        calendar.add(Calendar.MINUTE, amountOfMinutes);
        Date currentDatePlusMinutes = calendar.getTime();
        String newDate = dateFormat.format(currentDatePlusMinutes);
        System.out.println(newDate);
        return newDate;
    }

    public String addDaysToCurrentDate(int amountOfDays) {
        calendar.setTime(currentDate);
        calendar.add(Calendar.DATE, amountOfDays);
        Date currentDatePlusDays = calendar.getTime();
        String newDate = dateFormat.format(currentDatePlusDays);
        System.out.println(newDate);
        return newDate;
    }

    public String addMinutesHoursAndDaysToCurrentDate (int amountOfMinutes,int amountOfHours,int amountOfDays) {
        calendar.setTime(currentDate);
        calendar.add(Calendar.MINUTE,amountOfMinutes);
        calendar.add(Calendar.HOUR,amountOfHours);
        calendar.add(Calendar.DATE, amountOfDays);
        Date changedDate = calendar.getTime();
        String newDate = dateFormat.format(changedDate);
        System.out.println(newDate);
        return newDate;
    }
}