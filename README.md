# Краткое описание

Этот проект демонстрирует работу спринг приложения совместно с кафкой

# Запуск

## Запустить эту команду в консоли, в корне проекта
```bash
docker compose up -d
```

## Запустить в ide оба микросервиса

Url для swagger сервиса producer
http://localhost:8080/swagger-ui/index.html#/food-controller/createFoodOrder

Url для просмотра базы
http://localhost:8081/h2-console/

Url для просмотра веб-интерфейса кафки
http://localhost:9000/