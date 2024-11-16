package com.matrix.projectsathi.presentation.onboardingscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.matrix.projectsathi.R
import com.matrix.projectsathi.presentation.navigation.Routes

@Composable
fun OnBordingScreen(navController: NavHostController) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.height(16.dp))
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ){
            Text(
                text = "Project",
                fontSize = 20.sp,
                color = colorResource(id = R.color.amber),
                fontWeight = FontWeight.ExtraBold

            )
            Text(
                text = "Sathi",
                fontSize = 20.sp,
                color = colorResource(id = R.color.darkCharcoal),
                fontWeight = FontWeight.ExtraBold

            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        //image
        Image(
            painter = painterResource(id = R.drawable.onbordingicon),
            contentDescription = null,
            modifier = Modifier.size(260.dp)
        )
        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Welcome to ProjectSathi",
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold,
            color = colorResource(id = R.color.darkCharcoal)
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "The Mentoring Platform",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.amber)
        )
        Spacer(modifier = Modifier.height(32.dp))

        //signup
        Button(
            onClick = { navController.navigate(Routes.SignUpScreen) },
            modifier = Modifier.height(50.dp),
            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.amber))
        ) {
            Row {
                Text(
                    text = "New To ProjectSathi?",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = " Register Free ->",
                    fontSize = 16.sp
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "OR", fontSize = 16.sp)
        Spacer(modifier = Modifier.height(16.dp))
        //login
        Button(
            onClick = { navController.navigate(Routes.LoginScreen)
            },
            modifier = Modifier.height(50.dp),
            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.darkCharcoal))
        ) {
            Row {
                Text(
                    text = "Existing User?",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = " Login Now ->",
                    fontSize = 16.sp
                )
            }
        }
    }
}