FROM java:8-jre

ADD ./target/speech2txt-1.0.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/speech2txt-1.0.jar"]

EXPOSE 14568