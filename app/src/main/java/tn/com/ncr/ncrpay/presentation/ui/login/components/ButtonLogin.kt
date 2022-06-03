package tn.com.ncr.ncrpay.presentation.ui.login.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tn.com.ncr.ncrpay.R

@Composable
fun ButtonLogin(
    onclick: () -> Unit
){
    Button(
        onClick= onclick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 16.dp)
            .height(50.dp)
            .padding(0.dp)
        ,

    ) {
        Text(
            text = stringResource(id = R.string.login),
            fontSize = 16.sp,
        )
    }
}
