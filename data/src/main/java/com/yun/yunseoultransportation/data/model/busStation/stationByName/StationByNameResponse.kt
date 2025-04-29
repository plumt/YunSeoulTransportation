package com.yun.yunseoultransportation.data.model.busStation.stationByName

data class StationByNameResponse(
    val itemCount: Int,         // 항목 개수
    val msgBody: MsgBody,        // 본문 내용
)

data class MsgBody(
    val itemList: List<ItemList>?,    // 각 항목 리스트
)

data class ItemList(
    val stId: String,               // 정류소 고유 ID
    val stNm: String,               // 정류소 명
    val tmX: String,                // 정류소 좌표 X
    val tmY: String,                // 정류소 좌표 Y
    val arsId: String,              // 정류소 번호
)