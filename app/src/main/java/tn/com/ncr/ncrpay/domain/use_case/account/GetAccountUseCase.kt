package tn.com.ncr.ncrpay.domain.use_case.account

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import tn.com.ncr.ncrpay.common.Resource
import tn.com.ncr.ncrpay.data.remote.dto.accountDto.toAccount
import tn.com.ncr.ncrpay.domain.model.Account
import tn.com.ncr.ncrpay.domain.repository.AccountRepository
import java.io.IOException
import javax.inject.Inject

class GetAccountUseCase @Inject constructor(
    private val repository : AccountRepository
) {
    operator fun invoke(clientId : Int) : Flow<Resource<List<Account>>> = flow{
        try{
            emit(Resource.Loading())
            val accounts = repository.getAccount(clientId = clientId).data.map { it.toAccount() }
            emit(Resource.Success(accounts))
        }catch (e : HttpException){
            emit(Resource.Error(e.message()))
        }catch (e : IOException){
            emit(Resource.Error("INTERNET ERROR : ${e.message}"))
        }
    }
}
