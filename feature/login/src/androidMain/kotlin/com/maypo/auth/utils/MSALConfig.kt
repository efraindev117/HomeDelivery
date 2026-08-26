package com.maypo.auth.utils

import android.content.Context
import com.microsoft.identity.client.Logger

fun initialize(context: Context) {
    Logger.getInstance().setExternalLogger { tag, logLevel, message, containsPII ->
        println(" $tag, $logLevel, $message")
    }
}