package tn.com.ncr.ncrpay.data.remote.dto.accountDto

data class SendMandatRequest(
    val amount : Double,
    val phone : String,
    val cinReceiver : Int,
    val accountid : Int
)
