# 스크루지

```
"가계부 앱은 불편해..."  
"꾸준히 모으기 힘들어..."  
"아끼는 재미가 없어!"  
```

- Gamification을 통해 소비습관을 개선하는 서비스
- 스크루지 빌리지, 파이트, 드림과 같은 다양한 기능으로 경험치 획득 가능

<br/>

## 프로젝트 진행 기간 📅
**SSAFY 9기 2학기 공통 프로젝트**  
23.07.04(화) ~ 23.08.18(금)

<br/>

## 팀원 👥
| 이름   | 역할                  |
| ------ | --------------------- |
| 강다은 | FrontEnd & Mobile     |
| 노창현 👑 | BackEnd & Infra       |
| 박진희 | FrontEnd & Design     |
| 이동주 | FrontEnd & AI         |
| 이하린 | BackEnd & Mobile      |
| 최동우 | BackEnd & AI          |

<br/>

## ERD

![scrooge_erd](https://github.com/harinplz/algorithm_study/assets/62701446/f2cdc7e0-f253-47b6-9639-0f4fb20b5cb7)

<br/>

## 아키텍처

![image](https://github.com/harinplz/algorithm_study/assets/62701446/09436f42-d307-44b4-afc7-7adf991ae170)

<br/>

## 기술 스택

### BE
- IntelliJ IDEA
- Java
- Spring Boot
- Spring Security
- JWT
- Spring Web
- JPA
- MySQL
- Swagger
- GCP
- Web Socket

### AI
- OpenCV
- Fast API
- Python

### FE
- Visual Studio Code
- React.js
- Webstomp
- js-cookie

### MOBILE
- Android Studio
- Kotlin
- Notification Listener
- Retrofit
- okHttp3
- Alarm Manager

### Infra
- AWS EC2
- Jenkins
- NGINX

<br/>

## 협업툴
- Git
- Notion
- Jira
- MatterMost

<br/>

## FE 디렉터리 구조
```
📦scrooge-react
 ┣ 📂node_module
 ┣ 📂public
 ┣ 📂src
 ┃ ┣ 📂assets
 ┃ ┣ 📂components
 ┃ ┣ 📂pages
 ┃ ┣ 📂services
 ┃ ┣ 📂store
 ┃ ┗ 📂utils
 ┣ 📜.gitignore
 ┣ 📜Dockerfile
 ┣ 📜package-lock.json
 ┣ 📜package.json
 ┗ 📜README.md
```

<br/>

## BE 디렉터리 구조
```
📦Server
 ┗ 📂scrooge
 ┃ ┣ 📂build
 ┃ ┃ ┣ 📂classes
 ┃ ┃ ┃ ┗ 📂java
 ┃ ┃ ┃ ┃ ┗ 📂main
 ┃ ┃ ┃ ┃ ┃ ┗ 📂com
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂scrooge
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂scheduler
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂scrooge
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂config
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂jwt
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂data
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂domain
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ScroogeApplication.class
 ┃ ┃ ┣ 📂resources
 ┃ ┃ ┃ ┗ 📂main
 ┃ ┃ ┃ ┃ ┣ 📜application.properties
 ┃ ┃ ┃ ┃ ┗ 📜forward-lead-392307-4a37b2b70cad.json
 ┃ ┃ ┗ 📂test
 ┃ ┣ 📜.gitignore
 ┃ ┣ 📜build.gradle
 ┃ ┣ 📜Dockerfile
 ┃ ┣ 📜gradlew
 ┃ ┣ 📜gradlew.bat
 ┃ ┣ 📜HELP.md
 ┃ ┗ 📜settings.gradle
```

<br/>

## Mobile 디렉터리 구조

```
📦scrooge-android
 ┣ 📂app
 ┃ ┣ 📂build
 ┃ ┣ 📂release
 ┃ ┃ ┣ 📂main
 ┃ ┃ ┃ ┣ 📂java
 ┃ ┃ ┃ ┃ ┗ 📂com
 ┃ ┃ ┃ ┃ ┃ ┗ 📂example
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂scoorge_android
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂api
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂model
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂network
 ┃ ┃ ┃ ┣ 📂res
 ┃ ┃ ┗ 📂test
 ┣ 📂gradle
 ┃ ┗ 📂wrapper
 ┣ 📜.gitignore
 ┣ 📜build.gradle
 ┣ 📜gradle.properties
 ┣ 📜gradlew
 ┣ 📜gradlew.bat
 ┣ 📜local.properties
 ┣ 📜scrooge.jks
 ┗ 📜settings.gradle
```

<br/>

## 서비스 화면 

<br/>


