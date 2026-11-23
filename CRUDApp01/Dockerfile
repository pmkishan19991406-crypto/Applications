FROM eclipse-temurin

WORKDIR /app

COPY target/CRUDApp01-1.0.1.jar app.jar

EXPOSE 8080

#java jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]