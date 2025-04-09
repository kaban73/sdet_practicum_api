FROM eclipse-temurin:19-jdk

COPY target/your-api.jar app.jar

RUN apt-get update && apt-get install -y git maven
RUN git clone https://github.com/bondarenkokate73/simbirsoft_sdet_project.git /app-source
WORKDIR /app-source
RUN mvn clean package
RUN mv /app-source/target/*.jar /app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
