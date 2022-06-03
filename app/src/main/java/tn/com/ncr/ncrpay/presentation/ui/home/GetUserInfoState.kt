package tn.com.ncr.ncrpay.presentation.ui.home

import tn.com.ncr.ncrpay.domain.model.Account
import tn.com.ncr.ncrpay.domain.model.User

data class GetUserInfoState(
    val userInfo : User? = null,
    val error : String? = ""
)
