package com.yun.yunseoultransportation.data.remote.api

import com.yun.yunseoultransportation.domain.model.search.keyworkSearch.KeywordSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApiService {

    @GET("local/search/keyword.json")
    suspend fun keywordSearch(
        @Query("query") query: String,
        @Query("x") x: String?,
        @Query("y") y: String?,
        @Query("radius") radius: Int?,
        @Query("page") page: Int?,
        @Query("size") size: Int?,
        @Query("sort") sort: String?
    ): KeywordSearchResponse
}