# πSearch Suggestions App
![Kotlin](https://img.shields.io/badge/kotlin-%230095D5.svg?style=for-the-badge&logo=kotlin&logoColor=white)
![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)

Android app that shows search suggestions from DuckDuckGo API

# π· Previews
Download [apk](https://github.com/gargVader/Search-Suggestions/releases/download/1.0.0/search.suggestions.app.apk)

[Demo video πΉ](https://drive.google.com/file/d/1wN6xWOGRaKZNfruWx5i0QSOFo8OxvwPm/view?usp=share_link)
<p align="center">
  <img width="250" src="Screenshots/1.png" />
  <img width="250" src="Screenshots/2.png" />
  <img width="250" src="Screenshots/3.png" />
  <img width="250" src="Screenshots/4.png" />
  <img width="250" src="Screenshots/5.png" />
</p>

# π  Tech Stacks & Open Source Libraries

- ViewModel
- Flows
- Hilt
- Jetpack Navigation
- Retrofit
- Moshi

# β­ Features
- Click on π back arrow closes SearchFragment.
- Click on β cross clears the search field.
- Click on β diagonal arrow fills search box with the suggestion.
- Click on π in keyboard, closes SearchFragment and passes entered query to HomeFragment which then performs search.
# β­ Additional Features
- Progress Bar added to indicate loading of suggestions.
- Substring matching the query is highlighted in list of suggestions.
- β Cross icon not visible until some text is entered.
- Basic unit tests added for HomeViewModel & SearchViewModel.
- Github Workflow added to build & run tests for every push.
- [minor] If user returns from SearchFragment without making a search, then previous search results are still shown.
- [minor] Internationalization - String resources added for a Hindi.

# β­ Possible Improvements
- Could have added more tests
- Could have added Google Speech to Text. Found it very common on many apps.

# File structure

<details>
    <summary>Click me</summary>

  ```
searchsuggestions
    β   App.kt
    β
    ββββdata
    β   ββββremote
    β   β       DuckDuckGoApi.kt
    β   β
    β   ββββrepository
    β           SearchSuggestionsRepository.kt
    β
    ββββdi
    β       AppModule.kt
    β
    ββββpresentation
        β   MainActivity.kt
        β
        ββββhome_screen
        β       HomeFragment.kt
        β       HomeScreenEvents.kt
        β       HomeState.kt
        β       HomeViewModel.kt
        β
        ββββsearch_screen
                SearchFragment.kt
                SearchScreenEvents.kt
                SearchState.kt
                SearchSuggestionsAdapter.kt
                SearchViewModel.kt
  ```

</details>

