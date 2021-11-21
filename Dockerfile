#
#Package
#
FROM maven:3.8.3-openjdk-17-slim AS build
COPY src/ /usr/local/Atosiak/src
COPY pom.xml /usr/local/Atosiak/
COPY META-INF/MANIFEST.MF /usr/local/Atosiak/
RUN mvn -f /usr/local/Atosiak/pom.xml clean package

#
#Run
#
FROM openjdk:17-jdk-slim
WORKDIR /usr/local/Atosiak/
COPY --from=build /usr/local/Atosiak/target/*.jar /usr/local/Atosiak/
CMD ["java", "-jar", "Atosiak-jar-with-dependencies.jar"]