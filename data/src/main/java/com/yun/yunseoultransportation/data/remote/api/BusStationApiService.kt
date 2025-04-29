package com.yun.yunseoultransportation.data.remote.api

import com.yun.yunseoultransportation.data.model.bus.lowStationByUid.LowStationByUidResponse
import com.yun.yunseoultransportation.data.model.busStation.bustimeByStation.BustimeByStationResponse
import com.yun.yunseoultransportation.data.model.busStation.routeByStation.RouteByStationResponse
import com.yun.yunseoultransportation.data.model.busStation.stationByName.StationByNameResponse
import com.yun.yunseoultransportation.data.model.busStation.stationByPos.StationByPosResponse
import com.yun.yunseoultransportation.data.model.busStation.stationByUid.StationByUidResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BusStationApiService {

    /**
     * 고유번호별 정류소 항목 조회
     * 노선 고유번호에 해당하는 정류소 정보를 조회한다.
     */
    @GET("getStationByUid")
    suspend fun getStationByUid(
        @Query("arsId") arsId: String,
        @Query("serviceKey") serviceKey: String = "nHxMfmtyTjtuCvjAcPez7bDwl+PwLECo/F2/Lp92vVDqrtlW4KTvdmMMqZiXWu5zyrP6ehOEnYoeG6hpdbSA8w==",
        @Query("resultType") resultType: String = "json",
    ): Response<StationByUidResponse>

    /**
     * 명칭별 정류소 목록 조회
     * 검색어가 포함된 정류소 명칭을 조회한다.
     */
    @GET("getStationByName")
    suspend fun getStationByName(
        @Query("stSrch") stSrch: String,
        @Query("serviceKey") serviceKey: String = "nHxMfmtyTjtuCvjAcPez7bDwl+PwLECo/F2/Lp92vVDqrtlW4KTvdmMMqZiXWu5zyrP6ehOEnYoeG6hpdbSA8w==",
        @Query("resultType") resultType: String = "json",
    ): Response<StationByNameResponse>

    /**
     * 좌표 기반 근접 정류소 목록 조회
     * 지정된 좌표와 반경 범위 내의 정류소 목록을 조회한다.
     */
    @GET("getStationByPos")
    suspend fun getStationByPos(
        @Query("tmX") tmX: String,
        @Query("tmY") tmY: String,
        @Query("radius") radius: String,
        @Query("serviceKey") serviceKey: String = "nHxMfmtyTjtuCvjAcPez7bDwl+PwLECo/F2/Lp92vVDqrtlW4KTvdmMMqZiXWu5zyrP6ehOEnYoeG6hpdbSA8w==",
        @Query("resultType") resultType: String = "json",
    ): Response<StationByPosResponse>

    /**
     * 정류소별 경유 노선 목록 조회
     * 고유번호에 해당하는 경유노선목록을 조회한다.
     */
    @GET("getRouteByStation")
    suspend fun getRouteByStation(
        @Query("arsId") arsId: String,
        @Query("serviceKey") serviceKey: String = "nHxMfmtyTjtuCvjAcPez7bDwl+PwLECo/F2/Lp92vVDqrtlW4KTvdmMMqZiXWu5zyrP6ehOEnYoeG6hpdbSA8w==",
        @Query("resultType") resultType: String = "json",
    ): Response<RouteByStationResponse>

    /**
     * 정류소별 노선 첫차/막차 예정시간 목록조회
     * 정류소 고유번호와 노선id에 해당하는 첫차/막차 예정시간을 조회한다.
     */
    @GET("getBustimeByStation")
    suspend fun getBustimeByStation(
        @Query("arsId") arsId: String,
        @Query("busRouteId") busRouteId: String,
        @Query("serviceKey") serviceKey: String = "nHxMfmtyTjtuCvjAcPez7bDwl+PwLECo/F2/Lp92vVDqrtlW4KTvdmMMqZiXWu5zyrP6ehOEnYoeG6hpdbSA8w==",
        @Query("resultType") resultType: String = "json",
    ): Response<BustimeByStationResponse>
}