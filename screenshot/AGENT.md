# 🤖 CMPDevicePreview JVM Engine — Agent & Developer Integration Guide

This guide is designed for **AI coding agents** and **human developers** integrating [`screenshot/libs/cmpdevicepreview-jvm.jar`](libs/cmpdevicepreview-jvm.jar) into **News KMP App**.

---

## 1. Overview & Key Capabilities

`cmpdevicepreview-jvm.jar` is an enhanced, standalone build compiled from [Coding-Meet/CMPDevicePreview](https://github.com/Coding-Meet/CMPDevicePreview) (fork of Apadmi's CMPDevicePreview with native Dialog layer interception and realistic Chrome Web browser framing). It allows you to wrap any Jetpack Compose / Compose Multiplatform screen inside realistic hardware chassis bezels (Pixel, iPhone, Mac, Web Browser) and export pixel-perfect screenshots directly from JVM memory without needing an emulator or physical device.

### 🌟 Exclusive Enhancements in This JAR:
1. **`ComposeSceneLayer` Dialog & Popup Interception**:
   - Captures `AlertDialog`, `Dialog`, and popups directly inside the simulated device container with proper background scrims (e.g. `ThemeSelectionDialog` and `BookmarkDialog`).
2. **Solid Opacity Dialog Scrims**:
   - Fast-forwards Material 3 dialog entrance animations so captured snapshots have solid, high-contrast 100% opacity scrims rather than semi-transparent flashes.
3. **Realistic Chrome Web Browser Frame**:
   - Built-in `WebBrowserFrame` and `WebBrowserTopBar` with macOS traffic lights, active tab, Omnibox address bar, SSL lock, and dark/light styling.
4. **Native High-Res Chassis Capture**:
   - `LocalDeviceScreenshots.capture(includeFrame = true)` captures the internal hardware chassis layer at native pixel density (e.g. 1196×2539 for Pixel 9, 1308×2724 for iPhone 16 Pro) with clean transparent corner cutouts.

---

## 2. Quick Commands

### Run Fast JVM UI Tests
```bash
./gradlew :screenshot:test
```

### Export All Screenshots Across All Platforms (Dark & Light)
```bash
./gradlew :screenshot:exportDeviceScreenshots
```
Exported to `image/`:
- `image/android/dark/` & `image/android/light/` (Google Pixel 9 hardware frame)
- `image/ios/dark/` & `image/ios/light/` (Apple iPhone 16 Pro Dynamic Island frame)
- `image/mac/dark/` & `image/mac/light/` (macOS Two-Pane Desktop Window frame)
- `image/web/dark/` & `image/web/light/` (Chrome Web Browser Two-Pane frame)
