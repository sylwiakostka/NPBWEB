package utilities;
import java.util.*;


public class TryCode {
    public static void main(String[] args) {

        List<String> cityList = new ArrayList<String>();
        cityList.add("Delhi");
        cityList.add("Mumbai");
        cityList.add("Bangalore");
        cityList.add("Chennai");
        cityList.add("Kolkata");
        cityList.add("Mumbai");

        List<String> cityList2 = new ArrayList<String>();
        cityList2.add("Warsaw");
        cityList2.add("Mumbai");
        cityList2.add("Paris");
        cityList2.add("London");
        cityList2.add("Rome");
        cityList2.add("Madrid");

        ArrayList<String> list3 = new ArrayList<String>(cityList);
        list3.retainAll(cityList2);
        cityList.removeAll(list3);
        System.out.println("lista 1 bez powtórki"+cityList);

    }
}