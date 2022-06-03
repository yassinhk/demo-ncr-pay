package tn.com.ncr.ncrpay.presentation.ui.withdraw

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import tn.com.ncr.ncrpay.R
import tn.com.ncr.ncrpay.presentation.ui.components.AccountNumber
import tn.com.ncr.ncrpay.presentation.ui.components.TopAppBarLayout
import tn.com.ncr.ncrpay.presentation.ui.home.AccountViewModel
import tn.com.ncr.ncrpay.presentation.ui.login.components.ProgressBarCircle
import tn.com.ncr.ncrpay.presentation.ui.navigation.Screen
import tn.com.ncr.ncrpay.presentation.ui.send.SendMandatViewModel
import tn.com.ncr.ncrpay.presentation.ui.send.components.TextFieldOut

@Composable
fun WithdrawScreen(
    navController: NavHostController,
    accountId : Int?,
    accountNumber : String?,
    accountViewModel: AccountViewModel = hiltViewModel(),
    sendMandateViewModel: SendMandatViewModel = hiltViewModel()
){
    val context =  LocalContext.current
    var amount by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val user = accountViewModel.userState.value.userInfo

    if(sendMandateViewModel.isBusy.value){
        val state = sendMandateViewModel.state.value
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
                Column(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    TopAppBarLayout(
                        navController = navController,
                        title = stringResource(id = R.string.withdraw)
                    )
                    AccountNumber(accountNumber = accountNumber)
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp, vertical = 12.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = stringResource(id = R.string.your_code),
                                modifier = Modifier.padding(12.dp).fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                            sendMandateViewModel.state.value.sendMandat?.let {
                                Text(
                                    modifier = Modifier.padding(24.dp).fillMaxWidth(),
                                    textAlign = TextAlign.Center,
                                    text= it?.otp,
                                    style = MaterialTheme.typography.h4
                                )
                            }
                        }
                    }
                }
            }
        }
    }else {
        LaunchedEffect(Unit) {
            if (sendMandateViewModel.state.value.sendMandat?.success == false) {
                Toast.makeText(
                    context,
                    sendMandateViewModel.state.value.sendMandat?.message,
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            TopAppBarLayout(
                navController = navController,
                title = stringResource(id = R.string.withdraw)
            )
            AccountNumber(accountNumber = accountNumber)
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 12.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    TextFieldOut(
                        value = amount,
                        onChangeValue = {
                            amount = it
                        },
                        label = stringResource(id = R.string.amount),
                        keyboardType = KeyboardType.Number,
                        onNext = { focusManager.clearFocus() }
                    )
                    Button(
                        onClick = {
                            accountId?.let {
                                user?.let { user ->
                                    sendMandateViewModel.sendMandat(
                                        accountId = it,
                                        amount = amount.toDouble(),
                                        cinReceiver = user.cin,
                                        phone = user.phone
                                    )
                                }
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
