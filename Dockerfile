FROM openjdk:17-jdk
COPY ./out/artifacts/Atosiak_jar/Atosiak.jar /usr/local/Atosiak/
WORKDIR /usr/local/Atosiak/
CMD ["java", "-jar", "Atosiak.jar"]