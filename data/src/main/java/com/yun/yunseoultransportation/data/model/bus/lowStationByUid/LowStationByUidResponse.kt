package com.yun.yunseoultransportation.data.model.bus.lowStationByUid

data class LowStationByUidResponse(
    val itemCount: Int,         // 항목 개수
    val msgBody: MsgBody        // 본문 내용
)


data class MsgBody(
    val itemList: List<ItemList>    // 각 항목 리스트
)

data class ItemList(
    val stId: String,               // 정류소 고유 ID
    val stnNm: String,              // 정류소명
    val arsId: String,              // 정류소 번호
    val busRouteId: String,         // 노선 ID
    val busRouteAbrv: String,       // 노션 약칭(안내용 - 마을버스 제외)
    val rtNm: String,               // 노선명(DB관리용)
    val posX: String,               // 정류소 좌표 X
    val posY: String,               // 정류소 죄표 Y
)