package com.yun.yunseoultransportation.data.remote.api

import com.yun.yunseoultransportation.data.model.path.locationInfoList.LocationInfoListResponse
import com.yun.yunseoultransportation.data.model.path.pathInfoByBusNSub.PathInfoByBusNSubResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PathApiService {

    // 버스 지하철 환승 경로 조회, 목적지와 출발지의 버스와 지하철을 이용한 최단거리경로를 조회한다
    @GET("pathinfo/getPathInfoByBusNSub")
    suspend fun getPathInfoByBusNSub(
        @Query("startX") startX: String,
        @Query("startY") startY: String,
        @Query("endX") endX: String,
        @Query("endY") endY: String,
        @Query("serviceKey") serviceKey: String = "nHxMfmtyTjtuCvjAcPez7bDwl+PwLECo/F2/Lp92vVDqrtlW4KTvdmMMqZiXWu5zyrP6ehOEnYoeG6hpdbSA8w==",
        @Query("resultType") resultType: String = "json"
    ): PathInfoByBusNSubResponse

    // 출발지/목적지 명칭으로 검색, 검색어가 포함된 POI 정보를 요청한다.
    @GET("pathinfo/getLocationInfo")
    suspend fun getLocationInfoList(
        @Query("stSrch") stSrch: String,
        @Query("serviceKey") serviceKey: String = "nHxMfmtyTjtuCvjAcPez7bDwl+PwLECo/F2/Lp92vVDqrtlW4KTvdmMMqZiXWu5zyrP6ehOEnYoeG6hpdbSA8w==",
        @Query("resultType") resultType: String = "json"
    ): LocationInfoListResponse
}