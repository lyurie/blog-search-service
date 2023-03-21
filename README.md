`블로그 검색 서비스`를 위한 Server Application

* [Getting started](#getting-started)
* [API Specification](#api-specification)
* [Used Open Source Library](#used-open-source-library)


### Getting started

사전 설치가 필요한 항목
* https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/downloads-list.html

어플리케이션 실행
* jar 파일 다운로드 ()
* 실행 : java -jar blog-search-service.jar

### API Specification

[1] 블로그 검색 API - GET /search-blog
* CURL example
```
curl 'http://localhost/search-blog?query=keyword' -i -X GET
```
* Request parameter

| name     | type    | required | description                  |
| -------- |---------|----------|------------------------------|
| query | string | O | 검색 키워드 |
| sort | string | X | 정렬 기준 (accuracy:정확도순 / recency:최신순, default=accuracy) |
| page | integer | X | 결과 페이지 (1 to 5, default=1)|
| size | integer | X | 한 페이지에 보여질 문서 수 (1 to 50, default=10)|
| target | string | X | 검색 소스 (kakao / naver, default=kakao, naver 우선 순위를 가진다 (kakao 장애 시에만 naver 로 검색 소스 변경))|

* Response HTTP Example
```
HTTP/1.1 200 OK
Content-Type: application/json

{
    "total": 117152,
    "page": 1,
    "size": 10,
    "documents": [
        {
            "title": "Novel AI] 캐릭터 표정 명령어<b>keyword</b> 추천",
            "link": "http://mooto.tistory.com/49",
            "description": "&lt;시선에 대한 명령어&gt; &#34;그는 ~을 쳐다봤다.&#34; - The character looked at ~ with their eyes. &#34;그는 ~을 바라볼 때 입을 찡그렸다.&#34; - The character narrowed their mouth while looking at ~. &#34;그녀는 눈을 깜빡였다.&#34; - The character blinked their eyes. &#34;그녀는 눈을 감았다.&#34; - The character closed their eyes...",
            "blog_name": "크리에이터",
            "post_date": "23. 3. 18. 오전 1:17"
        },
        {
            "title": "Javascript This <b>Keyword</b>",
            "link": "http://seohags.tistory.com/84",
            "description": "Udemy의 &#34;Javascript : The Advanced Concepts&#34; 강의에서 학습한 내용을 정리한 포스팅입니다. 아직 배우고 있는 단계라 틀린 내용이 있을 수 있습니다 틀린 내용이 있다면 수정하도록 하겠습니다 감사합니다 :) Javascript 에서 this란 무엇일까 ? 함수가 프로퍼티인 객체이다. 잠깐 실행 컨텍스트 이야기로 돌아가서...",
            "blog_name": "seohag",
            "post_date": "23. 3. 3. 오후 12:11"
        },
        {
            "title": "PEP-3102 <b>Keyword</b>-Only-Arguments / python method 에서 asterisk(별표) 의미 (*, *args, **kwargs)",
            "link": "http://backstreet-programmer.tistory.com/196",
            "description": "Bug 발생을 줄이기 위한 목적이 있을 것이라 생각하고 찾아보던 중 PEP 3102 에 &#34;Keyworkd only Arguments&#34;로 명시되어 있음을 확인했습니다. PEP-3102 <b>Keyword</b> Only Arguments 그림3. PEP 3102 - <b>Keyword</b>-Only Arguments motivation postional arguments에 의해 자동으로 채워지지 않을 <b>keyword</b> only arguments 선언을...",
            "blog_name": "글쓰는 개발자",
            "post_date": "23. 3. 3. 오후 5:41"
        },
        {
            "title": "<b>keyword</b> : 고단",
            "link": "https://blog.naver.com/tjtjsdud1119/223011927095",
            "description": "시켰는데 빨대 길이 저게 맞음? 저게 끝까지 다 꽂은 고임 \u200b \u200b \u200b 2주간의 진주 출장을 끝내고 드디어 금요일에 부산으로 … 쿨럭 쿨럭 … 먹기만 한거 같은데 <b>keyword</b>가 왜 고단이냐고 묻는다면 맛있는거라도 먹어야 고단함을 이겨낼 수 있었기 때문이지 ^0^ … \u200b \u200b \u200b \u200b 드디어 부산에서의 금같은 주말😆 독도 소주가 있길래...",
            "blog_name": "One day :)",
            "post_date": "23. 2. 10. 오후 9:47"
        },
        {
            "title": "aside+lie+<b>keyword</b> | 2022.1029~2023.0304~퇴진 |",
            "link": "http://gaon2gaon.tistory.com/619",
            "description": "Aside 대한민국은 메인뉴스는 &#39;굥거니장모개검건진천공_참사왕국&#39;에 쫄았다. 경제를 안보 하위개념으로 삼으면서(중국을 적대시하고, 미국에게 뒤통수 맞고, 일본에 꼬리치면서), &#39;수출 마이너스 구조&#39;를 만들었다. 그 결과 최소10개월연속 무역수지 적자가 계속되고 있다. ..레거시언론은 IMF이후 처음이라는거 까지는...",
            "blog_name": "gaon2gaon.tistory.com",
            "post_date": "23. 3. 4. 오전 10:28"
        },
        {
            "title": "explicit <b>Keyword</b> in C++",
            "link": "http://studylida.tistory.com/54",
            "description": "I. Introduction A. Brief overview of the explicit <b>keyword</b> II. What is the explicit <b>keyword</b> in C++? A. Definition of the explicit <b>keyword</b> III. Examples of using explicit <b>keyword</b> A. Example : Preventing Implicit Conversions IV. Best Practices for using explicit <b>keyword</b> A. When to use explicit...",
            "blog_name": "studylida",
            "post_date": "23. 3. 2. 오후 9:00"
        },
        {
            "title": "최태성 <b>KEYWORD</b> 365 한국사",
            "link": "http://ksedd.tistory.com/78",
            "description": "아직 저희집엔 수능준비하는 중고등 애들이 없어서, 또 그럴려면 까마득히 시간이 필요하니깐.. 초등별별한국사랑 ebs한국사 할 때 단어사전으로 쓰라고 사줬어요. 그래서 첫장부터 끝까지 쭈욱 읽힌건 아니지만 나름대로 만족해요. 가끔 한국사만화책 읽다가 사림이 뭐야? 훈구파가 뭐야? 할 때마다 찾아보곤 해요...",
            "blog_name": "ksedd",
            "post_date": "23. 3. 17. 오후 1:50"
        },
        {
            "title": "Elasticsearch 기본개념 정리 (7) : text와 <b>keyword</b> Type",
            "link": "http://data-study-clip.tistory.com/233",
            "description": "당연히 ES에서도 RDB와 같이 Text, INT, LONG, DOUBLE ,Boolean 등의 다양한 데이터 타입을 가지고 있다. 그중 가장 중요하게 살펴볼 것은 text와 <b>keyword</b> 타입인데 이것에 대해서 먼저 정리해보도록 한다. 1. text type vs <b>keyword</b> type : 무슨차이일까? text와 <b>keyword</b> 둘다 문자열을 나타내기 위한 타입이지만 서로...",
            "blog_name": "Data_study_clip",
            "post_date": "23. 2. 12. 오후 3:52"
        },
        {
            "title": "Spatial <b>Keyword</b>(SK) search query",
            "link": "http://sarah0518.tistory.com/185",
            "description": "이런 데이터를 분석하기 위한 기법이라 할 수 있음 : (Geo-Textual) Data Geo-Textual Data의 components - text - location - time Boolean Range Query: <b>keyword</b>가 존재할 때 사용하는 range query 1. A query region: 범위에 대한 질의처리 2. A set of keywords: <b>keyword</b>를 포함하고 있는 object를 검색하는 질의...",
            "blog_name": "sarah0518",
            "post_date": "22. 12. 9. 오전 12:19"
        },
        {
            "title": "번역 : The Purpose of &#39;declare&#39; <b>Keyword</b> in TypeScript",
            "link": "http://wnsdufdl.tistory.com/493",
            "description": "하고 있지만, 카카오맵을 타입스크립트+리액트에서 사용할 때에도 적용할 수 있다.) 원문 : https://javascript.plainenglish.io/purpose-of-declare-<b>keyword</b>-in-typescript-8431d9db2b10 Purpose of Declare <b>Keyword</b> in TypeScript A Detailed Introduction to the Role of TypeScript Declare, so You Are Not a...",
            "blog_name": "기록 (21.7.19 ~",
            "post_date": "22. 12. 4. 오후 11:06"
        }
    ]
}

```
* Response Fields

| name     | type     | description                  |
| -------- |----------|------------------------------|
| total | integer | 총 검색 결과 개수 |
| page | integer | 결과 페이지 |
| size | integer | 한 페이지에 보여질 결과 수|
| documents[] | array | 블로그 검색 결과 리스트|
| documents[].title | string | 블로그 글 제목|
| documents[].link | string | 블로그 글 URL|
| documents[].description | string | 블로그 글 요약|
| documents[].blog_name | string | 블로그의 이름|
| documents[].post_date | datetime | 블로그 글 작성 시간 yyyy-MM-dd HH:mm:ss|

[2] 인기 검색어 조회 API - GET /top-n-search-keywords
* CURL example
```
curl 'http://localhost/top-n-search-keywords' -i -X GET
```
* Request parameter

| name     | type    | required | description                  |
| -------- |---------|----------|------------------------------|
| size | integer | X | 조회할 인기 검색어 개수 (1 to 10, default=10) |

* Response HTTP Example
```
HTTP/1.1 200 OK
Content-Type: application/json

[
    {
        "search_keyword": "keyword-11",
        "search_count": 11
    },
    {
        "search_keyword": "keyword-10",
        "search_count": 10
    },
    {
        "search_keyword": "keyword-9",
        "search_count": 9
    },
    {
        "search_keyword": "keyword-8",
        "search_count": 8
    },
    {
        "search_keyword": "keyword-7",
        "search_count": 7
    },
    {
        "search_keyword": "keyword-6",
        "search_count": 6
    },
    {
        "search_keyword": "keyword-5",
        "search_count": 5
    },
    {
        "search_keyword": "keyword-4",
        "search_count": 4
    },
    {
        "search_keyword": "keyword-3",
        "search_count": 3
    },
    {
        "search_keyword": "keyword-2",
        "search_count": 2
    }
]


```

* Response Fields

| name     | type     | description                  |
| -------- |----------|------------------------------|
| [] | array | 인기 검색어 리스트 |
| [].search_keyword | string | 검색어 |
| [].search_count | integer | 검색 카운트|


### Used Open Source Library

| 라이브러리 | 용도                                                                                                             |
| -------- |----------------------------------------------------------------------------------------------------------------|
| io.github.resilience4j:resilience4j-spring-boot2 | Circuit Breaker 기능 사용을 위해 선택|
| com.github.ben-manes.caffeine:caffeine | |
| com.h2database:h2 | |
| org.mapstruct:mapstruct | |
