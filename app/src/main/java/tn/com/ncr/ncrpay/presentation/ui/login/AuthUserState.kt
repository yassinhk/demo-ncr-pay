package tn.com.ncr.ncrpay.presentation.ui.login

import tn.com.ncr.ncrpay.domain.model.User

data class AuthUserState(
    val isLoading : Boolean = false,
    val user : User? = null,
    val error : String = ""
)
