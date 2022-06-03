package tn.com.ncr.ncrpay.presentation.ui.navigation

import tn.com.ncr.ncrpay.common.Constants.ACCOUNT_ID_ARG_KEY
import tn.com.ncr.ncrpay.common.Constants.ACCOUNT_NUMBER_ARG_KEY
import tn.com.ncr.ncrpay.common.Constants.CLIENT_ID_ARG_KEY
import tn.com.ncr.ncrpay.common.Constants.CLIENT_NAME_ARG_KEY

sealed class Screen(val route :String){
    ///{${CLIENT_ID_ARG_KEY}}/{${CLIENT_NAME_ARG_KEY}}
    // fun passUserData(id : Int, name : String) : String{
    //            return "home_screen/${id}/${name}"
    //        }
    object Splash : Screen("splash_screen")
    object Login : Screen("login_screen")
    object Home : Screen("home_screen")
    object Send :Screen("send_screen/{${ACCOUNT_ID_ARG_KEY}}/{${ACCOUNT_NUMBER_ARG_KEY}}")
    object History : Screen("history_screen/{${ACCOUNT_ID_ARG_KEY}}"){
        fun passAccountId(id : Int?): String {
            return "history_screen/${id}"
       }
    }
    object Withdraw : Screen(
        "withdraw_screen/{${ACCOUNT_ID_ARG_KEY}}/{${ACCOUNT_NUMBER_ARG_KEY}}"
    ){
        fun passAccountInformation(id : Int?, number : String?): String {
            return "withdraw_screen/${id}/${number}"
        }
    }
}
