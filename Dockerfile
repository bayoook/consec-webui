FROM registry.access.redhat.com/ubi8/openjdk-8-runtime
ARG JAR_FILE=target/*.jar
#EXPOSE 8080
COPY ${JAR_FILE} /usr/app/monitoring.jar
ENTRYPOINT ["java","-jar","/usr/app/monitoring.jar"]