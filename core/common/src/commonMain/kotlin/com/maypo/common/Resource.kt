package com.maypo.common

sealed interface NetworkResult<out T> {

    data class Success<T>(
        val data: T,
    ) : NetworkResult<T>

    data class Failure(
        val error: Throwable,
    ) : NetworkResult<Nothing>
}