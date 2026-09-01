package com.maypo.data.repository

import com.maypo.common.NetworkResult
import com.maypo.common.support.SupportContent
import com.maypo.data.datasource.SupportDataSource

class SupportRepositoryImpl(
    private val supportDataSource: SupportDataSource,
) : SupportRepository {

    override suspend fun getSupportContent(): NetworkResult<SupportContent> =
        supportDataSource.getSupportContent()
}
