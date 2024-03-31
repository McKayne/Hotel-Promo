@file:OptIn(ExperimentalAnimationApi::class)

package com.elnico.hotelpromo.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.elnico.hotelpromo.ui.DetailsScreen
import com.elnico.hotelpromo.ui.NotificationsScreen
import com.elnico.hotelpromo.ui.ProfileScreen
import com.elnico.hotelpromo.ui.nowloading.NowLoadingScreen
import com.elnico.hotelpromo.ui.splash.SplashScreen
import com.elnico.hotelpromo.ui.auth.AuthScreen
import com.elnico.hotelpromo.ui.auth.signup.SignUpScreen
import com.elnico.hotelpromo.ui.home.HomeScreen

@Composable
fun AppNavigation(
    modifier: Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        modifier = modifier.fillMaxSize(),
        navController = navController,
        startDestination = NavScreenRoutes.SPLASH.value,
    ) {
        composable(NavScreenRoutes.SPLASH.value) {
            SplashScreen(navController, transitionState = { transition.currentState })
        }
        composable(NavScreenRoutes.AUTH.value) {
            AuthScreen(navController, transitionState = { transition.currentState })
        }
        composable(NavScreenRoutes.SIGNUP.value) {
            SignUpScreen(navController, transitionState = { transition.currentState })
        }
        composable(NavScreenRoutes.NOWLOADING.value) {
            NowLoadingScreen(navController, transitionState = { transition.currentState })
        }
        composable(NavScreenRoutes.HOME.value) {
            HomeScreen(navController, transitionState = { transition.currentState })
        }
        composable(NavScreenRoutes.PROFILE.value) {
            ProfileScreen(navController, transitionState = { transition.currentState })
        }
        composable(NavScreenRoutes.NOTIFICATIONS.value) {
            NotificationsScreen(navController, transitionState = { transition.currentState })
        }
        composable(
            route = "${NavScreenRoutes.DETAILS.value}?index={index}&tab={tab}",
            arguments = listOf(
                navArgument("index") {
                    type = NavType.IntType
                    defaultValue = -1
                },
                navArgument("tab") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )) {
            val index = it.arguments?.getInt("index")
            val tab = it.arguments?.getInt("tab")
            if (index != null && tab != null) {
                DetailsScreen(navController, index, tab, transitionState = { transition.currentState })
            }
        }
    }
}