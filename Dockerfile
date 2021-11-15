FROM openjdk:17-jre-alpine
COPY . /usr/src/BelimoScraper
CMD ["usr/bin/java", "-jar", "Atosiak.jar"]