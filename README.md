# News Kotlin Multiplatform App ✨

[![News Kotlin Multiplatform AppPreview](image/News-KMP-App.png)](https://youtu.be/Dq1jcsVQmtY?si=nU6pnpR9jpuy0Cw5)

Click the image above to watch a demo of the News Kotlin Multiplatform App on YouTube.

## Overview

The **News KMP App** is a Kotlin Compose Multiplatform (KMP) project that provides a seamless, consistent news-reading experience across **Android, iOS, Windows, macOS, and Linux**. 

Built with **AGP 9**, **Kotlin 2.4**, and **Compose Multiplatform 1.12**, this project adopts a decoupled multi-module architecture adhering to **Clean Architecture** and the **MVI (Model-View-Intent)** state management pattern.

---

## Project Structure 🏗️

```text
News-KMP-App/
├── shared/         # Multiplatform Library (common Compose UI, MVI, Domain, Data, Room, Ktor, Koin)
├── androidApp/     # Android Host Application (MainActivity, MyApp, Splash API, System Bars)
├── desktopApp/     # Desktop Host Application (JVM Main entry, Window state, Desktop packaging)
├── iosApp/         # iOS Xcode Application (consuming Shared.framework)
├── gradle/         # Version Catalog (libs.versions.toml) & Gradle 9 Wrapper
└── build.gradle.kts
```

---

## Features & Technologies 🚀

* **Top News Headlines**: Stay updated with the day's top news stories.
* **Detailed Content**: Dive deeper into articles with rich metadata and source links.
* **Personalized Search**: Real-time debounce search for articles across various topics.
* **Offline Bookmarks**: Save favorite articles locally with Room Database for offline reading.
* **Dark / Light Mode**: Dynamic theme selection with system default fallback saved via DataStore Preferences.
* **Adaptive Navigation**: Responsive layouts using Material 3 Adaptive Navigation Suite across mobile, tablet, and desktop form factors.
* **Clean Architecture & MVI**: Decoupled Layers (`core`, `data`, `domain`, `designsystem`, `ui`) with unidirectional data flow (State, Intent/Event, Effect).
* **Multiplatform Networking**: Asynchronous HTTP client communication via Ktor 3 with JSON serialization.
* **Image Caching**: Asynchronous image loading and caching using Coil 3.
* **Dependency Injection**: Multiplatform dependency injection using Koin.
* **Logging & Config**: Centralized logging with Kermit and environment config with BuildKonfig.

---

## Tech Stack & Libraries 🛠️

| Technology / Library | Purpose |
| :--- | :--- |
| [Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform) (1.12.1) | Declarative UI for Android, Desktop, and iOS |
| [Kotlin](https://kotlinlang.org/) (2.4.20) | Programming language and multiplatform toolchain |
| [Android Gradle Plugin](https://developer.android.com/studio/releases/gradle-plugin) (9.1.1) | Android & KMP build toolchain |
| [Koin](https://insert-koin.io/) (4.2.2) | Pragmatic lightweight dependency injection |
| [Ktor](https://ktor.io/) (3.6.0) | Multiplatform asynchronous HTTP client |
| [Coil 3](https://coil-kt.github.io/coil/) (3.6.3) | Multiplatform image loading and caching |
| [Room](https://developer.android.com/kotlin/multiplatform/room) (2.8.5) & SQLite (2.7.1) | Multiplatform local persistence with type-safe DAO |
| [DataStore](https://developer.android.com/kotlin/multiplatform/datastore) (1.3.0) | Key-value preferences storage |
| [Jetpack Compose Navigation](https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-navigation-routing.html) | Type-safe multiplatform navigation |
| [BuildKonfig](https://github.com/yshrsmz/BuildKonfig) (0.23.0) | BuildConfig generator for multiplatform targets |
| [Kermit](https://github.com/touchlab/Kermit) (2.2.0) | Centralized multiplatform logger |
| [kotlinx.coroutines](https://github.com/Kotlin/kotlinx.coroutines) (1.11.0) | Asynchronous programming and reactive streams |
| [kotlinx.serialization](https://github.com/Kotlin/kotlinx.serialization) (1.10.0) | JSON parsing and serialization |

---

## Getting Started

### Prerequisites 📋

* JDK 17 or JDK 21
* Android Studio (latest stable or Ladybug / Meerkat+) or IntelliJ IDEA
* Xcode (for iOS build)
* macOS (if building for iOS / macOS desktop target)

### Installation 🛠️

1. **Clone this repository**:
   ```bash
   git clone https://github.com/Coding-Meet/News-KMP-App.git
   ```

2. **Obtain News API Key**:
   * Get a free API key from [NewsAPI.org](https://newsapi.org/).

3. **Configure `local.properties`**:
   * Create or open `local.properties` in the root folder and add your key:
   ```properties
   API_KEY=YOUR_API_KEY_HERE
   ```

---

### Running the App 🚀

* **Android**: Run the `:androidApp` module by selecting the `androidApp` configuration in Android Studio.
* **Desktop**:
  ```bash
  ./gradlew :desktopApp:run
  ```
* **Desktop (Package Distribution)**:
  ```bash
  ./gradlew :desktopApp:packageDmg   # On macOS
  ./gradlew :desktopApp:packageMsi   # On Windows
  ./gradlew :desktopApp:packageDeb   # On Linux
  ```
* **iOS**: Open `iosApp/iosApp.xcodeproj` in Xcode or run the `iosApp` run configuration in Android Studio / Fleet.

---

## Screenshots 💻

### Android

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

### iOS

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

### Windows / Desktop

<table>
  <tr>
    <td><img src="image/windows/new_feature.png" alt="win"></td>
  </tr>
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

---

## Contributing 🤝

Feel free to contribute to this project by submitting issues, pull requests, or providing valuable feedback. Your contributions are always welcome! 🙌

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