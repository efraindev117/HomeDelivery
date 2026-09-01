package com.maypo.data.datasource

import com.maypo.common.NetworkResult
import com.maypo.common.support.SupportContent

interface SupportDataSource {
    suspend fun getSupportContent(): NetworkResult<SupportContent>
}
