package tn.com.ncr.ncrpay.presentation.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import tn.com.ncr.ncrpay.domain.model.User
import tn.com.ncr.ncrpay.domain.repository.UserRepository
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel(){

    private val _user = MutableLiveData<User>()
    val user : LiveData<User> = _user

    init{
        getUserInfo()
    }

    private fun getUserInfo(){
        userRepository.getUserInfo().onEach {
            _user.value = it
        }.launchIn(viewModelScope)
    }

}
