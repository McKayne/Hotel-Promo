package com.elnico.hotelpromo.uicomponents

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.elnico.hotelpromo.DebugTempHolder
import com.elnico.hotelpromo.R
import com.elnico.hotelpromo.ui.auth.signup.SignUpViewModel
import com.elnico.hotelpromo.ui.theme.GoldColor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun ShadowedAvatar(modifier: Modifier, avatar: MutableState<Bitmap?>, clickAction: () -> Unit) {
    val width = LocalConfiguration.current.screenWidthDp / 3

    val viewModel = koinViewModel<SignUpViewModel>()

    val imageHandlerScope = rememberCoroutineScope()

    val activity = LocalContext.current as Activity

    // Photo Picker
    val avatarLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val uri = result.data?.data

                if (uri != null) {
                    imageHandlerScope.launch {
                        withContext(Dispatchers.IO) {
                            val contentResolver = activity.contentResolver
                            contentResolver.openInputStream(uri).use {
                                val bitmap = BitmapFactory.decodeStream(it)
                                DebugTempHolder.avatar = bitmap
                                avatar.value = bitmap
                            }
                        }
                    }
                }
            }
        }

    ConstraintLayout(modifier.fillMaxSize()) {
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
            .border(width = 1.dp, color = GoldColor, shape = RoundedCornerShape(1000.dp))
            .clickable {
                val intent = Intent()
                intent.type = "image/*"
                intent.action = Intent.ACTION_GET_CONTENT
                avatarLauncher.launch(intent)
            }) {
            ConstraintLayout(Modifier.fillMaxSize()) {
                val (lower, upper, img) = createRefs()

                val avatarImg = avatar.value
                if (avatarImg == null) {
                    Image(painterResource(id = R.drawable.photoiconlower), modifier = Modifier
                        .constrainAs(lower) {
                            start.linkTo(parent.start, 1.dp)
                            end.linkTo(parent.end, -1.dp)
                            top.linkTo(parent.top, 1.dp)
                            bottom.linkTo(parent.bottom, -1.dp)
                        }
                        .height(50.dp)
                        .aspectRatio(1.0f), contentDescription = "")

                    Image(painterResource(id = R.drawable.photoicon), modifier = Modifier
                        .constrainAs(upper) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                        }
                        .height(50.dp)
                        .aspectRatio(1.0f), contentDescription = "")
                } else {
                    Image(contentScale = ContentScale.Crop, bitmap = avatarImg.asImageBitmap(), modifier = Modifier
                        .constrainAs(upper) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                        }.clip(RoundedCornerShape(1000.dp))
                        .height(width.dp)
                        .aspectRatio(1.0f), contentDescription = "")
                }
            }
        }
    }
}