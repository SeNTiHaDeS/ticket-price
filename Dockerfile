FROM openjdk:11
ADD target/*.jar app.jar
LABEL name="ticket-price" \
      version="latest"
ENTRYPOINT ["java","-jar", "app.jar"]