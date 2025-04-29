# Matching Payment API

Spring Boot와 MySQL을 기반으로 한 간단한 API 서비스입니다.<br>
Docker Compose를 이용하여 쉽게 실행할 수 있습니다.

<br><br>

## 📁 프로젝트 구성

- db/schema.sql       : DB 스키마 정의
- db/data.sql         : 더미 데이터 (선택적)
- src/                : Spring Boot 애플리케이션 소스
- docker-compose.yml  : Docker 서비스 구성 파일
- env/                : 환경 변수 정의 파일
- README.txt          : 설명 파일 (이 파일)

<br><br>

## 🚀 실행 방법

도커 컴포즈로 애플리케이션과 데이터베이스를 동시에 실행합니다.

> docker-compose --env-file ./env/main.env up -d --build

Spring 애플리케이션은 http://localhost:8080  
MySQL은 localhost:3306 으로 접근 가능합니다.

<br><br>

## ⚙️ 환경 변수 (.env)

`env/main.env` 파일을 수정하여 DB 설정을 변경할수 있습니다.

```
DB_HOST=mysql  
DB_PORT=3306  
DB_DATABASE=matching  
DB_USERNAME=root  
DB_PASSWORD=root
```

<br><br>

## 📄 데이터 초기화

- db/schema.sql 파일로 테이블이 자동 생성됩니다.
- db/data.sql 에는 예시 데이터가 작성되어 있습니다.

회원 초기 데이터가 필요하다면 application.yaml 파일에서 아래 설정을 주석 해제하세요:

```
spring:
  sql:
    init:
      data-locations: classpath:db/data.sql
```

<br><br>

## 📬 API 문서


Swagger UI는 아래 주소에서 확인할 수 있습니다:

http://localhost:8080/swagger-ui/index.html

<br><br>

## 🙋‍♀️  문의

오류 또는 문의사항은 GitHub 이슈 또는 개발자에게 연락 주세요.
