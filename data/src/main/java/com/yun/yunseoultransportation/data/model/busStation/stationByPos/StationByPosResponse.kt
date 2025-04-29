package com.yun.yunseoultransportation.data.model.busStation.stationByPos

data class StationByPosResponse(
    val itemCount: Int,         // 항목 개수
    val msgBody: MsgBody,        // 본문 내용
)

data class MsgBody(
    val itemList: List<ItemList>?,    // 각 항목 리스트
)

data class ItemList(
    val stationId: String,          // 정류소 고유 ID
    val stationNm: String,          // 정류소 명
    val gpsX: String,               // 정류소 좌표 X
    val gpsY: String,               // 정류소 좌표 Y
    val arsId: String,              // 정류소 번호
    val dist: String,               // 거리
    val stationTp: String,          // 정류소 타입(0:공용, 1:일반형 시내/농어촌버스, 2:좌석형 시내/농어촌버스, 3:직행좌석형 시내/농어촌버스, 4:일반형 시외버스, 5:좌석형 시외버스. 6:고속형 시외버스, 7:마을버스)
)