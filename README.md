# iTunes Mobile

Written in Kotlin + MVVM pattern.

## Download

Download APK Installer [here](https://www.dropbox.com/s/0svwineujv7d16h/itunes-discover_1.0.apk?dl=1)

![Master List](https://raw.githubusercontent.com/mayongnaise/itunes-discover/master/itunes-discover-master-list.jpg) ![Detail View](https://raw.githubusercontent.com/mayongnaise/itunes-discover/master/itunes-discover-detail-view2.jpg) ![Detail View Collapsed](https://raw.githubusercontent.com/mayongnaise/itunes-discover/master/itunes-discover-detail-view-collapse.jpg)

## Minimum SDK API 23+


<a id="libraries"></a>
## Libraries

--------------
* [Foundation][0] - Components for core system capabilities, Kotlin extensions and support for
  multidex and automated testing.
   * [AndroidX][1] - AndroidX is a major improvement to the original Android Support Library, which is no longer maintained. androidx packages fully replace the Support Library by providing feature parity and new libraries.In addition
   * [Android KTX][2] - Write more concise, idiomatic Kotlin code.
   * [Test][3] - An Android testing framework for unit and runtime UI tests.
* [Architecture][4] - A collection of libraries that help you design robust, testable, and
  maintainable apps. Start with classes for managing your UI component lifecycle and handling data
  persistence.
   * [Lifecycles][5] - Create a UI that automatically responds to lifecycle events.
   * [Coroutines Flow][6] - Flows are built on top of coroutines and can provide multiple values.
   * [Room][7] - Access your app's SQLite database with in-app objects and compile-time checks.
   * [ViewModel][8] - Store UI-related data that isn't destroyed on app rotations. Easily schedule
     asynchronous tasks for optimal execution.
* [UI][9] - Details on why and how to use UI Components in your apps - together or separate
   * [Animations & Transitions][10] - Move widgets and transition between screens.
   * [Fragment][12] - A basic unit of composable UI.
   * [Layout][13] - Lay out widgets using different algorithms.
* Third party
   * [Retrofit][14] A pragmatic lightweight dependency injection framework for Kotlin developers
   * [Hilt][15] Hilt is a dependency injection library for Android that reduces the boilerplate of doing manual dependency injection in your project.
   * [Kotlin Coroutines][16] for managing background threads with simplified code and reducing needs for callbacks

[0]: https://developer.android.com/jetpack/components
[1]: https://developer.android.com/jetpack/androidx
[2]: https://developer.android.com/kotlin/ktx
[3]: https://developer.android.com/training/testing/
[4]: https://developer.android.com/jetpack/arch/
[5]: https://developer.android.com/topic/libraries/architecture/lifecycle
[6]: https://developer.android.com/kotlin/flow
[7]: https://developer.android.com/topic/libraries/architecture/room
[8]: https://developer.android.com/topic/libraries/architecture/viewmodel
[9]: https://developer.android.com/guide/topics/ui
[10]: https://developer.android.com/training/animation/
[11]: https://developer.android.com/training/constraint-layout/motionlayout
[12]: https://developer.android.com/guide/components/fragments
[13]: https://developer.android.com/guide/topics/ui/declaring-layout
[14]: https://square.github.io/retrofit/
[15]: https://developer.android.com/training/dependency-injection/hilt-android
[16]: https://kotlinlang.org/docs/reference/coroutines-overview.html

## Features

1. Display result list based on iTunes Search API call. Each item or track on the list displayed the following:

   * Track Name
   * ArtWork
   * Track Price
   * Primary Genre
  
2. Display a detailed view of the track selected by the user from the list. Aside from the above details, the detailed view also list the following:

   * Long description / Short description
  
3. Default value of search parameters are provided on first load, but the application caters to <b>dynamic searching</b>.
  
   - Search Field - add Text Watcher to listen to any user inputs
  
4. Data Persistence

   - Last date of visit - displayed above the result list.
  
   - Last visited screen - application remembers the last activity visited and its content. This is important so the user can still retrieved the last data displayed even if they accidentally kill the app.
   
   - Last track selected - application remembers the last tracked opened so when the app is destroyed, it has the ability to go to the Detail if it is the last screen visited, and retrieve the value of the track based on this saved id.
  
5. Offline Support 

   - Even though the network is not available on the user device, result list will be retrieved from a cached repository.

## UI & Design
### General use of Android Material Design

   - Added [Shared Element Transitions](https://developer.android.com/training/transitions/start-activity) from the master list view to the detailed view.
  
   - Added [CollapsingToolbarLayout](https://developer.android.com/reference/android/support/design/widget/CollapsingToolbarLayout) for the detailed view.
  
   - Added [SwipeRefreshLayout](https://developer.android.com/reference/android/support/v4/widget/SwipeRefreshLayout)
  
   - Use of [CardView](https://developer.android.com/reference/android/support/v7/widget/CardView) and [RecyclerView](https://developer.android.com/reference/android/support/v7/widget/RecyclerView) for the master-detail screens.
  
   - Use of custom fonts: [Montserrat](https://www.fontsquirrel.com/fonts/montserrat) and [SF UI Display](https://www.cufonfonts.com/font/sf-ui-text-2)
  
   - Use of [Glide](https://square.github.io/picasso/) as image loader
  

## References

iTunes Web Service Documentation: https://affiliate.itunes.apple.com/resources/documentation/itunes-store-web-service-search-api/#searching)

## Other Libraries

   - [Retrofit](https://square.github.io/retrofit/)
   - [RxJava](https://github.com/ReactiveX/RxAndroid)
   - [Dagger2](https://github.com/google/dagger)
   - [SDP](https://github.com/intuit/sdp)
