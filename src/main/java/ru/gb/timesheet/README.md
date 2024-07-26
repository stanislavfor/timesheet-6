# Фреймворк Spring (семинары)

## Урок 6. Проектирование и реализация API для серверного приложения.
#### Задание:<br> Используя Spring, разработайте RESTful API для вашего приложения. Ваш API должен включать операции для создания, чтения, обновления и удаления пользователей.<br><br>
### Описание<br>
- Проектирование RESTful API для приложения.<br>
- Реализация контроллеров и обработка запросов клиентов.<br>
- Тестирование API с использованием инструментов типа Postman<br>
<br><br><br>

### Домашнее задание

```
1. Подключить сваггер к своему проекту (который мы делаем, т.е. с проектами, таймшитами и тд)
(подключить в Pom.xml <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>).
2. Добавить описание ручек (название на русском языке + описание, что делают)
3. ** Описать параметры и возвращаемые типы данных.

В качестве домашки прислать скриншот(ы) OpenAPI-страницы (которая красивая).
Не нужно присылать 10+ скриншотов, достаточно тех, по которым понятно, что работа сделана.
```
   <br><br><br>

### Решение

- Начальная страница локального сервера (Таблицы)<br><br>

    http://localhost:8080/home/timesheets

```
http://localhost:8080/home/timesheets

```

- Страница проекта, например :

```
http://localhost:8080/home/projects/1

```

- Страница записи, например :

```
http://localhost:8080/home/timesheets/1

```
<br><br><br>

Чтобы интегрировать Swagger в проект нужно добавить зависимость

```

<!-- https://mvnrepository.com/artifact/org.springdoc/springdoc-openapi-starter-webmvc-ui -->
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
            <version>2.6.0</version>
        </dependency>
        

```

и добавить описание ресурсов, параметров и возвращаемых типов данных.<br>

После запуска проекта, Swagger UI будет доступен по адресу <br><br>
http://localhost:8080/swagger-ui.html, <br><br>
где можно просматривать и тестировать API проекта.

### Дополнение

- @Operation -  аннотация для описания операций в контроллерах,<br> 
используется для добавления описания, заголовка и других метаданных.<br><br>
- @Parameter - аннотация для описания параметров методов контроллера, таких как @PathVariable и @RequestParam.

<br><br><br>

![swagger](https://i.ibb.co/z60fG2g/swagger-test.jpg)

<br><br>
![swagger](https://i.ibb.co/9gX01pk/swagger-big.jpg)

