package com.maypo.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.PhotoCamera
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maypo.designsystem.theme.HdColors
import com.maypo.designsystem.theme.HdPreviewSurface
import com.maypo.designsystem.theme.HdShapeTokens
import com.maypo.designsystem.theme.HdSpacing

enum class HdCaptureStyle {
    Block,
    Footer,
}

@Composable
fun HdCaptureRow(
    captured: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    idleLabel: String = "Tomar foto",
    capturedLabel: String = "Foto capturada",
    style: HdCaptureStyle = HdCaptureStyle.Footer,
    enabled: Boolean = true,
) {
    val label = if (captured) capturedLabel else idleLabel
    val background = if (captured) HdColors.navyTint else HdColors.surfaceMuted
    val verticalPadding = if (captured) HdSpacing.xl else 20.dp
    val icon = if (captured) Icons.Filled.Check else Icons.Outlined.PhotoCamera
    val content: @Composable () -> Unit = {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = HdColors.navy,
            modifier = Modifier.size(18.dp),
        )
        Spacer(Modifier.height(HdSpacing.xs))
        Text(
            text = label,
            color = HdColors.navy,
            style = MaterialTheme.typography.labelLarge,
        )
    }
    when (style) {
        HdCaptureStyle.Footer -> {
            Column(modifier = modifier.fillMaxWidth()) {
                HorizontalDivider(thickness = 1.dp, color = HdColors.hairline)
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(background)
                        .clickable(enabled = enabled, role = Role.Button, onClick = onClick)
                        .padding(vertical = verticalPadding),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    content = { content() },
                )
            }
        }
        HdCaptureStyle.Block -> {
            val borderColor = if (captured) HdColors.navy else HdColors.hairline
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .clip(HdShapeTokens.medium)
                    .border(1.dp, borderColor, HdShapeTokens.medium)
                    .background(background)
                    .clickable(enabled = enabled, role = Role.Button, onClick = onClick)
                    .padding(vertical = verticalPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                content = { content() },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HdCaptureRowPreview() {
    HdPreviewSurface {
        Column(verticalArrangement = Arrangement.spacedBy(HdSpacing.xxl)) {
            HdCard {
                Text(
                    text = "Identificación oficial",
                    color = HdColors.ink,
                    style = MaterialTheme.typography.labelLarge,
                )
            }
            HdCaptureRow(captured = false, onClick = {}, style = HdCaptureStyle.Footer)
            HdCaptureRow(captured = true, onClick = {}, style = HdCaptureStyle.Block)
        }
    }
}
