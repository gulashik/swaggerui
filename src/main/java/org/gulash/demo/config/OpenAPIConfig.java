package org.gulash.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурационный класс для настройки OpenAPI спецификации.
 * <p>
 * В Spring Boot приложениях с использованием {@code springdoc-openapi}, основная конфигурация
 * может выполняться как через {@code application.yml}, так и через создание бина {@link OpenAPI}.
 * </p>
 *
 * <b>Почему это важно:</b>
 * Бин {@code OpenAPI} позволяет программно настроить глобальную информацию о проекте,
 * такую как версия, заголовок, описание, контакты и условия использования.
 *
 * <pre>{@code
 * @Bean
 * public OpenAPI customOpenAPI() { ... }
 * }</pre>
 *
 * <b>Подводные камни:</b>
 * Если конфигурация в YAML конфликтует с программной, приоритет зависит от конкретной настройки,
 * но обычно программная конфигурация переопределяет стандартные значения.
 */
@Configuration
public class OpenAPIConfig {

    /**
     * Создает и настраивает объект {@link OpenAPI}, который используется для генерации Swagger UI.
     *
     * @return сконфигурированный объект OpenAPI.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Swagger UI Demo API")
                        .version("1.0.0")
                        .description("Демонстрационный проект для изучения Swagger UI, OpenAPI и AsyncAPI")
                        .contact(new Contact()
                                .name("Gulash Team")
                                .email("support@gulash.org"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org"))
                );
    }
}
