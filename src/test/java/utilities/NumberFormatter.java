package utilities;

import java.text.DecimalFormat;

public class NumberFormatter {


    public static String changeToDecimalFormat(String number) {
        int i = Integer.parseInt(number);
        DecimalFormat dc = new DecimalFormat(".00");
        String decimalNumber = dc.format(i).replaceAll(",", ".");
        return decimalNumber;
    }

}
