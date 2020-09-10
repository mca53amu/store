FROM openjdk:8
ADD /target/store-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","store-0.0.1-SNAPSHOT.jar"]