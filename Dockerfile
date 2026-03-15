FROM amazoncorretto:8-alpine

EXPOSE 8080

# Using regular expression to capture the correct .jar file, as the version number may change
COPY ./target/java-maven-app-*.jar /usr/app/
WORKDIR /usr/app

# ENTRYPOINT ["java", "-jar", "java-maven-app-*.jar"]
CMD java -jar java-maven-app-*.jar
