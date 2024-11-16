package com.matrix.projectsathi.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun ProjectSathiNavigationSystem(){

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.OnBoardingScreen) {

        composable<Routes.OnBoardingScreen> {


        }

    }




}