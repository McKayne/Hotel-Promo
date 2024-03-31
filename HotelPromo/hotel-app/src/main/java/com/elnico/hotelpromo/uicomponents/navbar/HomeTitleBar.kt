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
import com.elnico.hotelpromo.ui.navigation.NavScreenRoutes
import com.elnico.hotelpromo.uicomponents.NavBarAvatarView
import com.elnico.hotelpromo.uicomponents.NotificationsView
import com.elnico.hotelpromo.uicomponents.ShadowedImage
import com.elnico.hotelpromo.uicomponents.logo.NavBarLogo
import com.elnico.hotelpromo.uicomponents.logo.ShadowedLogo

@Composable
internal fun HomeTitleBar(navController: NavHostController, modifier: Modifier) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
        ),
        navigationIcon = {
            NavBarAvatarView(modifier = Modifier.padding(start = 10.dp).clickable {
                navController.navigate(NavScreenRoutes.PROFILE.value)
            })
        },
        title = {
            NavBarLogo(modifier = Modifier.padding(top = 10.dp), text = "NEOS")
        },
        actions = {
            NotificationsView(modifier = Modifier.padding(end = 10.dp).clickable {
                navController.navigate(NavScreenRoutes.NOTIFICATIONS.value)
            }, 12)
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
    )
}