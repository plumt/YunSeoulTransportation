package com.yun.yunseoultransportation.data.model.bus.busRouteList

data class BusRouteListResponse(
    val itemCount: Int,         // 항목 개수
    val msgBody: MsgBody        // 본문 내용
)

data class MsgBody(
    val itemList: List<ItemList>
)

data class ItemList(
    val busRouteAbrv: String,           // 노선명 (안내용 – 마을버스 제외)
    val busRouteId: String,             // 노선 ID
    val busRouteNm: String,             // 노선명 (DB관리용)
    val length: String,                 // 노선 길이 (Km)
    val routeType: Int,                 // 노선 유형 (1:공항, 2:마을, 3:간선, 4:지선, 5:순환, 6:광역, 7:인천, 8:경기, 9:폐지, 0:공용)
    val stStationNm: String,            // 기점
    val edStationNm: String,            // 종점
    val term: String,                   // 배차간격(분)
    val lastBusYn: String,              // 막차운행여부
    val firstBusTm: String,             // 금일첫차시간
    val lastBusTm: String,              // 금일막차시간
    val firstLowTm: String,             // 금일저상첫차시간
    val lastLowTm: String,              // 금일저상막차시간
    val corpNm: String                  // 운수사명
)