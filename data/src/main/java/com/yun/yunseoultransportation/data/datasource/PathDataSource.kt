package com.yun.yunseoultransportation.data.datasource

import com.yun.yunseoultransportation.domain.model.path.locationInfoList.LocationInfoListRequest
import com.yun.yunseoultransportation.domain.model.path.locationInfoList.LocationInfoListResponse
import com.yun.yunseoultransportation.domain.model.path.pathInfoByBusNSub.PathInfoByBusNSubRequest
import com.yun.yunseoultransportation.domain.model.path.pathInfoByBusNSub.PathInfoByBusNSubResponse

interface PathDataSource {
    suspend fun getPathInfoByBusNSub(pathInfoByBusNSubRequest: PathInfoByBusNSubRequest): PathInfoByBusNSubResponse
    suspend fun getLocationInfoList(locationInfoListRequest: LocationInfoListRequest): LocationInfoListResponse
}