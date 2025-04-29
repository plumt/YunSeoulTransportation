package com.yun.yunseoultransportation.data.model.busStation.routeByStation

data class RouteByStationResponse(
    val itemCount: Int,         // 항목 개수
    val msgBody: MsgBody,        // 본문 내용
)

data class MsgBody(
    val itemList: List<ItemList>?,    // 각 항목 리스트
)

data class ItemList(
    val busRouteId: String,         // 노선 ID
    val busRouteNm: String,         // 노선명(DB관리용)
    val busRouteAbrv: String,       // 노선명(안내용 - 마을버스 제외)
    val length: String,             // 노선 길이(km)
    val busRouteType: String,       // 노선 유형 (1:공항, 2:마을, 3:간선, 4:지선, 5:순환, 6:광역, 7:인천, 8:경기, 9:폐지, 0:공용)
    val stBegin: String,            // 기점
    val stEnd: String,              // 종점
    val term: String,               // 배차 간격
    val nextBus: String,            // 다음버스 도착예정시간
    val firstBusTm: String,         // 금일 첫차 시간
    val lastBusTm: String,          // 금일 막차 시간
)