package tn.com.ncr.ncrpay.presentation.ui.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import tn.com.ncr.ncrpay.presentation.ui.navigation.Screen

@Composable
fun TopAppBarLayout(
    navController: NavHostController,
    title : String
){
    TopAppBar (
        title = { Text(text = title)},
        navigationIcon = {
            IconButton(onClick = {
                navController.navigate(Screen.Home.route)
            }) {
                Icon(Icons.Filled.ArrowBack, contentDescription ="back" )
            }
        },

    )
}
