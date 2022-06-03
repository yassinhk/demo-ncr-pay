package tn.com.ncr.ncrpay.presentation.ui.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import tn.com.ncr.ncrpay.common.Resource
import tn.com.ncr.ncrpay.domain.use_case.auth.AuthUserUseCase
import javax.inject.Inject

@HiltViewModel
class AuthUserViewModel @Inject constructor(
    private val authUserUseCase: AuthUserUseCase
) : ViewModel() {

    private val _state = mutableStateOf (AuthUserState())
    val state : State<AuthUserState> = _state

    private val _isBusy = mutableStateOf(false)
    val isBusy : State<Boolean> = _isBusy

    fun authUser(username: String, password: String) {
        authUserUseCase(username , password).onEach { result ->
            when(result){
                is Resource.Success ->{
                    _isBusy.value = true
                    _state.value = AuthUserState(user = result.data)
                }
                is Resource.Error ->{
                    _state.value = AuthUserState(error = result.message?: "An unexpected Error")
                    _isBusy.value = false
                }
                is Resource.Loading -> {
                    _state.value =  AuthUserState(isLoading = true)
                    _isBusy.value = true
                }
            }
        }.launchIn(viewModelScope)
    }


}
