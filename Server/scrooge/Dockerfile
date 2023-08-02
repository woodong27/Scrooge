# 빌드 단계: Gradle 설치 및 애플리케이션 빌드
FROM openjdk:8 AS build

# Gradle 설치
RUN apt-get update && apt-get install -y curl unzip
RUN curl -s "https://get.sdkman.io" | bash
RUN bash -c "source $HOME/.sdkman/bin/sdkman-init.sh && sdk install gradle 6.3"

WORKDIR /app
COPY build.gradle settings.gradle ./
COPY src/ ./src/
RUN bash -c "source $HOME/.sdkman/bin/sdkman-init.sh && gradle build --no-daemon"

# 실행 단계: 빌드된 JAR 파일을 실행하는 이미지 생성
FROM openjdk:8
VOLUME /tmp
EXPOSE 8081

ARG JAR_FILE=build/libs/scrooge-0.0.1-SNAPSHOT.jar

# 빌드 단계에서 생성된 JAR 파일을 이미지 안으로 복사
COPY --from=build /app/${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]

ENV TZ=Asia/Seoul

RUN apt-get update && apt-get install -y tzdata
