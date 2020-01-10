package de.stea1th.kafka.pilot.server.controler;

import de.stea1th.kafka.pilot.server.service.PizzaService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PizzaController {

    private final PizzaService pizzaService;

    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }


}
