package com.xsoft.designsystem.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

/**
 * Tokens MAYPO Hub (Guía de lineamientos web).
 * Los nombres `navy` / `navyTint` se conservan para no cambiar la API de componentes;
 * el valor es el teal primario de marca (#006666).
 */
object HdColors {
    val navy = Color(0xFF006666)
    val ink = Color(0xFF394149)
    val gray = Color(0xFF5F6970)
    val muted = Color(0xFF98A1A7)
    val mutedIcon = Color(0xFF7B848B)
    val canvas = Color(0xFFF4F5F5)
    val surface = Color(0xFFFFFFFF)
    val surfaceMuted = Color(0xFFF5F6F7)
    val surfaceTint = Color(0xFFE6F0F0)
    val surfaceGray = Color(0xFFF1F1F1)
    val navyTint = Color(0xFFE6F0F0)
    val disabled = Color(0xFF80B3B3)
    val success = Color(0xFF7AA600)
    val successDark = Color(0xFF5C7A00)
    val successContainer = Color(0xFFEAF5CC)
    val warning = Color(0xFFFF9900)
    val warningContainer = Color(0xFFFFEACC)
    val error = Color(0xFFE2231A)
    val errorContainer = Color(0xFFFBD9D6)
    val info = Color(0xFF006666)
    val infoContainer = Color(0xFFE6F0F0)
    val returned = Color(0xFFCC7A00)
    val returnedContainer = Color(0xFFFFEACC)
    val hairline = Color(0xFFEDF0F1)
    val outline = Color(0xFFDFE3E6)
    val onPrimary = Color(0xFFFFFFFF)
    val progressTrackOnNavy = Color(0x33FFFFFF)
}

internal fun hdLightColorScheme(): ColorScheme = lightColorScheme(
    primary = HdColors.navy,
    onPrimary = HdColors.onPrimary,
    primaryContainer = HdColors.navyTint,
    onPrimaryContainer = HdColors.navy,
    secondary = Color(0xFF99CC00),
    onSecondary = Color(0xFF1F2429),
    secondaryContainer = Color(0xFFEAF5CC),
    onSecondaryContainer = Color(0xFF5C7A00),
    tertiary = HdColors.warning,
    onTertiary = Color(0xFF1F2429),
    tertiaryContainer = HdColors.warningContainer,
    onTertiaryContainer = Color(0xFFCC7A00),
    error = HdColors.error,
    onError = HdColors.onPrimary,
    errorContainer = HdColors.errorContainer,
    onErrorContainer = Color(0xFFB01A13),
    background = HdColors.canvas,
    onBackground = HdColors.ink,
    surface = HdColors.surface,
    onSurface = HdColors.ink,
    surfaceVariant = HdColors.surfaceMuted,
    onSurfaceVariant = HdColors.gray,
    outline = HdColors.outline,
    outlineVariant = HdColors.hairline,
    inverseSurface = HdColors.ink,
    inverseOnSurface = HdColors.onPrimary,
    inversePrimary = Color(0xFF80B3B3),
    scrim = Color(0xFF1F2429),
    surfaceTint = HdColors.navy,
)
