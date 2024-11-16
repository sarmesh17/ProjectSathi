package com.matrix.projectsathi.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.matrix.projectsathi.presentation.onboardingscreen.OnBordingScreen
import com.matrix.projectsathi.presentation.signup_screen.SignUpScreen
import com.matrix.projectsathi.presentation.viewmodels.AuthViewModel

@Composable
fun ProjectSathiNavigationSystem(){

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.OnBoardingScreen) {

        composable<Routes.OnBoardingScreen> {

           OnBordingScreen(navController)

        }

        composable<Routes.SignUpScreen> {
            val viewModel:AuthViewModel= hiltViewModel()
            SignUpScreen(viewModel = viewModel) {

            }


        }

    }




}