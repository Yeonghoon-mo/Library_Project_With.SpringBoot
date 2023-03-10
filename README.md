# LibraryProject ( Spring boot 개인 프로젝트 With. 모영훈)

## 1. LibraryProject 소개
------------------------------
- LibraryProject는 **게시글 작성, 댓글 등록 및 대댓글 기능, 다중 파일 업로드 기능, 메일 인증, 회원별 권한 처리 등 많은 기능이 있는 게시판** 입니다.
- 게시글의 조회는 **비회원도 조회가 가능하며, 정회원만 볼 수 있고 등록할 수 있는 게시판**이 존재합니다. **회원 권한 별로 이용할 수 있는 메뉴를 차별화** 하였습니다.
- 게시글의 카테고리마다 **공지사항형 게시판, 썸네일형 게시판** 두 분류로 나누었으며, 각 게시판의 게시글마다**좋아요, 싫어요** 기능을 추가하였습니다.
- **관리자 페이지, 사용자 페이지**를 나누어, 홈페이지 관리자가 관리자 페이지에서 확실히 관리할 수 있으며, 역할이 확실할 수 있도록 나누었습니다.
- **관리자 페이지**에서는 **관리자 관리, 통합 회원 관리, 회원가입 요청 리스트(정회원), 각 게시판별 게시글 관리**기능이 있습니다. 

## 2. LibraryProject 개발 동기
------------------------------
- **게시판**은 모든 웹 애플리케이션의 필수이자 기본 요소라 생각하기에 제작하게 되었습니다.
- **Spring boot를 이용한 게시판**은 기본적인 스프링 프레임워크 사용법과 CRUD 기능 구현 능력을 학습하고 활용하는데 가장 적합하며, 추가 응용 및 확장 가능성 또한 많다 생각하여서 선택하게 되었습니다.
- 기본적인 생성(Create), 등록(Register), 수정(Update), 삭제(Delete) 기능에 회원 등록 및 파일 업로드 등 기능을 추가함으로써 **기본 CRUD 구현 능력을 심화하고 발전시킬 수 있다 생각하였습니다.**
- 단순히 게시판 구현이 아닌, **회원 가입, 로그인, 로그아웃 기능, 관리자 페이지와 사용자 페이지 세분화, 회원가입 시 이메일 인증코드 구현 등** 더 많은 기능들을 구현한다면, 웹 애플리케이션에서 필요로 하는 기능을 좀 더 심도있게 학습하고 내용을 경험할 수 있다 생각 하였습니다. 

## 3. 프로젝트 개발 환경 및 사용 기술
------------------------------
> ### IDE
- IntelliJ Ultimate

> ### Front-End
- JavaScript
- jQuery 3.6.0
- Thymeleaf

> ### Back-End
- JavaSE 1.8
- Spring boot 2.7.0
- Gradle 7.4.1
- Lombok 1.18.22
- MariaDB
- JPA
- QueryDSL 5.0.0
- HikariCP 4.0.3
- Tomcat 8.5
- Spring Security
- TICA Library
- lucy xss filter ( Cross Site Scripting 방지 )

## 4. 프로젝트 제작 기간 (2022/10/04 ~ 2022/12/03) 총 9주간 실제 작업기간 : 43일
-------------------------------

