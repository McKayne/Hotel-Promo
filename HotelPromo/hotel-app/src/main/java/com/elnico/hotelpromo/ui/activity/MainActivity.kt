package com.elnico.hotelpromo.ui.activity

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.elnico.hotelpromo.ui.navigation.AppNavigation
import com.elnico.hotelpromo.ui.theme.HotelPromoTheme
import com.elnico.hotelpromo.ui.theme.SplashBackgroundColor

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupScreenDimensions()

        setContent {
            HotelPromoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = SplashBackgroundColor
                ) {
                    AppNavigation(Modifier)
                }
            }
        }
    }

    private fun setupScreenDimensions() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        WindowCompat.setDecorFitsSystemWindows(window, false)
        /*val mRootView = window.decorView.findViewById<View>(android.R.id.content)
        mRootView.viewTreeObserver.addOnGlobalLayoutListener {
            handleKeyboardWithFix()
        }*/
    }
}