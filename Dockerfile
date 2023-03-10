FROM maven:3.6.3-openjdk-8-slim

WORKDIR /ticket-price

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src/ /ticket-price/src/
RUN mvn package

EXPOSE 8083

CMD ["java", "-jar", "target/ticket-price.jar"]