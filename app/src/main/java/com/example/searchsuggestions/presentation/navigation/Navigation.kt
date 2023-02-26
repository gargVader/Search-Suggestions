package com.example.searchsuggestions.presentation.navigation


import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.searchsuggestions.R
import java.security.InvalidParameterException

enum class Screen { HomeScreen, SearchScreen }

fun Fragment.navigate(to: Screen, from: Screen) {
    if (to == from) {
        throw InvalidParameterException("Can't navigate to $to")
    }
    when (to) {
        Screen.HomeScreen -> {
            findNavController().navigate(R.id.home_fragment)
        }
        Screen.SearchScreen -> {
            findNavController().navigate(R.id.search_fragment)
        }
    }
}
