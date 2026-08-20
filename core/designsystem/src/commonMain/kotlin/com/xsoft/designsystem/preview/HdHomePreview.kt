package com.xsoft.designsystem.preview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xsoft.designsystem.IconsHome
import com.xsoft.designsystem.component.HdBottomNav
import com.xsoft.designsystem.component.HdBottomNavItem
import com.xsoft.designsystem.component.HdButton
import com.xsoft.designsystem.component.HdButtonSize
import com.xsoft.designsystem.component.HdCard
import com.xsoft.designsystem.component.HdProgressBar
import com.xsoft.designsystem.component.HdStatus
import com.xsoft.designsystem.component.HdStatusChip
import com.xsoft.designsystem.theme.HdColors
import com.xsoft.designsystem.theme.HdShapeTokens
import com.xsoft.designsystem.theme.HdSpacing
import com.xsoft.designsystem.theme.HomeDeliveryTheme

@Preview(
    name = "Home — estilos MAYPO",
    showBackground = true,
    widthDp = 390,
    heightDp = 844,
)
@Composable
private fun HdHomePreview() {
    HomeDeliveryTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(HdColors.canvas),
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = HdSpacing.xxl, vertical = HdSpacing.huge),
                verticalArrangement = Arrangement.spacedBy(HdSpacing.xxl),
            ) {
                HomeHeaderPreview()
                DailyProgressPreview()
                Text(
                    text = "Rutas asignadas (3)",
                    color = HdColors.muted,
                    style = MaterialTheme.typography.labelMedium,
                )
                RouteCardPreview(
                    title = "Ruta R-104",
                    subtitle = "5 / 8 paradas · Unidad 12",
                    progress = 0.62f,
                    progressLabel = "5 entregas completadas",
                    status = HdStatus.InProgress,
                    statusLabel = "En progreso",
                )
                RouteCardPreview(
                    title = "Ruta R-105",
                    subtitle = "0 / 6 paradas · Unidad 08",
                    progress = 0f,
                    progressLabel = "Sin iniciar",
                    status = HdStatus.Pending,
                    statusLabel = "Pendiente",
                    showStart = true,
                )
                RouteCardPreview(
                    title = "Ruta R-103",
                    subtitle = "7 / 7 paradas · Unidad 12",
                    progress = 1f,
                    progressLabel = "Ruta finalizada",
                    status = HdStatus.Completed,
                    statusLabel = "Finalizada",
                )
            }
            HdBottomNav(
                items = listOf(
                    HdBottomNavItem(key = "home", label = "Mis Rutas", icon = IconsHome.ic_home),
                    HdBottomNavItem(key = "history", label = "Historial", icon = IconsHome.ic_record),
                    HdBottomNavItem(key = "support", label = "Soporte", icon = IconsHome.ic_support),
                ),
                selectedKey = "home",
                onItemSelected = {},
            )
        }
    }
}

@Composable
private fun HomeHeaderPreview() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column {
            Text(
                text = "Hola, Ana",
                color = HdColors.ink,
                style = MaterialTheme.typography.headlineMedium,
            )
            Spacer(Modifier.height(HdSpacing.xxs))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(HdSpacing.xs),
            ) {
                Box(
                    modifier = Modifier
                        .size(HdSpacing.sm)
                        .clip(HdShapeTokens.circle)
                        .background(HdColors.success),
                )
                Text(
                    text = "Turno Activo",
                    color = HdColors.gray,
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }
        Box(
            modifier = Modifier
                .size(44.dp)
                .clip(HdShapeTokens.circle)
                .background(HdColors.navy),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "A",
                color = HdColors.onPrimary,
                style = MaterialTheme.typography.titleLarge,
            )
        }
    }
}

@Composable
private fun DailyProgressPreview() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(HdShapeTokens.extraLarge)
            .background(HdColors.navy)
            .padding(HdSpacing.xxxl),
    ) {
        Text(
            text = "Progreso general del día",
            color = HdColors.onPrimary.copy(alpha = 0.75f),
            style = MaterialTheme.typography.labelMedium,
        )
        Spacer(Modifier.height(HdSpacing.md))
        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.spacedBy(HdSpacing.sm),
        ) {
            Text(
                text = "12 / 21",
                color = HdColors.onPrimary,
                style = MaterialTheme.typography.displaySmall,
            )
            Text(
                text = "entregas completadas",
                color = HdColors.onPrimary.copy(alpha = 0.8f),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = HdSpacing.xxs),
            )
        }
        Spacer(Modifier.height(HdSpacing.md))
        HdProgressBar(
            progress = 0.57f,
            trackColor = HdColors.progressTrackOnNavy,
            fillColor = HdColors.onPrimary,
        )
        Spacer(Modifier.height(HdSpacing.xs))
        Text(
            text = "57%",
            color = HdColors.onPrimary.copy(alpha = 0.9f),
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.align(Alignment.End),
        )
    }
}

@Composable
private fun RouteCardPreview(
    title: String,
    subtitle: String,
    progress: Float,
    progressLabel: String,
    status: HdStatus,
    statusLabel: String,
    showStart: Boolean = false,
) {
    HdCard(onClick = {}) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = title,
                color = HdColors.ink,
                style = MaterialTheme.typography.titleMedium,
            )
            HdStatusChip(text = statusLabel, status = status)
        }
        Spacer(Modifier.height(HdSpacing.sm))
        Text(
            text = subtitle,
            color = HdColors.gray,
            style = MaterialTheme.typography.bodyMedium,
        )
        Spacer(Modifier.height(HdSpacing.sm))
        HdProgressBar(progress = progress)
        Spacer(Modifier.height(HdSpacing.sm))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = progressLabel,
                color = HdColors.gray,
                style = MaterialTheme.typography.bodyMedium,
            )
            Text(
                text = "${(progress * 100).toInt()}%",
                color = HdColors.ink,
                style = MaterialTheme.typography.labelLarge,
            )
        }
        if (showStart) {
            Spacer(Modifier.height(HdSpacing.lg))
            HdButton(
                text = "Iniciar ruta",
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
                size = HdButtonSize.Medium,
            )
        }
    }
}
