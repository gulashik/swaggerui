package org.gulash.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO (Data Transfer Object) пользователя.
 * <p>
 * Аннотация {@code @Schema} используется для описания модели в Swagger.
 * Она позволяет задать человекочитаемое имя, описание и примеры значений.
 * </p>
 *
 * <pre>{@code
 * UserDto user = new UserDto(1L, "Ivan", "ivan@example.com");
 * }</pre>
 *
 * <b>Подводные камни:</b>
 * Если не использовать {@code @Schema}, Swagger будет использовать имена полей Java,
 * что не всегда информативно для потребителей API. Важно также указывать ограничения (валидацию),
 * так как Springdoc автоматически отображает их в документации.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Сущность пользователя для REST взаимодействия")
public class UserDto {

    /**
     * Уникальный идентификатор пользователя.
     * <p>
     * <b>Best Practice:</b> Всегда указывайте {@code example}, чтобы Swagger UI мог сгенерировать
     * валидное тело запроса для тестирования.
     * </p>
     */
    @Schema(description = "Уникальный идентификатор", example = "1")
    @Positive(message = "ID должен быть положительным числом")
    private Long id;

    /**
     * Имя пользователя. Не может быть пустым.
     * <p>
     * Аннотация {@link jakarta.validation.constraints.NotBlank} автоматически учитывается Springdoc-ом,
     * добавляя пометку "required" в схему OpenAPI.
     * </p>
     */
    @Schema(description = "Имя пользователя", example = "Иван")
    @NotBlank(message = "Имя не может быть пустым")
    private String name;

    /**
     * Электронная почта пользователя.
     */
    @Schema(description = "Электронная почта", example = "ivan@example.com")
    @NotBlank(message = "Email не может быть пустым")
    private String email;
}
