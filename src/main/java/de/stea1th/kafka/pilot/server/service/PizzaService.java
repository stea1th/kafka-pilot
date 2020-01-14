package de.stea1th.kafka.pilot.server.service;

import de.stea1th.kafka.pilot.server.dto.PizzaDto;

public interface PizzaService {

    void send(PizzaDto pizzaDto);

    void consume(String message);
}
