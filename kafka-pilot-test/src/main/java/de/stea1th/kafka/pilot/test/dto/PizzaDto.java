package de.stea1th.kafka.pilot.test.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PizzaDto extends AbstractDto {

    private String name;
    private double price;

}
