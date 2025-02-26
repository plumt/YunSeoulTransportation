package com.yun.yunseoultransportation.data.repository

import com.yun.yunseoultransportation.data.datasource.BusDataSource
import com.yun.yunseoultransportation.domain.model.bus.busPosByRtid.BusPosByRtidRequest
import com.yun.yunseoultransportation.domain.model.bus.busPosByRtid.BusPosByRtidResponse
import com.yun.yunseoultransportation.domain.model.bus.busPosByVehId.BusPosByVehIdRequest
import com.yun.yunseoultransportation.domain.model.bus.busPosByVehId.BusPosByVehIdResponse
import com.yun.yunseoultransportation.domain.model.bus.busRouteList.BusRouteListRequest
import com.yun.yunseoultransportation.domain.model.bus.busRouteList.BusRouteListResponse
import com.yun.yunseoultransportation.domain.repository.BusRepository
import javax.inject.Inject

class BusRepositoryImpl @Inject constructor(
    private val busDataSource: BusDataSource
) : BusRepository {

    override suspend fun getBusPosByVehId(vehId: String): Result<BusPosByVehIdResponse> {
        return try {
            val response = busDataSource.getBusPosByVehId(
                BusPosByVehIdRequest(
                    vehId = vehId,
                    serviceKey = "nHxMfmtyTjtuCvjAcPez7bDwl+PwLECo/F2/Lp92vVDqrtlW4KTvdmMMqZiXWu5zyrP6ehOEnYoeG6hpdbSA8w==",
                    resultType = "json"
                )
            )
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getBusPosByRtid(busRouteId: String): Result<BusPosByRtidResponse> {
        return try {
            val response = busDataSource.getBusPosByRtid(
                BusPosByRtidRequest(
                    busRouteId = busRouteId,
                    startOrd = "1",
                    endOrd = "15",
                    serviceKey = "nHxMfmtyTjtuCvjAcPez7bDwl+PwLECo/F2/Lp92vVDqrtlW4KTvdmMMqZiXWu5zyrP6ehOEnYoeG6hpdbSA8w==",
                    resultType = "json"
                )
            )
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getBusRouteList(strSrch: String): Result<BusRouteListResponse> {
        return try {
            val response = busDataSource.getBusRouteList(
                BusRouteListRequest(
                    strSrch = strSrch,
                    serviceKey = "nHxMfmtyTjtuCvjAcPez7bDwl+PwLECo/F2/Lp92vVDqrtlW4KTvdmMMqZiXWu5zyrP6ehOEnYoeG6hpdbSA8w==",
                    resultType = "json"
                )
            )
            Result.success(response)
        } catch (e: Exception){
            Result.failure(e)
        }
    }
}