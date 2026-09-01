package com.maypo.common

sealed interface ApiError {
    data object Unauthorized : ApiError

    data object Forbidden : ApiError

    data object TooManyRequests : ApiError

    data class Server(
        val statusCode: Int,
    ) : ApiError

    data class Authentication(
        val reason: String,
    ) : ApiError

    data object Network : ApiError

    data object Unknown : ApiError
}

fun ApiError.toUserMessage(): String = when (this) {
    is ApiError.Unauthorized -> "Tu sesión no es válida. Inicia sesión nuevamente."
    is ApiError.Forbidden -> "Tu cuenta no tiene permisos para realizar esta acción."
    is ApiError.TooManyRequests -> "Hay demasiadas solicitudes. Inténtalo más tarde."
    is ApiError.Server -> "El servicio no está disponible. Inténtalo más tarde."
    is ApiError.Authentication -> "No fue posible completar la autenticación."
    is ApiError.Network -> "Revisa tu conexión a internet e inténtalo nuevamente."
    is ApiError.Unknown -> "Ocurrió un error inesperado. Inténtalo nuevamente."
}
