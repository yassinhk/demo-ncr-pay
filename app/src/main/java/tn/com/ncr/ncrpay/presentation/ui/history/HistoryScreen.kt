package tn.com.ncr.ncrpay.presentation.ui.history


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import tn.com.ncr.ncrpay.R
import tn.com.ncr.ncrpay.presentation.ui.components.TopAppBarLayout
import tn.com.ncr.ncrpay.presentation.ui.history.components.HistoryItem
import tn.com.ncr.ncrpay.presentation.ui.login.components.ProgressBarCircle

@Composable
fun HistoryScreen(
    navController: NavHostController,
    viewModel: HistoryViewModel = hiltViewModel()
){
    val state = viewModel.state.value
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopAppBarLayout(
            navController = navController,
            title = stringResource(id = R.string.history)
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp)
        ){

            items(state.history){ historyItem->
                HistoryItem(historyItem = historyItem)
            }
        }
        if(state.isLoading){
            ProgressBarCircle()
        }

        if(state.error.isNotBlank()){
            val message = if(state.error=="no-data"){
                stringResource(id = R.string.no_data)
            }else{
                state.error
            }
            Text(
                text = message,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h5,
                color = Color.Gray
            )
        }

    }
}
