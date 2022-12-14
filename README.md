# 프로젝트 명 : CampusMap_v4

## 프로젝트 목적

  학교 앱에 웹으로 만들어진 지도가 불편하며 접근성이 좋지 않았고, 상징성을 가진 학교 이름이 포함된 교내 지도 앱이 있으면 학교에서 길을 찾을때 
  유용할 것 같다는 생각을 가지고 개발을 시작하였습니다.

  학교에 처음 방문하는 사람과 신입생, 재학생까지 포함하여 학교를 방문하는 사람들이 건물, 혹은 강의실을 찾는 것에 어려움을 겪지 않도록 하기 위하여 검색과 메뉴를 동시에 만들었습니다.
  
  그 외에도 교내 버스정류장과 공지사항을 추가하여 재학 중 필요한 정보를 편하게 얻을 수 있도록 하였습니다.

## Architecture

<img src="https://user-images.githubusercontent.com/59135621/202650966-6a0215bc-b16c-4224-af31-093a8bacfb53.png" width="600" height="600">

  
## 기술 스택
`Clean Architecture`, `MVVM`, `Kotlin`, `Hilt`, `Coroutine`, `Firebase Storage`, `Databinding`, `LiveData`, `Jetpack Navigation`, `Room DB`, `Retrofit`,
`Jsoup`, `Glide`, `ViewPager`, `RecyclerView`

## APIs
공공기관 API(실시간 버스 운행, 버스 정류장 정보), KaKaoMap API


  
  
## 시연 영상(미완)

현재 공학 1관 -> 103 ~ 107 , 115 ~ 118만 구현 상태.

**강의실 찾기**

https://user-images.githubusercontent.com/59135621/202423623-13ab056d-ce44-4296-a6c4-cc571bd30f4d.mp4

**버스정류장 정보 보기**

https://user-images.githubusercontent.com/59135621/202448922-ceb6617b-e396-4c04-b636-3110bf833c40.mp4

**공지사항 보기**

https://user-images.githubusercontent.com/59135621/202448953-accae80a-08f3-4e80-bdf9-4231f613eafe.mp4

## 개발 도구

Android Studio 2021.3.1 Dolphin

## 업데이트 예정

1. 건물 검색 후 검색기록 리스트 추가 예정
2. Material Design 이용하여 디자인
3. https://github.com/PRNDcompany/android-style-guide의 style guide 적용하여 refactoring
4. DB에 데이터 추가 예정 + firebase로 DB 관리 방식 변경.
5. 보일러 플레이트 제거
