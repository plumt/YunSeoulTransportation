package com.yun.yunseoultransportation.data.mapper

import com.yun.yunseoultransportation.domain.model.busStation.BusStationDetail
import com.yun.yunseoultransportation.domain.model.busStation.BusStationInfo
import com.yun.yunseoultransportation.domain.model.busStation.BusStationRoute

class BusStationMapper {

    companion object {

        @JvmName("routeByStationToBusStationRoute")
        fun List<com.yun.yunseoultransportation.data.model.busStation.routeByStation.ItemList>.toBusStationRouteList(): List<BusStationRoute> =
            map { item ->
                BusStationRoute(
                    busRouteId = item.busRouteId,
                    busRouteNm = item.busRouteNm,
                    busRouteAbrv = item.busRouteAbrv,
                    length = item.length,
                    busRouteType = item.busRouteType,
                    stBegin = item.stBegin,
                    stEnd = item.stEnd,
                    term = item.term,
                    nextBus = item.nextBus,
                    firstBusTm = item.firstBusTm,
                    lastBusTm = item.lastBusTm
                )
            }

        @JvmName("stationByNameToBusStationInfo")
        fun List<com.yun.yunseoultransportation.data.model.busStation.stationByName.ItemList>.toBusStationInfoList(): List<BusStationInfo> =
            map { item ->
                BusStationInfo(
                    stationId = item.stId,
                    stationNm = item.stNm,
                    arsId = item.arsId,
                    latitude = item.tmY,
                    longitude = item.tmX
                )
            }

        @JvmName("stationByUidToBusStationDetail")
        fun List<com.yun.yunseoultransportation.data.model.busStation.stationByUid.ItemList>.toBusStationDetailList(): List<BusStationDetail> =
            map { item ->
                BusStationDetail(
                    stId = item.stId,
                    stNm = item.stNm,
                    busRouteId = item.busRouteId,
                    busRouteAbrv = item.busRouteAbrv,
                    rtNm = item.rtNm,
                    gpsX = item.gpsX,
                    gpsY = item.gpsY,
                    stationTp = item.stationTp,
                    firstTm = item.firstTm,
                    lastTm = item.lastTm,
                    term = item.term,
                    routeType = item.routeType,
                    nextBus = item.nextBus,
                    vehId1 = item.vehId1,
                    stationNm1 = item.stationNm1,
                    busType1 = item.busType1,
                    vehId2 = item.vehId2,
                    stationNm2 = item.stationNm2,
                    busType2 = item.busType2,
                    adirection = item.adirection,
                    arrmsg1 = item.arrmsg1,
                    arrmsg2 = item.arrmsg2,
                    arrmsgSec1 = item.arrmsgSec1,
                    arrmsgSec2 = item.arrmsgSec2,
                    isFullFlag1 = item.isFullFlag1,
                    isFullFlag2 = item.isFullFlag2,
                    sectNm = item.sectNm,
                )
            }

        @JvmName("bustimeByStationToBusStationInfo")
        fun List<com.yun.yunseoultransportation.data.model.busStation.bustimeByStation.ItemList>.toBusStationInfo(): List<BusStationInfo> =
            map { item ->
                BusStationInfo(
                    stationNm = item.stationNm,
                    arsId = item.arsId,
                )
            }

        @JvmName("stationByPosToBusStationInfo")
        fun List<com.yun.yunseoultransportation.data.model.busStation.stationByPos.ItemList>.toBusStationInfo(): List<BusStationInfo> =
            map{ item ->
                BusStationInfo(
                    stationId = item.stationId,
                    stationNm = item.stationNm,
                    arsId = item.arsId,
                    latitude = item.gpsY,
                    longitude = item.gpsX
                )
            }
    }
}