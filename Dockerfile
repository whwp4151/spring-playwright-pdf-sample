FROM eclipse-temurin:21-jdk

### Playwright 시작
# Gradle 빌드 스테이지
COPY . /app
WORKDIR /app

# Gradle 래퍼를 사용하여 Playwright 설치
RUN chmod +x ./gradlew
RUN ./gradlew installPlaywright
### Playwright 끝

ARG JAR_FILE=/build/libs/spring-playwright-pdf-sample-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} /spring-playwright-pdf-sample.jar

ENTRYPOINT ["sh", "-c", "java -Xmx1g -jar /spring-playwright-pdf-sample.jar"]

