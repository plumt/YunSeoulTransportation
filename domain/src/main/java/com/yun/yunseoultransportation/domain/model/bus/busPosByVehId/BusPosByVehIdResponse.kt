package com.yun.yunseoultransportation.domain.model.bus.busPosByVehId

data class BusPosByVehIdResponse(
    val itemCount: Int,         // 항목 개수
    val msgBody: MsgBody        // 본문 내용
)

data class MsgBody(
    val itemList: List<ItemList>    // 각 항목 리스트
)

data class ItemList(
    val stopFlag: Int,          // 정류소도착여부
    val dataTm: String,         // 제공시간
    val tmX: String,            // 맵매칭X좌표
    val tmY: String,            // 맵매칭Y좌표
    val plainNo: String,        // 차량번호
    val busType: Int,           // 차량유형
    val vehId: String,          // 버스ID
    val posX: String,           // 맵매칭X좌표 (GRS80)
    val posY: String,           // 맵매칭Y좌표 (GRS80)
    val lastStnId: String,      // 최종정류장 ID
    val stId: String,           // 정류소ID
    val isFullFlag: Boolean,    // 만차여부
    val congetion: Int,         // 차량내부 혼잡도
    val stOrd: String           // 정류소순번
)

