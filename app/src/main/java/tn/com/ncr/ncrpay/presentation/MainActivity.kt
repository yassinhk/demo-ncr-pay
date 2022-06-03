package tn.com.ncr.ncrpay.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import tn.com.ncr.ncrpay.presentation.ui.navigation.SetupNavGraph

import tn.com.ncr.ncrpay.presentation.ui.theme.NCRPayTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NCRPayTheme {
                val navController = rememberNavController()
                SetupNavGraph(navHostController = navController)
            }
        }
    }
}

@Preview
@Composable
fun MainActivityPreview(){
    NCRPayTheme {
        val navController = rememberNavController()
        SetupNavGraph(navHostController = navController)
    }
}
