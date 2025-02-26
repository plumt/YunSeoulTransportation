package com.yun.yunseoultransportation.data.repository

import com.yun.yunseoultransportation.data.datasource.SearchDataSource
import com.yun.yunseoultransportation.domain.model.search.keyworkSearch.KeywordSearchRequest
import com.yun.yunseoultransportation.domain.model.search.keyworkSearch.KeywordSearchResponse
import com.yun.yunseoultransportation.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchDataSource: SearchDataSource
) : SearchRepository{

    override suspend fun keywordSearch(keywordSearchRequest: KeywordSearchRequest): Result<KeywordSearchResponse> {
        return try {
            val response = searchDataSource.keywordSearch(keywordSearchRequest)
            Result.success(response)
        } catch (e: Exception){
            Result.failure(e)
        }
    }
}