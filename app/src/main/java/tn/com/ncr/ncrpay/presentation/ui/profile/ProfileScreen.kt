package tn.com.ncr.ncrpay.presentation.ui.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import tn.com.ncr.ncrpay.R
import tn.com.ncr.ncrpay.presentation.ui.components.TopAppBarLayout
import tn.com.ncr.ncrpay.presentation.ui.navigation.Screen

@Composable
fun ProfileScreen(
    navController: NavHostController,
    viewModel: ProfileViewModel = hiltViewModel()
){
    Column( modifier = Modifier
        .fillMaxSize()
    ) {
        TopAppBarLayout(
            navController = navController,
            title = stringResource(id = R.string.profile)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val user = viewModel.user.value
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .height(100.dp)
                    .width(100.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colors.primaryVariant),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = user?.name?.substring(0,1)?.uppercase().toString(),
                    color  = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 40.sp
                )
            }
            Text(
                text="${user?.name?.uppercase()} ${user?.lastName?.uppercase()}",
                fontSize = 23.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.padding(16.dp))
            Card(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            ) {
                Column (
                    modifier = Modifier.padding(16.dp),
                ){
                    Row(verticalAlignment = Alignment.CenterVertically){
                        Text(
                            modifier = Modifier.width(90.dp),
                            text= stringResource(id = R.string.email),
                            fontSize = 16.sp,
                            color = Color.Black,
                        )
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text=user?.email.toString(),
                            fontSize = 18.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.padding(16.dp))
                    Row(verticalAlignment = Alignment.CenterVertically){
                        Text(
                            modifier = Modifier.width(90.dp),
                            text= stringResource(id = R.string.username),
                            fontSize = 16.sp,
                            color = Color.Black,
                        )
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text=user?.username.toString(),
                            fontSize = 18.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.padding(16.dp))
                    Row(verticalAlignment = Alignment.CenterVertically){
                        Text(
                            modifier = Modifier.width(90.dp),
                            text= stringResource(id = R.string.phone_number),
                            fontSize = 16.sp,
                            color = Color.Black,
                        )
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text=user?.phone.toString(),
                            fontSize = 18.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.padding(16.dp))
                    Row(verticalAlignment = Alignment.CenterVertically){
                        Text(
                            modifier = Modifier.width(90.dp),
                            text= stringResource(id = R.string.cin),
                            fontSize = 16.sp,
                            color = Color.Black,
                        )
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text=user?.cin.toString(),
                            fontSize = 18.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.padding(16.dp))

                }
            }
            Spacer(modifier = Modifier.padding(16.dp))
            OutlinedButton(
                onClick = { navController.navigate(Screen.Login.route) },
                border =  BorderStroke(1.dp, Color.Red),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(50.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.logout) ,
                    color = Color.Red
                )
            }
        }
    }
}

@Preview
@Composable
fun ProfileScreenPreview(
    navController: NavHostController = rememberNavController()
) {
    ProfileScreen(navController = navController)
}
