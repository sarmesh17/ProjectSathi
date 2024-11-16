package com.matrix.projectsathi.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.matrix.projectsathi.ProjectStatusCreateScreen
import com.matrix.projectsathi.R
import com.matrix.projectsathi.presentation.chatscreen.ChatScreen
import com.matrix.projectsathi.presentation.dashboard.DashBoard
import com.matrix.projectsathi.presentation.dashboard.appbar.AppBar
import com.matrix.projectsathi.presentation.loginscreen.LoginScreen
import com.matrix.projectsathi.presentation.notificationscreen.NotificationScreen
import com.matrix.projectsathi.presentation.onboardingscreen.OnBordingScreen
import com.matrix.projectsathi.presentation.profilescreen.ProfileScreen
import com.matrix.projectsathi.presentation.project_detail_screen.ProjectDetailsScreen
import com.matrix.projectsathi.presentation.requestscreen.RequestScreen
import com.matrix.projectsathi.presentation.savescreen.SaveScreen
import com.matrix.projectsathi.presentation.signup_screen.SignUpScreen
import com.matrix.projectsathi.presentation.viewmodels.AuthViewModel
import com.matrix.projectsathi.presentation.viewmodels.DashBoardScreenViewModel
import com.matrix.projectsathi.presentation.viewmodels.PublishProjectViewModel

@Composable
fun ProjectSathiNavigationSystem(){

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.OnBoardingScreen) {

        composable<Routes.OnBoardingScreen> {

           OnBordingScreen(navController)

        }

        composable<Routes.SignUpScreen> {
            val viewModel:AuthViewModel= hiltViewModel()
            SignUpScreen(viewModel = viewModel ) {

                navController.navigate(Routes.DashBoardScreen)
            }


        }

        composable<Routes.LoginScreen> {

            val viewModel:AuthViewModel= hiltViewModel()


            LoginScreen(authViewModel =  viewModel) {
                navController.navigate(Routes.DashBoardScreen)
            }
        }

        composable<Routes.DashBoardScreen> {

            val dashBoardScreenViewModel:DashBoardScreenViewModel= hiltViewModel()
            DashBoard(navController, dashBoardScreenViewModel)
        }

        composable<Routes.AppBar> {
            AppBar(navController)
        }

        composable<Routes.ProjectStatusCreateScreen> {
            val publishProjectViewModel:PublishProjectViewModel= hiltViewModel()
            ProjectStatusCreateScreen(navHostController = navController, profileImageUrl = R.drawable.img, publishProjectViewModel = publishProjectViewModel){

            }
        }

        composable<Routes.ProjectDetailScreen> {
            ProjectDetailsScreen(navController)
        }

        composable<Routes.RequestScreen> {
            RequestScreen(navController)
        }

        composable<Routes.SaveScreen> {
            SaveScreen(navController)
        }

        composable<Routes.NotificationScreen> {
            NotificationScreen(navController)
        }

        composable<Routes.ProfileScreen> {
            ProfileScreen(navController)
        }

        composable<Routes.ChatScreen> {

            ChatScreen(navHostController =navController )
        }



    }




}