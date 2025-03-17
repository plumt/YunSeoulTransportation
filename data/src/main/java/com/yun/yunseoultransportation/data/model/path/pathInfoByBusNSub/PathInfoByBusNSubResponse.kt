package com.yun.yunseoultransportation.data.model.path.pathInfoByBusNSub


data class PathInfoByBusNSubResponse(
    val itemCount: Int,         // 항목 개수
    val msgBody: MsgBody        // 본문 내용
)

data class MsgBody(
    val itemList: List<ItemList>    // 각 항목 리스트
)

data class ItemList(
    val distance: String,           // 거리
    val time: String,               // 소요시간
    val pathList: List<PathList>    // 경로목록
)

data class PathList(
    val routeId: String,            // 노선ID
    val routeNm: String,            // 노선명
    val fid: String,                // 탑승지ID
    val fname: String,              // 탑승지명
    val fx: String,                 // 탑승지 X좌표 (WGS84)
    val fy: String,                 // 탑승지 Y좌표 (WGS84)
    val tname: String,              // 하차지명
    val tx: String,                 // 하차지 X좌표 (WGS84)
    val ty: String,                 // 하차지 Y좌표 (WGS84)
)