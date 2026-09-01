package com.maypo.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.maypo.designsystem.theme.HdColors
import com.maypo.designsystem.theme.HdPreviewSurface
import com.maypo.designsystem.theme.HdShapeTokens
import com.maypo.designsystem.theme.HdSpacing

@Composable
fun HdProgressBar(
    progress: Float,
    modifier: Modifier = Modifier,
    trackColor: Color = HdColors.surfaceTint,
    fillColor: Color = HdColors.navy,
) {
    val coerced = progress.coerceIn(0f, 1f)
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(HdSpacing.sm)
            .clip(HdShapeTokens.extraSmall)
            .background(trackColor),
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(coerced)
                .clip(HdShapeTokens.extraSmall)
                .background(fillColor),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HdProgressBarPreview() {
    HdPreviewSurface {
        Column(
            modifier = Modifier
                .clip(HdShapeTokens.extraLarge)
                .background(HdColors.navy)
                .padding(HdSpacing.xxxl),
        ) {
            HdProgressBar(
                progress = 0.62f,
                trackColor = HdColors.progressTrackOnNavy,
                fillColor = HdColors.onPrimary,
            )
        }
    }
}
