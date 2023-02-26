package com.example.searchsuggestions.presentation.search_screen

sealed interface SearchScreenEvents {
    data class OnQueryUpdated(val query: String) : SearchScreenEvents
}