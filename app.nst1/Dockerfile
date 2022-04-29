FROM openjdk:11
RUN mkdir nst1
ADD target/app.nst1-0.0.1-SNAPSHOT.jar nst1/app.nst1-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "nst1/app.nst1-0.0.1-SNAPSHOT.jar"]

#TODO: make script: mvn clean install -DskipTetsts,
#change pom.xml to use hql database, run docker commmand