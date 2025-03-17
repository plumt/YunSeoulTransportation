package com.yun.yunseoultransportation.data.model.path.locationInfoList

data class LocationInfoListResponse(
    val itemCount: Int,         // 항목 개수
    val msgBody: com.yun.yunseoultransportation.data.model.path.locationInfoList.MsgBody        // 본문 내용
)

data class MsgBody(
    val itemList: List<com.yun.yunseoultransportation.data.model.path.locationInfoList.ItemList>    // 각 항목 리스트
)

data class ItemList(
    val poiId: String,              // POI ID
    val poiNm: String,              // POI 이름
    val gpsX: String,               // X좌표 (WGS84)
    val gpsY: String,               // Y좌표 (WGS84)
    val posX: String,               // X좌표 (WGS84)
    val posY: String,               // Y좌표 (WGS84)
)