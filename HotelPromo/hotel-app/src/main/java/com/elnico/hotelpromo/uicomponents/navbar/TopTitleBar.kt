@file:OptIn(ExperimentalMaterial3Api::class)

package com.elnico.hotelpromo.uicomponents.navbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.elnico.hotelpromo.uicomponents.ShadowedImage
import com.elnico.hotelpromo.uicomponents.ShadowedLabel

@Composable
internal fun TopTitleBar(
    navController: NavHostController, modifier: Modifier, text: String
) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
        ),
        navigationIcon = {
            ShadowedImage(modifier = Modifier.padding(start = 10.dp).clickable {
                navController.popBackStack()
            })
            /*if (isWhiteColor) {
                WhiteShadowedImage(
                    drawable = R.drawable.whiteback,
                    drawableL = R.drawable.lwhiteback,
                    modifier = Modifier
                        .padding(start = 12.dp)
                        .clickable { navController.navigateUp() },
                )
            } else {
                Image(
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .clickable {
                            if (customBackAction != null) {
                                customBackAction()
                            } else {
                                navController.navigateUp()
                            }
                        },
                    painter = painterResource(
                        id = if (isRoundedBackIcon) R.drawable.backrounded else
                            if (isWhiteColor)
                                R.drawable.whiteback
                            else
                                R.drawable.back_arrow
                    ), contentDescription = "back_arrow"
                )
            }*/
        },
        title = {
            ShadowedLabel(modifier = Modifier, text = text)
        },
        actions = {
            /*for (button in rightButtons) {
                button()
            }*/
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
    )
}