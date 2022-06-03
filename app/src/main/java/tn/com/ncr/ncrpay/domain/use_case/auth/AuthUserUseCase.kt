package tn.com.ncr.ncrpay.domain.use_case.auth

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import tn.com.ncr.ncrpay.common.Resource
import tn.com.ncr.ncrpay.data.remote.dto.userDto.toUser
import tn.com.ncr.ncrpay.domain.model.Account
import tn.com.ncr.ncrpay.domain.model.User
import tn.com.ncr.ncrpay.domain.repository.UserRepository
import java.io.IOException
import javax.inject.Inject

class AuthUserUseCase @Inject constructor(
    private val repository: UserRepository
){

    operator fun invoke(username: String, password: String):
            Flow<Resource<User>> = flow{
        try{
            emit(Resource.Loading())
            val user = repository.auth(username, password).toUser()
            repository.saveUserInfo(user)
            emit(Resource.Success(user))
        }catch (e : HttpException){
            emit(Resource.Error(e.message()))
        }catch (e : IOException){
            emit(Resource.Error("INTERNET ERROR : ${e.message}"))
        }
    }
}
