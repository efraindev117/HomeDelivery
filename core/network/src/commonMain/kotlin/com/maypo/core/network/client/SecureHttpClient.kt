package com.maypo.core.network.client

import com.maypo.common.ApiError
import com.maypo.common.AppLogger
import com.maypo.common.NetworkResult
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CancellationException
import kotlinx.serialization.json.Json

fun createSecureHttpClient(): HttpClient = HttpClient {
    install(ContentNegotiation) {
        json(
            Json {
                ignoreUnknownKeys = true
                explicitNulls = false
            },
        )
    }
    HttpResponseValidator {
        validateResponse { response ->
            SecureNetworkLogger.response(
                method = response.call.request.method.value,
                path = response.call.request.url.encodedPath,
                statusCode = response.status.value,
            )
        }
    }
}

internal suspend inline fun <reified T> safeApiCall(
    crossinline request: suspend () -> HttpResponse,
): NetworkResult<T> {
    return try {
        val response = request()
        if (response.status.value !in 200..299) {
            NetworkResult.Failure(response.toApiError())
        } else {
            NetworkResult.Success(response.body())
        }
    } catch (error: CancellationException) {
        throw error
    } catch (error: ResponseException) {
        NetworkResult.Failure(error.response.toApiError())
    } catch (_: Exception) {
        SecureNetworkLogger.networkFailure()
        NetworkResult.Failure(ApiError.Network)
    }
}

fun HttpResponse.toApiError(): ApiError {
    return when (status) {
        HttpStatusCode.Unauthorized -> ApiError.Unauthorized
        HttpStatusCode.Forbidden -> ApiError.Forbidden
        HttpStatusCode.TooManyRequests -> ApiError.TooManyRequests
        else -> if (status.value in 500..599) {
            ApiError.Server(status.value)
        } else {
            ApiError.Unknown
        }
    }
}

internal object SecureNetworkLogger {
    fun response(method: String, path: String, statusCode: Int) {
        AppLogger.debug(
            tag = "Network",
            message = "response method=$method path=$path status=$statusCode",
        )
    }

    fun networkFailure() {
        AppLogger.error(
            tag = "Network",
            message = "request failed before receiving a response",
        )
    }
}
