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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import tn.com.ncr.ncrpay.R

@Composable
fun PasswordOutNextField(
    textValue : String,
    onChangeValue : (String) -> Unit,
    onDone : (KeyboardActionScope.()-> Unit)
){
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 4.dp),
        value = textValue,
        onValueChange = onChangeValue,
        label = {
            Text(text = stringResource(id = R.string.password))
        },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = onDone
        )

    )
}
