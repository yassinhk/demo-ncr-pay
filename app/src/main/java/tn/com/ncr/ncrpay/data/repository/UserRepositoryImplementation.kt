package tn.com.ncr.ncrpay.data.repository

import tn.com.ncr.ncrpay.common.StoreData
import tn.com.ncr.ncrpay.data.remote.NcrPayApi
import tn.com.ncr.ncrpay.data.remote.dto.userDto.RequestBody
import tn.com.ncr.ncrpay.data.remote.dto.userDto.UserDto
import tn.com.ncr.ncrpay.domain.model.User
import tn.com.ncr.ncrpay.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImplementation @Inject constructor(
    private val api : NcrPayApi,
    private val dataStore : StoreData
) : UserRepository{
    override suspend fun auth(username: String, password: String): UserDto {
        val requestBody = RequestBody(username = username, password = password)
        return api.auth(requestBody = requestBody)
    }

    override suspend fun saveUserInfo(user: User) {
        dataStore.saveUser(user = user)
    }
}

