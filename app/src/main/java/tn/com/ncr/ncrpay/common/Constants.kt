package tn.com.ncr.ncrpay.common

import androidx.datastore.preferences.core.stringPreferencesKey

object Constants {

    const val BASE_URL = "https://ncr-pay-api.herokuapp.com/api/"
    const val CLIENT_ID_ARG_KEY = "clientId"
    const val CLIENT_NAME_ARG_KEY = "clientName"
    const val ACCOUNT_ID_ARG_KEY = "accountId"
    const val ACCOUNT_NUMBER_ARG_KEY = "accountNumber"
    const val TO_HISTORY = "history"
    const val TO_TRANSFER = "transfer"
    const val TO_WITHDRAW= "withdraw"
    const val TO_SEND = "send"
    const val TO_PROFILE = "profile"
    const val TO_LOGOUT = "logout"
    const val USER_PREFERENCES = "users"
    val USER_ID_KEY = stringPreferencesKey("user_id")
    val USER_NAME_KEY = stringPreferencesKey("user_name")
    val USER_LAST_NAME_KEY = stringPreferencesKey("user_last_name")
    val USER_EMAIL_KEY = stringPreferencesKey("user_email")
    val USER_USERNAME_KEY = stringPreferencesKey("user_username")
    val USER_PHONE_KEY = stringPreferencesKey("user_phone")
    val USER_CIN_KEY = stringPreferencesKey("user_cin")
}
