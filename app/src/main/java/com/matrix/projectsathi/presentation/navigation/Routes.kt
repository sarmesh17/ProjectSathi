package com.matrix.projectsathi.presentation.navigation

sealed class Routes {

    data object OnBoardingScreen:Routes()
    data object SignUpScreen:Routes()
    data object LoginScreen:Routes()
    data object DashBoardScreen:Routes()
}