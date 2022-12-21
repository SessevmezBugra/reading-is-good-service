FROM maven:3.8.6-amazoncorretto-17 as backend-build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -DskipTests

FROM openjdk:17-oracle
VOLUME /tmp
EXPOSE 8080
ARG DEPENDENCY=/home/app/target/dependency
COPY --from=backend-build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=backend-build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=backend-build ${DEPENDENCY}/BOOT-INF/classes /app
#ENV JAVA_OPTS="-Xmx200m"
ENTRYPOINT ["java","-Xmx500m","-cp","app:app/lib/*","com.getir.readingisgood.ReadingisgoodApplication"]
