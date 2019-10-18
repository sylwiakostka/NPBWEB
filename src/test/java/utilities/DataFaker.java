package utilities;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

import java.util.Locale;

public  class DataFaker {

    Faker plFaker = new Faker(new Locale("pl"));
   public static FakeValuesService fakeValuesService = new FakeValuesService(
            new Locale("pl"), new RandomService());

    public static String generate_fake_passwrod(){
        String password = fakeValuesService.regexify("[a-z1-9]{10}");
        return password;
    }

    public static String generate_fake_email(){
        String email = fakeValuesService.bothify("????##@test.test");
        return email;
    }


}
