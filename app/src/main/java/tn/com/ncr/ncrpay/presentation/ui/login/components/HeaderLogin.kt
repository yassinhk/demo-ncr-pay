package tn.com.ncr.ncrpay.presentation.ui.login.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tn.com.ncr.ncrpay.R


@Composable
fun HeaderLogin(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp),
        contentAlignment = Alignment.TopCenter
    ){
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(R.drawable.ic_login_bg),
            contentDescription = "background_image",
            contentScale = ContentScale.FillBounds
        )
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
            contentAlignment = Alignment.Center
        ){
            Row {
                Image(
                    modifier = Modifier
                        .height(100.dp)
                        .width(100.dp),
                    painter = painterResource(R.drawable.logo),
                    contentDescription = "logo"
                )
                Text(
                    text = stringResource(id =  R.string.pay),
                    color = MaterialTheme.colors.primary,
                    style = MaterialTheme.typography.h2,
                    fontFamily = FontFamily(Font(R.font.impact)),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview
@Composable
fun HeaderLoginPreview(){
    HeaderLogin()
}
