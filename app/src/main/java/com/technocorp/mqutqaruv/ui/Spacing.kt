package com.technocorp.mqutqaruv.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Spacing(
    val spacing28: Dp = 28.dp,
    val spacing24: Dp = 24.dp,
    val spacing16: Dp = 16.dp,
    val spacing12: Dp = 12.dp,
    val spacing8: Dp = 8.dp,
    val spacing5: Dp = 5.dp,
    val default: Dp = 0.dp
)

val LocalSpacing = compositionLocalOf { Spacing() }

val MaterialTheme.spacing: Spacing
    @Composable
    @ReadOnlyComposable
    get() = LocalSpacing.current