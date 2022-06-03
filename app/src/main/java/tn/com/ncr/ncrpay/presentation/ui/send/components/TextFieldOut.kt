package tn.com.ncr.ncrpay.presentation.ui.send.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun TextFieldOut(
    value : String,
    onChangeValue : (String) -> Unit,
    label :  String,
    keyboardType : KeyboardType,
    onNext : (KeyboardActionScope.()-> Unit),
){
    OutlinedTextField(
        modifier = Modifier.padding(vertical = 8.dp),
        value = value,
        onValueChange = onChangeValue,
        label = {Text(text = label) },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = ImeAction.Next
        ),
        singleLine = true,
        keyboardActions = KeyboardActions(
            onNext = onNext
        ),
    )

}
