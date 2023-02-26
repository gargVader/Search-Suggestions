package com.example.searchsuggestions.data.repository

import com.example.searchsuggestions.data.remote.DuckDuckGoApi
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import okhttp3.ResponseBody
import javax.inject.Inject

class SearchSuggestionsRepository @Inject constructor(
    private val api: DuckDuckGoApi
) {

    suspend fun getSearchSuggestions(query: String): List<String> {
        if (query.isEmpty() or query.isBlank()) return emptyList()

        val res: ResponseBody = api.getSearchSuggestions(query)

        val moshi: Moshi = Moshi.Builder().build()
        val listAny = Types.newParameterizedType(List::class.java, Any::class.java)
        val adapter: JsonAdapter<List<Any>> = moshi.adapter(listAny)

        val list = adapter.fromJson(res.string())

        val suggestionsList: List<String> = list?.let {
            if (list.size > 1) return@let list[1] as List<String> else emptyList()
        } ?: emptyList()

        return suggestionsList
    }
}