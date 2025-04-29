package com.yun.yunseoultransportation.domain.model.busStation

data class BusStationDetail(
    val stId: String,               // 정류소 고유 ID
    val stNm: String,               // 정류소 명
    val busRouteId: String,         // 노선 ID
    val busRouteAbrv: String,       // 노선 명(안내용 - 마을버스 제외)
    val rtNm: String,               // 노선 명(DB관리용)
    val gpsX: String,               // 정류소 좌표 X
    val gpsY: String,               // 정류소 좌표 Y
    val stationTp: String,          // 정류소 타입(0:공용, 1:일반형 시내/농어촌버스, 2:좌석형 시내/농어촌버스, 3:직행좌석형 시내/농어촌버스, 4:일반형 시외버스, 5:좌석형 시외버스. 6:고속형 시외버스, 7:마을버스)
    val firstTm: String,            // 첫차 시간
    val lastTm: String,             // 막차 시간
    val term: String,               // 배차 간격
    val routeType: String,          // 노선 유형 (1:공항, 2:마을, 3:간선, 4:지선, 5:순환, 6:광역, 7:인천, 8:경기, 9:폐지, 0:공용)
    val nextBus: String,            // 다음 버스 도착 예정 시간

    val vehId1: String,             // 첫 번째 도착 예정 버스 ID
    val stationNm1: String,         // 첫 번째 도착 예정 버스 최종 정류소 명
    val busType1: String,           // 첫 번째 도착 예정 버스 차량 유형(0:일반버스, 1:저상버스, 2:굴절버스)

    val vehId2: String,             // 두 번째 도착 예정 버스 ID
    val stationNm2: String,         // 두 번째 도착 예정 버스 최종 정류소 명
    val busType2: String,           // 두 번째 도착 예정 버스 차량 유형(0:일반버스, 1:저상버스, 2:굴절버스)

    val adirection: String,         // 방향
    val arrmsg1: String,            // 첫 번째 도착 예정 버스 도착 정보 메시지
    val arrmsg2: String,            // 두 번째 도착 예정 버스 도착 정보 메시지
    val arrmsgSec1: String,         // 첫 번째 도착 예정 버스 도착 정보 메시지
    val arrmsgSec2: String,         // 두 번째 도착 예정 버스 도착 정보 메시지
    val isFullFlag1: String,        // 첫 번째 도착 에정 버스 만차 여부(0:만차아님, 1:만차)
    val isFullFlag2: String,        // 두 번째 도착 에정 버스 만차 여부(0:만차아님, 1:만차)

    val sectNm: String              // 구간 명
)
