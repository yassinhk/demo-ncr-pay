package tn.com.ncr.ncrpay.presentation.ui.home

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import tn.com.ncr.ncrpay.common.Constants
import tn.com.ncr.ncrpay.common.Resource
import tn.com.ncr.ncrpay.domain.use_case.account.GetAccountUseCase
import tn.com.ncr.ncrpay.domain.use_case.account.GetUserInfoUseCase
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val getAccountUseCase: GetAccountUseCase,
    private val getUserInfoUseCase: GetUserInfoUseCase
) : ViewModel(){

    private val _state = mutableStateOf(GetAccountState())
    val state : State<GetAccountState> = _state

    private val _userState = mutableStateOf(GetUserInfoState())
    val userState : State<GetUserInfoState> = _userState

    init{

        getUserInfo()
        _userState.value.userInfo?.let { getAccounts(clientId = it?.idClient) }
    }

    private fun getUserInfo(){
        getUserInfoUseCase().onEach {
            if(it.idClient>0){
                _userState.value = GetUserInfoState(userInfo = it)
            }else{
                _userState.value = GetUserInfoState(error = "user-not-found")
            }

        }.launchIn(viewModelScope)
    }

    private fun getAccounts(clientId : Int){
        getAccountUseCase(clientId = clientId).onEach { result ->
            when (result){

                is Resource.Success ->{
                    _state.value = GetAccountState(accounts = result.data)
                }
                is Resource.Error ->{
                    _state.value = GetAccountState(error = result.message?: "An unexpected Error")
                }
                is Resource.Loading -> {
                    _state.value =  GetAccountState(isLoading = true)
                }
            }

        }.launchIn(viewModelScope)
    }

}
