package com.yun.yunseoultransportation.domain.model.bus.busPosByVehId

data class BusPosByVehIdResponse(
    val itemCount: Int,         // 항목 개수
    val msgBody: MsgBody        // 본문 내용
)

data class MsgBody(
    val itemList: List<ItemList>    // 각 항목 리스트
)

data class ItemList(
    val sectOrd: String,        // 구간순번
    val sectDist: String,       // 구간옵셋거리(Km)
    val stopFlag: Int,          // 정류소도착여부 (0:운행중, 1:도착)
    val sectionId: String,      // 구간 ID
    val dataTm: String,         // 제공시간
    val tmX: String,            // 맵매칭X좌표(WGS84)
    val tmY: String,            // 맵매칭Y좌표(WGS84)
    val plainNo: String,        // 차량번호
    val busType: Int,           // 차량유형 (0:일반버스, 1:저상버스, 2:굴절버스)
    val vehId: String,          // 버스ID
    val posX: String,           // 맵매칭X좌표 (GRS80)
    val posY: String,           // 맵매칭Y좌표 (GRS80)
    val lastStnId: String,      // 최종정류장 ID
    val stId: String,           // 정류소ID
    val isFullFlag: Int,        // 만차여부(0 : 만차아님, 1: 만차)
    val congetion: Int,         // 혼잡도(0 : 없음, 3 : 여유, 4 : 보통, 5 : 혼잡, 6 : 매우혼잡)
    val stOrd: String           // 정류소순번
)

