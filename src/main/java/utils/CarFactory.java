package utils;

import dto.Car;
import net.datafaker.Faker;
import utils.Enums.FuelType;
import utils.Enums.FuelTypeLocators;

import java.time.Instant;
import java.time.LocalDate;

public class CarFactory {
    public static Faker faker = new Faker();

    public static void main(String[] args) {
//        System.out.println(faker.vehicle().manufacturer());
//        System.out.println(faker.vehicle().model());
//        System.out.println(faker.vehicle().carType());
//        System.out.println(faker.vehicle().driveType());
//        System.out.println(faker.vehicle().licensePlate());
//        faker.text().text(0,500);
        System.out.println(positiveCar());
    }

    public static Car positiveCar() {
        return Car.builder()
                .city("Berlin")
                .manufacture(faker.vehicle().manufacturer())
                .model(faker.vehicle().model())
                .year(Integer.toString(faker.number().numberBetween
                        (0, LocalDate.now().getYear())))
                .fuelTypeLocators(faker.options().option(FuelTypeLocators.values()))
                .seats(faker.number().numberBetween(2, 20))
                .carClass(faker.vehicle().style())
                .serial(faker.vehicle().licensePlate())
                .price(faker.number().randomDouble(2, 0, 1000))
                .about(faker.text().text(0,500))
                .build();
    }
}

