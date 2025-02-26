package com.yun.yunseoultransportation.domain.model.search.keyworkSearch

data class KeywordSearchResponse(
    val meta: Meta,                 // 응답 관련 정보
    val documents: List<Documents>  // 응답 결과
)

data class Meta(
    val same_name: SameName,        // 질의어의 지역 및 키워드 분석 정보
    val pageable_count: Int,        // total_count 중 노출 가능 문서 수 (최대: 45)
    val total_count: Int,           // 검색어에 검색된 문서 수
    val is_end: Boolean             // 현재 페이지가 마지막 페이지인지 여부, 값이 false면 다음 요청 시 page 값을 증가시켜 다음 페이지 요청 가능
)

data class SameName(
    val region: List<String>,       // 질의어에서 인식된 지역의 리스트  (예: '중앙로 맛집' 에서 중앙로에 해당하는 지역 리스트)
    val keyword: String,            // 질의어에서 지역 정보를 제외한 키워드 (예: '중앙로 맛집' 에서 '맛집')
    val selected_region: String     // 인식된 지역 리스트 중, 현재 검색에 사용된 지역 정보
)

data class Documents(
    val place_name: String,         // 장소명, 업체명
    val distance: String,           // 중심좌표까지의 거리 (단, x,y 파라미터를 준 경우에만 존재), 단위 meter
    val place_url: String,          // 장소 상세페이지 URL
    val category_name: String,      // 카테고리 이름
    val address_name: String,       // 전체 지번 주소
    val road_address_name: String,  // 전체 도로명 주소
    val id: String,                 // 장소 ID
    val phone: String,              // 전화번호
    val category_group_code: String,    // 중요 카테고리만 그룹핑한 카테고리 그룹 코드
    val category_group_name: String,    // 중요 카테고리만 그룹핑한 카테고리 그룹명
    val x: String,                  // X 좌표값, 경위도인 경우 longitude (경도)
    val y: String                   // Y 좌표값, 경위도인 경우 latitude(위도)
)
