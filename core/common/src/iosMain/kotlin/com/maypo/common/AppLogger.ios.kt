package com.maypo.common

actual object AppLogger {
    private var isEnabled = false

    actual fun configure(enabled: Boolean) {
        isEnabled = enabled
    }

    actual fun debug(tag: String, message: String) {
        if (isEnabled) println("$tag: $message")
    }

    actual fun error(tag: String, message: String) {
        if (isEnabled) println("$tag: $message")
    }
}
