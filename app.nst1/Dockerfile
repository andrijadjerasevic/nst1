FROM openjdk:11
RUN mkdir nst1
ADD target/app.nst1-0.0.1-SNAPSHOT.jar nst1/app.nst1-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "nst1/app.nst1-0.0.1-SNAPSHOT.jar"]