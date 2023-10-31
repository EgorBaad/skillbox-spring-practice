FROM openjdk:17-oracle
WORKDIR /app
COPY build/libs/skillboxpractice2-0.0.1-SNAPSHOT.jar app.jar
CMD ["java", "-Dapp.students.init-default=false", "-jar", "app.jar"]