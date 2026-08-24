package com.xsoft.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xsoft.designsystem.theme.HdColors
import com.xsoft.designsystem.theme.HdPreviewSurface
import com.xsoft.designsystem.theme.HdShapeTokens
import com.xsoft.designsystem.theme.HdSpacing

@Composable
fun HdCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    val colors = CardDefaults.cardColors(containerColor = HdColors.surface)
    val border = BorderStroke(1.dp, HdColors.hairline)
    val elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    val shape = HdShapeTokens.card
    val body: @Composable ColumnScope.() -> Unit = {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(HdSpacing.huge),
            content = content,
        )
    }
    if (onClick != null) {
        Card(
            onClick = onClick,
            modifier = modifier.fillMaxWidth(),
            shape = shape,
            colors = colors,
            border = border,
            elevation = elevation,
            content = body,
        )
    } else {
        Card(
            modifier = modifier.fillMaxWidth(),
            shape = shape,
            colors = colors,
            border = border,
            elevation = elevation,
            content = body,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HdCardPreview() {
    HdPreviewSurface {
        HdCard(onClick = {}) {
            Text(
                text = "Ruta R-104",
                color = HdColors.ink,
                style = MaterialTheme.typography.headlineSmall,
            )
            Text(
                text = "Unidad 12 · 8 paradas",
                color = HdColors.gray,
                style = MaterialTheme.typography.bodyMedium,
            )
            Spacer(Modifier.height(HdSpacing.sm))
            HdProgressBar(progress = 0.62f)
        }
    }
}
