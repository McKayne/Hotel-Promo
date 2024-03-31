@file:OptIn(ExperimentalAnimationApi::class)

package com.elnico.hotelpromo.ui

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
import androidx.compose.ui.graphics.Shadow
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.elnico.hotelpromo.ui.theme.SplashBackgroundColor
import com.elnico.hotelpromo.uicomponents.ShadowedLabel
import com.elnico.hotelpromo.uicomponents.navbar.TopTitleBar
import kotlinx.coroutines.launch

@Composable
fun NotificationsScreen(navController: NavHostController, transitionState: () -> EnterExitState) {
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
                val (navBar, dummy) = createRefs()

                TopTitleBar(navController = navController, modifier = Modifier.constrainAs(navBar) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                }, text = "Уведомления")

                ShadowedLabel(modifier = Modifier.constrainAs(dummy) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(navBar.bottom)
                    bottom.linkTo(parent.bottom)
                }, text = "У Вас нет непрочитанных уведомлений")
            }
        }

        val scope = rememberCoroutineScope()

        LaunchedEffect(Unit) {
            scope.launch {
                //delay(3000L)
                //navController.navigate(NavScreenRoutes.AUTH.value) {
                //popUpTo(0)
                //}
            }
        }
    }
}