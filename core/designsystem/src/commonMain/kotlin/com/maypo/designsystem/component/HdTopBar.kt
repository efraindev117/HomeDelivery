package com.maypo.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maypo.designsystem.theme.HdColors
import com.maypo.designsystem.theme.HdPreviewSurface
import com.maypo.designsystem.theme.HdSpacing

@Composable
fun HdTopBar(
    title: String,
    modifier: Modifier = Modifier,
    onBack: (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(HdColors.surface),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .windowInsetsPadding(WindowInsets.statusBars)
                .padding(horizontal = HdSpacing.xxl, vertical = HdSpacing.xl),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (onBack != null) {
                HdIconButton(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Atrás",
                    onClick = onBack,
                )
                Spacer(Modifier.width(HdSpacing.md))
            }
            Text(
                text = title,
                modifier = Modifier.weight(1f),
                color = HdColors.ink,
                style = MaterialTheme.typography.headlineSmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(HdSpacing.sm),
                verticalAlignment = Alignment.CenterVertically,
                content = actions,
            )
        }
        HorizontalDivider(thickness = 1.dp, color = HdColors.hairline)
    }
}

@Preview(showBackground = true)
@Composable
private fun HdTopBarPreview() {
    HdPreviewSurface {
        Column(verticalArrangement = Arrangement.spacedBy(HdSpacing.lg)) {
            HdTopBar(
                title = "Carga · Ruta R-104",
                onBack = {},
                actions = {
                    HdStatusChip(text = "2/4", status = HdStatus.Info)
                },
            )
            HdTopBar(title = "Detalle de Parada")
        }
    }
}
