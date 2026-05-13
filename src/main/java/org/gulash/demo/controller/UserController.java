package org.gulash.demo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.gulash.demo.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Контроллер для управления пользователями.
 * <p>
 * Аннотация {@code @Tag} позволяет сгруппировать эндпоинты в Swagger UI.
 * </p>
 *
 * <b>Особенности:</b>
 * Использование {@code @RestController} автоматически добавляет {@code @ResponseBody} к каждому методу.
 */
@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "Пользователи", description = "Управление списком пользователей")
public class UserController {

    private final List<UserDto> users = new ArrayList<>();

    /**
     * Получение списка всех пользователей.
     *
     * <pre>{@code
     * GET /api/v1/users
     * }</pre>
     *
     * @return Список пользователей.
     */
    @Operation(summary = "Получить всех пользователей", description = "Возвращает полный список зарегистрированных пользователей")
    @GetMapping
    public List<UserDto> getAllUsers() {
        return users;
    }

    /**
     * Получение пользователя по ID.
     *
     * <pre>{@code
     * GET /api/v1/users/1
     * }</pre>
     *
     * <b>Подводные камни:</b>
     * Если не описать {@code @ApiResponses}, Swagger покажет только стандартный ответ 200 OK,
     * не информируя о возможной ошибке 404.
     */
    @Operation(summary = "Найти пользователя по ID", description = "Возвращает одного пользователя по его идентификатору")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Пользователь найден",
            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class)) }),
        @ApiResponse(responseCode = "404", description = "Пользователь не найден", content = @Content)
    })
    @GetMapping("/{id}")
    public UserDto getUserById(
            @Parameter(description = "ID пользователя для поиска", example = "1")
            @PathVariable Long id) {
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    /**
     * Создание нового пользователя.
     *
     * <pre>{@code
     * POST /api/v1/users
     * { "id": 1, "name": "Ivan", "email": "ivan@example.com" }
     * }</pre>
     *
     * <b>Best Practices:</b>
     * Всегда используйте {@code @Valid} для активации валидации DTO.
     */
    @Operation(summary = "Создать пользователя", description = "Добавляет нового пользователя в систему")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@Valid @RequestBody UserDto userDto) {
        users.add(userDto);
        return userDto;
    }
}
