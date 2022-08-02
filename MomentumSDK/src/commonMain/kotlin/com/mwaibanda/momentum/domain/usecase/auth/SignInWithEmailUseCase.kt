package com.mwaibanda.momentum.domain.usecase.auth

import com.mwaibanda.momentum.domain.models.UserResponse
import com.mwaibanda.momentum.domain.repository.AuthRepository
import dev.gitlive.firebase.auth.AuthResult

class SignInWithEmailUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String, onCompletion: (UserResponse) -> Unit){
        onCompletion(authRepository.signInWithEmail(email = email, password = password))
    }
}