@file:UnstableApi
@file:OptIn(ExperimentalAnimationApi::class)

package com.elnico.hotelpromo.ui.auth.signup

import android.content.Context
import android.graphics.Bitmap
import android.view.ViewGroup
import android.widget.FrameLayout
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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.common.util.Util
import androidx.media3.datasource.DefaultDataSourceFactory
import androidx.media3.datasource.RawResourceDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.SimpleExoPlayer
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import androidx.navigation.NavHostController
import com.elnico.hotelpromo.DebugTempHolder
import com.elnico.hotelpromo.R
import com.elnico.hotelpromo.ui.navigation.NavScreenRoutes
import com.elnico.hotelpromo.uicomponents.ShadowedAvatar
import com.elnico.hotelpromo.uicomponents.ShadowedButton
import com.elnico.hotelpromo.uicomponents.ShadowedLabel
import com.elnico.hotelpromo.uicomponents.ShadowedTextField
import com.elnico.hotelpromo.uicomponents.navbar.TopTitleBar
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@androidx.annotation.OptIn(UnstableApi::class) @Composable
fun SignUpScreen(navController: NavHostController, transitionState: () -> EnterExitState) {
    // System bar
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(color = Color.White, darkIcons = true)

    val context = LocalContext.current

    // create our player
    val exoPlayer = remember {
        getSimpleExoPlayer(context)
    }

    ConstraintLayout(Modifier.fillMaxSize()) {
        val (bg, video, gradient) = createRefs()

        Image(
            contentScale = ContentScale.Crop,
            painter = painterResource(id = R.drawable.signup),
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
                .constrainAs(video) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
                .fillMaxSize()
        ) {
            DisposableEffect(
                AndroidView(
                    modifier = Modifier.fillMaxSize(),
                    factory = {

                        // exo player view for our video player
                        PlayerView(context).apply {
                            useController = false
                            player = exoPlayer
                            resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
                            layoutParams =
                                FrameLayout.LayoutParams(
                                    ViewGroup.LayoutParams
                                        .MATCH_PARENT,
                                    ViewGroup.LayoutParams
                                        .MATCH_PARENT
                                )
                        }
                    }
                )
            ) {
                onDispose {
                    // relase player when no longer needed
                    exoPlayer.release()
                }
            }
        }

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
                val (navBar, avatarBox, controlsBox) = createRefs()

                TopTitleBar(navController = navController, modifier = Modifier.constrainAs(navBar) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                }, text = "Регистрация")

                ConstraintLayout(
                    Modifier
                        .constrainAs(avatarBox) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(navBar.bottom)
                        }
                        .fillMaxWidth()
                        .fillMaxHeight(0.3f)) {

                    val (avatar) = createRefs()
                    val avatarImg = remember {
                        mutableStateOf<Bitmap?>(null)
                    }

                    ShadowedAvatar(modifier = Modifier.constrainAs(avatar) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }, avatar = avatarImg) {

                    }
                }

                val nameText = remember {
                    mutableStateOf("")
                }
                val passwordText = remember {
                    mutableStateOf("")
                }
                val repeatPasswordText = remember {
                    mutableStateOf("")
                }

                ConstraintLayout(
                    Modifier
                        .constrainAs(controlsBox) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(avatarBox.bottom)
                            bottom.linkTo(parent.bottom)
                            height = Dimension.fillToConstraints
                        }
                        .fillMaxWidth(0.8f)) {
                    val (text, signup) = createRefs()

                    Column(modifier = Modifier.constrainAs(text) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                    }, horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        ShadowedTextField(modifier = Modifier, placeholder = "Ваше имя", textValue = nameText)
                        ShadowedTextField(modifier = Modifier, placeholder = "Пароль", textValue = passwordText, isPassword = true)
                        ShadowedTextField(modifier = Modifier, placeholder = "Повторите пароль", textValue = repeatPasswordText, isPassword = true)
                        ShadowedLabel(modifier = Modifier.padding(top = 10.dp), text = stringResource(
                            id = R.string.sign_up_description
                        ))
                    }

                    ShadowedButton(modifier = Modifier.constrainAs(signup) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom, 40.dp)
                    }, text = stringResource(id = R.string.sign_up)) {
                        DebugTempHolder.userName = if (nameText.value.isNotEmpty()) nameText.value else "Тестовый пользователь"

                        navController.navigate(NavScreenRoutes.NOWLOADING.value) {
                            popUpTo(0)
                        }
                    }
                }
            }
        }
    }
}

private fun getSimpleExoPlayer(context: Context): SimpleExoPlayer {
    val mediaItem = MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.ritz))

    return SimpleExoPlayer.Builder(context).build().apply {
        playWhenReady = true
        repeatMode = Player.REPEAT_MODE_ONE

        //videoScalingMode = C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING;

        val dataSourceFactory = DefaultDataSourceFactory(
            context,
            Util.getUserAgent(context, context.packageName)
        )
        //local video
        //val localVideoItem = MediaItem.fromUri(file.toUri())
        val localVideoSource = ProgressiveMediaSource
            .Factory(dataSourceFactory)
            .createMediaSource(mediaItem)
        this.addMediaSource(localVideoSource)

        // init
        this.prepare()
    }
}