package com.example.searchsuggestions.presentation.search_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.searchsuggestions.data.repository.SearchSuggestionsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repo: SearchSuggestionsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchState())
    val uiState: StateFlow<SearchState> = _uiState.asStateFlow()
    var latestApiRequestJob: Job? = null

    fun onEvent(event: SearchScreenEvents) {
        when (event) {
            is SearchScreenEvents.OnQueryUpdated -> {
                _uiState.update {
                    it.copy(query = event.query)
                }
                getSearchSuggestions(_uiState.value.query)
            }
        }
    }

    private fun getSearchSuggestions(query: String) {
        _uiState.update {
            it.copy(isLoading = true)
        }
        latestApiRequestJob?.cancel()
        latestApiRequestJob = viewModelScope.launch {
            val suggestions = repo.getSearchSuggestions(query)
            _uiState.update {
                Log.d("Girish", "uiUpdate: $suggestions")
                it.copy(searchSuggestionsList = suggestions, isLoading = false)
            }
        }
    }


}