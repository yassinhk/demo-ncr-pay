package tn.com.ncr.ncrpay.domain.repository

import kotlinx.coroutines.flow.Flow
import tn.com.ncr.ncrpay.data.remote.dto.accountDto.AccountDto
import tn.com.ncr.ncrpay.data.remote.dto.accountDto.SendMandatDto
import tn.com.ncr.ncrpay.data.remote.dto.historyDto.HistoryDto
import tn.com.ncr.ncrpay.domain.model.User

interface AccountRepository {

    fun getUserInfo() : Flow<User>

    suspend fun getAccount(clientId : Int) : AccountDto

    suspend fun sendMandat(
        amount : Double,
        phone : String,
        cinReceiver : Int,
        accountId : Int
    ) : SendMandatDto

    suspend fun getHistory(accountId : Int) : HistoryDto

}
