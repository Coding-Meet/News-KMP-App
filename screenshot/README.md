# Screenshot Module 📸

This module is an internal developer tool for generating realistic, device-framed screenshots of the **News KMP App** across multiple platforms (Android, iOS, macOS, and Web) in both **Dark Mode** and **Light Mode**.

---

## ⚠️ Important Note: Keep Commented in `settings.gradle.kts`

By default, the screenshot-related modules **can remain commented out** in [`settings.gradle.kts`](../settings.gradle.kts) when doing normal production app builds:

```kotlin
// Screenshot and Preview functionality only for developer internal screenshot capture
//include(":screenshot")
```

> [!TIP]
> Keeping it commented out ensures everyday Android and Desktop app builds remain lightweight and fast without loading test snapshot dependencies.

---

## 🔍 How It Works

1. **Portable Pre-built JVM JAR (`screenshot/libs/cmpdevicepreview-jvm.jar`)**:
   - The screenshot pipeline uses [CMPDevicePreview](https://github.com/Apadmi-Engineering/CMPDevicePreview) to render realistic device hardware bezels (Google Pixel 9, iPhone 16 Pro Dynamic Island, macOS desktop windows, and Chrome browser frames).
   - This module uses the enhanced pre-compiled JAR (`screenshot/libs/cmpdevicepreview-jvm.jar`) built from [Coding-Meet/CMPDevicePreview](https://github.com/Coding-Meet/CMPDevicePreview), which includes:
     - **Native Dialog Interception**: Direct `ComposeSceneLayer` capture for dialogs (`ThemeSelectionDialog`, `BookmarkDialog`).
     - **Solid Opacity Scrims**: Fast-forwards Material 3 dialog appearance animations to 100% solid opacity.
     - **Chrome Web Frame**: Realistic browser header with window controls, back/forward/refresh buttons, URL pill, and dark/light styling.

2. **Unified Adaptive Layout**:
   - Compact screens (`< 840.dp`, Android & iOS): Single-pane feed, article details, search, bookmarks, and settings.
   - Expanded screens (`>= 840.dp`, macOS & Web): Master-detail two-pane layout with headlines on the left and full article reader on the right.

---

## 🛠️ How to Export Screenshots & Run Tests

### Step 1: Enable the Screenshot Module
In [`settings.gradle.kts`](../settings.gradle.kts), ensure `include(":screenshot")` is uncommented:
```kotlin
include(":screenshot")
```

### Step 2: Export All Screenshots (All Platforms, Dark & Light)
Run the automated export task from the terminal:
```bash
./gradlew :screenshot:exportDeviceScreenshots
```
Screenshots are generated into:
```
image/
├── android/
│   ├── dark/   (10 screens: Headlines, Shimmer, Detail, Search, Bookmarks, Settings, Dialogs)
│   └── light/  (10 screens)
├── ios/
│   ├── dark/   (10 screens: iPhone 16 Pro Dynamic Island)
│   └── light/  (10 screens)
├── mac/
│   ├── dark/   (5 two-pane master-detail desktop window screens)
│   └── light/  (5 two-pane screens)
└── web/
    ├── dark/   (5 Chrome browser window screens)
    └── light/  (5 Chrome browser window screens)
```

### Step 3: Run Fast JVM UI Tests
Run the headless UI verification test suite (completes in ~4 seconds):
```bash
./gradlew :screenshot:test
```
Test results and generated images are saved to `screenshot/build/test-screenshots/`.
