package com.example.searchsuggestions.presentation.home_screen

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val DUMMY_QUERY = "dummy_query"

    @Test
    fun test_onEvent_OnQueryUpdated() {
        val viewModel = HomeViewModel()

        viewModel.onEvent(HomeScreenEvents.OnQueryUpdated(DUMMY_QUERY))

        Assert.assertEquals(DUMMY_QUERY, viewModel.uiState.value.query)
    }

}