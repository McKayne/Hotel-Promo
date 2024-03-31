package com.elnico.hotelpromo.uicomponents

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.elnico.hotelpromo.R

@Composable
internal fun ShadowedNameView(modifier: Modifier, text: String, fontSize: Int? = null) {
    ConstraintLayout(modifier) {
        val (lower, upper) = createRefs()

        Text(text = text, modifier = Modifier.constrainAs(lower) {
            start.linkTo(parent.start, 1.dp)
            end.linkTo(parent.end, -1.dp)
            top.linkTo(parent.top, 1.dp)
            bottom.linkTo(parent.bottom, -1.dp)
        }, textAlign = TextAlign.Center,
            fontFamily = FontFamily(Font(R.font.latolight)), fontSize = fontSize?.sp ?: TextUnit.Unspecified
        )

        Text(text = text, modifier = Modifier.constrainAs(upper) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
        }, textAlign = TextAlign.Center, color = Color.White,
            fontFamily = FontFamily(Font(R.font.latolight)), fontSize = fontSize?.sp ?: TextUnit.Unspecified)
    }
}

@Composable
internal fun ShadowedRegularNameView(modifier: Modifier, text: String, fontSize: Int? = null) {
    ConstraintLayout(modifier) {
        val (lower, upper) = createRefs()

        Text(text = text, modifier = Modifier.constrainAs(lower) {
            start.linkTo(parent.start, 1.dp)
            end.linkTo(parent.end, -1.dp)
            top.linkTo(parent.top, 1.dp)
            bottom.linkTo(parent.bottom, -1.dp)
        }, textAlign = TextAlign.Center,
            fontFamily = FontFamily(Font(R.font.lato)), fontSize = fontSize?.sp ?: TextUnit.Unspecified
        )

        Text(text = text, modifier = Modifier.constrainAs(upper) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
        }, textAlign = TextAlign.Center, color = Color.White,
            fontFamily = FontFamily(Font(R.font.lato)), fontSize = fontSize?.sp ?: TextUnit.Unspecified)
    }
}