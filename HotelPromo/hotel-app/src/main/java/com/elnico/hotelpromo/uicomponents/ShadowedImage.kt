package com.elnico.hotelpromo.uicomponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.elnico.hotelpromo.R

@Composable
internal fun ShadowedImage(modifier: Modifier) {
    ConstraintLayout(modifier) {
        val (lower, upper) = createRefs()

        Image(painterResource(id = R.drawable.backiconlower), modifier = Modifier.constrainAs(lower) {
            start.linkTo(parent.start, 1.dp)
            end.linkTo(parent.end, -1.dp)
            top.linkTo(parent.top, 1.dp)
            bottom.linkTo(parent.bottom, -1.dp)
        }.height(30.dp), contentDescription = "")

        Image(painterResource(id = R.drawable.backicon), modifier = Modifier.constrainAs(upper) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
        }.height(30.dp), contentDescription = "")
    }
}