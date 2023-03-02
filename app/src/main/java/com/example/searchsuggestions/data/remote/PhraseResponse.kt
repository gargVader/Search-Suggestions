package com.example.searchsuggestions.data.remote

import com.squareup.moshi.Json

data class PhraseResponse(
    @field:Json(name = "phrase")
    val value: String
)