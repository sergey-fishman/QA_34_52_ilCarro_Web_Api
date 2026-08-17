package utils;

import dto.UserLombok;
import net.datafaker.Faker;

public class UserFactory {
    static Faker faker = new Faker();

    public static void main(String[] args) {
        System.out.println(positiveUser().getFirstName());
        System.out.println(positiveUser().getLastName());
        System.out.println(positiveUser().getUsername());
        System.out.println(positiveUser().getPassword());
    }


    public static UserLombok positiveUser() {
        return UserLombok.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .username(faker.internet().emailAddress())
                .password(PropertiesReader.getProperty
                        ("base.properties", "password_for_registration"))
                .build();
    }
}
