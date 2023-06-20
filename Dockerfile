FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR application
COPY build/libs/db-*-SNAPSHOT.jar app.jar
RUN java -Djarmode=layertools -jar app.jar extract

FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG DEPENDENCY=/workspace/app/target/dependency
COPY --from=build application/dependencies/ ./
COPY --from=build application/snapshot-dependencies/ ./
COPY --from=build application/spring-boot-loader/ ./
COPY --from=build application/application/ ./
ENTRYPOINT ["java","org.springframework.boot.loader.JarLauncher"]