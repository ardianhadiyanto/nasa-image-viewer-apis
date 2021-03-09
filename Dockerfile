# Some resources in application are less likely to change (dependencies, etc.).
# Some resources are more often to change (application code, static resources, etc.).
# Since Docker creates layers in the container filesystem and they are cached, we want to
# improve performance by having the more frequently resources layered after the less frequent ones

FROM openjdk:8-jdk-alpine as build
WORKDIR /build
COPY "./target/nasa-image-viewer-apis-0.0.1-SNAPSHOT.jar" ./app.jar
RUN mkdir -p ./dependency && (cd dependency; jar -xf ../app.jar)

FROM openjdk:8-jre
WORKDIR /app
COPY --from=build /build/dependency/BOOT-INF/lib ./lib
COPY --from=build /build/dependency/META-INF ./app/META-INF
COPY --from=build /build/dependency/BOOT-INF/classes ./core_classes

ENTRYPOINT ["java", "-cp", "core_classes:lib/*", "io.resi.apis.NasaImageViewerApisApplication"]
