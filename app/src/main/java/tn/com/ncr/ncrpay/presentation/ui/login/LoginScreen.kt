package tn.com.ncr.ncrpay.presentation.ui.login

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import tn.com.ncr.ncrpay.R
import tn.com.ncr.ncrpay.presentation.ui.login.components.*
import tn.com.ncr.ncrpay.presentation.ui.navigation.Screen
import java.math.BigInteger
import java.security.MessageDigest

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(
    navController : NavHostController,
    viewModel : AuthUserViewModel = hiltViewModel()
){
    val context = LocalContext.current
    var username by remember { mutableStateOf("57987456")}
    var password by rememberSaveable { mutableStateOf("user") }
    val focusManager = LocalFocusManager.current



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                if (isSystemInDarkTheme())
                    MaterialTheme.colors.primaryVariant
                else
                    MaterialTheme.colors.primary
            )
    ){
        HeaderLogin()
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                elevation = 10.dp
            ){
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    val state = viewModel.state.value
                    val isBusy = viewModel.isBusy.value
                    when {
                        !isBusy -> {
                            UsernameOutTextField(
                                textValue = username,
                                onValueChange = {
                                    if(it.isDigitsOnly() && it.length<12) username = it
                                },
                                onNext = {focusManager.moveFocus(FocusDirection.Down)}
                            )
                            PasswordOutNextField(
                                textValue = password,
                                onChangeValue = {password = it},
                                onDone = {focusManager.clearFocus()}
                            )
                            ButtonLogin(
                                onclick = {
                                    if(username.isEmpty() || password.isEmpty()){
                                        toast(
                                            context,
                                            context.getString(R.string.error_login_field_empty)
                                        )
                                    }else{
                                        viewModel.authUser(username, crypt(password))
                                    }
                                }
                            )
                        }
                        else-> {
                            when {
                                state.isLoading -> {
                                    ProgressBarCircle()
                                }
                                state.error.isNotBlank() -> {
                                    if(state.error=="Forbidden"){
                                        toast(context = context,
                                            context.resources.getString(R.string.error_login))
                                    }else{
                                        toast(context = context,
                                            "Error")
                                    }
                                }
                                state.user!=null -> {
                                    LaunchedEffect(Unit){
                                        navController.navigate(Screen.Home.route)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

fun crypt(text : String = ""): String {
    val crypt = MessageDigest.getInstance("MD5");
    crypt.update(text.toByteArray());
    return BigInteger(1, crypt.digest()).toString(16)
}

fun toast(context : Context,message : String){
    Toast.makeText(
        context,
        message,
        Toast.LENGTH_LONG
    ).show()
}
