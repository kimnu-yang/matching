FROM eclipse-temurin:21

WORKDIR /app

ADD . .

RUN chmod +x ./gradlew && \
    ./gradlew clean && \
    ./gradlew module-api:build -x test

ENTRYPOINT ["sh", "./docker-entrypoint.sh"]

EXPOSE 8080