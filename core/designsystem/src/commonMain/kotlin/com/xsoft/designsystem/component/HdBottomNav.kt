package com.xsoft.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xsoft.designsystem.IconsHome
import com.xsoft.designsystem.theme.HdColors
import com.xsoft.designsystem.theme.HdPreviewSurface
import com.xsoft.designsystem.theme.HdSpacing

data class HdBottomNavItem<T>(
    val key: T,
    val label: String,
    val icon: ImageVector,
)

@Composable
fun <T> HdBottomNav(
    items: List<HdBottomNavItem<T>>,
    selectedKey: T,
    onItemSelected: (T) -> Unit,
    modifier: Modifier = Modifier,
) {
    if (items.isEmpty()) {
        return
    }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(HdColors.surface),
    ) {
        HorizontalDivider(thickness = 1.dp, color = HdColors.hairline)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .windowInsetsPadding(WindowInsets.navigationBars)
                .padding(top = HdSpacing.sm, bottom = HdSpacing.sm),
        ) {
            items.forEach { item ->
                val selected = item.key == selectedKey
                val tint = if (selected) HdColors.navy else HdColors.mutedIcon
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .semantics { this.selected = selected }
                        .clickable(role = Role.Tab) { onItemSelected(item.key) }
                        .padding(HdSpacing.xxs),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(HdSpacing.xxs),
                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label,
                        tint = tint,
                        modifier = Modifier.size(18.dp),
                    )
                    Text(
                        text = item.label,
                        color = tint,
                        style = MaterialTheme.typography.labelSmall,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HdBottomNavPreview() {
    HdPreviewSurface {
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
