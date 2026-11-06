package com.coding.meet.newsapp.screenshot

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asSkiaBitmap
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.v2.runComposeUiTest
import com.apadmi.cmpdevicepreview.core.Appearance
import com.apadmi.cmpdevicepreview.core.DeviceConfiguration
import com.apadmi.cmpdevicepreview.core.DeviceOrientation
import com.apadmi.cmpdevicepreview.devices.DevicePresets
import com.apadmi.cmpdevicepreview.preview.DeviceFrameView
import com.apadmi.cmpdevicepreview.preview.DeviceScreenshots
import com.apadmi.cmpdevicepreview.preview.LocalDeviceScreenshots
import com.apadmi.cmpdevicepreview.preview.WebBrowserFrame
import com.coding.meet.newsapp.ui.designsystem.theme.NewsAppTheme
import org.jetbrains.skia.EncodedImageFormat
import org.jetbrains.skia.Image
import java.io.File
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

/**
 * UI / Screenshot Integration Tests running on the Desktop JVM engine via [runComposeUiTest].
 *
 * Captures pixel-perfect, native-resolution device screenshots without any outer window background padding:
 * - Android (Pixel 9 Native Resolution Frame, tightly cropped)
 * - iOS (iPhone 16 Pro Dynamic Island Frame, tightly cropped)
 * - macOS (Large Desktop Window Chassis, tightly cropped)
 * - Web (Chrome Browser Two-Pane Window, tightly cropped)
 * - Dialog Layers (Native Dialog with dark scrim rendered over device screen)
 */
@OptIn(ExperimentalTestApi::class)
class AllPlatformScreenshotTest {

    private val outputDir = File("build/test-screenshots").apply { mkdirs() }

    @Test
    fun captureAndroidPixel9ScreenshotInJvm() = runComposeUiTest {
        val screenshots = DeviceScreenshots()
        setContent {
            CompositionLocalProvider(LocalDeviceScreenshots provides screenshots) {
                NewsAppTheme(darkTheme = true) {
                    DeviceFrameView(
                        device = DevicePresets.pixel9,
                        configuration = DeviceConfiguration(
                            orientation = DeviceOrientation.Portrait,
                            appearance = Appearance.Dark,
                            isFrameVisible = true,
                        ),
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        AdaptiveAppContent(
                            state = AdaptiveScreenState.HEADLINES,
                            isDark = true,
                        )
                    }
                }
            }
        }
        waitForIdle()

        val capturedImage = assertNotNull(
            screenshots.capture(includeFrame = true),
            "Pixel 9 device frame could not be captured",
        )
        val outputFile = File(outputDir, "test_android_pixel9_headlines.png")
        saveImageBitmap(capturedImage, outputFile)

        assertTrue(outputFile.exists() && outputFile.length() > 0, "Android screenshot was not saved!")
        println("✅ Captured Android UI Test Screenshot: ${outputFile.name} (${capturedImage.width}x${capturedImage.height})")
    }

    @Test
    fun captureIosIPhone16ProScreenshotInJvm() = runComposeUiTest {
        val screenshots = DeviceScreenshots()
        setContent {
            CompositionLocalProvider(LocalDeviceScreenshots provides screenshots) {
                NewsAppTheme(darkTheme = true) {
                    DeviceFrameView(
                        device = DevicePresets.iPhone16Pro,
                        configuration = DeviceConfiguration(
                            orientation = DeviceOrientation.Portrait,
                            appearance = Appearance.Dark,
                            isFrameVisible = true,
                        ),
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        AdaptiveAppContent(
                            state = AdaptiveScreenState.ARTICLE_DETAIL,
                            isDark = true,
                        )
                    }
                }
            }
        }
        waitForIdle()

        val capturedImage = assertNotNull(
            screenshots.capture(includeFrame = true),
            "iPhone 16 Pro device frame could not be captured",
        )
        val outputFile = File(outputDir, "test_ios_iphone16_article_detail.png")
        saveImageBitmap(capturedImage, outputFile)

        assertTrue(outputFile.exists() && outputFile.length() > 0, "iOS screenshot was not saved!")
        println("✅ Captured iOS UI Test Screenshot: ${outputFile.name} (${capturedImage.width}x${capturedImage.height})")
    }

