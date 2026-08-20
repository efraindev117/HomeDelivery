package com.xsoft.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xsoft.designsystem.theme.HdColors
import com.xsoft.designsystem.theme.HdPreviewSurface
import com.xsoft.designsystem.theme.HdShapeTokens
import com.xsoft.designsystem.theme.HdSpacing

@Composable
fun HdTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null,
    enabled: Boolean = true,
    singleLine: Boolean = true,
    minLines: Int = 1,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    val interactionSource = remember { MutableInteractionSource() }
    Column(modifier = modifier.fillMaxWidth()) {
        if (!label.isNullOrBlank()) {
            Text(
                text = label,
                color = HdColors.gray,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(bottom = HdSpacing.xxs),
            )
        }
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .clip(HdShapeTokens.small)
                .border(1.dp, HdColors.outline, HdShapeTokens.small)
                .background(HdColors.surface)
                .padding(HdSpacing.md)
                .then(
                    if (!singleLine) Modifier.heightIn(min = 70.dp) else Modifier
                ),
            enabled = enabled,
            textStyle = MaterialTheme.typography.bodyMedium.copy(
                color = if (enabled) HdColors.ink else HdColors.muted,
            ),
            keyboardOptions = keyboardOptions,
            singleLine = singleLine,
            minLines = if (singleLine) 1 else minLines.coerceAtLeast(3),
            cursorBrush = SolidColor(HdColors.navy),
            visualTransformation = visualTransformation,
            interactionSource = interactionSource,
            decorationBox = { innerTextField ->
                Box {
                    if (value.isEmpty() && !placeholder.isNullOrBlank()) {
                        Text(
                            text = placeholder,
                            color = HdColors.mutedIcon,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                    innerTextField()
                }
            },
        )
    }
}

@Composable
fun HdTextArea(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null,
    enabled: Boolean = true,
    minLines: Int = 3,
) {
    HdTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        label = label,
        placeholder = placeholder,
        enabled = enabled,
        singleLine = false,
        minLines = minLines,
    )
}

@Preview(showBackground = true)
@Composable
private fun HdTextFieldPreview() {
    HdPreviewSurface {
        Column(verticalArrangement = Arrangement.spacedBy(HdSpacing.lg)) {
            HdTextField(
                value = "",
                onValueChange = {},
                label = "Nombre completo",
                placeholder = "Nombre de la persona",
            )
            HdTextArea(
                value = "",
                onValueChange = {},
                label = "Justificación",
                placeholder = "Motivo por el que esta persona recibe la entrega...",
            )
        }
    }
}
