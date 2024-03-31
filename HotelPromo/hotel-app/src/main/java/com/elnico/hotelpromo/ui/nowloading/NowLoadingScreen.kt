@file:OptIn(ExperimentalAnimationApi::class)

package com.elnico.hotelpromo.ui.nowloading

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterExitState
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.elnico.hotelpromo.DebugTempHolder
import com.elnico.hotelpromo.ui.navigation.NavScreenRoutes
import com.elnico.hotelpromo.ui.theme.GoldColor
import com.elnico.hotelpromo.ui.theme.SplashBackgroundColor
import com.elnico.hotelpromo.uicomponents.ShadowedLabel
import com.elnico.hotelpromo.uicomponents.logo.ShadowedLogo
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.spr.jetpack_loading.components.indicators.gridIndicator.GridFadeDiagonal
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun NowLoadingScreen(navController: NavHostController, transitionState: () -> EnterExitState) {
    // System bar
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(color = Color.Black, darkIcons = false)

    Box(modifier = Modifier
        .background(color = SplashBackgroundColor)
        .fillMaxSize()) {
        AnimatedVisibility(
            visible = transitionState() == EnterExitState.Visible,
            enter = fadeIn(spring(stiffness = Spring.StiffnessVeryLow)),
            exit = fadeOut(spring(stiffness = Spring.StiffnessLow)),
            modifier = Modifier.fillMaxSize()
        ) {
            Column(Modifier.fillMaxSize()) {
                ConstraintLayout(
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.5f)) {
                    val (logo, welcome, name, nowLoading) = createRefs()

                    ShadowedLogo(modifier = Modifier.constrainAs(logo) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top, 60.dp)
                    }, text = "NEOS")

                    Column(Modifier.constrainAs(welcome) {
                        start.linkTo(parent.start, 15.dp)
                        top.linkTo(logo.bottom)
                        bottom.linkTo(parent.bottom)
                    }, verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        ShadowedLabel(modifier = Modifier, text = "Добро пожаловать,", fontSize = 20)
                        ShadowedLabel(modifier = Modifier, text = DebugTempHolder.userName, fontSize = 30)
                    }
                }

                Column(Modifier.padding(top = 150.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f), horizontalAlignment = Alignment.CenterHorizontally) {
                    GridFadeDiagonal(color = GoldColor, ballDiameter = 100f,
                        verticalSpace = (LocalConfiguration.current.screenHeightDp / 2).toFloat(),
                        horizontalSpace = (LocalConfiguration.current.screenWidthDp * 0.9f),
                        animationDuration = 500
                    )
                }
            }
        }

        val scope = rememberCoroutineScope()

        LaunchedEffect(Unit) {
            scope.launch {
                delay(4000L)
                navController.navigate(NavScreenRoutes.HOME.value) {
                    popUpTo(0)
                }
            }
        }
    }
}