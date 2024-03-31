package com.elnico.hotelpromo.uicomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.elnico.hotelpromo.R
import com.elnico.hotelpromo.ui.theme.GoldColor

@Composable
internal fun ShadowedButton(modifier: Modifier, text: String, clickAction: () -> Unit) {
    ConstraintLayout(modifier) {
        val (lower, upper) = createRefs()

        Box(modifier = Modifier.constrainAs(lower) {
            start.linkTo(parent.start, 1.dp)
            end.linkTo(parent.end, -1.dp)
            top.linkTo(parent.top, 1.dp)
            bottom.linkTo(parent.bottom, -1.dp)
        }.background(color = Color.Black, shape = RoundedCornerShape(10.dp))
            .fillMaxWidth(0.8f)
            .height(40.dp))

        Box(modifier = Modifier.constrainAs(upper) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
        }.background(color = GoldColor, shape = RoundedCornerShape(10.dp))
            .fillMaxWidth(0.8f)
            .height(40.dp).clickable {
                clickAction()
            }) {
            ConstraintLayout(Modifier.fillMaxSize()) {
                val (label) = createRefs()

                Text(text = text, modifier = Modifier.constrainAs(label) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }, textAlign = TextAlign.Center, fontFamily = FontFamily(Font(R.font.latolight)))
            }
        }
    }
}