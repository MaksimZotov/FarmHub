package ru.rshbdigital.farmhub.core.design

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
)

private val LightColorScheme = lightColorScheme(
    primary = FarmHubColors.BrightGrey,
    onPrimary = FarmHubColors.ColumbiaBlue,

    secondary = FarmHubColors.ColumbiaBlue,
    onSecondary = FarmHubColors.BrightGrey,

    background = FarmHubColors.BrightGrey,
    onBackground = FarmHubColors.EerieBlack,

    surface = FarmHubColors.White,
    onSurface = FarmHubColors.EerieBlack,
)

@Immutable
data class ColorContainer(
    private val light: Color,
    private val dark: Color = light,
) {
    @Composable
    fun get(darkTheme: Boolean = isSystemInDarkTheme()) = if (darkTheme) light else dark
}

object FarmHubTheme {

    @Stable
    val primary = ColorContainer(
        light = FarmHubColors.MaastrichtBlue,
    )

    @Stable
    val onPrimary = ColorContainer(
        light = FarmHubColors.White,
    )

    @Stable
    val secondary = ColorContainer(
        light = FarmHubColors.ColumbiaBlue,
    )

    @Stable
    val onSecondary = ColorContainer(
        light = FarmHubColors.MaastrichtBlue,
    )

    @Stable
    val surface = ColorContainer(
        light = FarmHubColors.White,
    )

    @Stable
    val onSurface = ColorContainer(
        light = FarmHubColors.EerieBlack,
    )

    @Stable
    val background = ColorContainer(
        light = FarmHubColors.BrightGrey,
    )

    @Stable
    val tabSelectedColor = ColorContainer(
        light = FarmHubColors.Celadon,
    )

    @Stable
    val taskSnippetSecondaryInfo = ColorContainer(
        light = FarmHubColors.SilverFoil,
    )

    @Stable
    val taskSnippetTimeInfo = ColorContainer(
        light = FarmHubColors.Emerald,
    )
}

@Composable
fun FarmHubTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit,
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
        content = content
    )
}
