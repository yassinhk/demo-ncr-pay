package tn.com.ncr.ncrpay.presentation.ui.login.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ProgressBarCircle(
    modifier: Modifier = Modifier,
    ) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(0.85f),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colors.primaryVariant,
            strokeWidth = 5.dp,
            modifier = modifier.size(60.dp)
        )
    }
}
