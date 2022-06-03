package tn.com.ncr.ncrpay.presentation.ui.splash_screen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.window.SplashScreen
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import tn.com.ncr.ncrpay.R
import tn.com.ncr.ncrpay.presentation.ui.navigation.Screen

@Composable
fun SplashScreen(navHostController: NavHostController){

    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim = animateFloatAsState(
        targetValue = if(startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 3000
        )
    )
    LaunchedEffect(key1 = true){
        startAnimation = true
        delay(4000)
        navHostController.navigate(Screen.Login.route)
    }
    Splash(alpha = alphaAnim.value)
}

@Composable
fun Splash(alpha : Float){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(if (isSystemInDarkTheme()) Color.Black else Color.White),
        contentAlignment = Alignment.Center
    ){
        Image(
            modifier = Modifier
                .size(200.dp)
                .alpha(alpha = alpha),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo"
        )
    }
}

@Preview
@Composable
fun SplashScreenPreview(){
    Splash(alpha=1f)
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SplashScreenPreviewDarkMode(){
    Splash(alpha=1f)
}
