@file:OptIn(ExperimentalAnimationApi::class)

package com.elnico.hotelpromo.ui.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterExitState
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.elnico.hotelpromo.ui.navigation.NavScreenRoutes
import com.elnico.hotelpromo.ui.theme.SplashBackgroundColor
import com.elnico.hotelpromo.uicomponents.logo.ShadowedLogo
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(navController: NavHostController, transitionState: () -> EnterExitState) {
    Box(modifier = Modifier
        .background(color = SplashBackgroundColor)
        .fillMaxSize()) {
        AnimatedVisibility(
            visible = transitionState() == EnterExitState.Visible,
            enter = fadeIn(spring(stiffness = Spring.StiffnessVeryLow)),
            exit = fadeOut(spring(stiffness = Spring.StiffnessLow)),
            modifier = Modifier.fillMaxSize()
        ) {
            ConstraintLayout(Modifier.fillMaxSize()) {
                val (logo) = createRefs()

                ShadowedLogo(modifier = Modifier.constrainAs(logo) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }, text = "NEOS\nPromo")
            }
        }

        val scope = rememberCoroutineScope()

        LaunchedEffect(Unit) {
            scope.launch {
                delay(3000L)
                navController.navigate(NavScreenRoutes.AUTH.value) {
                    popUpTo(0)
                }
            }
        }
    }
}