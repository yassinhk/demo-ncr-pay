package tn.com.ncr.ncrpay.domain.repository

import kotlinx.coroutines.flow.Flow
import tn.com.ncr.ncrpay.data.remote.dto.userDto.UserDto
import tn.com.ncr.ncrpay.domain.model.User

interface UserRepository {

    suspend fun auth(username: String, password: String): UserDto
    suspend fun saveUserInfo(user : User)
    fun getUserInfo()  : Flow<User>

}
