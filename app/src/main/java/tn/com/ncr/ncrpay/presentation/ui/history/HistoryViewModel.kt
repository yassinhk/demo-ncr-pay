package tn.com.ncr.ncrpay.presentation.ui.history

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import tn.com.ncr.ncrpay.common.Constants.ACCOUNT_ID_ARG_KEY
import tn.com.ncr.ncrpay.common.Resource
import tn.com.ncr.ncrpay.data.remote.dto.historyDto.toHistory
import tn.com.ncr.ncrpay.domain.use_case.history.GetHistoryUseCase
import javax.inject.Inject


@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val historyUseCase: GetHistoryUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(HistoryState())
    val state : State<HistoryState> = _state

    init{
        savedStateHandle.get<Int>(ACCOUNT_ID_ARG_KEY)?.let { accountId->
            getHistory(accountId = accountId)
        }
       //getHistory(2)
    }

    private fun getHistory(accountId : Int){
        Log.i("history_model", accountId.toString())
        historyUseCase(
            accountId = accountId
        ).onEach { result ->
            when(result){
                is Resource.Success ->{
                    if(result.data?.success==true){
                        _state.value = HistoryState(
                            history = result.data?.data!!.map { it.toHistory() }
                        )
                    }else{
                        _state.value = HistoryState(error = result.data?.message?: "Error")
                    }
                }
                is Resource.Error ->{
                    _state.value = HistoryState(error = result.message?: "An unexpected Error")
                }
                is Resource.Loading -> {
                    _state.value = HistoryState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}
