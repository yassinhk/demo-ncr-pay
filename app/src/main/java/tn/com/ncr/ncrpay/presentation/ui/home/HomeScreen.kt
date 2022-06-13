package tn.com.ncr.ncrpay.presentation.ui.home


import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import tn.com.ncr.ncrpay.R
import tn.com.ncr.ncrpay.common.Constants.TO_HISTORY
import tn.com.ncr.ncrpay.common.Constants.TO_LOGOUT
import tn.com.ncr.ncrpay.common.Constants.TO_PROFILE
import tn.com.ncr.ncrpay.common.Constants.TO_SEND
import tn.com.ncr.ncrpay.common.Constants.TO_TRANSFER
import tn.com.ncr.ncrpay.common.Constants.TO_WITHDRAW
import tn.com.ncr.ncrpay.presentation.ui.home.components.ClientInformation
import tn.com.ncr.ncrpay.presentation.ui.login.components.ProgressBarCircle
import tn.com.ncr.ncrpay.presentation.ui.navigation.Screen
import java.util.*


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    //nameClient: String?,
    viewModel : AccountViewModel = hiltViewModel()
) {
    //todo accountNumber incorrect send mandate
    val userState = viewModel.userState.value
    val state = viewModel.state.value
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        when{
            state.isLoading -> {
                ProgressBarCircle()
            }
            state.error.isNotBlank() ->{

                Text(
                    modifier = Modifier.padding(16.dp),
                    text= state.error,
                    color = Color.Black,
                    fontSize =16.sp,
                    textAlign = TextAlign.Center
                )
            }
            state.accounts?.isNotEmpty() == true ->{
                var expanded by remember { mutableStateOf(false) }
                var selectedOptionText by remember {
                    mutableStateOf(state.accounts[0].accountNumber)
                }
                val account = state.accounts.find {
                    it.accountNumber == selectedOptionText
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(275.dp)
                ){
                   
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .background(MaterialTheme.colors.primaryVariant)
                    ) {

                        ExposedDropdownMenuBox(
                            modifier= Modifier
                                .padding(24.dp, 48.dp),
                            expanded = expanded,
                            onExpandedChange = { expanded = !expanded }) {
                            TextField(
                                readOnly = true,
                                value = selectedOptionText,
                                onValueChange = { },
                                label = { Text (stringResource(id = R.string.account_number)) },
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(
                                        expanded = expanded
                                    )
                                },
                                colors = TextFieldDefaults.textFieldColors(
                                    backgroundColor = Color.White
                                )
                            )
                            ExposedDropdownMenu(
                                expanded = expanded,
                                onDismissRequest = {
                                    expanded = false
                                }
                            ) {
                                state.accounts.forEach { selectionOption ->
                                    DropdownMenuItem(
                                        onClick = {
                                            selectedOptionText = selectionOption.accountNumber
                                            expanded = false
                                        }
                                    ) {
                                        Text(text = selectionOption.accountNumber)
                                    }
                                }
                            }
                        }
                    }
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .padding(24.dp, 0.dp)
                            .align(Alignment.BottomCenter),
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Text(
                            modifier = Modifier.padding(16.dp),
                            text= stringResource(id = R.string.balance),
                            color = Color.Black,
                            fontSize =16.sp,
                        )
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            val amount = String.Companion.format(
                                java.util.Locale.FRANCE,
                                "%#,.3f",
                                account?.amountAvailable
                            )
                            Text(
                                text= "$amount ${account?.currency}",
                                color = Color(0xFF686868),
                                fontSize =32.sp,
                                fontFamily = FontFamily(Font(R.font.koulen)),
                            )
                        }
                    }
                }
                userState.userInfo?.let { ClientInformation(name = "${it?.name} ${it?.lastName}") }
                Menu(navController,account!!.idAccount, account.accountNumber)
            }
        }

    }
}

@Composable
fun Menu(navController: NavHostController, idAccount: Int, accountNumber: String) {
    Column(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxHeight(),
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center

        ){
            MenuBoxItem(
                navController,
                R.drawable.ic_history,
                R.string.history,
                idAccount,
                accountNumber,
                TO_HISTORY
            )
//            MenuBoxItem(
//                navController,
//                R.drawable.ic_transfer,
//                R.string.transfer,
//                idAccount ,
//                accountNumber,
//                TO_TRANSFER
//            )
            MenuBoxItem(
                navController,
                R.drawable.ic_withdraw,
                R.string.withdraw,
                idAccount,
                accountNumber,
                TO_WITHDRAW
            )

        }
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center

        ){
            MenuBoxItem(
                navController,
                R.drawable.ic_send_money,
                R.string.send_money,
                idAccount,
                accountNumber,
                TO_SEND
            )
            MenuBoxItem(
                navController,
                R.drawable.ic_person,
                R.string.profile,
                idAccount,
                accountNumber,
                TO_PROFILE
            )
//            MenuBoxItem(
//                navController,
//                R.drawable.ic__logout,
//                R.string.logout,
//                idAccount,
//                accountNumber,
//                TO_LOGOUT
//            )
        }
    }

}

@Composable
fun MenuBoxItem(
    navController: NavHostController,
    drawable : Int,
    text : Int,
    accountId : Int,
    accountNumber: String,
    to : String
){
    Card(modifier = Modifier
        .height(150.dp)
        .width(150.dp)
        .padding(8.dp)
        .clickable{navigate(navController,accountId,accountNumber, to)},
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colors.primary
        ),
        shape = RoundedCornerShape(10.dp)
    ){
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Icon(
                modifier = Modifier
                    .height(50.dp)
                    .width(50.dp),
                painter = painterResource(id = drawable) ,
                contentDescription = stringResource(id = text),
                tint = MaterialTheme.colors.primaryVariant
            )
            Text(
                modifier = Modifier.padding(4.dp),
                text = stringResource(id = text).uppercase(Locale.getDefault()),
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.primaryVariant,
            )
        }
    }
}

fun navigate(
    navController: NavHostController,
    accountId: Int?,
    accountNumber : String?,
    to: String
) {
    when(to){
        TO_HISTORY ->{
            navController.navigate(
                Screen.History.passAccountId(accountId)
            )
        }
        TO_TRANSFER ->{
            Log.i("MENU", "TRANSFER")
        }
        TO_WITHDRAW ->{
            navController.navigate(
                Screen.Withdraw.passAccountInformation(
                    accountId,
                    accountNumber
                )
            )
        }
        TO_SEND ->{
            navController.navigate(
                "send_screen/${accountId}/${accountNumber}"
            )
        }
        TO_PROFILE ->{
            navController.navigate(Screen.Profile.route)
        }
//        TO_LOGOUT->{
//            navController.navigate(Screen.Login.route)
//        }
    }
}

