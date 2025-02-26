package com.yun.yunseoultransportation.data.datasource

import com.yun.yunseoultransportation.data.remote.api.SearchApiService
import com.yun.yunseoultransportation.domain.model.search.keyworkSearch.KeywordSearchRequest
import com.yun.yunseoultransportation.domain.model.search.keyworkSearch.KeywordSearchResponse
import javax.inject.Inject

class SearchDataSourceImpl @Inject constructor(
    private val searchApiService: SearchApiService
) : SearchDataSource {

    override suspend fun keywordSearch(keywordSearchRequest: KeywordSearchRequest): KeywordSearchResponse {
        return  searchApiService.keywordSearch(
            query = keywordSearchRequest.query,
            x = keywordSearchRequest.x,
            y = keywordSearchRequest.y,
            radius = keywordSearchRequest.radius,
            page = keywordSearchRequest.page,
            size = keywordSearchRequest.size,
            sort = keywordSearchRequest.sort
        )
    }
}