> #### 1주차 (2022/10/04~2022/10/10, 게시판, 게시글 기능 마무리를 목표로!) 
- 10/04 개발 환경 설정 및 프로젝트 기획 및 설계. Library Project DB 생성 및 게시판 영속 및 비즈니스 계층 CRUD 작업  
- 10/04 전달받은 HTML 템플릿의 HTML/CSS를 프로젝트에 맞게 수정하고, 자주 사용하게 될 함수들을 공통 함수로 만드는 작업
- 10/05 JPA, QueryDSL을 이용한 게시판 페이징 처리 및 검색 기능 구현, 좋아요 싫어요 구현을 위한 DB 테이블 수정
- 10/05 공지사항형 게시판, 썸네일형 게시판(공지사항형 게시판 4개, 썸네일형 게시판 4개 총 8개), 작성(수정) 페이지, 상세정보 페이지 병합 (더욱 편리한 유지보수를 위해)
- 10/06 게시판 파일 업로드 & 다중 파일 업로드 구현, 게시글 좋아요 싫어요, 게시글 썸네일 작업
- 10/07~10/08 댓글 기능 서비스 계층 작업, 댓글 페이징 구현
- 10/10 게시판 통합검색 작업, 사용자 인덱스 페이지 구현
> #### 2주차 (2022/10/11~2022/10/17, 관리자 페이지 마무리를 목표로!) 
- 10/13 관리자 페이지 작업(메뉴 리스트는 하드코딩으로 작업)
- 10/14 관리자 등록 페이지 Back, Front CRUD 작업, 로그인 쿠키 & 관리자 세션관리 적용, Spring Security PasswordEncoder 적용
- 10/15 관리자 페이지 일반&정회원 회원관리, 정회원 가입 요청관리 페이지 추가, 게시판 & 게시글 리스트, 검색 작업
- 10/16 관리자 페이지 정회원 소개 페이지, 공지사항 작성 페이지 구현
- 10/17 관리자 페이지 게시글 대표 이미지 업로드, 다중 파일 업로드 구현
> #### 3주차 (2022/10/18~2022/10/24, 사용자 회원가입, 로그인 쿠키 & 세션 관리 마무리를 목표로!) 
- 10/18 사용자 일반회원 & 정회원 구분 작업, 프로필 이미지 업로드 작업
- 10/19 사용자 세션관리 & 쿠키를 이용한 조회 수 증가 적용 작업
- 10/20 파일 업로드 경로를 불러와 사용자 프로필 이미지 가져오기 구현
- 10/21 Back - 사용자 세션 값 조회 작업
- 10/22 Front - Thymeleaf로 세션값을 가져온 후 비교하여 게시글, 댓글 수정&삭제 권한 부여
- 10/24 사용자 마이페이지 구현
> #### 4주차 (2022/10/25~2022/11/02) 
- 10/25 사용자 회원가입 Spring Security PasswordEncoder 적용
- 10/26~10/28 정회원 회원가입 시 해당 사용자 이메일로 전송하는 기능 작업, 회원가입 인증버튼 클릭 시 DB상에 저장된 토큰 값을 비교하여 이메일 인증 구현
- 10/29 회원가입, 마이페이지에서 이미지를 업로드 했을 경우, 이미지 미리보기를 보여주는 작업
> #### 5주차 (2022/11/03~2022/11/09)
- 11/04 썸네일형 게시판 이미지 미리보기 구현
- 11/06 파일 저장 위치 OS별로 구분
- 11/07~ 11/08 페이지(사용자, 관리자)별 로그인 세션 저장위치 변경
- 11/09 프로젝트 패키지 구조 변경
> #### 6주차 (2022/11/10 ~ 2022/11/16)
- 11/10 ENUM 클래스를 추가하여 Y || N 이 아닌 데이터는 들어올 수 없게 변경 후 JPA에 적용하기 위해 Converter Controller 작성
- 11/11 CustomExcepiton 구현
- 11/14 응답(response) 클래스에서 entity를 리턴 타입으로 받는 것을 응답 클래스로 리턴타입 변경
- 11/15~ 11/16 JPA에서의 COUNT(CASE - WHEN - THEN)가 비정상적으로 COUNT 되는것을 확인하여 해결 방법을 찾지 못해 COUNT 쿼리 부분은 mybatis로 변경
> #### 7주차 (2022/11/17 ~ 2022/11/23)
- 11/17 자바 공부를 하며 문자열 비교는 연산자 부호를 사용하지 않는 것이 좋다는 말을 듣고 문자열 비교를 전부 equlas로 변경
- 11/18~ 11/21 파일 업로드 부분을 전역 변수로 두었던 부분, 삭제 처리를 데이터 속성으로 했던 부분을 익명 함수로 변경
- 11/23 공통으로 쓰이는 함수를 스크립트 파일 내(script-handler.js)공통 함수로 이동하여 코드 간결화
> #### 8주차 (2022/11/24 ~ 2022/11/30)
- 11/24 전체 테이블에서 공통으로 쓰이는 컬럼을 따로 Entity(BaseEntity.class)를 두어 상속받는 형식으로 변경
- 11/26 페이징, 검색 조건을 파라미터로 받는 변수들을 하나의 클래스로 묶어 간결화
- 11/27 댓글 테이블 구성 변경, 대댓글 CRUD 구현, 댓글 수정 방식 변경<br>
  (before - 댓글 등록할때의 textarea에 수정할 댓글 내용 innerHTML / after - 새로운 textarea생성)
