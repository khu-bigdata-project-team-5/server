# INFoU Server🌐

# INFoU<img src="https://github.com/khu-bigdata-project-team-5/server/assets/87745916/6428f77a-9595-4dca-af30-7d1b5f235acb" align=left width=100>

> _빅데이터를 기반으로 분석하여 Udemy와 Inflearn 강좌를 바탕으로 신뢰성 있는 강의를 제공합니다._

</br>

## 💁🏻 Introduction

해당 프로젝트는 [INFoU](https://github.com/philip-lee-khu/2024-BIGDATA-PROJECT-5)에서 제공하는 데이터를 기반으로 강의 추천 서비스를 제공하는 프로젝트입니다.

- 주요기능
  - 강의의 키워드 찾기
  - 강의의 키워드를 기반으로 다른 강의 추천
  - 키워드별 강의 커리큘럼 생성
  - 다른 유저의 커리큘럼 조회
  - 강의의 강의력, 강의자료, 실습, 평점, 난이도와 강의평의 긍정/부정 분석 결과를 제공

</br>

## 🚀 Version

```java
java {
    sourceCompatibility = '17'
    targetCompatibility = '17'
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}
```

```java
plugins {
    id 'java'
    id 'org.springframework.boot' version '3.3.0'
    id 'io.spring.dependency-management' version '1.1.5'
}
```

</br>

## 🛠️ Tech Stack
| 사용기술               | 정보                            |
|--------------------|-------------------------------|
| Spring             | 3.3.0                       |
| Database           | AWS RDS(MySQL)                |
| Instance           | AWS EC2(Ubuntu)               |
| CI/CD	             | Github Actions, AWS Elasticbeanstalk, Nginx |
| Build Tool         | Gradle                        |
| ERD                | StarUML, DataGrip           |
| Java               | Java 17                       |
| Swagger(Springdoc) | 2.0.2                         |

</br>

## 📂 Directory Architecture

```
├─🗂️ .ebextensions_dev
├─🗂️ .github/workflow
├─🗂️ .platform
│  ├─🗂️ src
│  │  └─🗂️ main
│  │     └─🗂️ java
│  │        └─🗂️ khu/bigdata
│  │           └─🗂️ infou
│  │              ├─🗂️ web
│  │              │  ├─🗂️ controller
│  │              │  └─🗂️ dto
│  │              │─🗂️ business
│  │              ├─🗂️ repository
│  │              ├─🗂️ implement
│  │              ├─🗂️ domain
│  │              ├─🗂️ config
│  │              └─💽 InfouApplication.java
│  │     └─🗂️ resources
│  │     │ └─💽 Application.yml
└─  └─🗂️ build.gradle

```

### 🧐 Spring boot framework Explanation

---

1.	**business**:

- 비즈니스 로직을 포함하는 디렉토리입니다. 서비스 레이어라고도 하며, 애플리케이션의 주요 기능을 수행하는 코드를 포함합니다. 예를 들어, 사용자 요청을 처리하고, 데이터베이스와 상호 작용하며, 도메인 객체를 조작하는 코드가 이 디렉토리에 포함됩니다.

2.	**config**:

- 애플리케이션의 설정 파일을 포함하는 디렉토리입니다. Spring Boot 설정 클래스, 보안 설정, 데이터베이스 설정, 애플리케이션 프로퍼티 파일 등이 여기에 위치합니다. 애플리케이션의 전반적인 설정 및 환경 구성을 담당합니다.

3.	**domain**:

- 도메인 모델을 포함하는 디렉토리입니다. 주로 엔터티 클래스가 위치하며, 데이터베이스 테이블과 매핑되는 객체를 정의합니다. 이 디렉토리의 클래스들은 애플리케이션의 주요 데이터 구조를 나타냅니다.

4.	**implement**:

- 비즈니스 로직을 실제로 구현하는 클래스들이 위치합니다. 인터페이스의 구현체가 이곳에 있으며, 서비스 인터페이스를 구현하는 구체적인 클래스들이 포함됩니다.

5.	**repository**:

- 데이터 액세스 레이어를 담당하는 디렉토리입니다. JPA 리포지토리 인터페이스나, 데이터베이스와 상호 작용하는 코드가 위치합니다. 데이터베이스 CRUD(Create, Read, Update, Delete) 작업을 수행합니다.

6.	**web**:

- 웹 레이어를 담당하는 디렉토리입니다. 주로 웹 요청을 처리하는 컨트롤러가 이곳에 위치합니다.
1. **controller**:
    - HTTP 요청을 처리하고, 비즈니스 로직을 호출하여 결과를 반환하는 클래스들이 위치합니다. 사용자 인터페이스와 상호 작용하며, 주로 REST API 엔드포인트를 정의합니다.
2. **dto**:
    - 데이터 전송 객체(Data Transfer Object)를 포함하는 디렉토리입니다. 주로 컨트롤러와 서비스 레이어 간의 데이터 전송을 목적으로 사용됩니다. 도메인 모델과는 별도로, 클라이언트와 상호작용하기 위한 데이터 구조를 정의합니다.

</br>

## 🌐 Server Infra

![architecture(infou)](https://github.com/khu-bigdata-project-team-5/server/assets/87745916/ab5c8abc-e62a-4748-80c0-9dbb04e89cba)

- ci/cd는 Git Action과 AWS Elastic Beanstalk을 활용했습니다.
- cloud DB는 AWS RDS를 사용했습니다.
- AWS VPC를 활용하여 서버 DB를 Vpc 내의 subnet에 할당하였습니다.
- Reverse proxy는 Nginx을 활용하여 구축했습니다.
- 도메인(infou.store) 등록은 가비아와 AWS Route 53을 활용하여 API 서버는 서브 도메인(dev.infou.store)을 활용했습니다.

</br>

## 📜 Swagger

<img src="https://github.com/khu-bigdata-project-team-5/server/assets/87745916/a06c8d4f-02c4-48ca-862e-373ae8367f19" alt="API-swagger" width="700" height="900"/>

</br>

## 🏢 Convention

| Tag Type   | content                                    |
| ---------- | ------------------------------------------ |
| ✨feat     | 기능(feature)                              |
| 📝docs     | 문서 작업 (documentation)                  |
| 🐛fix      | 버그 수정                                  |
| 🚑hotfix   | 긴급 버그 수정                             |
| ♻️refactor | 코드 리팩터링(가독성 향상)                 |
| 👷ci       | ci 파이프라인 구축 관련                    |
| 💚ci/cd    | ci 빌드 수정 및 deployment                 |
| chore      | 관리(maintain), 핵심 내용은 아닌 기타 변경 |
