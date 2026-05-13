package org.gulash.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO для передачи уведомлений через асинхронные каналы (например, Kafka).
 * <p>
 * Это сообщение будет описано в AsyncAPI схеме.
 * Аннотации {@link Schema} из OpenAPI также могут использоваться Springwolf-ом для генерации
 * схемы сообщения, что позволяет переиспользовать разметку.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Сущность уведомления для асинхронной передачи")
public class NotificationDto {

    /**
     * Уникальный идентификатор уведомления.
     * <pre>{@code
     * notification.setId("uuid-123");
     * }</pre>
     */
    @Schema(description = "ID уведомления", example = "550e8400-e29b-41d4-a716-446655440000")
    private String id;

    /**
     * Текст сообщения уведомления.
     */
    @Schema(description = "Текст уведомления", example = "Ваш заказ успешно оформлен!")
    private String message;

    /**
     * Тип уведомления (EMAIL, SMS, PUSH).
     */
    @Schema(description = "Тип уведомления", example = "EMAIL")
    private String type;
}
