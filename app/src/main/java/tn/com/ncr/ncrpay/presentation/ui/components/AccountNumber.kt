package tn.com.ncr.ncrpay.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tn.com.ncr.ncrpay.R

@Composable
fun AccountNumber(
    accountNumber : String?
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .padding(start = 24.dp, end = 24.dp, top = 24.dp),
    ) {
        Box(
            modifier = Modifier
                .background(Color.White),
            contentAlignment = Alignment.Center
        ){
            Text(
                text ="${stringResource(id = R.string.number)} $accountNumber",
                fontSize = 22.sp
            )
        }
    }
}
