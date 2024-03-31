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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import com.elnico.hotelpromo.R
import com.elnico.hotelpromo.ui.theme.SplashBackgroundColor
import com.elnico.hotelpromo.uicomponents.DetailsTabBar
import com.elnico.hotelpromo.uicomponents.ListItemView
import com.elnico.hotelpromo.uicomponents.ShadowedBoldLabel
import com.elnico.hotelpromo.uicomponents.ShadowedButton
import com.elnico.hotelpromo.uicomponents.ShadowedLabel
import com.elnico.hotelpromo.uicomponents.ShadowedNameView
import com.elnico.hotelpromo.uicomponents.ShadowedTextField
import com.elnico.hotelpromo.uicomponents.navbar.TopTitleBar
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.osmdroid.views.MapView

@Composable
internal fun DetailsScreen(navController: NavHostController, index: Int, barTab: Int , transitionState: () -> EnterExitState) {
    val viewModel = koinViewModel<DetailsViewModel>()

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
                val (img, navBar, name, contents) = createRefs()

                Image(
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .constrainAs(img) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                        }
                        .fillMaxWidth()
                        .fillMaxHeight(0.3f),
                    painter = painterResource(id = when (index) {
                        0 -> R.drawable.hotel1
                        1 -> R.drawable.hotel2
                        2 -> R.drawable.hotel3
                        3 -> R.drawable.hotel4
                        else -> R.drawable.hotel5
                    }),
                    contentDescription = "stock1"
                )

                TopTitleBar(navController = navController, modifier = Modifier.constrainAs(navBar) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                }, text = "")

                ShadowedBoldLabel(modifier = Modifier.constrainAs(name) {
                    start.linkTo(parent.start, 15.dp)
                    bottom.linkTo(img.bottom, 10.dp)
                }, text = when (index) {
                    0 -> "NEOS Park Dedeman"
                    1 -> "NEOS Renion Park"
                    2 -> "NEOS Reikartz Sky"
                    3 -> "NEOS London Mood"
                    else -> "NEOS Plaza"
                }, fontSize = 30)

                Column(
                    Modifier
                        .verticalScroll(rememberScrollState())
                        .constrainAs(contents) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(img.bottom)
                            bottom.linkTo(parent.bottom)
                            height = Dimension.fillToConstraints
                        }
                        .fillMaxWidth()) {
                    val activeTab = remember {
                        mutableIntStateOf(0)
                    }

                    DetailsTabBar(modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth(), activeTab) {
                        viewModel.updateMapFlow.update { true }
                    }

                    when (activeTab.intValue) {
                        0 -> {
                            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                                ShadowedNameView(modifier = Modifier
                                    .fillMaxWidth(0.9f)
                                    .padding(top = 10.dp), text = "Как добраться:")
                                ShadowedLabel(modifier = Modifier
                                    .fillMaxWidth(0.9f)
                                    .padding(top = 10.dp, bottom = 10.dp), text = "Казахстан, Алматы, ул. Тимирязева, 67/А")

                                val context = LocalContext.current

                                Box(modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)) {
                                    AndroidView(modifier = Modifier.clip(shape = RoundedCornerShape(1.dp)).width(
                                        LocalConfiguration.current.screenWidthDp.dp).height(200.dp),
                                        factory = {
                                            val mapView = MapView(context)
                                            mapView
                                        }, update = {
                                            viewModel.updateMapIfNeeded(it)
                                        })
                                }

                                ShadowedNameView(modifier = Modifier
                                    .fillMaxWidth(0.9f)
                                    .padding(top = 10.dp), text = "С пребыванием в Park Dedeman Almaty в Алмати (районе Бостандик) вы находитесь в 5 минутах езды от казино Zodiak и Fantasy World Almaty. Этот высококлассный отель составляет 1,7 мили (2,7 км) от Казахстанского музея искусств и 1,7 мили (2,7 км) от Государственного музея искусств Кастив.\n" +
                                        "Побалуйте себя массажем на месте или наслаждайтесь любителями отдыха, такими как крытый бассейн. Дополнительные удобства в этом отеле включают бесплатный беспроводной доступ в Интернет и консьерж.\n" +
                                        "В Park Dedeman Almaty наслаждайтесь едой в ресторане. Оберните свой день напитком в баре/отдыхе. Завтраки шведского стола доступны ежедневно с 7:00 до 10:00 утра за плату.\n" +
                                        "Избранные удобства включают услуги по чистке/прачечной, 24-часовую стойку регистрации и хранение багажа. Бесплатная самостоятельная парковка доступна на месте.\n" +
                                        "Сделайте себя как дома в одном из 110 номеров. Бесплатный беспроводной доступ к Интернету доступен для подключения к подключению. Удобства включают утюги/гладильные доски, а домашнее хозяйство предоставляется ежедневно.")
                            }
                        }
                        1 -> {
                            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                                ListItemView(
                                    modifier = Modifier,
                                    name = "",
                                    drawable = R.drawable.details1,
                                    secondLine = "",
                                    thirdLine = "",
                                    addGradient = false
                                ) {

                                }
                                ListItemView(
                                    modifier = Modifier,
                                    name = "",
                                    drawable = R.drawable.details2,
                                    secondLine = "",
                                    thirdLine = "",
                                    addGradient = false
                                ) {

                                }
                                ListItemView(
                                    modifier = Modifier,
                                    name = "",
                                    drawable = R.drawable.details3,
                                    secondLine = "",
                                    thirdLine = "",
                                    addGradient = false
                                ) {

                                }
                                ListItemView(
                                    modifier = Modifier,
                                    name = "",
                                    drawable = R.drawable.details4,
                                    secondLine = "",
                                    thirdLine = "",
                                    addGradient = false
                                ) {

                                }
                                ListItemView(
                                    modifier = Modifier,
                                    name = "",
                                    drawable = R.drawable.details5,
                                    secondLine = "",
                                    thirdLine = "",
                                    addGradient = false
                                ) {

                                }
                                ListItemView(
                                    modifier = Modifier,
                                    name = "",
                                    drawable = R.drawable.details6,
                                    secondLine = "",
                                    thirdLine = "",
                                    addGradient = false
                                ) {

                                }
                                ListItemView(
                                    modifier = Modifier,
                                    name = "",
                                    drawable = R.drawable.details7,
                                    secondLine = "",
                                    thirdLine = "",
                                    addGradient = false
                                ) {

                                }
                                ListItemView(
                                    modifier = Modifier,
                                    name = "",
                                    drawable = R.drawable.details8,
                                    secondLine = "",
                                    thirdLine = "",
                                    addGradient = false
                                ) {

                                }
                                ListItemView(
                                    modifier = Modifier,
                                    name = "",
                                    drawable = R.drawable.details9,
                                    secondLine = "",
                                    thirdLine = "",
                                    addGradient = false
                                ) {

                                }
                                ListItemView(
                                    modifier = Modifier,
                                    name = "",
                                    drawable = R.drawable.details10,
                                    secondLine = "",
                                    thirdLine = "",
                                    addGradient = false
                                ) {

                                }
                                ListItemView(
                                    modifier = Modifier,
                                    name = "",
                                    drawable = R.drawable.details11,
                                    secondLine = "",
                                    thirdLine = "",
                                    addGradient = false
                                ) {

                                }
                                ListItemView(
                                    modifier = Modifier,
                                    name = "",
                                    drawable = R.drawable.details12,
                                    secondLine = "",
                                    thirdLine = "",
                                    addGradient = false
                                ) {

                                }
                                ListItemView(
                                    modifier = Modifier,
                                    name = "",
                                    drawable = R.drawable.details13,
                                    secondLine = "",
                                    thirdLine = "",
                                    addGradient = false
                                ) {

                                }
                                ListItemView(
                                    modifier = Modifier,
                                    name = "",
                                    drawable = R.drawable.details14,
                                    secondLine = "",
                                    thirdLine = "",
                                    addGradient = false
                                ) {

                                }
                            }
                        }
                        2 -> {
                            val nameText = remember {
                                mutableStateOf("")
                            }

                            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                                ShadowedLabel(modifier = Modifier.padding(top = 10.dp, start = 10.dp, end = 10.dp), text = "Оставьте Ваши контакты (номер телефона, WhatsApp или Telegram) нам для связи. Наш свободный менеджер свяжется с Вами в ближайшее время примерно в течение часа")
                                ShadowedTextField(modifier = Modifier
                                    .padding(top = 10.dp)
                                    .fillMaxWidth(0.9f), placeholder = "Контакты для связи", textValue = nameText)
                                ShadowedButton(modifier = Modifier
                                    .padding(top = 10.dp)
                                    .fillMaxWidth(0.9f), text = "Отправить") {
                                    nameText.value = ""
                                }
                            }
                        }
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