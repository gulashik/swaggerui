### Swagger UI

### Зависимости
Основная зависимость — `springdoc-openapi-starter-webmvc-ui`.
Вспомогательные зависимости:
- `spring-boot-starter-validation` - для валидации данных Springdoc учитывает их при генерации схемы.

### Конфигурация
В [application.yml](src/main/resources/application.yml) задаются базовые пути:
    - `/api-docs` — JSON спецификация.
    - `/swagger-ui.html` — визуальный интерфейс.

Программная настройка через бин `OpenAPI` в [OpenAPIConfig.java](src/main/java/org/gulash/demo/config/OpenAPIConfig.java) позволяет добавить метаданные: лицензии, контакты, описание.

### Разметка кода
1. **Контроллеры**: [UserController.java](src/main/java/org/gulash/demo/controller/UserController.java) Использование `@Tag` для группировки и `@Operation` для описания методов.
2. **DTO**:  Использование `@Schema` для описания полей и примеров данных.
3. **Валидация**: [UserDto.java](src/main/java/org/gulash/demo/dto/UserDto.java), [UserController.java](src/main/java/org/gulash/demo/controller/UserController.java) Аннотации `@NotBlank`, `@Min` и др. автоматически подхватываются Swagger-ом и отображаются как ограничения в UI.


### Пример готовой схемы
Файл [api-docs.json](api-docs.json) показывает пример JSON спецификацию Open API.

## Как использовать
Сборка, Запуск и Использование смотрим [ACTIONS.md](ACTIONS.md)

---

## 2. OpenAPI vs AsyncAPI

| Характеристика         | OpenAPI (Swagger)             | AsyncAPI                                         |
|:-----------------------|:------------------------------|:-------------------------------------------------|
| **Тип взаимодействия** | Синхронный (Request-Response) | Асинхронный (Event-Driven / Pub-Sub)             |
| **Протоколы**          | HTTP(S)                       | Kafka, RabbitMQ, MQTT, WebSocket, и др.          |
| **Основной фокус**     | Эндпоинты (Paths)             | Каналы (Channels), Сообщения (Messages)          |
| **Spring Boot**        | Интеграция через `springdoc`  | Интеграция через `springwolf` (аналог springdoc) |

---

## 3. Генерация: Способы и приложения

### Генерация схемы из кода (Code-First)
- **Способ:** Использование библиотеки `springdoc-openapi`.
- **Плюсы:** Документация всегда актуальна и соответствует коду.
- **Минусы:** Схема "размазана" по аннотациям в коде.

### Генерация кода из схемы (Design-First / Contract-First)
- **Инструменты:** [OpenAPI Generator](https://openapi-generator.tech/), [Swagger Codegen](https://swagger.io/tools/swagger-codegen/).
- **Применение:** Сначала рисуется YAML файл, затем генерируются интерфейсы контроллеров и DTO.
- **Плюсы:** Строгое соблюдение контракта, возможность параллельной разработки фронтенда и бэкенда.

### Генерация UI из схемы
- **Swagger UI**: Самый популярный. Может запускаться как внутри Java-приложения, так и в Docker (см. `compose.yml`).
- **Redoc**: Альтернативный UI, ориентированный на чтение (трехпанельный вид).
