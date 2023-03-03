package com.example.searchsuggestions.data.repository

import android.util.Log
import com.example.searchsuggestions.data.remote.DuckDuckGoApi
import javax.inject.Inject

class SearchSuggestionsRepository @Inject constructor(
    private val api: DuckDuckGoApi
) {

    suspend fun getSearchSuggestions(query: String): List<String>? {
        if (query.isEmpty() or query.isBlank()) return emptyList()
        Log.d("Girish", "apiRequest: query:$query")
        val suggestions = try {
            api.getSearchSuggestions(query).map { it.value }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
        Log.d("Girish", "apiResponse: query:$query $suggestions")
        return suggestions
    }
}