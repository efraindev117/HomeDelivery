package com.maypo.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.maypo.common.AuthResult
import com.maypo.common.NetworkResult
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AuthScreen() {
    val mViewModel: AuthViewModel = koinViewModel()
    val isLoading by mViewModel.isLoading.collectAsState()

    LaunchedEffect(Unit){
        mViewModel.authResult.collect { result ->
            when(result) {
                is NetworkResult.Success<*> -> {
                    when(result.data){
                        AuthResult.CodeRequired -> {

                        }

                        AuthResult.Authenticated -> {

                        }

                        AuthResult.CodeResent -> {

                        }

                        AuthResult.SignedOut -> {

                        }
                    }
                }
                is NetworkResult.Failure -> {

                }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        content = {
            Button(
                enabled = !isLoading,
                onClick = {
                mViewModel.signIn("")
            }, content = {
                Text(modifier = Modifier,
                    text = if (isLoading){
                    "Comprobando"
                }else{
                    "Iniciar sesión"
                })
            })
        }
    )

}