
# INFoU Server🌐

# INFoU<img src="https://github.com/khu-bigdata-project-team-5/server/assets/87745916/6428f77a-9595-4dca-af30-7d1b5f235acb" align=left width=100>

> _빅데이터를 기반으로 분석하여 Udemy와 Inflearn 강좌를 바탕으로 신뢰성 있는 강의를 제공합니다._


## Introduction

해당 프로젝트는 [INFoU](https://github.com/philip-lee-khu/2024-BIGDATA-PROJECT-5)에서 제공하는 데이터를 기반으로 강의 추천 서비스를 제공하는 Client 프로젝트입니다.

주요기능은 다음과 같습니다.

- 강의의 키워드 찾기
- 강의의 키워드를 기반으로 다른 강의 추천
- 키워드별 강의 커리큘럼 생성
- 다른 유저의 커리큘럼 조회
- 강의의 강의력, 강의자료, 실습, 평점, 난이도와 강의평의 긍정/부정 분석 결과를 제공

## Requirements

- Node.js v20 이상
- npm v10 이상

Node.js와 npm에 대한 버전은 다음의 명령어로 확인할 수 있습니다.

```bash
$ node -v
v22.2.0

$ npm -v
10.7.0

```

```
$ npm run dev
```

## Installation

레포지토리 클론 및 의존성을 설치합니다.

```bash
$ git clone
$ npm install

```

백엔드 서버 주소 설정을 위해 `/src/services/apiService.js` 파일을 열어서 `baseURL`을 수정합니다.

```jsx
const baseUrl = /* 백엔드 서버 주소 */;

const apiService = {...};

```

## 🏢 Convention

| Tag Type | content |
| --- | --- |
| ✨feat | 기능(feature) |
| 📝docs | 문서 작업 (documentation) |
| 🐛fix | 버그 수정 |
| 🚑hotfix | 긴급 버그 수정 |
| ♻️refactor | 코드 리팩터링(가독성 향상) |
| 👷ci | ci 파이프라인 구축 관련 |
| 💚ci/cd | ci 빌드 수정 및 deployment |
| chore | 관리(maintain), 핵심 내용은 아닌 기타 변경 |

## Contribution

### Pull Request

- 레포지토지를 포크합니다.

```bash
$ git fork <https://github.com/khu-bigdata-project-team-5/client.git>

```

- 포크한 레포지토리를 클론합니다.

```bash
$ git clone <https://github.com/your-username/client.git>

```

- 브랜치를 생성합니다.

```bash
$ git checkout -b feature/your-feature

```

- 변경사항을 커밋합니다.

```bash
$ git commit -m "Add your feature"

```

- 변경사항을 푸시합니다.

```bash
$ git push origin feature/your-feature

```

- Pull Request를 생성합니다.

### Issue & Bug Report

Issues 탭에서 새로운 이슈를 등록합니다.

추가 기능 구현에 대한 이슈는 스크린 구현 이슈로, 버그 제보나 트러블 슈팅에 관한 이슈는 트러블 슈팅 이슈로 생성합니다.

![img/issue-1.png](img/issue-1.png)

각 이슈 타입에 맞는 템플릿을 따라 작성합니다.
