package com.xsoft.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import homedelivery.core.designsystem.generated.resources.Res
import homedelivery.core.designsystem.generated.resources.lato_bold
import homedelivery.core.designsystem.generated.resources.lato_regular
import homedelivery.core.designsystem.generated.resources.montserrat_bold
import homedelivery.core.designsystem.generated.resources.montserrat_extrabold
import homedelivery.core.designsystem.generated.resources.montserrat_medium
import homedelivery.core.designsystem.generated.resources.montserrat_regular
import homedelivery.core.designsystem.generated.resources.montserrat_semibold
import org.jetbrains.compose.resources.Font

@Composable
internal fun montserratFamily(): FontFamily = FontFamily(
    Font(Res.font.montserrat_regular, FontWeight.Normal),
    Font(Res.font.montserrat_medium, FontWeight.Medium),
    Font(Res.font.montserrat_semibold, FontWeight.SemiBold),
    Font(Res.font.montserrat_bold, FontWeight.Bold),
    Font(Res.font.montserrat_extrabold, FontWeight.ExtraBold),
)

@Composable
internal fun latoFamily(): FontFamily = FontFamily(
    Font(Res.font.lato_regular, FontWeight.Normal),
    Font(Res.font.lato_bold, FontWeight.Bold),
)
