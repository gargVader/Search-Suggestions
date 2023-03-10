package com.example.searchsuggestions.data.remote

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query


// https://duckduckgo.com/ac?q=hello&type=json
interface DuckDuckGoApi {

    @GET("ac/")
    suspend fun getSearchSuggestions(
        @Query("q") query: String,
        @Query("type") type: String = DEFAULT_TYPE
    ): List<PhraseResponse>

    companion object {
        const val BASE_URL = "https://duckduckgo.com/"

        const val DEFAULT_TYPE = "json"
    }

}