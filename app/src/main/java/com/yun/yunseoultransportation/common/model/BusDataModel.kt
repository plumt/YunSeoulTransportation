package com.yun.yunseoultransportation.common.model

//import com.yun.yunseoultransportation.domain.model.bus.busPosByRtid.BusPosByRtidResponse
//import com.yun.yunseoultransportation.domain.model.bus.routePath.RoutePathResponse
//import com.yun.yunseoultransportation.domain.model.bus.staionByRoute.StaionByRouteResponse

data class BusDataModel(
    val latitude: String,
    val longitude: String,
    val title: String,
    val id: String
)

//fun StaionByRouteResponse.toBusDataModel(): List<BusDataModel> {
//    val tempBusData = msgBody.itemList.map {
//        BusDataModel(
//            latitude = it.gpsY,
//            longitude = it.gpsX,
//            title = it.stationNm,
//            id = it.station
//        )
//    }
//    return tempBusData
//}
//
//fun BusPosByRtidResponse.toBusDataModel(): List<BusDataModel> {
//    val tempBusData = msgBody.itemList.map {
//        BusDataModel(
//            latitude = it.gpsY,
//            longitude = it.gpsX,
//            title = it.plainNo,
//            id = it.vehId
//        )
//    }
//    return tempBusData
//}
//
//fun RoutePathResponse.toBusDataModel(): List<BusDataModel> {
//    val tempBusData = msgBody.itemList.map {
//        BusDataModel(
//            latitude = it.gpsY,
//            longitude = it.gpsX,
//            title = it.no,
//            id = it.no
//        )
//    }
//    return tempBusData
//}