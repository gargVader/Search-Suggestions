package com.example.searchsuggestions.presentation.home_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.searchsuggestions.data.repository.SearchSuggestionsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: SearchSuggestionsRepository
) : ViewModel() {

    init {

    }

    fun getSuggestions() {
        viewModelScope.launch {
            val res = repo.getSearchSuggestions("India")
        }
    }

}