package com.coding.meet.newsapp.designsystem.preview

import androidx.compose.ui.tooling.preview.AndroidUiModes
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview


@Preview(
    name = "Phone Light",
    device = Devices.PIXEL_8,
    uiMode = AndroidUiModes.UI_MODE_NIGHT_NO,
    showBackground = true,
    showSystemUi = true
)
@Preview(
    name = "Phone Dark",
    device = Devices.PIXEL_8,
    uiMode = AndroidUiModes.UI_MODE_NIGHT_YES,
    showBackground = true,
    showSystemUi = true
)
annotation class PhonePreview

@Preview(
    name = "Foldable Light",
    device = Devices.PIXEL_9_PRO_FOLD,
    uiMode = AndroidUiModes.UI_MODE_NIGHT_NO,
    showBackground = true,
    showSystemUi = true
)
@Preview(
    name = "Foldable Dark",
    device = Devices.PIXEL_9_PRO_FOLD,
    uiMode = AndroidUiModes.UI_MODE_NIGHT_YES,
    showBackground = true,
    showSystemUi = true
)
annotation class FoldablePreview

@Preview(
    name = "Tablet Light",
    device = Devices.PIXEL_TABLET,
    uiMode = AndroidUiModes.UI_MODE_NIGHT_NO,
    showBackground = true,
    showSystemUi = true
)
@Preview(
    name = "Tablet Dark",
    device = Devices.PIXEL_TABLET,
    uiMode = AndroidUiModes.UI_MODE_NIGHT_YES,
    showBackground = true,
    showSystemUi = true
)
annotation class TabletPreview

@Preview(
    name = "Desktop Light",
    device = Devices.DESKTOP,
    uiMode = AndroidUiModes.UI_MODE_NIGHT_NO,
    showBackground = true,
    showSystemUi = true
)
@Preview(
    name = "Desktop Dark",
    device = Devices.DESKTOP,
    uiMode = AndroidUiModes.UI_MODE_NIGHT_YES,
    showBackground = true,
    showSystemUi = true
)
annotation class DesktopPreview

@Preview(
    name = "TV 1080p",
    device = Devices.TV_1080p,
    showBackground = true,
    showSystemUi = true
)
annotation class TvPreview

@PhonePreview
@FoldablePreview
@TabletPreview
@DesktopPreview
@TvPreview
annotation class AllAdaptivePreviews
