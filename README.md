api 명세

GET /search-blog
request param
 - * query  ; 검색 키워드
 -   sort   ; 정렬 기준 (accuracy(정확도)/recency(최신); default - accuracy)
 -   page   ; 결과 페이지 (1~50; default - 1)
 -   size   ; 한 페이지에 보여질 문서 수 (1~50; default - 10)
 -   target ; 검색 소스 (kakao/naver; 지정하지 않을 시 - kakao, naver 우선 순위를 가진다 (kakao 장애 시에만 naver 로 검색 소스 변경)) 


GET /top-search-keyword
request param
 - size     ; 조회할 인기 키워드 개수 (1~10; default - 10)
