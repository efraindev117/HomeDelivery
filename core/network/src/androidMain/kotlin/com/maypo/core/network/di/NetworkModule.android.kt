package com.maypo.core.network.di

import com.maypo.core.network.repository.INetworkAuthDataSource
import com.maypo.core.network.MsalNetworkAuthDataSource
import com.maypo.core.network.R
import com.microsoft.identity.client.PublicClientApplication
import com.microsoft.identity.nativeauth.INativeAuthPublicClientApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual val networkModule: Module = module {
    single<INativeAuthPublicClientApplication>{
        PublicClientApplication.createNativeAuthPublicClientApplication(
            androidContext(), R.raw.auth_config_native_auth
        )
    }

    single<INetworkAuthDataSource>{
        MsalNetworkAuthDataSource(authClient = get())
    }
}
