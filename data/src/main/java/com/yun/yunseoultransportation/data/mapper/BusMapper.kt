package com.yun.yunseoultransportation.data.mapper

import com.yun.yunseoultransportation.data.model.bus.BusInfoDto
import com.yun.yunseoultransportation.data.model.bus.BusPathInfoDto
import com.yun.yunseoultransportation.domain.model.bus.BusInfo
import com.yun.yunseoultransportation.domain.model.busStation.BusStationInfo
import com.yun.yunseoultransportation.domain.model.path.BusPathInfo

class BusMapper {

    companion object {

        @JvmName("staionByRouteToBusStationInfo")
        fun List<com.yun.yunseoultransportation.data.model.bus.stationByRoute.ItemList>.toBusStationInfoList(): List<BusStationInfo> =
            map { item ->
                BusStationInfo(
                    latitude = item.gpsY,
                    longitude = item.gpsX,
                    arsId = item.arsId,
//                    busRouteNm = item.busRouteNm,
                    stationNm = item.stationNm,
//                    edationNm = "",
//                    id = item.station,
//                    busRouteId = item.busRouteId
                )
            }

        @JvmName("busRouteListToBusStationInfo")
        fun List<com.yun.yunseoultransportation.data.model.bus.busRouteList.ItemList>.toBusStationInfoList(): List<BusStationInfo> =
            map { item ->
                BusStationInfo(
//                    id = item.busRouteId,
//                    busRouteNm = item.busRouteAbrv,
                    stationNm = item.stStationNm,
//                    edationNm = item.edStationNm,
                    latitude = "",
                    longitude = "",
                    arsId = ""
//                    busRouteId = item.busRouteId
                )
            }

        @JvmName("lowStationByUidToBusStationInfo")
        fun List<com.yun.yunseoultransportation.data.model.bus.lowStationByUid.ItemList>.toBusStationInfoList(): List<BusStationInfo> =
            map { item ->
                BusStationInfo(
//                    id = item.stId,
//                    busRouteNm = item.rtNm,
                    stationNm = item.busRouteAbrv,
//                    edationNm = "",
                    latitude = item.posY,
                    longitude = item.posX,
//                    busRouteId = item.busRouteId,
                    arsId = item.arsId
                )
            }

        fun List<com.yun.yunseoultransportation.data.model.bus.routePath.ItemList>.toBusPathInfoList(): List<BusPathInfo> =
            map { item ->
                BusPathInfoDto(
                    latitude = item.gpsY,
                    longitude = item.gpsX,
                    id = item.no
                ).toBusPathInfo()
            }

        @JvmName("busPosByVehIdToBusInfo")
        fun List<com.yun.yunseoultransportation.data.model.bus.busPosByVehId.ItemList>.toBusInfoList(): List<BusInfo> =
            map { item ->
                BusInfoDto(
                    latitude = item.tmY,
                    longitude = item.tmX,
                    plainNo = item.plainNo,
                    id = item.vehId
                ).toBusInfo()
            }

        @JvmName("busPosByRtidToBusInfo")
        fun List<com.yun.yunseoultransportation.data.model.bus.busPosByRtid.ItemList>.toBusInfoList(): List<BusInfo> =
            map { item ->
                BusInfoDto(
                    latitude = item.gpsY,
                    longitude = item.gpsX,
                    plainNo = item.plainNo,
                    id = item.vehId
                ).toBusInfo()
            }
    }
}