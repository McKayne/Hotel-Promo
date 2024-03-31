package com.elnico.hotelpromo.uicomponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.elnico.hotelpromo.R
import com.elnico.hotelpromo.ui.theme.GoldColor
import com.elnico.hotelpromo.ui.theme.SplashBackgroundColor

@Composable
internal fun HomeBottomBar(modifier: Modifier, activeTab: MutableIntState, clickAction: () -> Unit) {
    val width = LocalConfiguration.current.screenWidthDp / 5

    Column(
        modifier
            .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(4.dp)
            .background(color = GoldColor))
        Row(
            Modifier
                .fillMaxWidth()
                .height(50.dp)) {
            BottomBarTab(modifier = Modifier
                .width(width.dp)
                .height(50.dp), activeTab = activeTab, nthTab = 0, clickAction)
            BottomBarTab(modifier = Modifier
                .width(width.dp)
                .height(50.dp), activeTab = activeTab, nthTab = 1, clickAction)
            BottomBarTab(modifier = Modifier
                .width(width.dp)
                .height(50.dp), activeTab = activeTab, nthTab = 2, clickAction)
            BottomBarTab(modifier = Modifier
                .width(width.dp)
                .height(50.dp), activeTab = activeTab, nthTab = 3, clickAction)
            BottomBarTab(modifier = Modifier
                .width(width.dp)
                .height(50.dp), activeTab = activeTab, nthTab = 4, clickAction)
        }
    }
}

@Composable
private fun BottomBarTab(modifier: Modifier, activeTab: MutableIntState, nthTab: Int, clickAction: () -> Unit) {
    Box(modifier = modifier
        .background(color = if (activeTab.intValue == nthTab) GoldColor else SplashBackgroundColor)
        .clickable {
            activeTab.intValue = nthTab
            clickAction()
        }) {
        Column(Modifier.fillMaxSize()) {
            ConstraintLayout(
                Modifier
                    .weight(1F)
                    .fillMaxWidth()) {
                val (lower, upper) = createRefs()

                val lowerDrawable = when (nthTab) {
                    0 -> R.drawable.ringicon
                    1 -> R.drawable.restaurantlower
                    2 -> R.drawable.spalower
                    3 -> R.drawable.gymlower
                    else -> R.drawable.promolower
                }
                val upperDrawable = when (nthTab) {
                    0 -> R.drawable.ring
                    1 -> R.drawable.restaurant
                    2 -> R.drawable.spa
                    3 -> R.drawable.gym
                    else -> R.drawable.promo
                }

                Image(
                    painterResource(id = lowerDrawable), modifier = Modifier
                        .constrainAs(lower) {
                            start.linkTo(parent.start, 1.dp)
                            end.linkTo(parent.end, -1.dp)
                            top.linkTo(parent.top, 1.dp)
                            bottom.linkTo(parent.bottom, -1.dp)
                        }
                        .height(25.dp)
                        .aspectRatio(1.0f), contentDescription = "")

                if (activeTab.intValue != nthTab) {
                    Image(
                        painterResource(id = upperDrawable), modifier = Modifier
                            .constrainAs(upper) {
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                            }
                            .height(25.dp)
                            .aspectRatio(1.0f), contentDescription = "")
                }
            }
            ShadowedLabel(modifier = Modifier.fillMaxWidth(), text = when (nthTab) {
                0 -> "Отели"
                1 -> "Gourmet"
                2 -> "SPA"
                3 -> "Фитнес"
                else -> "Акции"
            })
        }
    }
}