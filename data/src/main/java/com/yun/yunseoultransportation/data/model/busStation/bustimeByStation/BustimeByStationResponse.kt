package com.yun.yunseoultransportation.data.model.busStation.bustimeByStation

data class BustimeByStationResponse(
    val itemCount: Int,         // 항목 개수
    val msgBody: MsgBody,        // 본문 내용
)

data class MsgBody(
    val itemList: List<ItemList>?,    // 각 항목 리스트
)

data class ItemList(
    val arsId: String,                  // 정류소 번호
    val stationNm: String,              // 정류소 명
    val busRouteId: String,             // 노선 ID
    val busRouteNm: String,             // 노선 명(DB관리용)
    val busRouteAbrv: String,           // 노선 명(안내용 - 마을버스 제외)
    val firstBusTm: String,             // 금일 첫차 시간
    val lastBusTm: String,              // 금일 막차 시간
)