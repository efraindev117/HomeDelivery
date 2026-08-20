package com.xsoft.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xsoft.designsystem.theme.HdColors
import com.xsoft.designsystem.theme.HdPreviewSurface
import com.xsoft.designsystem.theme.HdShapeTokens
import com.xsoft.designsystem.theme.HdSpacing

@Composable
fun HdIconButton(
    imageVector: ImageVector,
    contentDescription: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    FilledIconButton(
        onClick = onClick,
        modifier = modifier.size(34.dp),
        enabled = enabled,
        shape = HdShapeTokens.circle,
        colors = IconButtonDefaults.filledIconButtonColors(
            containerColor = HdColors.surfaceGray,
            contentColor = HdColors.ink,
            disabledContainerColor = HdColors.surfaceGray,
            disabledContentColor = HdColors.mutedIcon,
        ),
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            modifier = Modifier.size(18.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HdIconButtonPreview() {
    HdPreviewSurface {
        Row(horizontalArrangement = Arrangement.spacedBy(HdSpacing.lg)) {
            HdIconButton(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Atrás",
                onClick = {},
            )
            HdIconButton(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Atrás",
                onClick = {},
                enabled = false,
            )
        }
    }
}
