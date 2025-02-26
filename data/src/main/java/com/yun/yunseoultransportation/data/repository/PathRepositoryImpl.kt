package com.yun.yunseoultransportation.data.repository

import com.yun.yunseoultransportation.data.datasource.PathDataSource
import com.yun.yunseoultransportation.data.remote.api.PathApiService
import com.yun.yunseoultransportation.domain.model.path.locationInfoList.LocationInfoListRequest
import com.yun.yunseoultransportation.domain.model.path.locationInfoList.LocationInfoListResponse
import com.yun.yunseoultransportation.domain.model.path.pathInfoByBusNSub.PathInfoByBusNSubRequest
import com.yun.yunseoultransportation.domain.model.path.pathInfoByBusNSub.PathInfoByBusNSubResponse
import com.yun.yunseoultransportation.domain.repository.PathRepository
import javax.inject.Inject

class PathRepositoryImpl @Inject constructor(
    private val pathDataSource: PathDataSource
): PathRepository {

    override suspend fun getPathInfoByBusNSub(
        startX: String,
        startY: String,
        endX: String,
        endY: String
    ): Result<PathInfoByBusNSubResponse> {
        return try {
            val response = pathDataSource.getPathInfoByBusNSub(PathInfoByBusNSubRequest(
                startX = startX,
                startY = startY,
                endX = endX,
                endY = endY,
                serviceKey = "nHxMfmtyTjtuCvjAcPez7bDwl+PwLECo/F2/Lp92vVDqrtlW4KTvdmMMqZiXWu5zyrP6ehOEnYoeG6hpdbSA8w==",
                resultType = "json"
            ))
            Result.success(response)
        } catch (e: Exception){
            Result.failure(e)
        }
    }

    override suspend fun getLocationInfoList(stSrch: String): Result<LocationInfoListResponse> {
        return try {
            val response = pathDataSource.getLocationInfoList(LocationInfoListRequest(
                stSrch = stSrch,
                serviceKey = "nHxMfmtyTjtuCvjAcPez7bDwl+PwLECo/F2/Lp92vVDqrtlW4KTvdmMMqZiXWu5zyrP6ehOEnYoeG6hpdbSA8w==",
                resultType = "json"
            ))
            Result.success(response)
        } catch (e: Exception){
            Result.failure(e)
        }
    }
}