package com.yun.yunseoultransportation.domain.usecase

import com.yun.yunseoultransportation.domain.model.path.pathInfoByBusNSub.PathInfoByBusNSubResponse
import com.yun.yunseoultransportation.domain.repository.PathRepository
import javax.inject.Inject

class PathUseCase @Inject constructor(
    private val pathRepository: PathRepository
) {
    suspend fun getPathInfoByBusNSub(
        startX: String,
        startY: String,
        endX: String,
        endY: String
    ): Result<PathInfoByBusNSubResponse> {
        return pathRepository.getPathInfoByBusNSub(startX, startY, endX, endY)
    }
}