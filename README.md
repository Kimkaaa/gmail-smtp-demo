# 📧 Spring Boot Gmail SMTP Mail Demo

Gmail SMTP를 활용해 Spring Boot에서

**텍스트 메일 · HTML 템플릿 메일 · 첨부파일 메일**을 보내는 과정을 정리한 예제 프로젝트이다.

브라우저에서 바로 테스트해볼 수 있도록

Thymeleaf 기반의 **간단한 웹 UI**도 함께 제공한다.

---

## 🚀 기능 정리

- 텍스트 메일 전송
- HTML 템플릿 메일 전송
- 첨부파일 메일 전송
- 테스트용 웹 UI 페이지 제공

---

## 🛠 개발 환경

- Spring Boot 3.4.12
- Java 17
- IntelliJ IDEA
- Gradle

---

## 📸 테스트 폼 UI

### 텍스트 메일

![메일_01_폼_01_text.png](src%2Fmain%2Fresources%2Fstatic%2Fimages%2F%EB%A9%94%EC%9D%BC_01_%ED%8F%BC_01_text.png)

### HTML 메일

![메일_01_폼_02_html.png](src%2Fmain%2Fresources%2Fstatic%2Fimages%2F%EB%A9%94%EC%9D%BC_01_%ED%8F%BC_02_html.png)

### 첨부파일 메일

![메일_01_폼_03_attachment.png](src%2Fmain%2Fresources%2Fstatic%2Fimages%2F%EB%A9%94%EC%9D%BC_01_%ED%8F%BC_03_attachment.png)

---

## 📬 메일 수신 결과

### 텍스트 메일 수신

![결과_01_텍스트.png](src%2Fmain%2Fresources%2Fstatic%2Fimages%2F%EA%B2%B0%EA%B3%BC_01_%ED%85%8D%EC%8A%A4%ED%8A%B8.png)

### HTML 메일 수신

![결과_02_html.png](src%2Fmain%2Fresources%2Fstatic%2Fimages%2F%EA%B2%B0%EA%B3%BC_02_html.png)

### 첨부파일 메일 수신

![결과_03_첨부파일.png](src%2Fmain%2Fresources%2Fstatic%2Fimages%2F%EA%B2%B0%EA%B3%BC_03_%EC%B2%A8%EB%B6%80%ED%8C%8C%EC%9D%BC.png)

---

## 🔐 Gmail SMTP 설정 방법

1. Google 계정 → **보안** → 2단계 인증 활성화
2. 검색창에 **앱 비밀번호** 입력
3. 앱 비밀번호 생성 → 16자리 비밀번호 복사
4. 이 값을 `mail.password`로 사용

⚠ **주의:** Gmail 일반 비밀번호가 아니라 반드시 **앱 비밀번호**를 사용해야 한다.

---

## ⚙ application.yml 설정 예시

```yaml
spring:
  mail:
    host: smtp.gmail.com
    port: 587
    username: ${mail.username}
    password: ${mail.password}
    properties:
      mail:
        smtp:
          auth: true
          timeout: 5000
          starttls:
            enable: true
```

---

## 🧩 메일 전송 서비스 (MailService)

### 텍스트 메일

`SimpleMailMessage` 사용

### HTML / 첨부파일 메일

`MimeMessage` + `MimeMessageHelper` 사용

### 비동기 처리

메일 전송은 SMTP 서버와 통신하는 과정이 필요하기 때문에

`@Async`를 사용해 **비동기 처리**로 개선할 수 있다.

→ `@EnableAsync` 필요

---

## 🌐 테스트 UI 경로

프로젝트 실행 후 아래 페이지에서 바로 테스트할 수 있다.

| 메일 타입 | URL |
| --- | --- |
| 텍스트 메일 | http://localhost:8080/mail/form/text |
| HTML 메일 | http://localhost:8080/mail/form/html |
| 첨부파일 메일 | http://localhost:8080/mail/form/attachment |

---

## 📁 주요 템플릿 파일

```
src/main/resources/templates/
├── mail-text-form.html
├── mail-html-form.html
├── mail-attachment-form.html
├── mail-style.html
└── email.html
```

---

## 📦 첨부파일 처리 방식

`MultipartFile`은 JavaMailSender에 직접 전달할 수 없기 때문에,

Controller에서 임시 파일로 변환해 Service로 넘긴다.

```java
File saved = File.createTempFile("upload-", "-" + file.getOriginalFilename());
file.transferTo(saved);
```

이 방식은 운영체제의 임시 디렉토리에 파일을 생성한다.

---

## 📘 참고

이 프로젝트는 **학습용 데모**이며,

Spring Boot에서 메일 기능을 테스트하고 이해하는 데 목적이 있다.