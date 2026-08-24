package com.xsoft.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

data class HomeDeliveryNavigationItem(
    val icon: ImageVector,
    val title: String
)

@Composable
fun <T> HomeDeliveryBottomNavigation(
    items: Map<T, HomeDeliveryNavigationItem>,
    selectedItem: T,
    onItemSelected: (T) -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier,
        bottomBar = {
            NavigationBar {
                items.forEach { (key, item) ->
                    NavigationBarItem(
                        selected = key == selectedItem,
                        onClick = {
                            onItemSelected(key)
                        },
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.title
                            )
                        },
                        label = {
                            Text(item.title)
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        content(paddingValues)
    }
}