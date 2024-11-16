package com.matrix.projectsathi.presentation.savescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.matrix.projectsathi.R
import com.matrix.projectsathi.presentation.bottomnavigation.BottomNavigation
import com.matrix.projectsathi.presentation.navigation.Routes

@Composable
fun SaveScreen(navHostController: NavHostController) {
    Scaffold(
        bottomBar = {

            BottomNavigation(navHostController, selectedItem = 2, onClick = { index ->
                when (index) {
                    0 -> navHostController.navigate(Routes.DashBoardScreen)
                    1 -> navHostController.navigate(Routes.RequestScreen)
                    2 -> {
                        navHostController.navigate(Routes.SaveScreen);
                    }
                    3 -> navHostController.navigate(Routes.NotificationScreen)
                    4 -> navHostController.navigate(Routes.ProfileScreen)
                }
            })
        }
    ) { it ->
        Modifier.padding(it)
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(painter = painterResource(id = R.drawable.unavailable), contentDescription = null)
            Spacer(modifier = Modifier.height(32.dp))
            Text(text = "Currently not available", fontSize = 22.sp, color = Color.Gray)
        }
    }
}