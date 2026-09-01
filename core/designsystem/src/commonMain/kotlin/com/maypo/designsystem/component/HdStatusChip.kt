package com.maypo.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maypo.designsystem.theme.HdColors
import com.maypo.designsystem.theme.HdPreviewSurface
import com.maypo.designsystem.theme.HdShapeTokens
import com.maypo.designsystem.theme.HdSpacing

enum class HdStatus {
    Pending,
    InProgress,
    Completed,
    Incident,
    Delivered,
    Suspended,
    Returned,
    Info,
}

@Composable
fun HdStatusChip(
    text: String,
    status: HdStatus,
    modifier: Modifier = Modifier,
) {
    val (container, content) = statusColors(status)
    Surface(
        modifier = modifier,
        shape = HdShapeTokens.small,
        color = container,
        contentColor = content,
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 9.dp, vertical = HdSpacing.xxs),
            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
        )
    }
}

private fun statusColors(status: HdStatus): Pair<Color, Color> = when (status) {
    HdStatus.Pending -> HdColors.surfaceGray to HdColors.gray
    HdStatus.InProgress -> HdColors.navyTint to HdColors.navy
    HdStatus.Completed -> HdColors.successContainer to HdColors.successDark
    HdStatus.Incident -> HdColors.warningContainer to HdColors.warning
    HdStatus.Delivered -> HdColors.successContainer to HdColors.success
    HdStatus.Suspended -> HdColors.errorContainer to HdColors.error
    HdStatus.Returned -> HdColors.returnedContainer to HdColors.returned
    HdStatus.Info -> HdColors.surfaceTint to HdColors.navy
}

@Preview(showBackground = true)
@Composable
private fun HdStatusChipPreview() {
    HdPreviewSurface {
        Column(verticalArrangement = Arrangement.spacedBy(HdSpacing.sm)) {
            HdStatusChip(text = "Pendiente", status = HdStatus.Pending)
            HdStatusChip(text = "En progreso", status = HdStatus.InProgress)
            HdStatusChip(text = "Finalizada", status = HdStatus.Completed)
            HdStatusChip(text = "Con incidencia", status = HdStatus.Incident)
            HdStatusChip(text = "Entregado", status = HdStatus.Delivered)
            HdStatusChip(text = "Suspendida", status = HdStatus.Suspended)
            HdStatusChip(text = "Devolución", status = HdStatus.Returned)
            HdStatusChip(text = "2/4", status = HdStatus.Info)
        }
    }
}
