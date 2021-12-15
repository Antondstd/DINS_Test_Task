# Тестовое задание в DINS
### База данных = PostgreSQL
### Kafka 2.13-2.8.0

## Настройки для приложения производятся через файл application.properties:
## Настройка таблиц
### Имя для таблицы 1
#### Пример
> table.custom.one = customTableOne
### Имя для таблицы 2
#### Пример
> table.custom.two = customTableTwo

## Настройки Kafka
### Адрес Kafka 
#### Пример
> kafka.bootstrap_server_config = localhost:9092
### Название топика
#### Пример
> kafka.customForTableOne.topic = TableOneTopic
### ID Lister-а
#### Пример
kafka.customForTableOne.id = TableOne
### Время простоя
Указываем время в мс, через которое будет выключен Listener, если не придут сообщения.
kafka.listener.idletime = 5000

## Выбор режима
### spamKafka
Отправляет все объекты из таблицы 1 по Kafka в топик, указанный выше. При этом если в таблице 1 меньше 1000 строк, оно сгенерирует их.
### readKafka
Считывает из топика и записывает информацию в таблицу. Выключение программы произойдет, если в топике не будет сообщений для считывания, указанном во времени простоя.
#### Пример
> mode = spamKafka

## Настройки Hibernate
### Адрес для подключения
> spring.datasource.url=jdbc:postgresql://localhost:5432/SOA_Lab1
### Логин
> spring.datasource.username=postgres
### Пароль
> spring.datasource.password=1234567

## Сборка приложения
> gradle build

## Запуск приложения
> java -jar DINS_Test_Task-0.0.1-SNAPSHOT.jar

