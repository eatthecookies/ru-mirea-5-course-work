# Используем базовый образ OpenJDK для Java 17
FROM openjdk:17-jdk-slim

# Устанавливаем рабочую директорию внутри контейнера
WORKDIR /app

# Копируем файл JAR из сборки проекта в контейнер
COPY build/libs/familytaskmanagement-0.0.1-SNAPSHOT.jar app.jar

# Указываем порт, на котором будет работать приложение
EXPOSE 8080

# Команда для запуска JAR-файла
ENTRYPOINT ["java", "-jar", "app.jar"]
