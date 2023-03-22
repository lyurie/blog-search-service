# blog-search-service

### Index
---
* [Features](#features)
* [Getting started](#getting-started)
* [API Specification](#api-specification)
* [Applied open source list](#applied-open-source-list)
* [Misc](#misc)

#
### Features
---
* `블로그 검색 서비스` 를 위한 Server Application 구현
1. [블로그 검색](#1-블로그-검색-api)
    * 검색어 조회 (Search Query)
    
2. [인기 검색어 조회](#2-인기-검색어-조회-api)
    * 조회할 인기 검색어 개수 (Number of search results)

#
### Getting started
---
* 아래 항목에 대해 사전에 설치가 되어 있어야 합니다:
   * [amazon corretto jdk 17](https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/downloads-list.html) 

* 어플리케이션 구동 :
   * 다운로드 : 
   ```shell
   $ wget https://drive.google.com/file/d/1ZymRJUDyZ16qN8BwdE5F7pL6EVwXDUba/view?usp=share_link
   ```
   * 실행 : 
   ```shell
   $ java -jar blog-search-service.jar
   ```
   * 로그 :
   ```shell
   $ cd ~/{프로젝트 위치}/logs/trace.log - NDJSON 형식의 request/response 로그
   $ cd ~/{프로젝트 위치}/logs/app.log - NDJSON 형식의 application 로그
   $ cd ~/{프로젝트 위치}/logs/app-error.log - NDJSON 형식의 application error 로그
   ```

#
### API Specification
---
#### [1] 블로그 검색 API
<details>
 <summary><code>GET</code> <code><b>/search-blog</b></code></summary>
 
#### Parameters
> | name     | type    | required | description                  |
> | :------: |:-------:|:--------:|------------------------------|
> | query | string | O | 검색 키워드 |
> | sort | string | X | 정렬 기준 (accuracy:정확도순 / recency:최신순, default=accuracy) |
> | page | integer | X | 결과 페이지 (1 to 5, default=1)|
> | size | integer | X | 한 페이지에 보여질 문서 수 (1 to 50, default=10)|
> | target | string | X | 검색 소스 (kakao / naver, default=kakao, naver 우선 순위를 가진다 (kakao 조회 실패 시에만 naver 로 검색 소스 변경))|

##### Responses
> | http code     | content-type                      | response                                                            |
> |---------------|-----------------------------------|---------------------------------------------------------------------|
> | `200`         | `application/json` 				        | 하단 `Example Response` 참조                                          |
> | `400`         | `application/json`                | `{"error":{"code":2,"name":"INVALID_REQUEST","message":"request parameter query is required"}}` |
> | `500`         | `application/json`                | `{"error":{"code":1,"name":"INTERNAL_SERVER_ERROR","message":"Internal Server Error"}}` |

##### Example cURL
> ```javascript
>  curl 'http://localhost/search-blog?query=keyword' -i -X GET
> ```

##### Example Response
> ```javascript
> HTTP/1.1 200 OK
> Content-Type: application/json
> ```

> ```json
> {
>   "total": 117152,
>   "page": 1,
>   "size": 10,
>   "documents": [
>     {
>       "title": "Novel AI] 캐릭터 표정 명령어<b>keyword</b> 추천",
>       "link": "http://mooto.tistory.com/49",
>       "description": "&lt;시선에 대한 명령어&gt; &#34;그는 ~을 쳐다봤다.&#34; - (...ellipsis...)",
>       "blog_name": "크리에이터",
>       "post_date": "23. 3. 18. 오전 1:17"
>     },
>     {
>       "title": "Javascript This <b>Keyword</b>",
>       "link": "http://seohags.tistory.com/84",
>       "description": "Udemy의 &#34;Javascript : The Advanced Concepts&#34; 강의에서 학습한 내용을 정리한 포스팅입니다. (...ellipsis...)",
>       "blog_name": "seohag",
>       "post_date": "23. 3. 3. 오후 12:11"
>     },
>    {
>       "title": "PEP-3102 <b>Keyword</b>-Only-Arguments / python method 에서 asterisk(별표) 의미 (*, *args, **kwargs)",
>       "link": "http://backstreet-programmer.tistory.com/196",
>       "description": "Bug 발생을 줄이기 위한 목적이 있을 것이라 생각하고 찾아보던 중 PEP 3102 (...ellipsis...).",
>       "blog_name": "글쓰는 개발자",
>       "post_date": "23. 3. 3. 오후 5:41"
>     },
>     {
>       "title": "<b>keyword</b> : 고단",
>       "link": "https://blog.naver.com/tjtjsdud1119/223011927095",
>       "description": "시켰는데 빨대 길이 저게 맞음? 저게 끝까지 다 꽂은 고임 (...ellipsis...)",
>       "blog_name": "One day :)",
>       "post_date": "23. 2. 10. 오후 9:47"
>     },
>     {
>       "title": "aside+lie+<b>keyword</b> | 2022.1029~2023.0304~퇴진 |",
>       "link": "http://gaon2gaon.tistory.com/619",
>       "description": "Aside 대한민국은 메인뉴스는 &#39;굥거니장모개검건진천공_참사왕국&#39;에 쫄았다. (...ellipsis...)",
>       "blog_name": "gaon2gaon.tistory.com",
>       "post_date": "23. 3. 4. 오전 10:28"
>     },
>     {
>       "title": "explicit <b>Keyword</b> in C++",
>       "link": "http://studylida.tistory.com/54",
>       "description": "I. Introduction A. Brief overview of the explicit <b>keyword</b> II. (...ellipsis...)",
>       "blog_name": "studylida",
>       "post_date": "23. 3. 2. 오후 9:00"
>     },
>     {
>      "title": "최태성 <b>KEYWORD</b> 365 한국사",
>       "link": "http://ksedd.tistory.com/78",
>       "description": "아직 저희집엔 수능준비하는 중고등 애들이 없어서, 또 그럴려면 까마득히 시간이 필요하니깐.. (...ellipsis...)",
>       "blog_name": "ksedd",
>       "post_date": "23. 3. 17. 오후 1:50"
>     },
>     {
>       "title": "Elasticsearch 기본개념 정리 (7) : text와 <b>keyword</b> Type",
>       "link": "http://data-study-clip.tistory.com/233",
>       "description": "당연히 ES에서도 RDB와 같이 Text, INT, LONG, DOUBLE ,Boolean 등의 다양한 데이터 타입을 가지고 있다. (...ellipsis...).",
>       "blog_name": "Data_study_clip",
>       "post_date": "23. 2. 12. 오후 3:52"
>     },
>     {
>       "title": "Spatial <b>Keyword</b>(SK) search query",
>       "link": "http://sarah0518.tistory.com/185",
>       "description": "이런 데이터를 분석하기 위한 기법이라 할 수 있음 : (Geo-Textual) Data Geo-Textual Data의 components -(...ellipsis...)",
>       "blog_name": "sarah0518",
>       "post_date": "22. 12. 9. 오전 12:19"
>     },
>     {
>       "title": "번역 : The Purpose of &#39;declare&#39; <b>Keyword</b> in TypeScript",
>       "link": "http://wnsdufdl.tistory.com/493",
>       "description": "하고 있지만, 카카오맵을 타입스크립트+리액트에서 사용할 때에도 적용할 수 있다.) 원문 : https://jav(...ellipsis...)",
>       "blog_name": "기록 (21.7.19 ~",
>       "post_date": "22. 12. 4. 오후 11:06"
>     }
>   ]
> }
> ```
</details>

#

#### [2] 인기 검색어 조회 API
<details>
 <summary><code>GET</code> <code><b>/top-n-search-keywords</b></code></summary>
 
#### Parameters
> | name     | type    | required | description                  |
> | :------: |:-------:|:--------:|------------------------------|
> | size | integer | X | 조회할 인기 검색어 개수 (1 to 10, default=10) |


##### Responses
> | http code     | content-type                      | response                                                            |
> |---------------|-----------------------------------|---------------------------------------------------------------------|
> | `200`         | `application/json` 				        | 하단 `Example Response` 참조                                          |
> | `400`         | `application/json`                | `{"error":{"code":2,"name":"INVALID_REQUEST","message":"size should be min 1, max 10"}}` |
> | `500`         | `application/json`                | `{"error":{"code":1,"name":"INTERNAL_SERVER_ERROR","message":"Internal Server Error"}}` |

##### Example cURL
> ```javascirpt
>  curl 'http://localhost/top-n-search-keywords' -i -X GET
> ```

##### Example Response
> ```javascript
> HTTP/1.1 200 OK
> Content-Type: application/json
> ```

> ```json
> [
>     {
>         "search_keyword": "keyword-11",
>         "search_count": 11
>     },
>     {
>         "search_keyword": "keyword-10",
>         "search_count": 10
>     },
>     {
>         "search_keyword": "keyword-9",
>         "search_count": 9
>     },
>     {
>         "search_keyword": "keyword-8",
>         "search_count": 8
>     },
>     {
>         "search_keyword": "keyword-7",
>         "search_count": 7
>     },
>     {
>         "search_keyword": "keyword-6",
>         "search_count": 6
>     },
>     {
>         "search_keyword": "keyword-5",
>         "search_count": 5
>     },
>     {
>         "search_keyword": "keyword-4",
>         "search_count": 4
>     },
>     {
>         "search_keyword": "keyword-3",
>         "search_count": 3
>     },
>     {
>         "search_keyword": "keyword-2",
>         "search_count": 2
>     }
> ]
> ```
</details>

#
### Applied open source list
---
> | 라이브러리 | 용도                                                                                                             |
> | -------- |----------------------------------------------------------------------------------------------------------------|
> | org.springframework.cloud:spring-cloud-starter-openfeign | 선언적 http client 사용을 위해 선택 |
> | io.github.resilience4j:resilience4j-spring-boot2 | Circuit Breaker 기능 사용을 위해 선택 |
> | com.github.ben-manes.caffeine:caffeine | 로컬 캐시 적용을 위해 선택 |
> | com.h2database:h2 | 인메모리 데이터베이스 사용을 위해 선택 |
> | org.mapstruct:mapstruct | 객체 매핑 위임을 위해 선택 |

#
### Misc
---
##### Architecture

- [clean architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)

##### Framework

- [spring boot](https://github.com/spring-projects/spring-boot)

##### Build tool

- [gradle](https://docs.gradle.org/current/userguide/userguide.html)

#
