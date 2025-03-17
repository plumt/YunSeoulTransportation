package com.yun.yunseoultransportation.data.model.bus.busPosByRtid

data class BusPosByRtidResponse(
    val itemCount: Int,         // 항목 개수
    val msgBody: MsgBody        // 본문 내용
)

data class MsgBody(
    val itemList: List<ItemList>    // 각 항목 리스트
)

data class ItemList(
    val sectOrd: String,            // 구간순번
    val fullSectDist: String,       // 정류소간 거리
    val sectDist: String,           // 구간옵셋거리 (Km)
    val rtDist: String,             // 노선옵셋거리 (Km)
    val stopFlag: Int,              // 정류소도착여부 (0:운행중, 1:도착)
    val sectionId: String,          // 구간ID
    val dataTm: String,             // 제공시간
    val gpsX: String,               // 맵매칭X좌표 (WGS84)
    val gpsY: String,               // 맵매칭Y좌표 (WGS84)
    val vehId: String,              // 버스 ID
    val plainNo: String,            // 차량번호
    val busType: Int,               // 차량유형 (0:일반버스, 1:저상버스, 2:굴절버스)
    val lastStTm: String,           // 종점도착소요시간
    val nextStTm: String,           // 다음정류소도착소요시간
    val isrunyn: Int,               // 해당차량 운행여부 (0: 운행종료, 1: 운행)
    val trnstnid: String,           // 회차지 정류소ID
    val islastyn: Int,              // 막차여부(0 : 막차아님, 1: 막차)
    val isFullFlag: Int,            // 만차여부(0 : 만차아님, 1: 만차)
    val posX: String,               // 맵매칭X좌표 (GRS80)
    val posY: String,               // 맵매칭Y좌표 (GRS80)
    val lastStnId: String,          // 최종정류소 고유 ID
    val congetion: Int,             // 혼잡도(0 : 없음, 3 : 여유, 4 : 보통, 5 : 혼잡, 6 : 매우혼잡)
    val nextStId: String            // 다음정류소아이디
)