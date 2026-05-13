package org.gulash.demo.controller;

import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import io.github.springwolf.core.asyncapi.annotations.AsyncPublisher;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.gulash.demo.dto.NotificationDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для отправки уведомлений. Демонстрирует "Producer" сторону в AsyncAPI.
 */
@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
@Tag(name = "Notifications", description = "Управление уведомлениями (AsyncAPI Demo)")
public class NotificationController {

    private final static Logger log = LoggerFactory.getLogger(NotificationController.class);

    private final KafkaTemplate<String, NotificationDto> kafkaTemplate;

    /**
     * Отправка уведомления в Kafka.
     */
    @PostMapping
    @Operation(summary = "Отправить уведомление", description = "REST-метод, который инициирует асинхронную отправку сообщения в Kafka")
    @AsyncPublisher(operation = @AsyncOperation(
            channelName = "notifications-topic",
            description = "Публикация уведомления в Kafka топик",
            servers = {"kafka"}
    ))
    public void sendNotification(@RequestBody NotificationDto notification) {
        log.info("Sending notification: {}", notification);
        kafkaTemplate.send("notifications-topic", notification.getId(), notification);
    }
}
