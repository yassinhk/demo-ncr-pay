package tn.com.ncr.ncrpay.common

import android.content.Context
import androidx.datastore.preferences.core.edit

import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import tn.com.ncr.ncrpay.common.Constants.USER_CIN_KEY
import tn.com.ncr.ncrpay.common.Constants.USER_EMAIL_KEY
import tn.com.ncr.ncrpay.common.Constants.USER_ID_KEY
import tn.com.ncr.ncrpay.common.Constants.USER_LAST_NAME_KEY
import tn.com.ncr.ncrpay.common.Constants.USER_NAME_KEY
import tn.com.ncr.ncrpay.common.Constants.USER_PHONE_KEY
import tn.com.ncr.ncrpay.common.Constants.USER_PREFERENCES
import tn.com.ncr.ncrpay.common.Constants.USER_USERNAME_KEY
import tn.com.ncr.ncrpay.domain.model.User

import javax.inject.Inject

class StoreData @Inject constructor(
    private val context : Context
) {
    private val Context.dataStore by preferencesDataStore(name = USER_PREFERENCES)

    suspend fun saveUser(user : User){
        context.dataStore.edit { preferences ->
            preferences[USER_ID_KEY] = user.idClient.toString()
            preferences[USER_NAME_KEY] = user.name
            preferences[USER_LAST_NAME_KEY] = user.lastName
            preferences[USER_EMAIL_KEY] = user.email
            preferences[USER_PHONE_KEY] = user.phone
            preferences[USER_USERNAME_KEY] = user.username
            preferences[USER_CIN_KEY] = user.cin.toString()
        }
    }

    fun getUserInfo() : Flow<User> = context.dataStore.data.map { preferences->
        User(
            cin = preferences[USER_CIN_KEY]?.toInt() ?: -1,
            idClient = preferences[USER_ID_KEY]?.toInt() ?: -1,
            name = preferences[USER_NAME_KEY] ?: "",
            lastName = preferences[USER_LAST_NAME_KEY] ?: "",
            phone = preferences[USER_PHONE_KEY] ?: "",
            email = preferences[USER_EMAIL_KEY] ?: "",
            username = preferences[USER_USERNAME_KEY] ?: ""
        )
    }

}
