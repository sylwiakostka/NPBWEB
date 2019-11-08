package utilities;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class TryCode {
    public static void main(String[] args) {
        int amountOfDays = 60;
        int listSize = 30;
        int daysForNextMonth =amountOfDays - listSize;
        while (daysForNextMonth>listSize){
        daysForNextMonth = daysForNextMonth - listSize;
        }
        System.out.println(daysForNextMonth);
    }
}