    @Test
    fun captureMacDesktopScreenshotInJvm() = runComposeUiTest {
        val screenshots = DeviceScreenshots()
        setContent {
            CompositionLocalProvider(LocalDeviceScreenshots provides screenshots) {
                NewsAppTheme(darkTheme = true) {
                    DeviceFrameView(
                        device = DevicePresets.largeDesktopWindow,
                        configuration = DeviceConfiguration(
                            orientation = DeviceOrientation.Landscape,
                            appearance = Appearance.Dark,
                            isFrameVisible = true,
                        ),
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        AdaptiveAppContent(
                            state = AdaptiveScreenState.HEADLINES,
                            isDark = true,
                        )
                    }
                }
            }
        }
        waitForIdle()

        val capturedImage = assertNotNull(
            screenshots.capture(includeFrame = true),
            "macOS window frame could not be captured",
        )
        val outputFile = File(outputDir, "test_mac_desktop_headlines.png")
        saveImageBitmap(capturedImage, outputFile)

        assertTrue(outputFile.exists() && outputFile.length() > 0, "macOS screenshot was not saved!")
        println("✅ Captured macOS UI Test Screenshot: ${outputFile.name} (${capturedImage.width}x${capturedImage.height})")
    }

    @Test
    fun captureWebBrowserScreenshotInJvm() = runComposeUiTest {
        setContent {
            NewsAppTheme(darkTheme = true) {
                WebBrowserFrame(
                    title = "News KMP — Top Headlines & Global Stories",
                    url = "https://coding-meet.github.io/News-KMP-App/",
                    isDark = true,
                ) {
                    AdaptiveAppContent(
                        state = AdaptiveScreenState.HEADLINES,
                        isDark = true,
                    )
                }
            }
        }
        waitForIdle()

        val rootNode = onRoot()
        val imageBitmap = rootNode.captureToImage()
        val outputFile = File(outputDir, "test_web_browser_headlines.png")
        saveImageBitmap(imageBitmap, outputFile)

        assertTrue(outputFile.exists() && outputFile.length() > 0, "Web browser screenshot was not saved!")
        println("✅ Captured Web Browser UI Test Screenshot: ${outputFile.name} (${imageBitmap.width}x${imageBitmap.height})")
    }

    @Test
    fun captureDialogInterceptionInJvm() = runComposeUiTest {
        val screenshots = DeviceScreenshots()
        setContent {
            CompositionLocalProvider(LocalDeviceScreenshots provides screenshots) {
                NewsAppTheme(darkTheme = true) {
                    DeviceFrameView(
                        device = DevicePresets.pixel9,
                        configuration = DeviceConfiguration(
                            orientation = DeviceOrientation.Portrait,
                            appearance = Appearance.Dark,
                            isFrameVisible = true,
                        ),
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        AdaptiveAppContent(
                            state = AdaptiveScreenState.THEME_DIALOG,
                            isDark = true,
                        )
                    }
                }
            }
        }
        waitForIdle()

        val capturedImage = assertNotNull(
            screenshots.capture(includeFrame = true),
            "Dialog device frame could not be captured",
        )
        val outputFile = File(outputDir, "test_android_pixel9_theme_dialog.png")
        saveImageBitmap(capturedImage, outputFile)

        assertTrue(outputFile.exists() && outputFile.length() > 0, "Dialog screenshot was not saved!")
        println("✅ Captured Dialog Interception UI Test Screenshot: ${outputFile.name} (${capturedImage.width}x${capturedImage.height})")
    }

    private fun saveImageBitmap(imageBitmap: ImageBitmap, destination: File) {
        val skiaBitmap = imageBitmap.asSkiaBitmap()
        val data = Image.makeFromBitmap(skiaBitmap).encodeToData(EncodedImageFormat.PNG)
            ?: error("Failed to encode ImageBitmap to PNG")
        destination.writeBytes(data.bytes)
    }
}
