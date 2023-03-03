package com.example.searchsuggestions.presentation.search_screen

data class SearchState(
    val query: String = "",
    val searchSuggestionsList: List<String>? = emptyList(),
    val isLoading: Boolean = false
)