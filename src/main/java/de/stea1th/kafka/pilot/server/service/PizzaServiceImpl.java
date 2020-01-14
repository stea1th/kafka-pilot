package de.stea1th.kafka.pilot.server.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.stea1th.kafka.pilot.server.dto.PizzaDto;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PizzaServiceImpl implements PizzaService {


    private final KafkaTemplate<Long, PizzaDto> kafkaPizzaTemplate;

    private final ObjectMapper objectMapper;

    @Autowired
    public PizzaServiceImpl(KafkaTemplate<Long, PizzaDto> kafkaPizzaTemplate, ObjectMapper objectMapper) {
        this.kafkaPizzaTemplate = kafkaPizzaTemplate;
        this.objectMapper = objectMapper;
    }


//    @Autowired
//    public PizzaServiceImpl(KafkaTemplate<Long, PizzaDto> kafkaPizzaTemplate, ObjectMapper objectMapper) {
//        this.kafkaPizzaTemplate = kafkaPizzaTemplate;
//        this.objectMapper = objectMapper;
//    }


    @Override
    public void send(PizzaDto pizzaDto) {
        log.info("<= sending {}", writeValueAsString(pizzaDto));
        kafkaPizzaTemplate.send("server.pizza", pizzaDto);
    }

    @Override
    @KafkaListener(id = "group_id", topics = {"server.pizza"})
    public void consume(PizzaDto pizzaDto) {
        log.info("=> consumed {}", writeValueAsString(pizzaDto));
    }

    @SneakyThrows
    private String writeValueAsString(PizzaDto pizzaDto) {
        return objectMapper.writeValueAsString(pizzaDto);
    }
}
