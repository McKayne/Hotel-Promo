package com.elnico.hotelpromo.uicomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elnico.hotelpromo.R
import com.elnico.hotelpromo.ui.theme.GoldColor

@Composable
internal fun HomeTabBar(
    modifier: Modifier, activeTab: MutableIntState,
    firstTab: MutableState<String>, secondTab: MutableState<String>
) {
    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Row(horizontalArrangement = Arrangement.spacedBy(0.dp)) {
            Column(
                modifier = Modifier.weight(1F).clickable {
                    activeTab.intValue = 0
                }, horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val localDensity = LocalDensity.current
                val textWidth = remember { mutableStateOf(1.dp) }

                Text(
                    text = firstTab.value,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.latolight)),
                        fontWeight = FontWeight(600),
                        color = GoldColor,
                        textAlign = TextAlign.Center,
                    ),
                    modifier = Modifier
                        .onSizeChanged {
                            if (it.width != textWidth.value.value.toInt()) {
                                textWidth.value = (it.width / localDensity.density).dp
                            }
                        },
                )

                if (activeTab.intValue == 0) {
                    Box(
                        modifier = Modifier.fillMaxWidth()
                            //.weight(1F)
                            .height(4.dp).background(color = GoldColor),
                    )
                }
            }

            Column(
                modifier = Modifier.weight(1F).clickable {
                    activeTab.intValue = 1
                }, horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                val localDensity = LocalDensity.current
                val textWidth = remember { mutableStateOf(1.dp) }

                Text(
                    text = secondTab.value,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.latolight)),
                        fontWeight = FontWeight(600),
                        color = GoldColor,
                        textAlign = TextAlign.Center,
                    ),
                    modifier = Modifier
                        .onSizeChanged {
                            if (it.width != textWidth.value.value.toInt()) {
                                textWidth.value = (it.width / localDensity.density).dp
                            }
                        },
                )

                if (activeTab.intValue == 1) {
                    Box(
                        modifier = Modifier.fillMaxWidth()
                            //.weight(1F)
                            .height(4.dp).background(color = GoldColor),
                    )
                }
            }
        }
    }
}