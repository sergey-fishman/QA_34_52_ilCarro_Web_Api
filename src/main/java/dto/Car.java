package dto;

import Enums.FuelType;
import lombok.*;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Car {
    private String manufacture;
    private String model;
    private String year;
    private String fuelType;
    private String seats;
    private String carClass;
    private String serial;
    private String price;
}
