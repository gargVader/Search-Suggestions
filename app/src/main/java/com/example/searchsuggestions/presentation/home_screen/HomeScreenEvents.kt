package com.example.searchsuggestions.presentation.home_screen

sealed interface HomeScreenEvents {
    data class OnQueryUpdated(val query: String) : HomeScreenEvents
}