<div align=center>
 <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/7/74/%EB%A9%8B%EC%9F%81%EC%9D%B4%EC%82%AC%EC%9E%90%EC%B2%98%EB%9F%BC_%EB%A1%9C%EA%B3%A0.png/692px-%EB%A9%8B%EC%9F%81%EC%9D%B4%EC%82%AC%EC%9E%90%EC%B2%98%EB%9F%BC_%EB%A1%9C%EA%B3%A0.png" width="150"/>
 <h2>멋사스네스(MutsaSNS)</h2>
 </div>

<div align="center">
 <img src="https://img.shields.io/badge/SpringBoot-6DB33F.svg?logo=Spring-Boot&logoColor=white" />
 <img src="https://img.shields.io/badge/SpringSecurity-6DB33F.svg?logo=Spring-Security&logoColor=white" />
 <img src="https://img.shields.io/badge/MySQL-3776AB.svg?logo=MySql&logoColor=white" />
 <img src="https://img.shields.io/badge/Docker-2496ED.svg?logo=Docker&logoColor=white" />
 <img src="https://img.shields.io/badge/AmazonEC2-FF9900.svg?logo=Amazon-EC2&logoColor=white" />
</div>

## 🔧 기능요구사항

**필수**
- [x] AWS EC2에 Docker로 배포
- [x] Swagger
- [x] Gitlab CI & Crontab CD
- [x] 회원가입
- [x] 로그인
- [x] 포스트 작성, 수정, 리스트, 삭제

**도전**
- [ ] 화면 UI 개발
- [ ] ADMIN 회원으로 등급업하는 기능
- [ ] 초기 ADMIN 회원은 하나가 존재하고 ADMIN 회원은 일반회원의 권한을 ADMIN으로 승격시킬 수 있다.
- [ ] ADMIN 회원이 일반 회원을 ADMIN으로 승격시키는 기능
- [ ] ADMIN 회원이 로그인 시 자신이 쓴 글이 아닌 글과 댓글에 수정, 삭제를 할 수 있는 기능


## 📃Architecture(아키텍처)
<img src="https://lh5.googleusercontent.com/PpZhHTjogNa5qt32TX7-Gws51y6_wBckwc-6i_0zXjdfSGxTUpARXW9wFJcUIciCCPQW2bE2vZreS373_uMk-buO1fG-FgueXhe-cU-riRdu0hZugZJhF01H8fMv3rYUt86sN8uJ_DUg_wLjMOt1R9LsQv2XoobfSOvHlYehdfMDDoHHr_kaNYPHBTBXoQ"/>

▲ 위 Layered Architecture 구조에 맞게 작성 해주세요.

- Println 사용 추천
    - Controller와 Service와 Repository의 호출 여부 확인
    - 디버깅시 유용함
- 각 단에 데이터를 전달 받을시 DTO 형태로 전달한다.


## 📃 Swagger

> Local
http://localhost:8080/swagger-ui/

> Remote
http://ec2-43-200-172-83.ap-northeast-2.compute.amazonaws.com:8080/swagger-ui/



## 📌 ERD
<img src="https://likelion.notion.site/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2Fe3d2805a-c105-4a7a-ac2b-9751b3544f55%2FUntitled.png?table=block&id=e90b71f4-cbf6-4514-9780-7ef671f8c795&spaceId=c69962b0-3951-485b-b10a-5bb29576bba8&width=1070&userId=&cache=v2" width = "200"/>



## 🛎 Endpoints

- `Get /api/v1/hello`<br>
### 사용자
- 회원가입 `Post /api/v1/users/join`<br>
  **입력 폼(JSON형식)**
  ```
  {
    "password": "string",
    "userName": "string"
  }
  ```
  **리턴(JSON형식)**
  ```
  {
    "result": {
      "userId": 0,
      "userName": "string"
    },
    "resultCode": "string"
  }
  ```

- 로그인 `Post /api/v1/users/login`<br>
  **입력 폼(JSON형식)**
  ```
  {
    "password": "string",
    "userName": "string"
  }
  ```
  **리턴(JSON형식)**
  ```
  {
    "result": {
      "jwt": "string"
    },
    "resultCode": "string"
  }
  ```

  ### 포스트
- 포스트 작성 `Post /api/v1/posts`<br>
  **입력 폼(JSON형식)**
  ```
  {
    "body": "string",
    "title": "string"
  }
  ```
  **리턴(JSON형식)**
  ```
  {
    "result": {
      "message": "string",
      "postId": 0
    },
    "resultCode": "string"
  }
  ```

- 포스트 리스트 조회 `Get /api/v1/posts`<br>
  **입력 폼(JSON형식)**
  ```
  {
    "result": {
      "content": [
        {
          "body": "string",
          "createdAt": "2022-12-26T08:55:29.165Z",
          "id": 0,
          "lastModifiedAt": "2022-12-26T08:55:29.165Z",
          "title": "string",
          "userName": "string"
        }
      ],
      "empty": true,
      "first": true,
      "last": true,
      "number": 0,
      "numberOfElements": 0,
      "pageable": {
        "offset": 0,
        "pageNumber": 0,
        "pageSize": 0,
        "paged": true,
        "sort": {
          "empty": true,
          "sorted": true,
          "unsorted": true
        },
        "unpaged": true
      },
      "size": 0,
      "sort": {
        "empty": true,
        "sorted": true,
        "unsorted": true
      },
      "totalElements": 0,
      "totalPages": 0
    },
    "resultCode": "string"
  }
  ```

- 포스트 상세 조회 `Get /api/v1/posts/{postId}`<br>
  **리턴(JSON형식)**
  ```
  {
    "result": {
      "body": "string",
      "createdAt": "2022-12-26T08:56:55.992Z",
      "id": 0,
      "lastModifiedAt": "2022-12-26T08:56:55.992Z",
      "title": "string",
      "userName": "string"
    },
    "resultCode": "string"
  }
  ```

- 포스트 수정 `Put /api/v1/posts/{id}`<br>
  **입력 폼(JSON형식)**
  ```
  {
    "body": "string",
    "title": "string"
  }
  ```
  **리턴(JSON형식)**
  ```
  {
    "result": {
      "message": "string",
      "postId": 0
    },
    "resultCode": "string"
  }
  ```
- 포스트 삭제 `Delete /api/v1/posts/{id}`<br>
  **리턴(JSON형식)**
  ```
  {
  "result": {
    "message": "string",
    "postId": 0
  },
  "resultCode": "string"
  }
  ```
