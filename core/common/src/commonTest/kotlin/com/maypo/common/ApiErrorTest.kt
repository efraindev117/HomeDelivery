package com.maypo.common

import kotlin.test.Test
import kotlin.test.assertEquals

class ApiErrorTest {
    @Test
    fun mapsHttpErrorsToSafeUserMessages() {
        assertEquals(
            "Tu sesión no es válida. Inicia sesión nuevamente.",
                ApiError.Unauthorized.toUserMessage(),
        )
        assertEquals(
            "Tu cuenta no tiene permisos para realizar esta acción.",
                ApiError.Forbidden.toUserMessage(),
        )
        assertEquals(
            "Revisa tu conexión a internet e inténtalo nuevamente.",
                ApiError.Network.toUserMessage(),
        )
    }
}
