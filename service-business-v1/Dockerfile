FROM openjdk:8-jre-alpine

LABEL maintainer="ittony.ma@gmail.com"

ARG JAR_FILE=target/service*.jar

ADD ${JAR_FILE} /app.jar

CMD ["java", "-Xmx1024m", "-XX:-UseGCOverheadLimit", "-jar", "/app.jar"]

EXPOSE 8080
