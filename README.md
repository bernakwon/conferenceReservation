Conference room reservation system
programed By hrkwon 
==================================
## Spec
- Springboot
- Spring data Jpa
- lombok
- H2-database
- thymeleaf(View리턴에만 사용함)
- JuitTest
--------------------------------------------
## build Method
clone https://github.com/bernakwon/conferenceReservation.git
->import project
>자바만 설치되어 있다고 가정한다. JAVA 8 은 로컬에 설치되어 있어야 한다. eclipse 기준으로 설명한다. InteliJ에서는 별도로 lombok플러그인을 제공한다. 

#### gradle, lombok 설치

	1. gradle 
    - Help->Eclipse Markerplace에서 buildShip 검색 후 설치
    - 재시작 후 Help->Eclipse Markerplace 에서 Gradle IDE Pack 3.8.x+1.0.x+2.2.x 검색 후 설치
    - 재시작 후 소스빌드가 되지 않을 시, 소스 오른쪽 마우스를 클릭하여 configure->convert Gradle(STS) project 를 실행한다.
	2. lombok : 
    - gradle 빌드 후 lombok1.16.22.jar가 다운로드된 위치를 찾아서 더블클릭한다.이클립스실행파일에 설치해준다.
    - 이클립스를 재시작한다.
    
#### Encoding
    - project->properties->resource에서 text file encoding을 UTF-8로 변경한다.

#### Run
    - Run->Run As ->Spring Boot App
    - 포트 설정을 7070으로 해두었기때문에 localhost:7070으로 접속해야한다.
--------------------------------------------
## 요구사항과 개발전략

 ### 요구사항

	1.예약 시간은 정시, 30분을 기준으로 시작하여 30분 단위로 예약 가능

	2.1회성 예약과 주 단위 반복 예약 설정 가능

	3.동일한 회의실에 중첩된 일시로 예약 불가

	4.다수의 사용자가 동시에 동일 날짜, 회의실에 예약할 때 일시가 중첩되어 예약될 수 없고, 서버에서 먼저 처리되는 1건만 예약
-------------------------------------------

 ### 개발전략

  #### DB

  

    1. 회의실 관리

     - ID(PK) , 회의실이름

    2. 예약메인

     - ID(PK), 예약명, 예약자명, 예약일, 저장일시, 반복횟수 , 버전

    3. 예약일관리

     - ID(PK), 예약일 ,버전

    4. 예약상세

     - [회의실ID(FK), 예약메인 ID(FK), 예약일ID(FK)]PK , 시작시간, 종료시간, 버전

   

   ##### 연관관계
	    예약메인 : 예약상세 = 1 : 1
	    예약일관리 : 예약상세 = 1 : N 
	    회의실코드 : 예약상세 = 1 : N

   
---------------------------------------------  

   #### Solve

  
  	1. 요구사항1 :  시작시간과 종료시간을 LocalTime으로 변환하여 분체크, 30분이나 정시가 아닌경우 TimeErrorException호출

  	2. 요구사항2 :  예약일과 반복횟수를 이용하여 예약일리스트로 반환하여 처리, LocalDate의 plusWeek 메서드를 이용하여 주단위 예약설

  	3. 요구사항3 :  예약상세 테이블의 예약일ID가 있고, 시작시간이 종료시간사이에 데이터가 있는자를 체크, 중복된 데이터가 있는경우 ReservationReduplicationException호출

  	4. 요구사항4 :  JPA의 낙관전락 기법을 사용하여 처리, 각 Entity class에 @Version을 설정  

  	5. 단위테스트 : serviceImpl단의 주요로직 검증

  	6. 에러처리 :  @ExcepionHander와 @ControllerAdvise를 이용하여 에러정보를 리턴

  

   --------------------------------------------

  


