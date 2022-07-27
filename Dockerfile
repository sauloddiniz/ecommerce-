FROM openjdk:17-alpine3.14
EXPOSE 8080:8080
ADD /build/libs/ecommerce-0.0.1-SNAPSHOT.jar ecommerce.jar
ENTRYPOINT ["java", "-jar", "ecommerce.jar"]