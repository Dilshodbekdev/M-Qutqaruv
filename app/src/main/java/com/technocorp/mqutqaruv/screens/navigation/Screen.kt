package com.technocorp.mqutqaruv.screens.navigation

import com.technocorp.mqutqaruv.R

sealed class Screen(val route: String) {
    object Register : Screen("register_screen")
    object Maps : Screen("maps_screen")
}

/*
val bottomNavigationItems = listOf(
    BottomModule(Screen.PageScreen.route, "Quran", R.drawable.ic_main),*/
/*
    BottomModule(Screen.QuranScreen.route, "Quran", Icons.Filled.Book),*//*

    BottomModule(Screen.SettingsScreen.route, "Settings", R.drawable.ic_settings)
)

data class BottomModule(
    val screen: String,
    val title: String,
    val painter: Int
)*/
