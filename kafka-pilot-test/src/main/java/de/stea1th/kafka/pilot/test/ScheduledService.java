package de.stea1th.kafka.pilot.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.stea1th.kafka.pilot.test.dto.PizzaDto;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;

@Service
@EnableScheduling
@Slf4j
public class ScheduledService {

    private final KafkaTemplate<Long, PizzaDto> kafkaPizzaTemplate;

    private final ObjectMapper objectMapper;

    private final BigDecimal bigDecimal;

    public ScheduledService(KafkaTemplate<Long, PizzaDto> kafkaPizzaTemplate, ObjectMapper objectMapper) {
        this.kafkaPizzaTemplate = kafkaPizzaTemplate;
        this.objectMapper = objectMapper;
        this.bigDecimal = BigDecimal.valueOf(5.99);
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
        pizzaDto.setPrice(bigDecimal.add(BigDecimal.valueOf(0.5)).doubleValue());
        return pizzaDto;
    }


}
