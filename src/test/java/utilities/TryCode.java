package utilities;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class TryCode {
    public static void main(String[] args) {
        String orderTime = "Na teraz!";
        String timeWithoutExclamationMark = orderTime.split("!")[0].trim().toUpperCase();
        System.out.println(timeWithoutExclamationMark);
    }
}
