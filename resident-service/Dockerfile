FROM maven:3.9.8-eclipse-temurin-21 AS build

# Указываем рабочую директорию
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean package -DskipTests

# --- Этап 2: минимальный runtime ---
FROM eclipse-temurin:21-jdk-jammy

# Указываем рабочую директорию
WORKDIR /app

# Копируем jar из предыдущего этапа
COPY --from=build /app/target/*.jar app.jar

# Указываем порт, который слушает приложение
EXPOSE 8080

# Переменные окружения (можно переопределить при запуске контейнера)
ENV SPRING_PROFILES_ACTIVE=prod

# Запуск Spring Boot приложения
ENTRYPOINT ["java", "-jar", "app.jar"]
