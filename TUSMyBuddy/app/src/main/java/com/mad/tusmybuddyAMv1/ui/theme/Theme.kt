package com.mad.tusmybuddyAMv1.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController



private val DarkColorScheme = darkColorScheme(
    primary = Gold80,
    secondary = Gold80,
    tertiary = Gold,
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onSurface = Color.White,
    onTertiary = Custom40
)

private val LightColorScheme = lightColorScheme(
    primary = Gold,
    secondary = Gold,
    tertiary = Gold,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onSurface = Color.Black,
    onTertiary = Custom40

    /*Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

/*

@Composable
fun TUSMyBuddyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}*/


@Composable
fun TUSMyBuddyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    val systemUiController = rememberSystemUiController()
    //Custom theme for the status bar color
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            if(darkTheme){
                window.statusBarColor = 0xFF212121.toInt()
                systemUiController.setSystemBarsColor(
                    color = Color(0xFF212121.toInt())
                )
                WindowCompat.getInsetsController(window, view)?.isAppearanceLightStatusBars = false
            }else{
                window.statusBarColor = Color.White.toArgb()
                systemUiController.setSystemBarsColor(
                    color = Color.White
                )
                WindowCompat.getInsetsController(window, view)?.isAppearanceLightStatusBars = true
            }
        }
    }


    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

