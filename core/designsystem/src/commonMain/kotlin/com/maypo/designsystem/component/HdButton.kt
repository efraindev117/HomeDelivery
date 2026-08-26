package com.maypo.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maypo.designsystem.theme.HdColors
import com.maypo.designsystem.theme.HdPreviewSurface
import com.maypo.designsystem.theme.HdShapeTokens
import com.maypo.designsystem.theme.HdSpacing

enum class HdButtonVariant {
    Primary,
    Secondary,
    Neutral,
    Destructive,
}

enum class HdButtonSize {
    Large,
    Medium,
    Compact,
}

@Composable
fun HdButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    variant: HdButtonVariant = HdButtonVariant.Primary,
    size: HdButtonSize = HdButtonSize.Large,
    enabled: Boolean = true,
    leadingIcon: ImageVector? = null,
) {
    val container = if (!enabled) {
        HdColors.disabled
    } else {
        when (variant) {
            HdButtonVariant.Primary -> HdColors.navy
            HdButtonVariant.Destructive -> HdColors.error
            HdButtonVariant.Neutral -> HdColors.surfaceGray
            HdButtonVariant.Secondary -> HdColors.surface
        }
    }
    val content = if (!enabled) {
        HdColors.muted
    } else {
        when (variant) {
            HdButtonVariant.Primary, HdButtonVariant.Destructive -> HdColors.onPrimary
            HdButtonVariant.Neutral -> HdColors.ink
            HdButtonVariant.Secondary -> HdColors.navy
        }
    }
    val border = if (variant == HdButtonVariant.Secondary && enabled) {
        BorderStroke(1.5.dp, HdColors.navy)
    } else {
        null
    }
    val (padding, textStyle, corner) = sizeTokens(size)
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = corner,
        border = border,
        colors = ButtonDefaults.buttonColors(
            containerColor = container,
            contentColor = content,
            disabledContainerColor = HdColors.disabled,
            disabledContentColor = HdColors.muted,
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            disabledElevation = 0.dp,
        ),
        contentPadding = padding,
    ) {
        if (leadingIcon != null) {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null,
                modifier = Modifier.size(18.dp),
            )
            Spacer(Modifier.width(HdSpacing.xs))
        }
        Text(
            text = text,
            style = textStyle,
        )
    }
}

private data class ButtonSizeTokens(
    val padding: PaddingValues,
    val textStyle: TextStyle,
    val corner: RoundedCornerShape,
)

@Composable
private fun sizeTokens(size: HdButtonSize): ButtonSizeTokens {
    val typography = MaterialTheme.typography
    return when (size) {
        HdButtonSize.Large -> ButtonSizeTokens(
            padding = PaddingValues(horizontal = HdSpacing.xxl, vertical = HdSpacing.xl),
            textStyle = typography.titleLarge,
            corner = HdShapeTokens.small,
        )
        HdButtonSize.Medium -> ButtonSizeTokens(
            padding = PaddingValues(horizontal = HdSpacing.xxl, vertical = HdSpacing.lg),
            textStyle = typography.titleMedium,
            corner = HdShapeTokens.small,
        )
        HdButtonSize.Compact -> ButtonSizeTokens(
            padding = PaddingValues(horizontal = HdSpacing.xl, vertical = HdSpacing.md),
            textStyle = typography.labelLarge,
            corner = HdShapeTokens.small,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HdButtonPreview() {
    HdPreviewSurface {
        Column(verticalArrangement = Arrangement.spacedBy(HdSpacing.lg)) {
            HdButton(
                text = "Iniciar entrega",
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
            )
            HdButton(
                text = "Llamar",
                onClick = {},
                variant = HdButtonVariant.Secondary,
                size = HdButtonSize.Compact,
                leadingIcon = Icons.Outlined.Phone,
            )
            HdButton(
                text = "Cancelar",
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
                variant = HdButtonVariant.Neutral,
                size = HdButtonSize.Compact,
            )
            HdButton(
                text = "Enviar incidencia",
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
                variant = HdButtonVariant.Destructive,
                size = HdButtonSize.Medium,
            )
            HdButton(
                text = "Confirmar carga e iniciar ruta",
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
                enabled = false,
            )
        }
    }
}
