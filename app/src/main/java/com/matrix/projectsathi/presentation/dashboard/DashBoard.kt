package com.matrix.projectsathi.presentation.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.matrix.projectsathi.presentation.bottomnavigation.BottomNavigation
import com.matrix.projectsathi.presentation.dashboard.appbar.AppBar
import com.matrix.projectsathi.presentation.dashboard.feed_screen.FeedScreen
import com.matrix.projectsathi.presentation.viewmodels.DashBoardScreenViewModel

@Composable
fun DashBoard(
    navHostController: NavHostController,
    dashBoardScreenViewModel: DashBoardScreenViewModel
) {

    val statuses by dashBoardScreenViewModel.globalStatuses.observeAsState(emptyList())
    val error by dashBoardScreenViewModel.error.observeAsState(null)

    Scaffold(topBar = {
        AppBar(navHostController)
    }, bottomBar = { BottomNavigation(onClick = {}, selectedItem = 0) }) {

        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(color = Color(0xFFe0e4e7))
        ) {

            LazyColumn {

                items(statuses) { data ->

                    FeedScreen(
                        userName = dashBoardScreenViewModel.firstName + dashBoardScreenViewModel.lastName,
                        images = data.images,
                        statusText = data.projectDescription,
                        skills = data.skillsRequired,
                        totalAmount = data.amount, navHostController = navHostController
                    )
                }

            }

        }
    }


}