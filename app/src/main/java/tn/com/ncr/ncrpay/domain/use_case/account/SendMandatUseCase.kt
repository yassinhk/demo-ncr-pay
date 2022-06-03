package tn.com.ncr.ncrpay.domain.use_case.account

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import tn.com.ncr.ncrpay.common.Resource
import tn.com.ncr.ncrpay.data.remote.dto.accountDto.SendMandatDto
import tn.com.ncr.ncrpay.domain.repository.AccountRepository
import java.io.IOException
import javax.inject.Inject

class SendMandatUseCase @Inject constructor(
    private val repository: AccountRepository
) {
    operator fun invoke(
        amount: Double,
        phone: String,
        cinReceiver: Int,
        accountId: Int
    ) : Flow<Resource<SendMandatDto>> = flow {
        try{
            emit(Resource.Loading())
            val sendMandat = repository.sendMandat(
                amount = amount,
                phone = phone,
                cinReceiver = cinReceiver,
                accountId = accountId
            )
            emit(Resource.Success(sendMandat))
        }catch (e : HttpException){
            emit(Resource.Error(e.message()))
        }catch (e : IOException){
            emit(Resource.Error("INTERNET ERROR : ${e.message}"))
        }
    }
}
