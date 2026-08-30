package dto;

import lombok.*;
import utils.Enums.FuelType;
import utils.Enums.FuelTypeLocators;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Car {
    private String city;
    private String manufacture;
    private String model;
    private String year;
    private FuelTypeLocators fuelTypeLocators;
    private Integer seats;
    private String carClass;
    private String serial;
    private Double price;
    private String about;
}
