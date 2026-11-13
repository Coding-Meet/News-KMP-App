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
* **Design System & M3 Tokens**: Production-grade Material Design 3 design system with standardized tokens (`Spacing`, `NewsAppShapes`, `Elevation`, tonal `Color` containers) and reusable atomic components (`NewsCard`, `NewsButton`, `NewsCategoryChip`, `NewsSourceBadge`, `NewsStateView`, `NewsTopAppBar`).
* **Clean Architecture & MVI**: Decoupled Layers (`core`, `data`, `domain`, `designsystem`, `ui`) with unidirectional data flow (State, Intent/Event, Effect).
* **Multiplatform Networking**: Asynchronous HTTP client communication via Ktor 3 with JSON serialization.
* **Image Caching**: Asynchronous image loading and caching using Coil 3.
* **Dependency Injection**: Multiplatform dependency injection using Koin.
* **Logging & Config**: Centralized logging with Kermit and environment config with BuildKonfig.

---

## Design System Architecture 🎨

The UI layer is built upon a standardized, accessible Material Design 3 design system:

* **Tokens (`designsystem/theme/`)**:
  * **Spacing**: 8dp-based geometric grid tokens (`default`, `xs: 4dp`, `sm: 8dp`, `md: 12dp`, `lg: 16dp`, `xl: 24dp`, `xxl: 32dp`, `huge: 48dp`) injected via `LocalSpacing` and accessible through `MaterialTheme.spacing`.
  * **Shapes**: Standardized `NewsAppShapes` from `extraSmall` (4dp) to `extraLarge` (24dp).
  * **Elevation**: Material 3 elevation levels (Level 0 through Level 5) accessible via `MaterialTheme.elevation`.
  * **Colors**: Complete M3 tonal color palette including `surfaceContainerLowest` through `surfaceContainerHighest`, `surfaceDim`, `surfaceBright`, `inverseSurface`, and scrim.
* **Reusable Components (`designsystem/component/`)**:
  * **Cards**: `NewsElevatedCard` and `NewsOutlinedCard` with customizable shapes and colors.
  * **Buttons**: `NewsPrimaryButton`, `NewsOutlinedButton`, and `NewsTextButton` supporting loading states, icons, and text labels.
  * **Badges & Chips**: `NewsSourceBadge` for article sources and `NewsCategoryChip` for category filtering.
  * **State Views**: Unified `NewsLoadingView`, `NewsEmptyView`, and `NewsErrorView` with retry action support.
  * **Top App Bars**: Standard `NewsTopAppBar` and `NewsCenterAlignedTopAppBar` with title overflow handling, M3 surface tinting, and action slots.

---

## Tech Stack & Libraries 🛠️

