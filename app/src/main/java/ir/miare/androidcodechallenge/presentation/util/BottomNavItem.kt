package ir.miare.androidcodechallenge.presentation.util

import androidx.compose.ui.graphics.painter.Painter

data class BottomNavItem(
    val title: String,
    val route: MyFootMobScreens,
    val icon: Painter
)
