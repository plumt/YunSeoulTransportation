package com.yun.yunseoultransportation.domain.repository

import com.yun.yunseoultransportation.domain.model.search.keyworkSearch.KeywordSearchRequest
import com.yun.yunseoultransportation.domain.model.search.keyworkSearch.KeywordSearchResponse

interface SearchRepository {

    suspend fun keywordSearch(keywordSearchRequest: KeywordSearchRequest): Result<KeywordSearchResponse>
}