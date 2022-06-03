package tn.com.ncr.ncrpay.presentation.ui.send

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import tn.com.ncr.ncrpay.common.Resource
import tn.com.ncr.ncrpay.domain.use_case.account.SendMandatUseCase
import javax.inject.Inject

@HiltViewModel
class SendMandatViewModel @Inject constructor(
    private val sendMandatUseCase: SendMandatUseCase
) : ViewModel(){

    private val _state = mutableStateOf(SendMandatState())
    val state : State<SendMandatState> = _state

    private val _isBusy = mutableStateOf(false)
    val isBusy: State<Boolean> = _isBusy

    fun sendMandat(
        amount: Double,
        phone: String,
        cinReceiver: Int,
        accountId: Int
    ){

        sendMandatUseCase(
            accountId = accountId,
            amount = amount,
            phone = phone,
            cinReceiver = cinReceiver
        ).onEach { result ->
            when (result){

                is Resource.Success ->{
                    _state.value = SendMandatState(sendMandat = result.data)
                    _isBusy.value = _state.value.sendMandat?.success != false
                }
                is Resource.Error ->{
                    _state.value = SendMandatState(error = result.message?: "An unexpected Error")
                    _isBusy.value=false
                }
                is Resource.Loading -> {
                    _state.value =  SendMandatState(isLoading = true)
                    _isBusy.value=true
                }
            }
        }.launchIn(viewModelScope)
    }

}
