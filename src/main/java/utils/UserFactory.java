package utils;

import dto.UserLombok;
import net.datafaker.Faker;

public class UserFactory {
    static Faker faker = new Faker();

    public static void main(String[] args) {
        System.out.println(positiveUser().getFirstName());
        System.out.println(positiveUser().getLastName());
        System.out.println(positiveUser().getEmail());
        System.out.println(positiveUser().getPassword());
        System.out.println(faker.halfLife().character());
        System.out.println(faker.vehicle().model());
    }

    public static UserLombok positiveUser() {
        return UserLombok.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .password(PropertiesReader.getProperty
                        ("base.properties", "password_for_registration"))
                .build();
    }
}
