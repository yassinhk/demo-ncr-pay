package tn.com.ncr.ncrpay.presentation.ui.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import tn.com.ncr.ncrpay.common.Constants.ACCOUNT_ID_ARG_KEY
import tn.com.ncr.ncrpay.common.Constants.ACCOUNT_NUMBER_ARG_KEY
import tn.com.ncr.ncrpay.common.Constants.CLIENT_ID_ARG_KEY
import tn.com.ncr.ncrpay.common.Constants.CLIENT_NAME_ARG_KEY
import tn.com.ncr.ncrpay.presentation.ui.history.HistoryScreen
import tn.com.ncr.ncrpay.presentation.ui.home.HomeScreen
import tn.com.ncr.ncrpay.presentation.ui.login.LoginScreen
import tn.com.ncr.ncrpay.presentation.ui.profile.ProfileScreen
import tn.com.ncr.ncrpay.presentation.ui.send.SendScreen
import tn.com.ncr.ncrpay.presentation.ui.splash_screen.SplashScreen
import tn.com.ncr.ncrpay.presentation.ui.withdraw.WithdrawScreen

@Composable
fun SetupNavGraph(navHostController:NavHostController){
    NavHost(
        navController = navHostController ,
        startDestination = Screen.Login.route
    ){
        composable(route = Screen.Splash.route){
            SplashScreen(navHostController = navHostController)
        }
        composable(route= Screen.Login.route){
            LoginScreen(navController =  navHostController)
        }
        composable(
            route = Screen.Home.route,
        ){
            HomeScreen(
                navHostController
            )
        }
        composable(
            route = Screen.Send.route,
            arguments = listOf(
                navArgument(ACCOUNT_ID_ARG_KEY){
                    type = NavType.IntType
                },
                navArgument(ACCOUNT_NUMBER_ARG_KEY){
                    type = NavType.StringType
                }
            )
        ){
            SendScreen(
                navController = navHostController,
                it.arguments?.getInt(ACCOUNT_ID_ARG_KEY),
                it.arguments?.getString(ACCOUNT_NUMBER_ARG_KEY)
            )
        }
        composable(
            route = Screen.History.route,
            arguments = listOf(
                navArgument(ACCOUNT_ID_ARG_KEY){
                    type = NavType.IntType
                }
            )
        ){
            HistoryScreen(
                navController = navHostController,
            )
        }
        composable(
            route = Screen.Withdraw.route,
            arguments = listOf(
                navArgument(ACCOUNT_ID_ARG_KEY){
                    type = NavType.IntType
                },
                navArgument(ACCOUNT_NUMBER_ARG_KEY){
                    type = NavType.StringType
                }
            )
        ){
            WithdrawScreen(
                navController = navHostController,
                it.arguments?.getInt(ACCOUNT_ID_ARG_KEY),
                it.arguments?.getString(ACCOUNT_NUMBER_ARG_KEY)
            )
        }
        composable(
            route = Screen.Profile.route
        ){
            ProfileScreen(navController = navHostController)
        }

    }

}
