package com.yun.yunseoultransportation.data.model.search.keyworkSearch

data class KeywordSearchRequest(
    val query: String,          // 검색을 원하는 질의어
    val x: String? = null,      // 중심 좌표의 X 혹은 경도(longitude) 값, 특정 지역을 중심으로 검색할 경우 radius와 함께 사용 가능
    val y: String? = null,      // 중심 좌표의 Y 혹은 위도(latitude) 값, 특정 지역을 중심으로 검색할 경우 radius와 함께 사용 가능
    val radius: Int? = null,    // 중심 좌표부터의 반경거리. 특정 지역을 중심으로 검색하려고 할 경우 중심좌표로 쓰일 x,y와 함께 사용, (단위: 미터(m), 최소: 0, 최대: 20000)
    val page: Int? = null,      // 결과 페이지 번호 (최소: 1, 최대: 45, 기본값: 1)
    val size: Int? = null,      // 한 페이지에 보여질 문서의 개수 (최소: 1, 최대: 15, 기본값: 15)
    val sort: String? = null    // 결과 정렬 순서, distance 정렬을 원할 때는 기준 좌표로 쓰일 x, y와 함께 사용, distance 또는 accuracy(기본값: accuracy)
)