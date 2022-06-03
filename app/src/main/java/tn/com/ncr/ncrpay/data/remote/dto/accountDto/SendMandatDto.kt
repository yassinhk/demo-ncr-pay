package tn.com.ncr.ncrpay.data.remote.dto.accountDto

data class SendMandatDto(
    val code: String,
    val message: String,
    val otp: String,
    val success: Boolean
)
