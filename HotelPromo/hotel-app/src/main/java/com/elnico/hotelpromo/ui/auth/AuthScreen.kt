@file:OptIn(ExperimentalAnimationApi::class)

package com.elnico.hotelpromo.ui.auth

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterExitState
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.elnico.hotelpromo.R
import com.elnico.hotelpromo.ui.navigation.NavScreenRoutes
import com.elnico.hotelpromo.uicomponents.logo.ShadowedLogo
import com.elnico.hotelpromo.uicomponents.ShadowedButton
import com.elnico.hotelpromo.uicomponents.ShadowedLabel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun AuthScreen(navController: NavHostController, transitionState: () -> EnterExitState) {
    // System bar
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(color = Color.Black, darkIcons = false)

    ConstraintLayout(Modifier.fillMaxSize()) {
        val (bg, gradient) = createRefs()

        Image(
            contentScale = ContentScale.Crop,
            painter = painterResource(id = R.drawable.auth),
            contentDescription = "",
            modifier = Modifier
                .constrainAs(bg) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
                .fillMaxSize())

        Box(
            modifier = Modifier
                .constrainAs(gradient) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black
                        )
                    )
                )
                .fillMaxSize()
        )

        AnimatedVisibility(
            visible = transitionState() == EnterExitState.Visible,
            enter = fadeIn(spring(stiffness = Spring.StiffnessVeryLow)),
            exit = fadeOut(spring(stiffness = Spring.StiffnessLow)),
            modifier = Modifier.fillMaxSize()
        ) {
            ConstraintLayout(Modifier.fillMaxSize()) {
                val (headerBox, controls) = createRefs()

                Box(modifier = Modifier
                    .constrainAs(headerBox) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                    }
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f)) {
                    ConstraintLayout(Modifier.fillMaxSize()) {
                        val (header) = createRefs()

                        ShadowedLogo(modifier = Modifier.constrainAs(header) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                        }, text = "NEOS\nPromo")
                    }
                }

                Box(modifier = Modifier
                    .constrainAs(controls) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f)) {
                    ConstraintLayout(Modifier.fillMaxSize()) {
                        val (buttonsColumn, policy) = createRefs()

                        Column(modifier = Modifier.constrainAs(buttonsColumn) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                        }, horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            ShadowedButton(modifier = Modifier, text = stringResource(id = R.string.sign_in)) {
                                navController.navigate(NavScreenRoutes.SIGNUP.value)
                            }
                            ShadowedButton(modifier = Modifier, text = stringResource(id = R.string.google_auth)) {
                                navController.navigate(NavScreenRoutes.SIGNUP.value)
                            }

                            ShadowedLabel(modifier = Modifier.padding(top = 10.dp), text = stringResource(
                                id = R.string.no_account
                            ))
                            ShadowedButton(modifier = Modifier, text = stringResource(id = R.string.sign_up)) {
                                navController.navigate(NavScreenRoutes.SIGNUP.value)
                            }
                        }

                        ShadowedLabel(modifier = Modifier.constrainAs(policy) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom, 40.dp)
                        }, text = stringResource(id = R.string.privacy_policy))

                    }
                }
            }
        }



    }
}