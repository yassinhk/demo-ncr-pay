package tn.com.ncr.ncrpay.presentation.ui.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tn.com.ncr.ncrpay.R

@Composable
fun ClientInformation(name : String){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp,24.dp)

    ){
        Column (modifier = Modifier.padding(16.dp)){
            Text(
                modifier = Modifier.padding(vertical = 8.dp),
                text = name.uppercase(),
                color = Color.DarkGray,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
//            Text(
//                modifier = Modifier.padding(0.dp,8.dp),
//                text = stringResource(id = R.string.last_connection) +" 2022-05-12 16:40:15",
//                color = Color.DarkGray,
//                fontSize = 12.sp,
//            )
        }
    }
}
