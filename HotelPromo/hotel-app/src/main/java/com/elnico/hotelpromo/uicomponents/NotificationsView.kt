package com.elnico.hotelpromo.uicomponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.elnico.hotelpromo.R
import com.elnico.hotelpromo.ui.theme.SuaveRed

@Composable
internal fun NotificationsView(modifier: Modifier, numberOfNotifications: Int) {
    ConstraintLayout(modifier) {
        val (lower, upper, number) = createRefs()

        Image(
            painterResource(id = R.drawable.notificationlower), modifier = Modifier
                .constrainAs(lower) {
                    start.linkTo(parent.start, 1.dp)
                    end.linkTo(parent.end, -1.dp)
                    top.linkTo(parent.top, 1.dp)
                    bottom.linkTo(parent.bottom, -1.dp)
                }
                .height(25.dp)
                .aspectRatio(1.0f), contentDescription = "")

        Image(
            painterResource(id = R.drawable.notification), modifier = Modifier
                .constrainAs(upper) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
                .height(25.dp)
                .aspectRatio(1.0f), contentDescription = "")

        NotificationsNumberView(modifier = Modifier
            .constrainAs(number) {
                end.linkTo(parent.end, -5.dp)
                top.linkTo(parent.top, -5.dp)
            }, numberOfNotifications = numberOfNotifications)
    }
}

@Composable
private fun NotificationsNumberView(modifier: Modifier, numberOfNotifications: Int) {
    Box(
        modifier
            .width(20.dp)
            .height(20.dp)
            .background(color = SuaveRed, shape = RoundedCornerShape(1000.dp))) {
        ConstraintLayout(Modifier.fillMaxSize()) {
            val (number) = createRefs()

            Text(text = numberOfNotifications.toString(), modifier = Modifier.constrainAs(number) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }, color = Color.White, fontFamily = FontFamily(Font(R.font.latolight)), fontSize = 8.sp)
        }
    }
}