@file:OptIn(ExperimentalAnimationApi::class)

package com.elnico.hotelpromo.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterExitState
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import com.elnico.hotelpromo.DebugTempHolder
import com.elnico.hotelpromo.R
import com.elnico.hotelpromo.ui.theme.GoldColor
import com.elnico.hotelpromo.ui.theme.LightGoldColor
import com.elnico.hotelpromo.ui.theme.SplashBackgroundColor
import com.elnico.hotelpromo.uicomponents.ProfileAvatarView
import com.elnico.hotelpromo.uicomponents.ShadowedLabel
import com.elnico.hotelpromo.uicomponents.ShadowedNameView
import com.lightspark.composeqr.QrCodeView
import kotlinx.coroutines.launch
import java.util.Random

@Composable
fun ProfileScreen(navController: NavHostController, transitionState: () -> EnterExitState) {
    val qrCode = remember {
        mutableStateOf(Random().nextInt(900000000) + 100000000)
    }

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
                val (navBar, qrBox) = createRefs()

                Box(modifier = Modifier
                    .constrainAs(navBar) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                    }
                    .fillMaxWidth()
                    .fillMaxHeight(0.3f)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                GoldColor,
                                LightGoldColor
                            )
                        )
                    )) {
                    ConstraintLayout(Modifier.fillMaxSize()) {
                        val (avatar, name) = createRefs()

                        ProfileAvatarView(modifier = Modifier.constrainAs(avatar) {
                            start.linkTo(parent.start, 30.dp)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                        })

                        Column(Modifier.constrainAs(name) {
                            start.linkTo(avatar.end, 15.dp)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                        }) {
                            ShadowedNameView(modifier = Modifier, text = DebugTempHolder.userName, fontSize = 15)
                            StatusView(modifier = Modifier)
                            Text(text = "У Вас накоплено:\n14285 бонусов", modifier = Modifier,
                                fontFamily = FontFamily(Font(R.font.latolight)), fontSize = 15.sp
                            )
                        }
                    }
                }

                Box(modifier = Modifier
                    .constrainAs(qrBox) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(navBar.bottom)
                        bottom.linkTo(parent.bottom)
                        height = Dimension.fillToConstraints
                    }
                    .fillMaxWidth()
                    .background(color = Color.White)) {
                    ConstraintLayout(Modifier.fillMaxSize()) {
                        val (header, number, qr) = createRefs()

                        ShadowedLabel(modifier = Modifier.constrainAs(header) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(parent.top, 20.dp)
                        }, text = "Ваша карта лояльности", fontSize = 20)

                        Text(text = "Номер ${qrCode.value}", modifier = Modifier.constrainAs(number) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(header.bottom, 20.dp)
                        },
                            fontFamily = FontFamily(Font(R.font.latolight)), fontSize = 20.sp, textAlign = TextAlign.Center
                        )

                        QrCodeView(
                            data = "${qrCode.value}",
                            modifier = Modifier.constrainAs(qr) {
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                top.linkTo(number.bottom, 20.dp)
                            }.size(300.dp)
                        )
                    }
                }
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

@Composable
private fun StatusView(modifier: Modifier) {
    Row(modifier) {
        ConstraintLayout {
            val (lower, upper) = createRefs()

            Image(painter = painterResource(id = R.drawable.vipstatus), modifier = modifier
                .constrainAs(lower) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
                .width(30.dp)
                .height(30.dp), contentDescription = "")
        }

        ConstraintLayout {
            val (lower, upper) = createRefs()

            Text(text = "Золотой уровень", modifier = Modifier.constrainAs(lower) {
                start.linkTo(parent.start, 1.dp)
                end.linkTo(parent.end, -1.dp)
                top.linkTo(parent.top, 6.dp)
                bottom.linkTo(parent.bottom, -1.dp)
            }, textAlign = TextAlign.Center,
                fontFamily = FontFamily(Font(R.font.latolight)), fontSize = 15.sp
            )

            Text(text = "Золотой уровень", modifier = Modifier.constrainAs(upper) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top, 5.dp)
                bottom.linkTo(parent.bottom)
            }, textAlign = TextAlign.Center, color = GoldColor,
                fontFamily = FontFamily(Font(R.font.latolight)), fontSize = 15.sp
            )
        }
    }
}