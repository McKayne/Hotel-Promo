@file:OptIn(ExperimentalAnimationApi::class)

package com.elnico.hotelpromo.ui.home

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import com.elnico.hotelpromo.R
import com.elnico.hotelpromo.ui.navigation.NavScreenRoutes
import com.elnico.hotelpromo.ui.theme.SplashBackgroundColor
import com.elnico.hotelpromo.uicomponents.HomeBottomBar
import com.elnico.hotelpromo.uicomponents.HomeTabBar
import com.elnico.hotelpromo.uicomponents.ListItemView
import com.elnico.hotelpromo.uicomponents.navbar.HomeTitleBar
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavHostController, transitionState: () -> EnterExitState) {
    val firstTab = remember {
        mutableStateOf("Отели NEOS")
    }
    val secondTab = remember {
        mutableStateOf("Мои бронирования")
    }

    val barActiveTab = remember {
        mutableIntStateOf(0)
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
                val (navBar, tabBar, bottomBar, list) = createRefs()

                HomeTitleBar(navController = navController, modifier = Modifier.constrainAs(navBar) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top, 30.dp)
                })

                val activeTab = remember {
                    mutableIntStateOf(0)
                }

                HomeTabBar(modifier = Modifier.constrainAs(tabBar) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(navBar.bottom, 30.dp)
                }, activeTab, firstTab, secondTab)

                HomeBottomBar(modifier = Modifier.constrainAs(bottomBar) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom, 50.dp)
                }, activeTab = barActiveTab) {
                    when (barActiveTab.intValue) {
                        0 -> firstTab.value = "Отели NEOS"
                        1 -> firstTab.value = "Все Рестораны NEOS"
                        2 -> firstTab.value = "NEOS SPA-Deluxe"
                        3 -> firstTab.value = "NEOS-Фитнес"
                        else -> firstTab.value = "Промо-акции NEOS"
                    }
                }

                LazyColumn(modifier = Modifier.constrainAs(list) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(tabBar.bottom)
                    bottom.linkTo(bottomBar.top)
                    height = Dimension.fillToConstraints
                }.fillMaxSize()) {
                    items(if (activeTab.intValue == 0) 5 else 1) {
                        ListItemView(modifier = Modifier, name =
                        when (barActiveTab.intValue) {
                            0 -> {
                                when (it) {
                                    0 -> "NEOS Park Dedeman"
                                    1 -> "NEOS Renion Park"
                                    2 -> "NEOS Reikartz Sky"
                                    3 -> "NEOS London Mood"
                                    else -> "NEOS Plaza"
                                }
                            }
                            1 -> {
                                when (it) {
                                    0 -> "NEOS Nuala"
                                    1 -> "NEOS Seven Bar"
                                    2 -> "NEOS Vista"
                                    3 -> "NEOS INZHU"
                                    else -> "NEOS Crudo"
                                }
                            }
                            2 -> {
                                when (it) {
                                    0 -> "NEOS Siam SPA"
                                    1 -> "NEOS Pattaya SPA"
                                    2 -> "NEOS AsiaThaiSpa"
                                    3 -> "NEOS TAU Spa"
                                    else -> "NEOS Luxor"
                                }
                            }
                            3 -> {
                                when (it) {
                                    0 -> "NEOS Banzai"
                                    1 -> "NEOS Fitness"
                                    2 -> "NEOS Sport Trade"
                                    3 -> "NEOS Gym Point"
                                    else -> "NEOS V-Flight"
                                }
                            }
                            else -> {
                                when (it) {
                                    0 -> "NEOS Aqua Park"
                                    1 -> "NEOS Family"
                                    2 -> "NEOS Hawaii"
                                    3 -> "NEOS Tours"
                                    else -> "NEOS Suv Trips"
                                }
                            }
                        }, drawable = when (barActiveTab.intValue) {
                            0 -> {
                                when (it) {
                                    0 -> R.drawable.hotel1
                                    1 -> R.drawable.hotel2
                                    2 -> R.drawable.hotel3
                                    3 -> R.drawable.hotel4
                                    4 -> R.drawable.hotel5
                                    else -> R.drawable.auth
                                }
                            }
                            1 -> {
                                when (it) {
                                    0 -> R.drawable.rest1
                                    1 -> R.drawable.rest2
                                    2 -> R.drawable.rest3
                                    3 -> R.drawable.rest4
                                    4 -> R.drawable.rest5
                                    else -> R.drawable.auth
                                }
                            }
                            2 -> {
                                when (it) {
                                    0 -> R.drawable.spa1
                                    1 -> R.drawable.spa2
                                    2 -> R.drawable.spa3
                                    3 -> R.drawable.spa4
                                    4 -> R.drawable.spa5
                                    else -> R.drawable.auth
                                }
                            }
                            3 -> {
                                when (it) {
                                    0 -> R.drawable.gym1
                                    1 -> R.drawable.gym2
                                    2 -> R.drawable.gym3
                                    3 -> R.drawable.gym4
                                    4 -> R.drawable.gym5
                                    else -> R.drawable.auth
                                }
                            }
                            else -> {
                                when (it) {
                                    0 -> R.drawable.offer1
                                    1 -> R.drawable.offer2
                                    2 -> R.drawable.offer3
                                    3 -> R.drawable.offer4
                                    4 -> R.drawable.offer5
                                    else -> R.drawable.auth
                                }
                            }
                        } , secondLine = if (activeTab.intValue == 0) {
                            when (barActiveTab.intValue) {
                                0 -> {
                                    when (it) {
                                        0 -> "90 отзывов"
                                        1 -> "214 отзывов"
                                        2 -> "111 отзывов"
                                        3 -> "50 отзывов"
                                        else -> "37 отзывов"
                                    }
                                }
                                1 -> {
                                    when (it) {
                                        0 -> "Средиземноморская, Европейская"
                                        1 -> "Стейк-хаус, Морепродукты"
                                        2 -> "Итальянская, Международная"
                                        3 -> "Итальянская, Средиземноморская"
                                        else -> "Стейк-хаус, Барбекю"
                                    }
                                }
                                2 -> {
                                    when (it) {
                                        0 -> "Хаммамы и турецкие бани"
                                        1 -> "Тайский и балийский массаж"
                                        2 -> "Спа и оздоровление"
                                        3 -> "Спа и оздоровление"
                                        else -> "Йога и пилатес"
                                    }
                                }
                                3 -> {
                                    when (it) {
                                        0 -> "Спортивный, тренажёрный зал"
                                        1 -> "Фитнес-клуб"
                                        2 -> "Теннисный клуб, спортзал"
                                        3 -> "Фитнес, групповые тренировки"
                                        else -> "Фитнес-клуб"
                                    }
                                }
                                else -> {
                                    "Наши Промоакции"
                                }
                            }
                        } else {
                            "Забронировано"
                        } , thirdLine = if (activeTab.intValue == 0) {
                            when (barActiveTab.intValue) {
                                0 -> {
                                    when (it) {
                                        0 -> "ул. Тимирязева, 67/А"
                                        1 -> "улица Кунаева 66"
                                        2 -> "ул. Наурызбай Батыра, 99/1"
                                        3 -> "Tole bi street 189/3"
                                        else -> "ул.Кожамкулова, 215/120"
                                    }
                                }
                                1 -> {
                                    when (it) {
                                        0 -> "1236 отзывов"
                                        1 -> "2525 отзывов"
                                        2 -> "2469 отзывов"
                                        3 -> "248 отзывов"
                                        else -> "315 отзывов"
                                    }
                                }
                                2 -> {
                                    when (it) {
                                        0 -> "126 отзывов"
                                        1 -> "2525 отзывов"
                                        2 -> "2469 отзывов"
                                        3 -> "248 отзывов"
                                        else -> "315 отзывов"
                                    }
                                }
                                3 -> {
                                    when (it) {
                                        0 -> "3187 отзывов"
                                        1 -> "428 отзывов"
                                        2 -> "413 отзывов"
                                        3 -> "899 отзывов"
                                        else -> "247 отзывов"
                                    }
                                }
                                else -> {
                                    when (it) {
                                        0 -> "876 отзывов"
                                        1 -> "1249 отзывов"
                                        2 -> "788 отзывов"
                                        3 -> "1346 отзывов"
                                        else -> "1098 отзывов"
                                    }
                                }
                            }
                        } else {
                            ""
                        }  ) {
                            navController.navigate("${NavScreenRoutes.DETAILS.value}?index=$it&tab=${barActiveTab.intValue}")
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