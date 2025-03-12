package com.yun.yunseoultransportation.data.remote.api

import com.yun.yunseoultransportation.domain.model.bus.busPosByRtid.BusPosByRtidResponse
import com.yun.yunseoultransportation.domain.model.bus.busPosByVehId.BusPosByVehIdResponse
import com.yun.yunseoultransportation.domain.model.bus.busRouteList.BusRouteListResponse
import com.yun.yunseoultransportation.domain.model.bus.routePath.RoutePathResponse
import com.yun.yunseoultransportation.domain.model.bus.staionByRoute.StaionByRouteResponse
import retrofit2.http.GET
import retrofit2.http.Headers
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
    @Headers("Cache-Control: no-cache")
    suspend fun getBusPosByRtid(
        @Query("busRouteId") busRouteId: String,
        @Query("startOrd") startOrd: String,
        @Query("endOrd") endOrd: String,
        @Query("serviceKey") serviceKey: String,
        @Query("resultType") resultType: String
    ): BusPosByRtidResponse

    // 노선번호에 해당하는 노선 목록 조회
    @GET("busRouteInfo/getBusRouteList")
    suspend fun getBusRouteList(
        @Query("strSrch") strSrch: String,
        @Query("serviceKey") serviceKey: String,
        @Query("resultType") resultType: String
    ): BusRouteListResponse

    // 노선번호에 해당하는 지도상 경로 목록을 조회
    @GET("busRouteInfo/getRoutePath")
    suspend fun getRoutePath(
        @Query("busRouteId") busRouteId: String,
        @Query("serviceKey") serviceKey: String,
        @Query("resultType") resultType: String
    ): RoutePathResponse

    @GET("busRouteInfo/getStaionByRoute")
    suspend fun getStaionByRoute(
        @Query("busRouteId") busRouteId: String,
        @Query("serviceKey") serviceKey: String,
        @Query("resultType") resultType: String
    ): StaionByRouteResponse
}