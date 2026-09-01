package com.maypo.domain.support

import com.maypo.common.NetworkResult
import com.maypo.common.support.SupportContent
import com.maypo.data.repository.SupportRepository

class GetSupportContentUseCase(
    private val supportRepository: SupportRepository,
) {
    suspend operator fun invoke(): NetworkResult<SupportContent> =
        supportRepository.getSupportContent()
}
