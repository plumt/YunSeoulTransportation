package com.yun.yunseoultransportation.data.datasource

import com.yun.yunseoultransportation.domain.model.search.keyworkSearch.KeywordSearchRequest
import com.yun.yunseoultransportation.domain.model.search.keyworkSearch.KeywordSearchResponse

interface SearchDataSource {
    suspend fun keywordSearch(keywordSearchRequest: KeywordSearchRequest): KeywordSearchResponse
}