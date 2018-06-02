FROM java:8-jre

ADD ./target/txt2speech-1.0.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/txt2speech-1.0.jar"]

EXPOSE 14569