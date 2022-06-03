package tn.com.ncr.ncrpay.domain.use_case.history

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import tn.com.ncr.ncrpay.common.Resource
import tn.com.ncr.ncrpay.data.remote.dto.historyDto.HistoryDto
import tn.com.ncr.ncrpay.domain.model.History
import tn.com.ncr.ncrpay.domain.repository.AccountRepository
import java.io.IOException
import javax.inject.Inject

class GetHistoryUseCase @Inject constructor(
    private val repository : AccountRepository
){

    operator fun invoke(accountId : Int) : Flow<Resource<HistoryDto>> = flow {
        try{
            emit(Resource.Loading())
            val history = repository.getHistory(accountId = accountId)
            emit(Resource.Success(history))
        }catch (e : HttpException ){
            emit(Resource.Error(e.message()))
        }catch (e : IOException){
            emit(Resource.Error(e.message.toString()))
        }
    }

}
