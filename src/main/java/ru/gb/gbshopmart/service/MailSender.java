package ru.gb.gbshopmart.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.gb.gbapi.events.OrderEvent;
import ru.gb.gbapi.order.dto.OrderDto;
import ru.gb.gbshopmart.config.JmsConfig;


@RequiredArgsConstructor
@Component
public class MailSender {

    private final JmsTemplate jmsTemplate;
//    private final ObjectMapper objectMapper;

//    @Scheduled(fixedRate = 2000)
    public void sendMessage( OrderDto orderDto) {

        OrderEvent orderEvent = new OrderEvent(orderDto);

        jmsTemplate.convertAndSend(JmsConfig.ORDER_CHANGED_QUEUE, orderEvent);

    }
}
