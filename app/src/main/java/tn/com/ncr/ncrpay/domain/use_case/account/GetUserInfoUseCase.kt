package tn.com.ncr.ncrpay.domain.use_case.account

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import tn.com.ncr.ncrpay.common.Resource
import tn.com.ncr.ncrpay.domain.model.User
import tn.com.ncr.ncrpay.domain.repository.AccountRepository
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(
    private val repository: AccountRepository
) {

    operator fun invoke() : Flow<User> = repository.getUserInfo()

}
