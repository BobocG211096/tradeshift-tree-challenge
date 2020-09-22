FROM openjdk:8-jre-alpine

VOLUME /tmp
ADD target/treechallenge-1.0-SNAPSHOT.jar tree-challenge.jar

EXPOSE 8080

ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /tree-challenge.jar" ]
