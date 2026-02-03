#  AWS Spring Boot 배포 프로젝트

AWS 인프라 위에 Spring Boot 애플리케이션을 배포하고,  
비용 관리 · 보안 · 운영 가능성을 고려해 구성한 프로젝트입니다.

---

## LV 0. AWS Budget 설정 

- 월 예산: **$100**
- 예산 **80% 도달 시 이메일 알림** 설정

 *AWS Budgets 설정 화면*
![AWS Budget 설정](images/1.png)
---

## LV 1. 네트워크 & 기본 배포 

### 인프라
- VPC 구성
  - Public / Private Subnet 분리
- EC2 인스턴스
  - Public Subnet에 생성

### 애플리케이션
- 간단한 팀원 정보 저장 및 조회 API 개발
- Profile 분리
  - `local`: H2
  - `prod`: MySQL
- 로그
  - 요청: `INFO`
  - 예외: `ERROR`
- Actuator
  - `/actuator/health`
####
EC2 Public IP: 43.203.218.202

---

## LV 2. RDS & Parameter Store

### 인프라
- MySQL RDS 생성
- **보안 그룹 체이닝**
  - RDS Inbound에 EC2 보안 그룹 ID만 허용
- AWS Parameter Store
  - DB 접속 정보 저장

### 기능 확인
http://43.203.218.202:8080:actuator/info

![RDS 보안 그룹 인바운드 규칙](images/2.png)

---

## LV 3. S3 프로필 이미지 & 권한 관리

### 인프라
- S3 버킷 생성 (퍼블릭 액세스 차단)
- IAM Role을 EC2에 연결 

### API
- 프로필 이미지 업로드
  - `POST /api/members/{id}/profile-image`
- 프로필 이미지 조회
  - `GET /api/members/{id}/profile-image`
  - **Presigned URL (유효기간 7일)**

### 제출용 Presigned URL
- URL: https://cloud-architecture-hyu1335-files.s3.ap-northeast-2.amazonaws.com/uploads/446e5a48-572a-466b-a261-98c12a6afd86_images.jpg?X-Amz-Security-Token=IQoJb3JpZ2luX2VjEBgaDmFwLW5vcnRoZWFzdC0yIkgwRgIhAJMIsXf00jW6YKnB1DDobsyMWhWRY4qSUfqKVs%2B5CpYEAiEA8cVsWxu17z9arZcX2u0mrzaMF%2FPNHhXI6EDbSWjhdvsq0gUI4f%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FARAAGgwzMjMyMTUwNzA4MDgiDKOfeNL6mM4ULYP4%2FiqmBUDx0i0mzmXZCZnyEFjs670IiEvueZ%2F4ezWIhbfI9%2BWi5iSLj1jdhLdsQfFGuhcrpnZeGQJl5utYL%2FMslLWfNEN1LjMAHCLHyZDONUwWw73W7Z8iWpKiLgEQTV9r237kb8noDjvOJSTPIpBy1c9xdLpmFJFialppsF%2BzPXkWJMBCPOu5eIh0Ht%2FBbpj4%2BGpBxJWuwlAu6E6%2BQABzOHVl3yywfo9vJ9I4L%2FdRVf2OKD6eXbIyPqaiiBsCfVsoMO9QI9bYU%2F0CVYK1GwEnpHkDhdcZJ2YmJJp90TuiBJLMYUxoediilKg6KUVosUV%2BoFBUDu%2BEuZ7L2WCpDwLNnFX29pndk3CvfY6GdmE93YmBVAqjtQXbWRFt0Azo2pE%2B8bhN6gTt17z1DSyIZDChhgsh4vp5C8p2FWqM6c76Hjz3uV%2BZkBuhNZgRXxHvRH4NX8N1YLIWK6ZeYZqykTkWDunE68ig6Hzd7UojOq7Cla5Jaq4ivXtqmF%2BDNArtDn5qP%2FccR1viYoY1p5w1dIipf7aa%2FO%2Bn0Jq4xX0AsHoXFWj2FSCKTsRCJrRUcscs5H7om4GEv93W%2BpZt9LW4R3Q%2BikFIgOFL5OJvi9dUMkBRKcdE2PvgQFPjGddCGKLebD6B58p8KqMi5Llt56axFPvrOPl0mgSUPuOmwUpQAylMdt%2BIIx9rV%2BAXqWC%2FI%2F5rbNCGppYHhW0ixKH0FIIc0HXdxbcxKuV6k68ClR3vtqHsK2uKUvxTyVjuP7oxy%2Fx1rDZwV%2FkTcFpIsV2VeGl2d%2FZ7m%2BNtzYbNVTV1QuZO83lSaGlO%2Fg0IcUwF5L5tY1PtXusg5DVccrJmZS3pG%2FMomrZCwIy93oGMIcedl6mBh7unK%2F3J03ig3PtQ7FAcSPPUVHbBOlbPANsV3E8DNDDhuIHMBjqwAYcdc5PIBhYNly%2Bsl1orHQRZXns5ve8%2FGg9lTN%2Ba7FXxuGnVfbpnltKW4dFFh6KHDXLZvBLrIiGorNPmZm8mVe53C4DtVeuVULtlspIwHzVOOR2MSlkUN6ksSwzFMppjJ1%2B36lrgq0dU068Q5QQFo2%2BABkM7StLBGL%2FD7HK2CaLYD1rjv5IxZmyHt3k%2BfXXGbs5hkyKpw9cydGY6c6xdQUGZkr1GDI8k04B5HamN1AGI&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20260202T082612Z&X-Amz-SignedHeaders=host&X-Amz-Credential=ASIAUWQI6SZMNZXAR4PQ%2F20260202%2Fap-northeast-2%2Fs3%2Faws4_request&X-Amz-Expires=604800&X-Amz-Signature=8a1d2fc28ffc6fc239563a1d6df5f68fb56968d574c4ca328d7fab5d1587f285
- 만료 시간: 7일

---


