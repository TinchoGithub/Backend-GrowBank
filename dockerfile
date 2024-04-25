FROM openjdk:17

COPY build/grow-bank.jar grow-bank.jar

CMD ["java", "-jar", "grow-bank.jar"]