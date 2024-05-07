package com.jammes.boletimnota10.collections.usuario

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jammes.boletimnota10.collections.domain.usuario.CriarUsuarioAnonimoUseCase
import com.jammes.boletimnota10.collections.domain.usuario.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginDoUsuarioUseCase: LoginUseCase,
    private val criarUsuarioAnonimoUseCase: CriarUsuarioAnonimoUseCase
): ViewModel() {

    fun criarUsuarioAnonimo() {
        val usuario = UUID.randomUUID().toString()
        viewModelScope.launch {
            val criado = criarUsuarioAnonimoUseCase(usuario, "123456")
        }
    }

    fun login(usuario: String, senha: String) {
        viewModelScope.launch {
            val ok = loginDoUsuarioUseCase(usuario, senha)
        }
    }
}