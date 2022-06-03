package tn.com.ncr.ncrpay.presentation.ui.send

import tn.com.ncr.ncrpay.data.remote.dto.accountDto.SendMandatDto
import tn.com.ncr.ncrpay.domain.model.Account

data class SendMandatState (
    val isLoading : Boolean = false,
    val sendMandat : SendMandatDto? = null,
    val error : String = ""
)
