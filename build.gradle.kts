import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    java
    id("org.springframework.boot") version "3.3.4"
    id("io.spring.dependency-management") version "1.1.6"
}

group = "org.gulash.demo"
version = "1.0.0"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // Spring Boot Starter Web: включает Tomcat и Spring MVC для создания REST API.
    // Необходим, так как Swagger/OpenAPI обычно документируют HTTP эндпоинты.
    implementation("org.springframework.boot:spring-boot-starter-web")

    // Springdoc OpenAPI UI: Основная зависимость для генерации Swagger UI.
    // Автоматически сканирует контроллеры и генерирует OpenAPI 3 спецификацию.
    // Включает в себя swagger-ui.
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")

    // Spring Boot Starter Validation: Для поддержки аннотаций @NotNull, @Min и др.
    // Springdoc учитывает их при генерации схемы.
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // Lombok: Для уменьшения шаблонного кода (геттеры, сеттеры, конструкторы).
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    // Spring Boot Starter Test: Для юнит и интеграционных тестов.
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events(TestLogEvent.PASSED, TestLogEvent.SKIPPED, TestLogEvent.FAILED)
    }
}
