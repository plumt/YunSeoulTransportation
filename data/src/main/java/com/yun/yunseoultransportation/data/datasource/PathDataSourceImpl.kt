package com.yun.yunseoultransportation.data.datasource

import com.yun.yunseoultransportation.data.remote.api.PathApiService
import com.yun.yunseoultransportation.domain.model.path.pathInfoByBusNSub.PathInfoByBusNSubRequest
import com.yun.yunseoultransportation.domain.model.path.pathInfoByBusNSub.PathInfoByBusNSubResponse
import javax.inject.Inject

class PathDataSourceImpl @Inject constructor(
    private val pathApiService: PathApiService
) : PathDataSource{

    override suspend fun getPathInfoByBusNSub(pathInfoByBusNSubRequest: PathInfoByBusNSubRequest): PathInfoByBusNSubResponse {
        return pathApiService.getPathInfoByBusNSub(
            startX = pathInfoByBusNSubRequest.startX,
            startY = pathInfoByBusNSubRequest.startY,
            endX = pathInfoByBusNSubRequest.endX,
            endY = pathInfoByBusNSubRequest.endY,
            serviceKey = pathInfoByBusNSubRequest.serviceKey,
            resultType = pathInfoByBusNSubRequest.resultType
        )
    }
}