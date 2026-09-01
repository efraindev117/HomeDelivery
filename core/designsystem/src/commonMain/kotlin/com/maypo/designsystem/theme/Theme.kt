package com.maypo.designsystem.theme

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomeDeliveryTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = hdLightColorScheme(),
        typography = hdTypography(),
        shapes = HdShapes,
        content = content,
    )
}

@Composable
internal fun HdPreviewSurface(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    HomeDeliveryTheme {
        Surface(color = HdColors.canvas, modifier = modifier) {
            Box(Modifier.padding(HdSpacing.xxl)) {
                content()
            }
        }
    }
}
