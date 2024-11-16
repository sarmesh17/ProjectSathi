package com.matrix.projectsathi.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Routes {


    @Serializable
    data object OnBoardingScreen:Routes()
    @Serializable
    data object SignUpScreen:Routes()
    @Serializable
    data object LoginScreen:Routes()
    @Serializable
    data object DashBoardScreen:Routes()
    @Serializable
    data object AppBar:Routes()
    @Serializable
    data object ProjectStatusCreateScreen:Routes()

    @Serializable
    data object ProjectDetailScreen:Routes()
}