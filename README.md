# 🔍Search Suggestions App
![Kotlin](https://img.shields.io/badge/kotlin-%230095D5.svg?style=for-the-badge&logo=kotlin&logoColor=white)
![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)

Android app that shows search suggestions from DuckDuckGo API

# 📷 Previews
<!-- Download [apk](https://github.com/gargVader/Reactive-News-App/releases/tag/v1) -->

<!-- [Demo video 📹](https://drive.google.com/file/d/1v7DwMtX6meV7R4MmEpq9Ybcdm-laAl8O/view?usp=sharing) -->
<p align="center">
  <img width="250" src="Screenshots/1.png" />
  <img width="250" src="Screenshots/2.png" />
  <img width="250" src="Screenshots/3.png" />
</p>

# 🛠 Tech Sacks & Open Source Libraries

- ViewModel
- Flows
- Hilt
- Jetpack Navigation
- Retrofit
- Moshi

# ⭐ Features
- Click on 🔙 back arrow closes SearchFragment.
- Click on ❌ cross clears the search field.
- Click on ↖ diagonal arrow fills search box with the suggestion.
- Click on 🔍 in keyboard, closes SearchFragment and passes entered query to HomeFragment which then performs search.
# ⭐ Additional Features
- Progress Bar added to indicate loading of suggestions.
- Substring matching the query is highlighted in list of suggestions.
- ❌ Cross icon not visible until some text is entered.
- Basic unit tests added for HomeViewModel & SearchViewModel.
- Github Workflow added to build & run tests for every push.
- [minor] If user returns from SearchFragment without making a search, then previous search results are still shown.
- [minor] Internationalization - String resources added for a Hindi.

# ⭐ Possible Improvements
- Could have added more tests
- Could have added Google Speech to Text. Found it very common on many apps.

# File structure

<details>
    <summary>Click me</summary>

  ```
searchsuggestions
    │   App.kt
    │
    ├───data
    │   ├───remote
    │   │       DuckDuckGoApi.kt
    │   │
    │   └───repository
    │           SearchSuggestionsRepository.kt
    │
    ├───di
    │       AppModule.kt
    │
    └───presentation
        │   MainActivity.kt
        │
        ├───home_screen
        │       HomeFragment.kt
        │       HomeScreenEvents.kt
        │       HomeState.kt
        │       HomeViewModel.kt
        │
        └───search_screen
                SearchFragment.kt
                SearchScreenEvents.kt
                SearchState.kt
                SearchSuggestionsAdapter.kt
                SearchViewModel.kt
  ```

</details>

