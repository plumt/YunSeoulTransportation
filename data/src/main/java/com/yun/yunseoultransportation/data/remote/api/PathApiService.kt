package com.yun.yunseoultransportation.data.remote.api

import com.yun.yunseoultransportation.domain.model.path.pathInfoByBusNSub.PathInfoByBusNSubResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PathApiService {

    @GET("pathinfo/getPathInfoByBusNSub")
    suspend fun getPathInfoByBusNSub(
        @Query("startX") startX: String,
        @Query("startY") startY: String,
        @Query("endX") endX: String,
        @Query("endY") endY: String,
        @Query("serviceKey") serviceKey: String,
        @Query("resultType") resultType: String,
    ): PathInfoByBusNSubResponse
}