
<!-- TOC -->
* [Swagger UI, OpenAPI & AsyncAPI в Spring Boot](#swagger-ui-openapi--asyncapi-в-spring-boot)
  * [Зависимости (build.gradle.kts)](#зависимости-buildgradlekts)
  * [Разметка](#разметка-)
    * [OpenAPI (Swagger)](#openapi-swagger)
    * [AsyncAPI (Springwolf)](#asyncapi-springwolf)
  * [OpenAPI vs AsyncAPI](#openapi-vs-asyncapi)
  * [Генерация: Способы и инструменты](#генерация-способы-и-инструменты)
    * [Генерация схемы из кода (Code-First)](#генерация-схемы-из-кода-code-first)
    * [Генерация кода из схемы (Design-First / Contract-First)](#генерация-кода-из-схемы-design-first--contract-first)
  * [Запуск проекта](#запуск-проекта)
<!-- TOC -->

---
# Swagger UI, OpenAPI & AsyncAPI в Spring Boot
## Зависимости (build.gradle.kts)
- `spring-boot-starter-web`: Базовая зависимость для REST API. Swagger документирует именно эти эндпоинты.
- `springdoc-openapi-starter-webmvc-ui`: Основной движок для OpenAPI 3. Генерирует JSON/YAML спецификацию и предоставляет встроенный Swagger UI.
- `spring-boot-starter-validation`: Позволяет Swagger-у видеть ограничения полей (`@Min`, `@NotBlank`) и отображать их в UI как правила валидации.
- `io.github.springwolf:springwolf-kafka`: Аналог springdoc, но для асинхронных протоколов. Сканирует `@KafkaListener` и генерирует AsyncAPI схему.
- `io.github.springwolf:springwolf-ui`: Визуальный интерфейс для просмотра AsyncAPI схем.

---

## Разметка 

### OpenAPI (Swagger)
1. **Добавление зависимости**: `springdoc-openapi-starter-webmvc-ui`.
2. **Конфигурация**: В `application.yml` задаются пути `/api-docs` и `/swagger-ui.html`.
3. **Метаданные**: Создание бина `OpenAPI` в `OpenAPIConfig.java` для описания версии, контактов и лицензий.
4. **Разметка**:
   - `@Tag`: Группировка методов контроллера.
   - `@Operation`: Описание конкретного HTTP-метода.
   - `@Schema`: Описание DTO и его полей.

### AsyncAPI (Springwolf)
1. **Добавление зависимостей**: `springwolf-kafka` и `springwolf-ui`.
2. **Конфигурация**: Настройка свойств `springwolf.docket.*` в `application.yml` или через бин `AsyncAPI`.
3. **Разметка**:
   - `@AsyncListener`: Документирование потребления сообщений (Consumer).
   - `@AsyncPublisher`: Документирование публикации сообщений (Producer).
   - `@KafkaTopicOperation`: Специфичные для Kafka настройки топика.

---

## OpenAPI vs AsyncAPI

| Характеристика     | OpenAPI (Swagger)             | AsyncAPI                         |
|:-------------------|:------------------------------|:---------------------------------|
| **Взаимодействие** | Синхронное (Request-Response) | Асинхронное (Event-Driven)       |
| **Протоколы**      | HTTP(S)                       | Kafka, RabbitMQ, MQTT, WebSocket |
| **Фокус**          | Эндпоинты (Paths)             | Каналы (Channels), Сообщения     |
| **Spring Boot**    | `springdoc-openapi`           | `springwolf`                     |

---

## Генерация: Способы и инструменты

### Генерация схемы из кода (Code-First)
- **Как:** Используется в этом проекте. Библиотеки сканируют аннотации и рефлексией строят схему.
- **Плюсы:** Документация всегда соответствует коду.
- **Минусы:** Код "загрязнен" аннотациями документации.

### Генерация кода из схемы (Design-First / Contract-First)
- **Инструменты:** [OpenAPI Generator](https://openapi-generator.tech/), [Swagger Codegen].
- **Как:** Сначала создается YAML файл (контракт), затем плагин генерирует интерфейсы контроллеров и DTO.
- **Плюсы:** Строгое соблюдение контракта, параллельная разработка фронта и бэка.

---

## Запуск проекта
Подробные инструкции по запуску и тестированию находятся в файле [ACTIONS.md](ACTIONS.md).
