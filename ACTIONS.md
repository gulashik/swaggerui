
### Поднимаем контейнеры
```bash
podman compose -f ./compose.yml down
podman compose -f ./compose.yml up -d 
podman ps
```

### Пересобираем проект
```
./gradlew clean build 
```

### Тесты
```
./gradlew test
```

### Запускаем проект
```
./gradlew clean build bootRun 
```

### Проверка OpenAPI схемы
```bash
curl http://localhost:8080/api-docs | jq
```
### Просмотр Swagger UI cгенерируемого SpringBoot 
###   по умолчанию откроется локальная схема и будет "Explorer" куда можно скопировать ссылки на дургие OpenAPI схемы
```bash
open http://localhost:8080/swagger-ui.html
```

### Запуск внешнего Swagger UI (для нескольких приложений) 
### Если в образе [compose.yml](compose.yml) НЕТ environment.URLS, то будет "Explorer" куда можно скопировать ссылки на OpenAPI схемы генерируется в SpringBoot - `http://localhost:8080/api-docs`
### Если в образе [compose.yml](compose.yml) environment.URLS ЗАПОЛНЕН, переключяемся межды URL-ами по умолчанию Explorer откроется с `https://petstore.swagger.io/v2/swagger.json` так же можно открыть `http://localhost:8080/api-docs` 
```bash
open http://localhost:8081
```

### Остановка
```bash
pkill -f 'org.gulash.demo.SwaggerDemoApplication'
```