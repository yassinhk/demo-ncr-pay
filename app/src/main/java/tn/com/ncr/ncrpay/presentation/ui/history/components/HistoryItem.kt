package tn.com.ncr.ncrpay.presentation.ui.history.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tn.com.ncr.ncrpay.domain.model.History


@Composable
fun HistoryItem(
    historyItem : History
){

    val bgColor : Color

    when(historyItem.state){
        "A" ->{
            bgColor = MaterialTheme.colors.primaryVariant
        }
        "E" ->{
            bgColor = Color(0xFFFF8D29)
        }
        "S" -> {
            bgColor = Color(0xFF2155CD)
        }
        else  ->{
            bgColor = Color(0xFFF32424)
        }

    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        contentAlignment = Alignment.CenterStart
    ){
        Row(
            modifier = Modifier
                .padding(12.dp)
                .height(50.dp)
        ) {
            Box(
                modifier = Modifier
                    .background(bgColor)
                    .fillMaxHeight()
                    .width(50.dp)
                    .clip(CircleShape),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = historyItem.state,
                    fontSize = 22.sp,
                    color = Color.White
                )
            }
           Row(
               modifier = Modifier.fillMaxWidth(),
               horizontalArrangement = Arrangement.SpaceBetween
           ){
               Column (
                   modifier = Modifier
                       .padding(horizontal = 12.dp)
                       .fillMaxHeight(),
                   verticalArrangement = Arrangement.SpaceAround
               ){
                   Text(
                       text = historyItem.code,
                       style = MaterialTheme.typography.h5
                   )
                   Text(
                       text = historyItem.cinRecepteur.toString(),
                       style = MaterialTheme.typography.caption
                   )
               }
               Column (
                   modifier = Modifier
                       .padding(horizontal = 12.dp)
                       .fillMaxHeight(),
                   verticalArrangement = Arrangement.SpaceAround,
                   horizontalAlignment = Alignment.End
               ){
                   val amount = String.Companion.format(
                       java.util.Locale.FRANCE,
                       "%#,.3f",
                       historyItem.montant
                   )
                   Text(
                       //todo get currency from account information
                       text = "$amount TND",
                       style = MaterialTheme.typography.body1
                   )
                   Text(
                       text = historyItem.createdAt.substring(0,10),
                       style = MaterialTheme.typography.caption,
                       color = Color.Gray,
                       fontSize = 10.sp,
                       textAlign = TextAlign.End
                   )
               }
           }
        }
    }

}
