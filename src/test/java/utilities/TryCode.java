package utilities;

import java.util.*;

public class TryCode {

    public static void orderMoreThanOneTaxi(List<String>employees){
        int listSize = employees.size();

        List<String> passengerNames = new ArrayList<>();
        for (int i = 0; i <= listSize - 1; i++) {
            passengerNames.add(employees.get(i));
        }
        System.out.println(passengerNames);
    }


    public static void main(String[] args) {
        List<String> employees = Arrays.asList("2", "Julek Angielski", "Kuba Mors,", "Al. Niepodległości 1, Warszawa", "Na teraz!");

        for (String employee:employees) {
            employee.replaceAll(",","");
        }
        System.out.println("Actual new: "+employees);
    }
}
