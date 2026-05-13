package org.gulash.demo.service;

import io.github.springwolf.core.asyncapi.annotations.AsyncListener;
import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import lombok.extern.slf4j.Slf4j;
import org.gulash.demo.dto.NotificationDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * Сервис-слушатель для демонстрации работы AsyncAPI и Kafka.
 */
@Slf4j
@Service
public class NotificationListener {

    /**
     * Обработка входящих уведомлений из Kafka.
     */
    @AsyncListener(operation = @AsyncOperation(
            channelName = "notifications-topic",
            description = "Получение уведомлений для последующей отправки пользователям",
            servers = {"kafka"}
    ))
    @KafkaListener(topics = "notifications-topic", groupId = "swagger-demo-group")
    public void consumeNotification(NotificationDto notification) {
        log.info("Получено уведомление через Kafka: {}", notification);
    }
}