- 11/30 Front-end 리팩토링
> #### 9주차 (2022/11/31 ~ 2022/12/03)
- 12/02~ 12/03 Back-end 리팩토링

## 5. Entity Relationship Diagram
-------------------------------
- ERD 다이어그램 모델링 툴은 ERDCloud를 사용하였습니다.

![ERD](https://user-images.githubusercontent.com/54883318/173002175-73fc5eeb-b310-47d0-af4b-145b92925350.PNG)

## 6. Package layer
-------------------------------
**MVC 디자인 패턴을 사용하였습니다.**

![스크린샷 2022-12-24 22 47 59](https://user-images.githubusercontent.com/54883318/209438736-b30716fe-7cd8-4a2c-a79c-8f6517d5c561.png)

## 7.Project Function Flow
-------------------------------
- 모든 회원, 비회원은 특정 게시판(정회원 게시판)을 제외한 모든 게시글 조회가 가능합니다.
- 게시글 작성, 댓글 작성은 회원이 아닌 경우 불가능합니다.
- 인터셉터를 이용하여 로그인 기능을 구현하였습니다. 따라서 회원, 비회원 여부 체크도 인터셉터로 처리합니다.
- 회원의 권한은 회원 세션 안에 있는 정보를 불러와서 권한을 확인합니다.
- 게시글 수정, 삭제는 본인이 아닌 경우와 관리자 페이지에서 수정, 삭제가 아닌 경우는 불가능합니다. <br>
  (Service 단에서 현재 세션의 회원ID(PK)와 게시글 작성자ID(PK)를 비교. 단 회원 타입이 ADMIN일 경우 이 과정은 PASS)
  
- **상단 레이아웃, 사용자 페이지 인덱스 알고리즘**
![상단 레이아웃](https://user-images.githubusercontent.com/54883318/173299071-3ca42316-66c2-42d0-b6ce-30f71bc5bff9.JPG)
![레이아웃, 인덱스 알고리즘](https://user-images.githubusercontent.com/54883318/173301984-a4be9a1b-b1f3-4677-b04c-f77f7559ebc9.JPG)

- **로그인 여부와 상관없이 접근할 수 있는 게시판 페이지의 Function Flow 및 권한에 따른 True & False Service는 아래와 같습니다.**
![게시판 알고리즘](https://user-images.githubusercontent.com/54883318/173310176-d80a8e58-f372-4eba-8d08-5e698ce15562.JPG)

- **관리자 페이지의 Function Flow**
![관리자 페이지 알고리즘](https://user-images.githubusercontent.com/54883318/173314634-56d9fefd-c970-428b-961d-64ca65c436c7.JPG)

## 8.프로젝트 결과물 (HTML 디자인은 대학 동기 디자이너에게 공유를 받았습니다.)
-------------------------------
- ##### 1) 관리자 페이지 로그인, 관리자 관리
    - **Fetch API 비동기 방식**으로 구현
    - 관리자 ID 중복확인 후 등록, 관리자 ID의 사용여부가 미사용일 경우 deleteYn의 컬럼 값이 Y로 변경되며 로그인 불가. <br>
        ![ezgif com-gif-maker](https://user-images.githubusercontent.com/54883318/174234525-2279e50f-5ef9-4b79-91f6-0674649bee13.gif)

- ##### 2) 관리자 페이지 회원관리, 정회원 가입 요청관리
    - 검색 조건은 회원 유형(Member Type), 이름, 아이디로 검색 가능, 회원 탈퇴 시 deleteYn의 컬럼 값이 Y으로 변경되며 로그인 불가. <br>
        ![ezgif com-gif-maker](https://user-images.githubusercontent.com/54883318/174235055-812f39ac-5566-4c8d-95d9-54730d236441.gif)

- ##### 3) 관리자 페이지 게시판 관리
    - 공지사항 작성 버튼은 공지사항 소메뉴에서만 노출
    - 공지사항 최상위 노출은 사용자 페이지에서 게시글의 번호가 아닌, (공지)로 표시되며 최상위 리스트로 이동을 의미함.
    - 게시판관리 -> 공지사항 소메뉴가 아닌 다른 게시판 관리 소메뉴들은 회원 게시판의 수정, 삭제 & 댓글 수정, 삭제가 가능함. <br>
       ![ezgif com-gif-maker](https://user-images.githubusercontent.com/54883318/174236125-f9c6e257-7e63-4892-85cb-891e821432e4.gif)
       
- ##### 4) 회원가입 페이지
    - 회원가입은 일반회원, 정회원으로 이루어져있음.
    - 정회원은 회원가입 후 관리자 승인이 있어야 로그인이 가능함.
    - 회원 비밀번호는 Spring Security PasswordEncoder로 랜덤 문자열이 들어감.
    - 이메일 인증번호는 Service단에서 6자리의 난수를 생성하여 인증번호를 생성 후 DB에 저장. DB에 저장된 값과 입력값 비교. <br>
        ![ezgif com-gif-maker](https://user-images.githubusercontent.com/54883318/174238736-ddcd1958-7acc-4a15-8bd2-9740d38636c0.gif)
        ![비밀번호 PasswordEncoder](https://user-images.githubusercontent.com/54883318/174239015-8c8b55f0-b886-44a1-8c15-9aca0d57713b.JPG)

- ##### 5) 로그인
    - **5-1) 비밀번호 찾기**
    - 회원가입한 이메일로 인증번호를 보내고, 이름을 확인하여 본인 여부 확인 후 비밀번호 변경. <br>
        ![ezgif com-gif-maker (1)](https://user-images.githubusercontent.com/54883318/174240959-e9d73d99-908a-4054-8007-cba6803f4788.gif)
        
    - **5-2) 로그인 & 회원 정보 수정(마이페이지)**
    - 일반회원, 정회원 로그인 시 마이페이지 필드 항목들이 다름.
    - 마이페이지에서 회원 정보 수정 시, 외래키가 걸려있는 Entity도 Update.
    - 쿠키를 이용하여 아이디 저장 기능 구현 (최대 1주일) <br>
        ![ezgif com-gif-maker (2)](https://user-images.githubusercontent.com/54883318/174242658-2dc871f5-a8ce-486d-af9d-5c1464817d3a.gif)
        
    - **5-3) 아이디 저장**
    - 사용자가 '아이디 저장' 버튼을 체크하고 로그인을 했을 경우, 'key'라는 쿠키 생성 및 생성 쿠키에 loginId가 값으로 저장된다.
    - 쿠키의 보관 일자는 7일로 지정 하였으므로, 7일 후에는 쿠키 삭제 <br>
        ![ezgif com-gif-maker (3)](https://user-images.githubusercontent.com/54883318/174244975-1e201149-74df-4f00-a5ec-c51ed60d3e8d.gif)

- ##### 6) 게시판(공지사항형, 썸네일형)
    - **6-1) 게시글 조회, 작성**
    - 게시글 조회는 누구나 가능. (단, 정회원 게시판 제외)
    - 게시글 작성은 회원만 가능하므로 로그인하지 않은 사용자는 게시글 작성 버튼 비노출 (직접 URL 입력 시에는 로그인 창으로 이동) <br>
        ![ezgif com-gif-maker (4)](https://user-images.githubusercontent.com/54883318/174246873-b8a23c42-3a57-469d-a238-9f2d73e93042.gif)
        
    - **6-2) 정회원 게시판 조회, 등록**
    - Front 단에서 세션 값을 조회하여 세션 안에 있는 멤버변수의 MemberType 값을 조회하여 정회원 여부 판단.
    - 정회원 게시판 게시글 조회는 정회원만 가능하며, 정회원이 아닐 경우 메세지 출력 후 리스트로 이동. <br>
        ![ezgif com-gif-maker (5)](https://user-images.githubusercontent.com/54883318/174247653-29ac6889-6bd9-49ad-ab65-708e344aaf9e.gif)
        
    - **6-3) 게시글 수정, 삭제**    
    - 게시글 수정, 삭제는 Member Table의 해당 회원 PK값을 비교 후 같은 경우에만 가능.
    - PK가 다를 경우 수정, 삭제 버튼이 출력되지 않지만 URL을 직접 입력하여 임의적으로 수정하려는 경우 HTTP 400 ERROR가 발생.
    - 수정, 삭제가 완료되었을 경우 alert메세지 출력. <br>
        ![ezgif com-gif-maker (6)](https://user-images.githubusercontent.com/54883318/174248914-8dfec82b-c77a-494f-9fe3-fb62ff45ec83.gif)
        
    - **6-4) 게시글 검색, 통합검색**
    - 게시판에서의 검색, 맨 위 Layout 부분에서의 검색 두 종류의 검색이 존재함.
    - 게시판에서 검색은 해당 게시판의 게시글만 검색이 되며, 게시글 이전 페이지 유지가 됨. (ex) 자유게시판)
    - 통합검색에서의 검색은 전체 게시판의 게시글이 검색이 되며, 고의로 이전 페이지 유지 기능을 추가하지 않았음.
    - 게시판에서의 검색, 통함검색은 제목, 내용, 작성자 전체가 검색이 됨. (검색 조건을 선택하는 SelectBox가 없음.)
    - **게시판에서 검색하여 들어갔을 경우, 통합검색에서 검색했을 경우 게시글에서 목록 버튼을 누르면 이전 페이지로 이동하는 URL이 다름** <br>
        ![ezgif com-gif-maker (7)](https://user-images.githubusercontent.com/54883318/174250738-0129be06-3b58-4011-9b18-1e3e9f94f3cb.gif)
        
    - **6-4) 게시글 좋아요 & 싫어요**
    - 게시글 좋아요, 싫어요 기능은 **무분별한** 좋아요, 싫어요를 방지하고자 DB에 있는 게시글 번호, 회원 번호를 이용.
    - tb_like(좋아요)테이블에 있는 게시글 PK를 통해서 좋아요 & 싫어요 개수를 카운트함.
    - **비회원**은 좋아요 & 싫어요가 불가능하며, 비회원이 눌렀을 경우 로그인 후 이용이 가능하다는 메세지 출력.
        ![ezgif com-gif-maker](https://user-images.githubusercontent.com/54883318/174302257-3e8633ed-d3f9-47d7-903d-651f38612371.gif)

- ##### 7) 댓글
     - 댓글 등록은 **로그인한 회원**만 가능하도록 구현.
     - 댓글 수정, 삭제는 Member Table의 해당 회원 PK값을 비교 후 같은 경우에만 가능.
        ![ezgif com-gif-maker (1)](https://user-images.githubusercontent.com/54883318/174302964-944576f0-6760-4ddf-adb8-0f8a1403a32e.gif)

- ##### 8) 파일 업로드
     - **8-1) 파일 업로드 경로**
     - OS별로 다른 파일 업로드 Path를 설정<br>
        ![파일 업로드 경로](https://user-images.githubusercontent.com/54883318/174427472-632fa430-432f-48d6-9137-0a4851897892.JPG)
      
     - **8-2) 저장될 파일명** 
     - java.util.uuid 클래스를 사용하여 저장될 파일명이 **겹치지 않게** 생성되도록 하였다.<br>
        ![파일 업로드 saveName](https://user-images.githubusercontent.com/54883318/174427801-0f5861e4-9c6a-48b4-acc7-87f109e8ce88.JPG)
        
     - **8-3) 게시글 대표 이미지(썸네일), 첨부파일 구분**
     - 대표 이미지 사진도 다운로드가 가능하며, 다운로드 시 사용자에게 보여줄 파일 이름(originalname)과 저장될 파일 이름(savename)은 **게시판 테이블** 안에 존재하고,
       **첨부파일은 tb_attach 테이블 내**에 파일 정보가 담겨있다.<br>
        ![파일 저장_1](https://user-images.githubusercontent.com/54883318/174428625-e3834b7e-b447-43b4-9176-f96fbec4145a.JPG)
        
     - **8-4) 다중 파일 업로드**
     - 1. 파일 업로드는 Back-End 단에서 파라미터가 Null일 경우, 비어있는 파일 리스트를 만든다. (NullPointException 방지) <br>
        ![파일 업로드_1](https://user-images.githubusercontent.com/54883318/174428961-35670f6f-b640-4c37-b8f9-399355273bba.JPG)
     - 2. 파라미터로 넘어온 파일의 갯수만큼 for문을 돌고, for문 안에서 **'저장할 파일명.파일 확장자'** 를 String saveName 변수 안에 넣는다.
     - 3. 새로운 파일 객체를 만들어 업로드 경로, saveName를 파라미터로 넘겨서 file.transferTo 메서드로 자바의 파일 객체로 변환한다.
     - 4. Excepiton이 생길 경우, CustomException(Back-End)으로 파일 업로드에 실패하였다는 메세지를 사용자에게 보여준다(Front-End)<br>
        ![파일 업로드_2](https://user-images.githubusercontent.com/54883318/174429310-b7d27511-7e80-4f00-b667-017c505bcdfe.JPG)

     - 다중 파일 업로드는 tb_attach 테이블 내에 foreign key로 걸려있는 **boardId(게시글 번호)를 기준으로 Count**한다. <br>
        ![tb_attach 테이블 구조](https://user-images.githubusercontent.com/54883318/174428736-5c4f2078-d090-4ccc-8033-b9cf439a2714.JPG)
         
     - **8-5) 파일 조회, 파일 삭제** 
     - 파일 조회와 삭제는 공통JS파일 안에 파일 시퀀스 처리용, 파일 삭제 처리용 **익명함수**를 두어 관리한다.<br>
        ![파일 조회, 삭제_1](https://user-images.githubusercontent.com/54883318/174429357-b8c65dbd-c330-41d1-b32c-296d10c186cc.JPG)
     - 1. 기존 파일 정보를 불러올 때마다(fileList.length를 불러올 때마다) 파일 시퀀스 처리용 익명함수 내에 있는 up메서드를 불러오며, **각 파일마다 시퀀스**를 준다. 파일 시퀀스를            통하여 파일 번호를 확인 후 file에 id값을 준다.
     - 2. 파일 삭제는 **파일의 ID가 있을 경우**, 파일의 ID를 가져온 후 해당하는 파일 ID를 삭제한 후, 파일에 있는 data-value를 조회하여 해당하는 data-value를 remove하여 **사용            자에게 보여지는** 리스트에서도 삭제한다. <br>
        ![ezgif com-gif-maker (2)](https://user-images.githubusercontent.com/54883318/174430490-04991f90-413b-435e-80c4-2c274c579381.gif)

