package com.example.searchsuggestions.presentation.search_screen

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.searchsuggestions.data.repository.SearchSuggestionsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
class SearchViewModelTest {
    private val testDispatcher = StandardTestDispatcher()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val DUMMY_QUERY = "dummy_query"
    private val DUMMY_SUGGESTIONS = listOf("A", "B", "C")

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
    }

    @Mock
    lateinit var repo: SearchSuggestionsRepository

    @Test
    fun test_onEvent_OnQueryUpdated() = runTest {
        val viewModel = SearchViewModel(repo)
        Mockito.`when`(repo.getSearchSuggestions(DUMMY_QUERY)).thenReturn(DUMMY_SUGGESTIONS)

        viewModel.onEvent(SearchScreenEvents.OnQueryUpdated(DUMMY_QUERY))
        testDispatcher.scheduler.advanceUntilIdle()

        Assert.assertEquals(DUMMY_QUERY, viewModel.uiState.value.query)
        Assert.assertEquals(DUMMY_SUGGESTIONS, viewModel.uiState.value.searchSuggestionsList)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

}