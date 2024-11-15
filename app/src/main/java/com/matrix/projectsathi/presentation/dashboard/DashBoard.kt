package com.matrix.projectsathi.presentation.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.matrix.projectsathi.presentation.dashboard.appbar.AppBar
import com.matrix.projectsathi.presentation.dashboard.feed_screen.FeedScreen
import com.matrix.projectsathi.R

@Composable
@Preview(showSystemUi = true)
fun DashBoard(){


    Scaffold (topBar = {
        AppBar()
    }){

        Column(modifier = Modifier.padding(it).fillMaxSize().background(color = Color(0xFFe0e4e7))) {

            FeedScreen(images = listOf(R.drawable.user_image,), statusText = null)
        }
    }



}