Flow 정리
=========

# 기능

## 1. 인증 이메일 보내기 API
외부에서 해당 API 호출  

### Request Parameters
|Parameter|Description|
|---------|-----------|
|email|메일 보낼 주소|
|redirectURL|인증 정보를 전달할 URL, 이메일 인증 API 를 활용하는 클라이언트 서비스의 API URL 에 인증 결과를 전달해주기 위함|
|homeURL|인증 후 사용자 리다이렉트 시킬 홈 URL, 이메일 인증 과정 후 최종적으로 유저를 클라이언트 서비스 홈 URL 로 이동시키기 위함|

### Method, Path
POST /verify/email  

### Controller
VerifyEmailController

### DTO
VerifyEmailRequestDTO

### 주요 로직
1. 외부로부터 email, redirectURL, homeURL 을 받는다.  
2. 해당 데이터를 가지고 JWT 발급 후 이메일 인증을 위한 URI 를 생성한다 
3. Email 보낸다  

## 2. 이메일 인증 API
이메일에서 인증 URL 클릭 

### Method Path
GET /verify/email

### Request Parameters
|Parameter|Description|
|---------|-----------|
|token|JWT token, 이메일 보낼 때 만들어서 보낸 토큰|

### Controller
VerifyEmailController

### 주요 로직
1. 넘어온 token 이 우리가 발급한 token 이 맞는지 검증한다.  
2. 맞다면 해당 결과를 클라이언트 서비스 API 를 호출하여 전달해준다.  
3. 마지막으로 이메일을 클릭한 유저를 올바른 곳으로 이동시킨다.  