| Technology / Library | Purpose |
| :--- | :--- |
| [Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform) (1.12.1) | Declarative UI for Android, Desktop, and iOS |
| [Material Icons Extended](https://developer.android.com/reference/kotlin/androidx/compose/material/icons/Icons) (1.7.3) | Unified multiplatform vector icon set replacing static XML drawables |
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

## Screenshots 📸

### Android (Google Pixel 9) 🤖

<div align="center">

| Dark Mode | Light Mode |
| :---: | :---: |
| <img src="image/android/dark/01_headlines_feed.png" width="380" alt="Headlines Dark" /> | <img src="image/android/light/01_headlines_feed.png" width="380" alt="Headlines Light" /> |
| <img src="image/android/dark/02_headlines_shimmer_loading.png" width="380" alt="Shimmer Dark" /> | <img src="image/android/light/02_headlines_shimmer_loading.png" width="380" alt="Shimmer Light" /> |
| <img src="image/android/dark/03_article_detail.png" width="380" alt="Article Detail Dark" /> | <img src="image/android/light/03_article_detail.png" width="380" alt="Article Detail Light" /> |
| <img src="image/android/dark/04_search_empty.png" width="380" alt="Search Empty Dark" /> | <img src="image/android/light/04_search_empty.png" width="380" alt="Search Empty Light" /> |
| <img src="image/android/dark/05_search_results.png" width="380" alt="Search Results Dark" /> | <img src="image/android/light/05_search_results.png" width="380" alt="Search Results Light" /> |
| <img src="image/android/dark/06_bookmarks_empty.png" width="380" alt="Bookmarks Empty Dark" /> | <img src="image/android/light/06_bookmarks_empty.png" width="380" alt="Bookmarks Empty Light" /> |
| <img src="image/android/dark/07_bookmarks_saved.png" width="380" alt="Bookmarks Saved Dark" /> | <img src="image/android/light/07_bookmarks_saved.png" width="380" alt="Bookmarks Saved Light" /> |
| <img src="image/android/dark/08_settings_screen.png" width="380" alt="Settings Dark" /> | <img src="image/android/light/08_settings_screen.png" width="380" alt="Settings Light" /> |
| <img src="image/android/dark/09_theme_selection_dialog.png" width="380" alt="Theme Dialog Dark" /> | <img src="image/android/light/09_theme_selection_dialog.png" width="380" alt="Theme Dialog Light" /> |
| <img src="image/android/dark/10_delete_bookmarks_dialog.png" width="380" alt="Delete Dialog Dark" /> | <img src="image/android/light/10_delete_bookmarks_dialog.png" width="380" alt="Delete Dialog Light" /> |

</div>

---

### iOS (Apple iPhone 16 Pro) 🍎

<div align="center">

| Dark Mode | Light Mode |
| :---: | :---: |
| <img src="image/ios/dark/01_headlines_feed.png" width="380" alt="iOS Headlines Dark" /> | <img src="image/ios/light/01_headlines_feed.png" width="380" alt="iOS Headlines Light" /> |
| <img src="image/ios/dark/02_headlines_shimmer_loading.png" width="380" alt="iOS Shimmer Dark" /> | <img src="image/ios/light/02_headlines_shimmer_loading.png" width="380" alt="iOS Shimmer Light" /> |
| <img src="image/ios/dark/03_article_detail.png" width="380" alt="iOS Article Detail Dark" /> | <img src="image/ios/light/03_article_detail.png" width="380" alt="iOS Article Detail Light" /> |
| <img src="image/ios/dark/04_search_empty.png" width="380" alt="iOS Search Empty Dark" /> | <img src="image/ios/light/04_search_empty.png" width="380" alt="iOS Search Empty Light" /> |
| <img src="image/ios/dark/05_search_results.png" width="380" alt="iOS Search Results Dark" /> | <img src="image/ios/light/05_search_results.png" width="380" alt="iOS Search Results Light" /> |
| <img src="image/ios/dark/06_bookmarks_empty.png" width="380" alt="iOS Bookmarks Empty Dark" /> | <img src="image/ios/light/06_bookmarks_empty.png" width="380" alt="iOS Bookmarks Empty Light" /> |
| <img src="image/ios/dark/07_bookmarks_saved.png" width="380" alt="iOS Bookmarks Saved Dark" /> | <img src="image/ios/light/07_bookmarks_saved.png" width="380" alt="iOS Bookmarks Saved Light" /> |
| <img src="image/ios/dark/08_settings_screen.png" width="380" alt="iOS Settings Dark" /> | <img src="image/ios/light/08_settings_screen.png" width="380" alt="iOS Settings Light" /> |
| <img src="image/ios/dark/09_theme_selection_dialog.png" width="380" alt="iOS Theme Dialog Dark" /> | <img src="image/ios/light/09_theme_selection_dialog.png" width="380" alt="iOS Theme Dialog Light" /> |
| <img src="image/ios/dark/10_delete_bookmarks_dialog.png" width="380" alt="iOS Delete Dialog Dark" /> | <img src="image/ios/light/10_delete_bookmarks_dialog.png" width="380" alt="iOS Delete Dialog Light" /> |

</div>

---

### macOS / Desktop (Adaptive Desktop Window) 🖥️

<div align="center">

| Dark Mode | Light Mode |
| :---: | :---: |
| <img src="image/mac/dark/01_headlines_feed.png" width="460" alt="Desktop Headlines Dark" /> | <img src="image/mac/light/01_headlines_feed.png" width="460" alt="Desktop Headlines Light" /> |
| <img src="image/mac/dark/02_article_detail.png" width="460" alt="Desktop Article Detail Dark" /> | <img src="image/mac/light/02_article_detail.png" width="460" alt="Desktop Article Detail Light" /> |
| <img src="image/mac/dark/03_search_results.png" width="460" alt="Desktop Search Dark" /> | <img src="image/mac/light/03_search_results.png" width="460" alt="Desktop Search Light" /> |
| <img src="image/mac/dark/04_bookmarks_saved.png" width="460" alt="Desktop Bookmarks Dark" /> | <img src="image/mac/light/04_bookmarks_saved.png" width="460" alt="Desktop Bookmarks Light" /> |
| <img src="image/mac/dark/05_settings_screen.png" width="460" alt="Desktop Settings Dark" /> | <img src="image/mac/light/05_settings_screen.png" width="460" alt="Desktop Settings Light" /> |
| <img src="image/mac/dark/06_theme_selection_dialog.png" width="460" alt="Desktop Theme Dialog Dark" /> | <img src="image/mac/light/06_theme_selection_dialog.png" width="460" alt="Desktop Theme Dialog Light" /> |

</div>

---

### Web (Chrome Browser Adaptive Frame) 🌐

<div align="center">

| Dark Mode | Light Mode |
| :---: | :---: |
| <img src="image/web/dark/01_web_headlines_feed.png" width="460" alt="Web Headlines Dark" /> | <img src="image/web/light/01_web_headlines_feed.png" width="460" alt="Web Headlines Light" /> |
| <img src="image/web/dark/02_web_article_detail.png" width="460" alt="Web Article Detail Dark" /> | <img src="image/web/light/02_web_article_detail.png" width="460" alt="Web Article Detail Light" /> |
| <img src="image/web/dark/03_web_search_results.png" width="460" alt="Web Search Dark" /> | <img src="image/web/light/03_web_search_results.png" width="460" alt="Web Search Light" /> |
| <img src="image/web/dark/04_web_bookmarks_saved.png" width="460" alt="Web Bookmarks Dark" /> | <img src="image/web/light/04_web_bookmarks_saved.png" width="460" alt="Web Bookmarks Light" /> |
| <img src="image/web/dark/05_web_settings_screen.png" width="460" alt="Web Settings Dark" /> | <img src="image/web/light/05_web_settings_screen.png" width="460" alt="Web Settings Light" /> |
| <img src="image/web/dark/06_web_theme_selection_dialog.png" width="460" alt="Web Theme Dialog Dark" /> | <img src="image/web/light/06_web_theme_selection_dialog.png" width="460" alt="Web Theme Dialog Light" /> |

</div>

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