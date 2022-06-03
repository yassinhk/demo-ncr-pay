package tn.com.ncr.ncrpay.domain.model

data class Account(
    val accountNumber: String,
    val amountAvailable: Float,
    val clientCode: Int,
    val currency: String,
    val currentBalance: Float,
    val idAccount: Int
)
