package de.stea1th.kafka.pilot.test.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.stea1th.kafka.pilot.test.dto.PizzaDto;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

@Service
@EnableScheduling
@Slf4j
public class ScheduledService {

    private final KafkaTemplate<Long, PizzaDto> kafkaPizzaTemplate;

    private final ObjectMapper objectMapper;


    public ScheduledService(KafkaTemplate<Long, PizzaDto> kafkaPizzaTemplate, ObjectMapper objectMapper) {
        this.kafkaPizzaTemplate = kafkaPizzaTemplate;
        this.objectMapper = objectMapper;
    }

    @Scheduled(initialDelay = 5000, fixedDelay = 5000)
    public void produce() {

        var pizzaDto = createDto();
        log.info("<= sending {}", writeValueAsString(pizzaDto));
        kafkaPizzaTemplate.send("server.pizza", pizzaDto);
    }

    @SneakyThrows
    private String writeValueAsString(PizzaDto pizzaDto) {
        return objectMapper.writeValueAsString(pizzaDto);
    }

    private PizzaDto createDto() {
        var pizzaDto = new PizzaDto();
        pizzaDto.setName("Pizza Mozarella");
        pizzaDto.setPrice(getRandomPrice());
        return pizzaDto;
    }

    private double getRandomPrice() {
        var random = new Random();
        var bigDecimal = new BigDecimal(random.nextDouble() * 100);
        return bigDecimal.setScale(2, RoundingMode.CEILING).doubleValue();
    }
}
