package com.example.searchsuggestions.presentation.home_screen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeState())
    val uiState: StateFlow<HomeState> = _uiState.asStateFlow()

    fun onEvent(event: HomeScreenEvents) {
        when (event) {
            is HomeScreenEvents.OnQueryUpdated -> {
                _uiState.update {
                    it.copy(query = event.query)
                }
            }
        }
    }

    fun performSearch() {
        if (uiState.value.query.isNotBlank()) {
            // make api call
        }
    }


}