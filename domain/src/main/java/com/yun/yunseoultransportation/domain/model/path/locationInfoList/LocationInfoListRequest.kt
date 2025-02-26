package com.yun.yunseoultransportation.domain.model.path.locationInfoList

data class LocationInfoListRequest(
    val stSrch: String,             // 검색어
    val serviceKey: String,         // 서비스키
    val resultType: String          // 응답유형
)
