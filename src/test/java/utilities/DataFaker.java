package utilities;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Locale;

public  class DataFaker {


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

    public static String generate_fake_word(){
        String email = fakeValuesService.regexify("[a-z1-9]{5}");
        return email;
    }

    public static String generate_fake_password_only_numbers(){
        String password = fakeValuesService.regexify("[1-9]{10}");
        return password;
    }

    public static String generate_fake_word_with_only_special_signs(){
        String specialSigns = fakeValuesService.regexify("[!@#$%^&*()?><~`]{10}");
        return specialSigns;
    }


    public static String generate_text_with_range_of_characters(int minChar,int maxChar) {
        String generatedString = RandomStringUtils.randomAlphabetic(minChar, maxChar);
        return generatedString;
    }


    public static String generate_text_with_exact_amount_of_characters(int characters) {
        String generatedString = RandomStringUtils.randomAlphabetic(characters);
        return generatedString;
    }


}
