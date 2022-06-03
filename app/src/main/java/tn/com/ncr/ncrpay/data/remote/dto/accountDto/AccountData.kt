package tn.com.ncr.ncrpay.data.remote.dto.accountDto

import com.google.gson.annotations.SerializedName
import tn.com.ncr.ncrpay.domain.model.Account

data class AccountData(
    @SerializedName("account_number")
    val accountNumber: String,
    @SerializedName("amount_available")
    val amountAvailable: Float,
    @SerializedName("client_code")
    val clientCode: Int,
    @SerializedName("client_id")
    val clientId: Int,
    val currency: String,
    @SerializedName("current_balance")
    val currentBalance: Float,
    @SerializedName("idaccount")
    val idAccount: Int
)

fun AccountData.toAccount() : Account{
    return Account (
        accountNumber = accountNumber,
        amountAvailable = amountAvailable,
        clientCode = clientCode,
        currency = currency,
        currentBalance = currentBalance,
        idAccount = idAccount
    )
}
