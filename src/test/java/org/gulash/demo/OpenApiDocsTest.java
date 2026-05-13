package org.gulash.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * Тест для проверки доступности документации OpenAPI.
 * <p>
 * Этот тест демонстрирует, что Springdoc корректно настроен и генерирует JSON схему.
 * </p>
 */
@SpringBootTest
@AutoConfigureMockMvc
class OpenApiDocsTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * Проверяет, что эндпоинт /api-docs доступен и возвращает корректную структуру JSON.
     * <b>Подводные камни:</b>
     * В реальных проектах доступ к этому эндпоинту часто закрыт Security.
     * Тест помогает убедиться, что путь совпадает с указанным в application.yml.
     */
    @Test
    void shouldReturnOpenApiDocs() throws Exception {
        mockMvc.perform(get("/api-docs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.openapi").value("3.0.1"))
                .andExpect(jsonPath("$.info.title").value("Swagger UI Demo API"));
    }

    /**
     * Проверяет, что эндпоинт /springwolf/docs доступен и возвращает корректную структуру JSON AsyncAPI.
     */
    @Test
    void shouldReturnAsyncApiDocs() throws Exception {
        mockMvc.perform(get("/springwolf/docs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.asyncapi").exists())
                .andExpect(jsonPath("$.info.title").value("Swagger UI Demo AsyncAPI"));
    }

    /**
     * Проверяет редирект на Swagger UI.
     */
    @Test
    void shouldRedirectToSwaggerUi() throws Exception {
        mockMvc.perform(get("/swagger-ui.html"))
                .andExpect(status().is3xxRedirection());
    }
}
