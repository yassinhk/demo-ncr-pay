package tn.com.ncr.ncrpay.data.repository

import kotlinx.coroutines.flow.Flow
import tn.com.ncr.ncrpay.common.StoreData
import tn.com.ncr.ncrpay.data.remote.NcrPayApi
import tn.com.ncr.ncrpay.data.remote.dto.accountDto.AccountDto
import tn.com.ncr.ncrpay.data.remote.dto.accountDto.SendMandatDto
import tn.com.ncr.ncrpay.data.remote.dto.accountDto.SendMandatRequest
import tn.com.ncr.ncrpay.data.remote.dto.historyDto.HistoryDto
import tn.com.ncr.ncrpay.domain.model.User
import tn.com.ncr.ncrpay.domain.repository.AccountRepository
import javax.inject.Inject

class AccountRepositoryImplementation @Inject constructor(
    private val ncrPayApi: NcrPayApi,
    private val dataStore: StoreData
) : AccountRepository{

    override fun getUserInfo(): Flow<User> {
        return dataStore.getUserInfo()
    }

    override suspend fun getAccount(clientId : Int):AccountDto {
        return ncrPayApi.getAccount(clientId = clientId)
    }

    override suspend fun sendMandat(
        amount: Double,
        phone: String,
        cinReceiver: Int,
        accountId: Int
    ): SendMandatDto {
        return ncrPayApi.sendMandat(
            SendMandatRequest(
                amount = amount,
                phone = phone,
                cinReceiver = cinReceiver,
                accountid = accountId
            )
        )
    }

    override suspend fun getHistory(accountId: Int): HistoryDto {
        return ncrPayApi.getHistory(accountId = accountId)
    }
}
