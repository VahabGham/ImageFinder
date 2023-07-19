package com.example.imagefinder.common.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val Space = Spaces()

@Immutable
class Spaces(
    val s1 : Dp = 2.dp,
    val s2 : Dp = 4.dp,
    val s3: Dp = 8.dp,
    val s4: Dp = 16.dp,
    val s5: Dp = 32.dp,
)
