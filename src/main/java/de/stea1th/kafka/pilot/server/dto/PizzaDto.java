package de.stea1th.kafka.pilot.server.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PizzaDto extends AbstractDto {

    private String name;
    private double price;

}
