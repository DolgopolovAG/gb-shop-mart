package ru.gb.gbshopmart.service;

import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import ru.gb.gbapi.events.OrderEvent;
import ru.gb.gbshopmart.config.JmsConfig;

@RequiredArgsConstructor
@Component
public class OrderListener {

    private final MailService mailService;

    @JmsListener(destination = JmsConfig.ORDER_CHANGED_QUEUE)
    public void listen(@Payload OrderEvent orderEvent) {
        System.out.println(orderEvent.getOrderDto());
        mailService.sendSimpleMessage("dolgopolov.ag@yandex.ru", "Изменилась информация по заказу",
                orderEvent.getOrderDto().toString());
//        throw new RuntimeException("my error");
    }
}