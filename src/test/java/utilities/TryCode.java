package utilities;


import org.apache.commons.lang3.RandomStringUtils;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

public class TryCode {
    public static void main(String[] args) {

//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy,HH:mm");
//        Date currentDate = new Date();
//        System.out.println(dateFormat.format(currentDate));
//
//        // convert date to calendar
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(currentDate);
//
//        // manipulate date
//        calendar.add(Calendar.HOUR,1);
//
//        Date currentDatePlusOneDay = calendar.getTime();
//
//        System.out.println(dateFormat.format(currentDatePlusOneDay));
        String emailText = "Taksówka 1 Zuzia Nowak";
        String firstStage = emailText.split("1 ")[1].trim();
        String secondStage = firstStage.split("Odpowiedz")[0].trim();
        String code = secondStage;
        System.out.println(code);
    }


}
