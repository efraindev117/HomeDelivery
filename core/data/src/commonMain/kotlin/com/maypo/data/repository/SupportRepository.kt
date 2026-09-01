package com.maypo.data.repository

import com.maypo.common.NetworkResult
import com.maypo.common.support.SupportContent

interface SupportRepository {
    suspend fun getSupportContent(): NetworkResult<SupportContent>
}
