package tn.com.ncr.ncrpay.presentation.ui.home

import tn.com.ncr.ncrpay.domain.model.Account
import tn.com.ncr.ncrpay.domain.model.User

data class GetAccountState (
    val isLoading : Boolean = false,
    val accounts : List<Account>? = null,
    val error : String = ""
)
