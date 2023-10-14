# iTunes Mobile

Android Coding Challenge using iTunes Search API. Written in Kotlin + Clean Architecture/MVVM pattern.

## Download

Download APK from Bitrise [here](https://app.bitrise.io/app/a65b4597-e4c4-423d-8003-f9b22e650289/build/0912b263-09a4-4ccd-85db-06ad5d8ac177/artifact/3f192fed80a714b1/p/be6693e9aa001d01e9cc9e2bdd55be20)

## Preview
https://github.com/mayannpalencia/itunes-mobile/assets/49086494/f77c8943-7a71-43fd-9a16-8af52cf337cc

## Setup 
* Minimum SDK API 26
* Target SDK API 34
* Java 17
* Koltin 1.9.0
* AGP 8.1.2
* Android Studio Iguana | 2023.2.1 Canary 4

## Architecture and Patterns
* MVVM - Used in the presentation layer of the app
* Clean Architecture - Used so the app will have separate layers (Data, Domain, Network, Persistence, Presentation/UI) and have reusability
* Repository and Use-Cases
 
## Libraries

--------------
* [Foundation][0]
   * [AndroidX][1]
   * [KotlinX][2]
   * [ActivityX][3]
* [Core][4]
   * [Lifecycles][5]
   * [Flow][6] - I used Flow over LiveData to not worry about life cycle dependencies. Flows emit values inside of suspend functions, so it makes sense to use this in this challenge since the functions are straightforward like grabbing the data and updating the UI
   * [Hilt][7]
   * [Coroutines][8]
   * [ViewModel][9]
* UI
   * [Material Design][10]
   * [ConstraintLayout][11]
   * [Swiperefreshlayout][12]
   * [Shimmer][13]
   * [SDP][14]
   * [Glide][15]
* Network
   * [Retrofit][16]
   * [OkHTTP][17]
   * [Gson Converter][18]
   * [Kotlin Serialization (Properties, JSON)][19]
* Persistence
   * [Room][20] - I used room cause I am familiar with generating SQL queries and it has fewer boilerplate codes
   * [Data store][21] - I used datastore instead of SharedPreferences to take advantage of coroutines and flow
* Unit Test - Implemented in network module
   * [JUnit4][22]
   * [MockWebServer][23]

[0]: https://developer.android.com/jetpack/components
[1]: https://developer.android.com/jetpack/androidx/releases/core
[2]: https://developer.android.com/kotlin/ktx
[3]: https://developer.android.com/jetpack/androidx/releases/activity
[4]: https://developer.android.com/jetpack/arch/
[5]: https://developer.android.com/topic/libraries/architecture/lifecycle
[6]: https://developer.android.com/kotlin/flow
[7]: https://developer.android.com/training/dependency-injection/hilt-android
[8]: https://kotlinlang.org/docs/reference/coroutines-overview.html
[9]: https://developer.android.com/topic/libraries/architecture/viewmodel
[10]: https://m2.material.io/develop/android/docs/getting-started
[11]: https://developer.android.com/develop/ui/views/layout/constraint-layout
[12]: https://developer.android.com/jetpack/androidx/releases/swiperefreshlayout
[13]: https://github.com/facebookarchive/shimmer-android
[14]: https://github.com/intuit/sdp
[15]: https://github.com/bumptech/glide
[16]: https://square.github.io/okhttp/
[17]: https://square.github.io/retrofit/
[18]: https://github.com/square/retrofit/tree/master/retrofit-converters/gson
[19]: https://kotlinlang.org/docs/serialization.html
[20]: https://developer.android.com/jetpack/androidx/releases/room
[21]: https://developer.android.com/topic/libraries/architecture/datastore
[22]: https://junit.org/junit4/
[23]: https://github.com/square/okhttp/tree/master/mockwebserver



## Features

1. Display result list based on iTunes Search API call. Each item or track on the list displayed the following:

   * Track Name
   * Artwork
   * Track Price
   * Primary Genre
  
2. Display a detailed view of the track selected by the user from the list. Aside from the above details, the detailed view also lists the following:

   * Long description
  
3. The default value of search parameters is provided on the first load, but the application caters to <b>dynamic searching</b>.
  
   - Search Field - add Text Watcher to listen to any user inputs
  
4. Favorite Items
   - Marking items as favorites in master/detail screens
  
5. Data Persistence

   - Last date of visit - displayed above the result list.
  
   - Last visited screen - The application remembers the last activity visited and its content. This is important so the user can still retrieve the last data displayed even if they accidentally kill the app.
   
   - Last track selected - The application remembers the last tracked opened so when the app is destroyed, it has the ability to go to the Detail screen if it is the last screen visited, and retrieve the value of the track based on this saved ID.
  
6. Offline Support 

   - Even though the network is not available on the user device, the result list will be retrieved from a cached repository.

## UI & Design
### General use of Android Material Design
  
   - Added [CollapsingToolbarLayout](https://developer.android.com/reference/android/support/design/widget/CollapsingToolbarLayout) for the detailed view.
  
   - Added [SwipeRefreshLayout](https://developer.android.com/reference/android/support/v4/widget/SwipeRefreshLayout) for refreshing of list
  
   - Use of [CardView](https://developer.android.com/reference/android/support/v7/widget/CardView) and [RecyclerView](https://developer.android.com/reference/android/support/v7/widget/RecyclerView) for the master-detail screens.
  
   - Use of [Glide](https://square.github.io/picasso/) as image loader
  

## References

iTunes Web Service Documentation: https://affiliate.itunes.apple.com/resources/documentation/itunes-store-web-service-search-api/#searching)
