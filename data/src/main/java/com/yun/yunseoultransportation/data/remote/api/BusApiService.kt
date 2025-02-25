package com.yun.yunseoultransportation.data.remote.api

import com.yun.yunseoultransportation.domain.model.bus.busPosByRtid.BusPosByRtidResponse
import com.yun.yunseoultransportation.domain.model.bus.busPosByVehId.BusPosByVehIdResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BusApiService {

    // 차량ID로 위치정보를 조회
    @GET("buspos/getBusPosByVehId")
    suspend fun getBusPosByVehId(
        @Query("vehId") vehId: String,
        @Query("serviceKey") serviceKey: String,
        @Query("resultType") resultType: String
    ): BusPosByVehIdResponse

    // 노선ID로 차량들의 위치정보를 조회한다
    @GET("buspos/getBusPosByRtid")
    suspend fun getBusPosByRtid(
        @Query("busRouteId") busRouteId: String,
        @Query("startOrd") startOrd: String,
        @Query("endOrd") endOrd: String,
        @Query("serviceKey") serviceKey: String,
        @Query("resultType") resultType: String
    ): BusPosByRtidResponse
}