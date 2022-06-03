package tn.com.ncr.ncrpay.presentation.ui.send

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import tn.com.ncr.ncrpay.R
import tn.com.ncr.ncrpay.presentation.ui.components.AccountNumber
import tn.com.ncr.ncrpay.presentation.ui.components.TopAppBarLayout
import tn.com.ncr.ncrpay.presentation.ui.login.components.ProgressBarCircle
import tn.com.ncr.ncrpay.presentation.ui.navigation.Screen
import tn.com.ncr.ncrpay.presentation.ui.send.components.TextFieldOut


@Composable
fun SendScreen(
    navController: NavHostController,
    accountId : Int?,
    accountNumber: String?,
    viewModel: SendMandatViewModel = hiltViewModel()
){

    val context =  LocalContext.current
    var amount by remember { mutableStateOf("")}

    var phone by remember { mutableStateOf("")}
    var cinReceiver  by remember { mutableStateOf("")}
        val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBarLayout(
            navController = navController,
            title = stringResource(id = R.string.send_mandat)
        )
        AccountNumber(accountNumber = accountNumber)
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 12.dp)
        ){
            if(viewModel.isBusy.value){
                val state = viewModel.state.value
                when {
                    state.isLoading -> {
                        ProgressBarCircle()
                    }
                    state.error.isNotBlank() -> {
                        Toast.makeText(
                            context,
                            state.error,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    else -> {
                        val message = stringResource(id = R.string.send_mandat_success)
                        LaunchedEffect(Unit){
                                Toast.makeText(
                                    context,
                                    message,
                                    Toast.LENGTH_LONG
                                ).show()
                            navController.navigate(Screen.Home.route)
                            }
                    }
                }
            }else{
                LaunchedEffect(Unit) {
                    if (viewModel.state.value.sendMandat?.success == false) {
                        Toast.makeText(
                            context,
                            viewModel.state.value.sendMandat?.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

                Column(modifier = Modifier.padding(16.dp)) {
                    TextFieldOut(
                        value = amount,
                        onChangeValue = {
                            amount = it
                        },
                        label = stringResource(id = R.string.amount),
                        keyboardType = KeyboardType.Number,
                        onNext= {focusManager.moveFocus(FocusDirection.Down)}
                    )
                    TextFieldOut(
                        value = cinReceiver,
                        onChangeValue = {if (it.isDigitsOnly()) cinReceiver = it},
                        label = stringResource(id = R.string.receiver_identity_number),
                        keyboardType = KeyboardType.Number,
                        onNext = {focusManager.moveFocus(FocusDirection.Down)}
                    )
                    Row(){
                        OutlinedTextField(
                            modifier = Modifier.width(80.dp)
                                .padding(0.dp,16.dp,8.dp,0.dp),
                            value = "+216",
                            onValueChange = { "+216" },
                            enabled = false
                        )
                        TextFieldOut(
                            value = phone,
                            onChangeValue = {if(it.isDigitsOnly() && it.length<=12) phone = it},
                            label = stringResource(id = R.string.phone_number),
                            keyboardType = KeyboardType.Number,
                            onNext = {focusManager.clearFocus()},
                        )
                    }

                    //todo add list country code phone number
                    Button(
                        onClick = {
                            accountId?.let {
                                viewModel.sendMandat(
                                    accountId = it,
                                    amount = amount.toDouble(),
                                    cinReceiver = cinReceiver.toInt(),
                                    phone = "+216${phone}"
                                )
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 32.dp)
                            .height(50.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.send).uppercase()
                        )
                    }
                }
            }
        }
    }
}
