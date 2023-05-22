FROM eclipse-temurin:17-jdk as build

WORKDIR /workspace/app 
#DIRECTORIO DENTRO DEL CONTENEDOR DONDE SE GUARDARA PROYECTO


COPY mvnw  .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw install -DskipTests
RUN mkdir -p target/extracted && java -Djarmode=layertools -jar  target/*.jar extract --destination target/extracted

FROM eclipse-temurin:17-jdk

VOLUME [ "/temp" ]
ARG EXTRACTED=/workspace/app/target/extracted

COPY --from=build ${EXTRACTED}/dependencies/ .
COPY --from=build ${EXTRACTED}/spring-boot-loader/ ./
COPY --from=build ${EXTRACTED}/snapshot-dependencies/ ./
COPY --from=build ${EXTRACTED}/application/ ./

ENTRYPOINT [ "java", "org.springframework.boot.loader.JarLauncher" ]