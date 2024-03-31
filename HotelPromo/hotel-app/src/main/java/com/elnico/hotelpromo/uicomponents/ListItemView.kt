package com.elnico.hotelpromo.uicomponents

import android.util.DisplayMetrics
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

@Composable
internal fun ListItemView(
    modifier: Modifier, name: String, drawable: Int, secondLine: String, thirdLine: String, addGradient: Boolean = true, clickAction: () -> Unit
) {
    val activity = LocalContext.current as ComponentActivity
    val displayMetrics: DisplayMetrics = activity.resources.displayMetrics

    val ratio = 2.0f

    val coverSideOffset = 10

    Box(modifier = modifier
        .width((displayMetrics.widthPixels / displayMetrics.density).dp)
        .aspectRatio(ratio)
        .clickable {
            clickAction()
        }) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (cover, coverL, gradient, info) = createRefs()

            Box(
                modifier = Modifier
                    .background(
                        color = Color(red = 0, green = 0, blue = 0, alpha = 50),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .constrainAs(coverL) {
                        start.linkTo(parent.start, 11.dp)
                        end.linkTo(parent.end, 9.dp)
                        top.linkTo(parent.top, 11.dp)
                        bottom.linkTo(parent.bottom, 9.dp)
                        width = Dimension.fillToConstraints
                        height = Dimension.fillToConstraints
                    }
            )

            ListItemCover(modifier = Modifier.constrainAs(cover) {
                start.linkTo(parent.start, (coverSideOffset).dp)
                end.linkTo(parent.end, (coverSideOffset).dp)
                top.linkTo(parent.top, 10.dp)
                bottom.linkTo(parent.bottom, 10.dp)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            }, drawable)

            if (addGradient) {
                Box(
                    modifier = Modifier
                        .constrainAs(gradient) {
                            start.linkTo(parent.start, (coverSideOffset).dp)
                            end.linkTo(parent.end, (coverSideOffset).dp)
                            top.linkTo(parent.top, 10.dp)
                            bottom.linkTo(parent.bottom, 10.dp)
                            width = Dimension.fillToConstraints
                            height = Dimension.fillToConstraints
                        }
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black
                                )
                            ), shape = RoundedCornerShape(8.dp)
                        ).fillMaxSize()
                )
            }

            Column(Modifier.constrainAs(info) {
                start.linkTo(parent.start, (coverSideOffset * 2).dp)
                end.linkTo(parent.end, (coverSideOffset * 2).dp)
                bottom.linkTo(parent.bottom, 20.dp)
                width = Dimension.fillToConstraints
            }) {
                ShadowedBoldLabel(modifier = Modifier, text = name, fontSize = 30)
                ShadowedRegularNameView(modifier = Modifier, text = secondLine)
                ShadowedNameView(modifier = Modifier, text = thirdLine)
            }

            /*if (loggedUserID != hypelist.authorID) {
                Row(modifier = Modifier
                    .constrainAs(avatar) {
                        start.linkTo(parent.start, 26.5.dp)
                        top.linkTo(parent.top, 22.dp)
                    }
                    .clickable {
                        navController.navigate(NavScreenRoutes.OTHERSPROFILE.value)
                    }, horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment =  Alignment.CenterVertically) {
                    Image(
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .clip(RoundedCornerShape(15.dp))
                            .width(30.dp)
                            .height(30.dp),
                        painter = painterResource(id = R.drawable.debug_avatar),
                        contentDescription = "cover")

                    WhiteShadowedText(
                        modifier = Modifier,
                        text = hypelist.author ?: "Anonymous",
                        fontSize = 16.sp
                    )
                }
            }

            Column(modifier = Modifier.constrainAs(name) {
                start.linkTo(parent.start, 26.5.dp)
                end.linkTo(parent.end, 80.dp)
                bottom.linkTo(parent.bottom, 18.dp)
                width = Dimension.fillToConstraints
            }, horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(5.dp)) {
                WhiteShadowedText(
                    modifier = Modifier,
                    text = if (hypelist.items.size == 1) "${hypelist.items.size} item" else "${hypelist.items.size} items",
                    fontSize = 16.sp
                )

                WhiteShadowedText(
                    modifier = Modifier,
                    text = hypelistName,
                    fontSize = 24.sp,
                    font = R.font.hkgroteskbold,
                    isSingleLine = true,
                    customAlign = TextAlign.Left
                )
            }

            if (loggedUserID == hypelist.authorID) {
                WhiteShadowedImage(modifier = Modifier.constrainAs(privateStatus) {
                    start.linkTo(parent.start, 26.5.dp)
                    top.linkTo(parent.top, 22.dp)
                }, drawable = if (hypelist.isPrivate) R.drawable.viewprivate else R.drawable.viewpublic
                    , drawableL = if (hypelist.isPrivate) R.drawable.lviewprivate else R.drawable.lviewpublic)
            }

            ///

            val interactionSource = remember { MutableInteractionSource() }
            Box(modifier = Modifier//.background(color = Color.Magenta)
                .constrainAs(menu) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                }
                .fillMaxWidth(0.3f)
                .fillMaxHeight(0.4f)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    dialogExpanded = true
                }) {

                ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                    val (image) = createRefs()

                    WhiteShadowedImage(
                        modifier = Modifier.constrainAs(image) {
                            end.linkTo(parent.end, 30.dp)
                            top.linkTo(parent.top, 22.dp)
                        },
                        drawable = R.drawable.hypelistmenu,
                        drawableL = R.drawable.lhypelistmenu
                    )
                }
            }*/

            ///

            /**
             * Set it to true if will be needed again
             */
            /*val allowSavingMyHypelists = false
            if (allowSavingMyHypelists || loggedUserID != hypelist.authorID) {
                Column(modifier = Modifier.constrainAs(favoriteStatus) {
                    end.linkTo(parent.end, 26.5.dp)
                    bottom.linkTo(parent.bottom, 18.dp)
                }, horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    val favored = remember {
                        mutableIntStateOf(Random().nextInt(50) + 10)
                    }

                    WhiteShadowedImage(modifier = Modifier.clickable {
                        hypelistViewModel.changeFavoriteStatusFor(hypelist)
                    }, drawable = if (hypelist.isFavorite) R.drawable.staractive else R.drawable.starinactive,
                        drawableL = if (hypelist.isFavorite) R.drawable.lstaractive else R.drawable.lstarinactive)

                    WhiteShadowedText(
                        modifier = Modifier,
                        text = "${favored.intValue}",
                        fontSize = 16.sp,
                        font = R.font.hkgrotesk
                    )
                }
            }*/
        }
    }
}

@Composable
private fun ListItemCover(modifier: Modifier, drawable: Int) {
    Image(
        contentScale = ContentScale.Crop,
        modifier = modifier.clip(RoundedCornerShape(8.dp)),
        painter = painterResource(id = drawable),
        contentDescription = "stock1"
    )
}