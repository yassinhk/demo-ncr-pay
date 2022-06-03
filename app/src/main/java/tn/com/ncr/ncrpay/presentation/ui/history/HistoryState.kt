package tn.com.ncr.ncrpay.presentation.ui.history

import tn.com.ncr.ncrpay.domain.model.History

data class HistoryState(
    val isLoading : Boolean = false,
    val history : List<History> = emptyList(),
    val error : String = ""
)
