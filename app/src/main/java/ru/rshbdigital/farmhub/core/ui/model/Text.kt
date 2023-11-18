package ru.rshbdigital.farmhub.core.ui.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.platform.LocalContext

@Immutable
sealed class Text {
    @Immutable
    data class Resource(@StringRes val resId: Int) : Text()

    @Immutable
    data class ResourceParams(
        @StringRes val value: Int,
        val args: List<Any>,
    ) : Text()

    @Immutable
    data class Simple(val text: String) : Text()
}

@Composable
fun Text.getString(): String = when (this) {
    is Text.Resource -> LocalContext.current.getString(resId)
    is Text.ResourceParams -> LocalContext.current.getString(value, *args.toTypedArray())
    is Text.Simple -> text
}
