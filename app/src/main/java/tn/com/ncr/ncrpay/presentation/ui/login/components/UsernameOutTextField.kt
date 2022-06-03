package tn.com.ncr.ncrpay.presentation.ui.login.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import tn.com.ncr.ncrpay.R

@Composable
fun UsernameOutTextField(
    textValue : String,
    onValueChange : (String) -> Unit,
    onNext : (KeyboardActionScope.()-> Unit)
){
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 4.dp),
        value = textValue ,
        onValueChange =onValueChange,
        label = {
            Text(text= stringResource(id = R.string.username))
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
        singleLine = true,
        keyboardActions = KeyboardActions(
            onNext = onNext
        )
    )
}
