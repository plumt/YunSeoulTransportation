package com.yun.yunseoultransportation.data.datasource

import com.yun.yunseoultransportation.data.remote.api.PathApiService
import javax.inject.Inject

class PathDataSourceImpl @Inject constructor(
    private val pathApiService: PathApiService
) : PathDataSource {

//    override suspend fun getPathInfoByBusNSub(pathInfoByBusNSubRequest: PathInfoByBusNSubRequest): PathInfoByBusNSubResponse {
//        return pathApiService.getPathInfoByBusNSub(
//            startX = pathInfoByBusNSubRequest.startX,
//            startY = pathInfoByBusNSubRequest.startY,
//            endX = pathInfoByBusNSubRequest.endX,
//            endY = pathInfoByBusNSubRequest.endY,
//            serviceKey = pathInfoByBusNSubRequest.serviceKey,
//            resultType = pathInfoByBusNSubRequest.resultType
//        )
//    }
//
//    override suspend fun getLocationInfoList(locationInfoListRequest: LocationInfoListRequest): LocationInfoListResponse {
//        return pathApiService.getLocationInfoList(
//            stSrch = locationInfoListRequest.stSrch,
//            serviceKey = locationInfoListRequest.serviceKey,
//            resultType = locationInfoListRequest.resultType
//        )
//    }
}