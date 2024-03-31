package com.elnico.hotelpromo.uicomponents.logo

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.elnico.hotelpromo.R
import com.elnico.hotelpromo.ui.theme.GoldColor

@Composable
internal fun NavBarLogo(modifier: Modifier, text: String) {
    ConstraintLayout(modifier) {
        val (lowerLabel, upperLabel) = createRefs()

        Text(text = text, modifier = Modifier.constrainAs(lowerLabel) {
            start.linkTo(parent.start, 1.dp)
            end.linkTo(parent.end, -1.dp)
            top.linkTo(parent.top, 1.dp)
            bottom.linkTo(parent.bottom, -1.dp)
        }, textAlign = TextAlign.Center, color = Color.Black,
            fontSize = 50.sp, fontFamily = FontFamily(Font(R.font.greatvibes)), lineHeight = 50.sp)

        Text(text = text, modifier = Modifier.constrainAs(upperLabel) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
        }, textAlign = TextAlign.Center, color = GoldColor,
            fontSize = 50.sp, fontFamily = FontFamily(Font(R.font.greatvibes)), lineHeight = 50.sp)
    }
}