package com.yun.yunseoultransportation.domain.repository

import com.yun.yunseoultransportation.domain.model.path.locationInfoList.LocationInfoListResponse
import com.yun.yunseoultransportation.domain.model.path.pathInfoByBusNSub.PathInfoByBusNSubRequest
import com.yun.yunseoultransportation.domain.model.path.pathInfoByBusNSub.PathInfoByBusNSubResponse

interface PathRepository {

    suspend fun getPathInfoByBusNSub(
        startX: String,
        startY: String,
        endX: String,
        endY: String
    ): Result<PathInfoByBusNSubResponse>

    suspend fun getLocationInfoList(
        stSrch: String
    ): Result<LocationInfoListResponse>
}