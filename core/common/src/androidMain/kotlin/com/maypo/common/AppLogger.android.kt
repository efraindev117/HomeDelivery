package com.maypo.common

import android.util.Log

actual object AppLogger {
    private var isEnabled = false

    actual fun configure(enabled: Boolean) {
        isEnabled = enabled
    }

    actual fun debug(tag: String, message: String) {
        if (isEnabled) Log.d(tag, message)
    }

    actual fun error(tag: String, message: String) {
        if (isEnabled) Log.e(tag, message)
    }
}
