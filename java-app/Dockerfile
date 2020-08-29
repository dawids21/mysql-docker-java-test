FROM maven:3.6.3-openjdk-14-slim

COPY src /usr/src/mymaven/src
COPY wait-for-it.sh pom.xml /usr/src/mymaven/

WORKDIR /usr/src/mymaven

RUN mvn install

CMD ["java", "-jar", "target/mysql-docker-java-test-1.0-SNAPSHOT-jar-with-dependencies.jar"]
