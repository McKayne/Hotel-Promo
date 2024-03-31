package com.elnico.hotelpromo.uicomponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.elnico.hotelpromo.DebugTempHolder
import com.elnico.hotelpromo.R
import com.elnico.hotelpromo.ui.theme.GoldColor
import com.elnico.hotelpromo.uicomponents.logo.ShadowedLogo

@Composable
internal fun NavBarAvatarView(modifier: Modifier) {
    val width = 50

    ConstraintLayout(modifier) {
        val (roundLower, roundUpper) = createRefs()

        Box(modifier = Modifier
            .constrainAs(roundLower) {
                start.linkTo(parent.start, 1.dp)
                end.linkTo(parent.end, -1.dp)
                top.linkTo(parent.top, 1.dp)
                bottom.linkTo(parent.bottom, -1.dp)
            }
            .width(width.dp)
            .height(width.dp)
            .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(1000.dp)))

        Box(modifier = Modifier
            .constrainAs(roundUpper) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }
            .width(width.dp)
            .height(width.dp)
            .border(width = 1.dp, color = GoldColor, shape = RoundedCornerShape(1000.dp))) {
            ConstraintLayout(Modifier.fillMaxSize()) {
                val (img, vip) = createRefs()

                val avatarImg = DebugTempHolder.avatar
                if (avatarImg != null) {
                    Image(contentScale = ContentScale.Crop, bitmap = avatarImg.asImageBitmap(), modifier = Modifier
                        .constrainAs(img) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                        }
                        .clip(RoundedCornerShape(1000.dp))
                        .height(width.dp)
                        .aspectRatio(1.0f), contentDescription = "")
                } else {
                    ShadowedLogo(modifier = Modifier
                        .constrainAs(img) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                        }, text = "N", fontSize = 20)
                }

                VipStatusView(modifier = Modifier
                    .constrainAs(vip) {
                        end.linkTo(parent.end, -10.dp)
                        top.linkTo(parent.top, -7.5.dp)
                    })
            }
        }
    }
}

@Composable
private fun VipStatusView(modifier: Modifier) {
    Image(painter = painterResource(id = R.drawable.vipstatus), modifier = modifier
        .width(30.dp)
        .height(30.dp), contentDescription = "")
}