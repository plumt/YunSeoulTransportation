package com.yun.yunseoultransportation.data.remote.api

import com.yun.yunseoultransportation.domain.model.bus.busPosByVehId.BusPosByVehIdResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BusApiService {

    @GET("buspos/getBusPosByVehId")
    suspend fun getBusPosByVehId(
        @Query("vehId") vehId: String,
        @Query("serviceKey") serviceKey: String,
        @Query("resultType") resultType: String
    ): BusPosByVehIdResponse
}