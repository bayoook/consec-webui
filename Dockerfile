FROM registry.access.redhat.com/ubi8/openjdk-8-runtime
ARG JAR_FILE=target/*.jar
ARG KEYSTORE_FILE=target/classes/cert/keystore.jks
ARG TRUSTSTORE_FILE=target/classes/cert/truststore.jks
#EXPOSE 8080
COPY ${JAR_FILE} /usr/app/monitoring.jar
COPY ${KEYSTORE_FILE} /usr/app/keystore.jks
COPY ${TRUSTSTORE_FILE} /usr/app/truststore.jks
ENTRYPOINT ["java","-jar","/usr/app/monitoring.jar"]