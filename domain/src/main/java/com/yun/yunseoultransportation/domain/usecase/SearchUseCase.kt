package com.yun.yunseoultransportation.domain.usecase

import com.yun.yunseoultransportation.domain.repository.SearchRepository
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {

//    suspend fun keywordSearch(keywordSearchRequest: KeywordSearchRequest): Result<KeywordSearchResponse> {
//        return searchRepository.keywordSearch(keywordSearchRequest)
//    }
}