FROM openjdk:8-jre-alpine
EXPOSE 8080
ARG JAR_FILE=impl/target/order-management.jar
ADD ${JAR_FILE} order-management.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/order-management.jar"]