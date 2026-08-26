package com.maypo.auth.auth

import android.content.Context
import com.maypo.auth.R
import com.microsoft.identity.client.PublicClientApplication
import com.microsoft.identity.nativeauth.INativeAuthPublicClientApplication

class MicrosoftAuthClient(context: Context) {
    val authClient : INativeAuthPublicClientApplication =
        PublicClientApplication.createNativeAuthPublicClientApplication(context.applicationContext,
       R.raw.auth_config_native_auth
    )
}