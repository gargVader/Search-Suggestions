package com.example.searchsuggestions.presentation.search_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.searchsuggestions.data.repository.SearchSuggestionsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
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

    fun onEvent(event: SearchScreenEvents) {
        when (event) {
            is SearchScreenEvents.OnQueryUpdated -> {
                _uiState.update {
                    event.query.run {
                        getSearchSuggestions(this)
                        it.copy(query = this)
                    }
                }
            }
        }
    }

    private fun getSearchSuggestions(query: String) {
        _uiState.update {
            it.copy(isLoading = true)
        }
        viewModelScope.launch {
            val suggestions = repo.getSearchSuggestions(query)
            _uiState.update {
                it.copy(searchSuggestionsList = suggestions, isLoading = false)
            }
        }
    }


}