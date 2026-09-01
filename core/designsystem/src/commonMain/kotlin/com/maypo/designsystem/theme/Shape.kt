package com.maypo.designsystem.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

internal val HdShapes = Shapes(
    extraSmall = RoundedCornerShape(3.dp),
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(6.dp),
    large = RoundedCornerShape(10.dp),
    extraLarge = RoundedCornerShape(14.dp),
)

object HdShapeTokens {
    val extraSmall = RoundedCornerShape(3.dp)
    val small = RoundedCornerShape(4.dp)
    val medium = RoundedCornerShape(6.dp)
    val large = RoundedCornerShape(10.dp)
    val card = RoundedCornerShape(10.dp)
    val extraLarge = RoundedCornerShape(14.dp)
    val pill = RoundedCornerShape(percent = 50)
    val circle = CircleShape
}
