# News Kotlin Multiplatform App ✨

[![News Kotlin Multiplatform AppPreview](image/News-KMP-App.png)](https://youtu.be/Dq1jcsVQmtY?si=nU6pnpR9jpuy0Cw5)

Click the image above to watch a demo of the News Kotlin Multiplatform App on YouTube.

## Overview

The News KMP App is a Kotlin Compose Multiplatform (KMP) project that aims to provide a consistent news reading
experience across multiple platforms, including Android, iOS, Windows, macOS, and Linux. This project leverages Kotlin's
multiplatform capabilities to share code and logic while using Compose for UI, ensuring a seamless and native experience
on each platform.

## Features & Technologies:

* Explore Top News Headlines:
    * Stay updated with the day's top news stories effortlessly.
* Read Detailed Content:
    * Dive deeper into your favorite news articles for comprehensive insights.
* Personalized Search:
    * Easily find news articles tailored to your interests.
* Save for Later:
    * Bookmark articles to read offline and enjoy uninterrupted reading pleasure.
* Cross-Platform Support:
    * Seamless operation on Android, iOS, Windows, macOS, and Linux.
* Declarative UI with Jetpack Compose
    * Shared UI components across Android and desktop.
    * Material Design and Material Design 3 support.
* Smooth Navigation & State Management:
    * Simple navigation using Jetpack Compose Navigation.
* MVVM Architecture:
    * Model-View-ViewModel pattern for separation of concerns.
    * ViewModel management for UI-related data.
* Robust Networking:
    * HTTP client-server communication with Ktor.
    * JSON serialization/deserialization and content negotiation.
* Efficient Image Loading:
    * Image loading and caching with Coil.
* Local Data Storage:
    * Local database management with Room for all platform.
    * Key-value pair storage with DataStore.
* Build Configuration Management:
    * Centralized build configurations using BuildKonfig.

## Libraries 🛠️

- [BuildKonfig](https://github.com/yshrsmz/BuildKonfig) - BuildConfig for Kotlin Multiplatform Project. It currently
  supports embedding values from gradle file.
- [Ktor](https://ktor.io/docs/http-client-multiplatform.html) - Provides multiplatform libraries required to make
  network calls to the REST API.
- [Kermit](https://github.com/touchlab/Kermit) - Kermit by Touchlab is a Kotlin Multiplatform centralized logging
  utility.
- [Room](https://developer.android.com/kotlin/multiplatform/room) - The Room persistence library provides an abstraction
  layer over SQLite to allow for more robust database access while harnessing the full power of SQLite.
- [DataStore](https://developer.android.com/kotlin/multiplatform/datastore) - The DataStore library stores data
  asynchronously, consistently, and transactionally, overcoming some of the drawbacks of SharedPreferences
- [Navigation](https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-navigation-routing.html) Navigation is a
  key part of UI applications that allows users to move between different application screens.
- [kotlinx.coroutines](https://github.com/Kotlin/kotlinx.coroutines) - Library support for Kotlin coroutines with
  multiplatform support.
- [Common ViewModel](https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-viewmodel.html) The Android
  ViewModel approach to building UI can be implemented in common code using Compose Multiplatform.
- [kotlinx.serialization](https://github.com/Kotlin/kotlinx.serialization) - Provides sets of libraries for various
  serialization formats eg. JSON, protocol buffers, CBOR etc.

## Getting Started

### Installation 🛠️

1. Clone this repository:
   ```bash
   git clone https://github.com/Coding-Meet/News-KMP-App.git
   ```

2. Open in the latest version of Android Studio or intellij idea.
3. Before running the project, obtain an API key from [News Api](https://newsapi.org/) .
4. Add a `local.properties` file to the project root.
5. Place your News API key in `local.properties` file as `API_KEY` property.

```properties
API_KEY=YOUR_API_KEY
```

### Run the app on your device or emulator:

- For Android, run the `composeApp` module by selecting the `app` configuration. If you need help with the
  configuration, follow this link
  for [android](https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-multiplatform-create-first-app.html#run-your-application-on-android)
- For iOS, run the `composeApp` module by selecting the `iosApp` configuration. If you need help with the configuration,
  follow this link
  for [Ios](https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-multiplatform-create-first-app.html#run-your-application-on-ios)
- For Desktop, run `./gradlew :composeApp:run`

## Screenshot 💻

## Android

<table>
   <tr>
    <td><img src="image/android/android1.png" alt="android"></td>
    <td><img src="image/android/android2.png" alt="android"></td>
   </tr>
   <tr>
    <td><img src="image/android/android3.png" alt="android"></td>
    <td><img src="image/android/android4.png" alt="android"></td>
   </tr>
   <tr>
    <td><img src="image/android/android5.png" alt="android"></td>
    <td><img src="image/android/android6.png" alt="android"></td>
   </tr>
   <tr>
    <td><img src="image/android/android7.png" alt="android"></td>
    <td><img src="image/android/android8.png" alt="android"></td>
       </tr>
   <tr>
    <td><img src="image/android/android9.png" alt="android"></td>
    <td><img src="image/android/android10.png" alt="android"></td>
   </tr>
   <tr>
    <td><img src="image/android/android11.png" alt="android"></td>
    <td><img src="image/android/android12.png" alt="android"></td>
   </tr>
</table>

## IOS

<table>
   <tr>
    <td><img src="image/ios/ios1.png" alt="ios"></td>
    <td><img src="image/ios/ios2.png" alt="ios"></td>
   </tr>
   <tr>
    <td><img src="image/ios/ios3.png" alt="ios"></td>
    <td><img src="image/ios/ios4.png" alt="ios"></td>
   </tr>
   <tr>
    <td><img src="image/ios/ios5.png" alt="ios"></td>
    <td><img src="image/ios/ios6.png" alt="ios"></td>
   </tr>
   <tr>
    <td><img src="image/ios/ios7.png" alt="ios"></td>
    <td><img src="image/ios/ios8.png" alt="ios"></td>
       </tr>
   <tr>
    <td><img src="image/ios/ios9.png" alt="ios"></td>
    <td><img src="image/ios/ios10.png" alt="ios"></td>
   </tr>
</table>

## Window

<table>
  <tr>
    <td><img src="image/windows/new_feature.png" alt="win"></td>
      </tr>
  <tr>
  <tr>
    <td><img src="image/windows/win1.png" alt="win"></td>
      </tr>
  <tr>
    <td><img src="image/windows/win2.png" alt="win"></td>
  </tr>
  <tr>
    <td><img src="image/windows/win3.png" alt="win"></td>
      </tr>
  <tr>
    <td><img src="image/windows/win4.png" alt="win"></td>
  </tr>
  <tr>
    <td><img src="image/windows/win5.png" alt="win"></td>
      </tr>
  <tr>
    <td><img src="image/windows/win6.png" alt="win"></td>
  </tr>
  <tr>
    <td><img src="image/windows/win7.png" alt="win"></td>
      </tr>
  <tr>
    <td><img src="image/windows/win8.png" alt="win"></td>
  </tr>
  <tr>
    <td><img src="image/windows/win9.png" alt="win"></td>
      </tr>
  <tr>
    <td><img src="image/windows/win10.png" alt="win"></td>
  </tr>
  <tr>
    <td><img src="image/windows/win11.png" alt="win"></td>
  </tr>
</table>

## Contributing 🤝

Feel free to contribute to this project by submitting issues, pull requests, or providing valuable feedback. Your
contributions are always welcome! 🙌

## ❤ Show your support

Give a ⭐️ if this project helped you!

<a href="https://www.buymeacoffee.com/codingmeet" target="_blank">
<img src="https://cdn.buymeacoffee.com/buttons/v2/default-yellow.png" alt="Buy Me A Coffee" width="160">
</a>

Your generosity is greatly appreciated! Thank you for supporting this project.

## Connect with me

[![](https://img.shields.io/badge/Youtube-red?style=for-the-badge&logo=youtube&logoColor=white)](https://youtube.com/@CodingMeet26?si=FuKwU-aBaf_5kukR)
[![](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/coding-meet-a74933273/)
[![](https://img.shields.io/badge/Twitter-1DA1F2?style=for-the-badge&logo=twitter&logoColor=white)](https://twitter.com/CodingMeet)

## Author

**Meet**