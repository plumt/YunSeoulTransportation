package com.yun.yunseoultransportation.domain.usecase

import com.yun.yunseoultransportation.domain.model.bus.BusResult
import com.yun.yunseoultransportation.domain.model.busStation.BusStationResult
import com.yun.yunseoultransportation.domain.model.path.BusPathResult
import com.yun.yunseoultransportation.domain.repository.BusRepository
import javax.inject.Inject

class BusUseCase @Inject constructor(
    private val busRepository: BusRepository
) {

    suspend fun getStaionByRoute(busRouteId: String): BusStationResult {
        return busRepository.getStaionByRoute(busRouteId)
    }

    suspend fun getRoutePath(busRouteId: String): BusPathResult {
        return busRepository.getRoutePath(busRouteId)
    }

    suspend fun getBusPosByVehId(vehId: String): BusResult {
        return busRepository.getBusPosByVehId(vehId)
    }

    suspend fun getBusPosByRtid(busRouteId: String): BusResult {
        return busRepository.getBusPosByRtid(busRouteId)
    }

    suspend fun getBusRouteList(strSrch: String): BusStationResult {
        return busRepository.getBusRouteList(strSrch)
    }

//    suspend fun getLowStationByUid(arsId: String): BusStationResult {
//        return busRepository.getLowStationByUid(arsId)
//    }

}