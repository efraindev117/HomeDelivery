package com.maypo.domain.auth

class AuthUsesCase(
    val sigIn: SignInUseCase,
    val submitCode: SubmitCodeUseCase,
    val resendCode:ResendCodeUseCase,
    val signOut: SignOutUseCase
)