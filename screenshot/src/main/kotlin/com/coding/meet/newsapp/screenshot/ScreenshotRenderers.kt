package com.coding.meet.newsapp.screenshot

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.ImageComposeScene
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asSkiaBitmap
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.apadmi.cmpdevicepreview.core.Appearance
import com.apadmi.cmpdevicepreview.core.DeviceConfiguration
import com.apadmi.cmpdevicepreview.core.DeviceOrientation
import com.apadmi.cmpdevicepreview.core.DevicePreset
import com.apadmi.cmpdevicepreview.preview.DeviceFrameView
import com.apadmi.cmpdevicepreview.preview.DeviceScreenshots
import com.apadmi.cmpdevicepreview.preview.LocalDeviceScreenshots
import com.apadmi.cmpdevicepreview.preview.WebBrowserFrame
import com.coding.meet.newsapp.ui.designsystem.theme.NewsAppTheme
import org.jetbrains.skia.EncodedImageFormat
import org.jetbrains.skia.Image
import java.io.File
import kotlin.math.roundToInt

@Composable
internal fun TwoPaneLayout(
    leftPane: @Composable () -> Unit,
    rightPane: @Composable () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Box(
            modifier = Modifier
                .width(420.dp)
                .fillMaxHeight()
        ) {
            leftPane()
        }
        VerticalDivider(
            modifier = Modifier.fillMaxHeight(),
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.outlineVariant
        )
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        ) {
            rightPane()
        }
    }
}

internal fun renderPhoneScreen(
    preset: DevicePreset,
    outputFile: File,
    isDark: Boolean = true,
    content: @Composable () -> Unit,
) {
    val isPortrait = true
    val bounds = preset.frame?.bodyBounds(preset.portraitSize, isPortrait)
        ?: Rect(Offset.Zero, preset.portraitSize)

    val sceneWidth = (bounds.size.width + 40f).roundToInt()
    val sceneHeight = (bounds.size.height + 40f).roundToInt()

    val screenshots = DeviceScreenshots()
    val config = DeviceConfiguration(
        orientation = DeviceOrientation.Portrait,
        appearance = if (isDark) Appearance.Dark else Appearance.Light,
        isFrameVisible = true,
    )

    val scene = ImageComposeScene(width = sceneWidth, height = sceneHeight) {
        CompositionLocalProvider(LocalDeviceScreenshots provides screenshots) {
            NewsAppTheme(darkTheme = isDark) {
                DeviceFrameView(
                    device = preset,
                    configuration = config,
                    modifier = Modifier.fillMaxSize(),
                ) {
                    content()
                }
            }
        }
    }

    try {
        repeat(10) { frame ->
            Thread.sleep(30)
            scene.render(nanoTime = (frame + 1) * 100_000_000L)
        }
        val captured = screenshots.capture(includeFrame = true)
        if (captured == null) {
            System.err.println("Failed to capture: ${outputFile.name}")
            return
        }
        val pngBytes = Image.makeFromBitmap(captured.asSkiaBitmap())
            .encodeToData(EncodedImageFormat.PNG)
            ?.bytes

        if (pngBytes != null) {
            outputFile.writeBytes(pngBytes)
            println("  -> Saved ${outputFile.name} (${captured.width}x${captured.height}, ${pngBytes.size / 1024} KB)")
        }
    } finally {
        scene.close()
    }
}

internal fun renderDesktopScreen(
    preset: DevicePreset,
    outputFiles: List<File>,
    isDark: Boolean = true,
    content: @Composable () -> Unit,
) {
    val bounds = preset.frame?.bodyBounds(preset.portraitSize, false)
        ?: Rect(Offset.Zero, preset.portraitSize)

    val sceneWidth = (bounds.size.width + 30f).roundToInt()
    val sceneHeight = (bounds.size.height + 30f).roundToInt()

    val screenshots = DeviceScreenshots()
    val config = DeviceConfiguration(
        orientation = DeviceOrientation.Landscape,
        appearance = if (isDark) Appearance.Dark else Appearance.Light,
        isFrameVisible = true,
    )

    val scene = ImageComposeScene(width = sceneWidth, height = sceneHeight) {
        CompositionLocalProvider(LocalDeviceScreenshots provides screenshots) {
            NewsAppTheme(darkTheme = isDark) {
                DeviceFrameView(
                    device = preset,
                    configuration = config,
                    modifier = Modifier.fillMaxSize(),
                ) {
                    content()
                }
            }
        }
    }

    try {
        repeat(10) { frame ->
            Thread.sleep(30)
            scene.render(nanoTime = (frame + 1) * 100_000_000L)
        }
        val captured = screenshots.capture(includeFrame = true)
        if (captured == null) {
            System.err.println("Failed to capture desktop")
            return
        }

        val targetScale = 1600f / captured.width.toFloat()
        val finalImage = captured.scaled(targetScale)
        val skiaBitmap = finalImage.asSkiaBitmap()

        val pngBytes = Image.makeFromBitmap(skiaBitmap).encodeToData(EncodedImageFormat.PNG)?.bytes

        for (file in outputFiles) {
            if (pngBytes != null) {
                file.writeBytes(pngBytes)
                println("  -> Saved ${file.name} (${finalImage.width}x${finalImage.height}, ${pngBytes.size / 1024} KB)")
            }
        }
    } finally {
        scene.close()
    }
}

internal fun renderWebBrowserScreen(
    outputFile: File,
    isDark: Boolean = true,
    content: @Composable () -> Unit,
) {
    val sceneWidth = 1600
    val sceneHeight = 900

    val scene = ImageComposeScene(width = sceneWidth, height = sceneHeight) {
        NewsAppTheme(darkTheme = isDark) {
            WebBrowserFrame(
                title = "News KMP — Top Headlines & Global Stories",
                url = "https://coding-meet.github.io/News-KMP-App/",
                isDark = isDark,
                content = content
            )
        }
    }

    try {
        repeat(10) { frame ->
            Thread.sleep(30)
            scene.render(nanoTime = (frame + 1) * 100_000_000L)
        }
        val skiaImage = scene.render(nanoTime = 1_000_000_000L)
        val pngBytes = skiaImage.encodeToData(EncodedImageFormat.PNG)?.bytes
        if (pngBytes != null) {
            outputFile.writeBytes(pngBytes)
            println("  -> Saved ${outputFile.name} (${sceneWidth}x${sceneHeight}, ${pngBytes.size / 1024} KB)")
        }
    } finally {
        scene.close()
    }
}

private fun ImageBitmap.scaled(scale: Float): ImageBitmap {
    if (scale == 1f) return this
    val targetW = (width * scale).roundToInt().coerceAtLeast(1)
    val targetH = (height * scale).roundToInt().coerceAtLeast(1)
    val scaledImage = ImageBitmap(targetW, targetH)
    CanvasDrawScope().draw(Density(1f), LayoutDirection.Ltr, Canvas(scaledImage), Size(targetW.toFloat(), targetH.toFloat())) {
        scale(scale, scale, pivot = Offset.Zero) {
            drawImage(this@scaled)
        }
    }
    return scaledImage
}

