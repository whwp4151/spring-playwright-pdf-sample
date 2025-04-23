
# 실행방법

## 1. Docker Compose 실행
```
docker-compose up --build
```
`--build`의 의미 : docker-compose.yml에 정의된 이미지가 최신 상태가 아니더라도 강제로 Dockerfile을 빌드하도록 만드는 옵션입니다.
Playwright 라이브러리를 설치하기 때문에 대략 3분정도 소요됩니다.

## 2. Swagger 접근
브라우저에서 http://localhost:8080 이동

## 3. API 요청
스웨거에서 `PDF 생성` API 요청
- example 기준으로 그대로 요청 보낼경우 https://playwright.dev/ 페이지를 PDF로 생성해줌.