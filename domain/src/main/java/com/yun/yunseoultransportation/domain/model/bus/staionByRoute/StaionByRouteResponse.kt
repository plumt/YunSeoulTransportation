package com.yun.yunseoultransportation.domain.model.bus.staionByRoute

data class StaionByRouteResponse(
    val itemCount: Int,         // 항목 개수
    val msgBody: MsgBody        // 본문 내용
)

data class MsgBody(
    val itemList: List<ItemList>    // 각 항목 리스트
)

data class ItemList(
    val busRouteAbrv: String,       // 노선명 (안내용 – 마을버스 제외)
    val busRouteId: String,         // 노선 ID
    val busRouteNm: String,         // 노선명 (DB관리용)
    val seq: String,                // 순번
    val section: String,            // 구간 ID
    val station: String,            // 정류소 고유 ID
    val stationNm: String,          // 정류소 이름
    val gpsX: String,               // X좌표 (WGS 84)
    val gpsY: String,               // Y좌표 (WGS 84)
    val direction: String,          // 진행방향
    val fullSectDist: String,       // 정류소간 거리
    val stationNo: String,          // 정류소 번호
    val routeType: String,          // 노선 유형 (1:공항, 2:마을, 3:간선, 4:지선, 5:순환, 6:광역, 7:인천, 8:경기, 9:폐지, 0:공용)
    val beginTm: String,            // 첫차 시간
    val lastTm: String,             // 막차 시간
    val trnstnid: String,           // 회차지 정류소ID
    val posX: String,               // 좌표X (GRS80)
    val posY: String,               // 좌표Y (GRS80)
    val sectSpd: String,            // 구간 속도
    val arsId: String,              // 정류소 고유번호
    val transYn: String             // 회차지 여부
)