package com.morozov.testtaskforviesure.ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class BookAppDimensions(
    //
    val notificationContainerHeight: Dp,

    val screenSidePadding: Dp,
    val cardVerticalPadding: Dp,

    val columnContentItemSpacing: Dp,

    // Bases Spacings
    val basesSpacingsSpacingXxxl32: Dp,
    val basesSpacingsSpacingXxl24: Dp,
    val basesSpacingsSpacingXl20: Dp,
    val basesSpacingsSpacingLg16: Dp,
    val basesSpacingsSpacingMd12: Dp,
    val basesSpacingsSpacingSm8: Dp,

    // Corners
    val basesCornerRadiusRadiusXs4: Dp,

    // Icons
    val iconLargeSize: Dp,
    val iconNormalSize: Dp,
    val iconSmallSize: Dp,
    val iconExtraSmallSize: Dp,
    val playIconSmallSize: Dp,

    // Buttons
    val horizontalButtonPadding: Dp,
    val verticalButtonPadding: Dp,

    //Row
    val rowItemLargeSpacer: Dp,
    val rowItemMediumSpacer: Dp,
    val rowItemSmallSpacer: Dp,
    val rowItemExtraSmallSpacer: Dp,

    //TabRow
    val tabRowContentSpacer: Dp,

    val roundedCornerSmallSize: Dp,
    val roundedCornerMediumSize: Dp,
    val roundedCornerLargeSize: Dp,

    //HeaderText
    val headerTextPaddingTop: Dp,
    val headerTextPaddingBottom: Dp,

    //Grid
    val gridItemSpacer: Dp,
    val gridItemLargeSpacer: Dp,
    val gridItemHorizontalSpacer: Dp,
    val gridItemVerticalSpacer: Dp,

    val buttonHeight: Dp,
    val buttonSidePadding: Dp,

    //Achievement Screen
    val achievementScreenVerticalPaddings: Dp,

    // dialog
    val dialogTopBarHeight: Dp,

    val trailerCardPaddingTop: Dp,

    //Settings
    val settingsTopPadding: Dp,

    // Video
    val videoDetailsSidePadding: Dp,
    val videoComponentTopPadding: Dp,
    val videoComponentBottomPadding: Dp,
    val videoItemExtraLargeSpacer: Dp,
    val videoItemLargeSpacer: Dp,
    val videoItemMediumSpacer: Dp,
    val videoTabRowHeight: Dp,
    val videoTabRowItemPadding: Dp,
)

//Devices with width minimum of 360dp
val DefaultDimensions = BookAppDimensions(
    notificationContainerHeight = 62.dp,

    screenSidePadding = 12.dp,
    cardVerticalPadding = 12.dp,

    columnContentItemSpacing = 24.dp,

    // Bases Spacings
    basesSpacingsSpacingXxxl32 = 32.dp,
    basesSpacingsSpacingXxl24 = 24.dp,
    basesSpacingsSpacingXl20 = 20.dp,
    basesSpacingsSpacingLg16 = 16.dp,
    basesSpacingsSpacingMd12 = 12.dp,
    basesSpacingsSpacingSm8 = 8.dp,

    // Corners
    basesCornerRadiusRadiusXs4 = 4.dp,

    // Icons
    iconLargeSize = 42.dp,
    iconNormalSize = 24.dp,
    iconSmallSize = 20.dp,
    iconExtraSmallSize = 14.dp,
    playIconSmallSize = 16.dp,

    // Buttons
    horizontalButtonPadding = 22.dp,
    verticalButtonPadding = 16.dp,

    //Row
    rowItemLargeSpacer = 12.dp,
    rowItemMediumSpacer = 8.dp,
    rowItemSmallSpacer = 4.dp,
    rowItemExtraSmallSpacer = 2.dp,

    //TabRow
    tabRowContentSpacer = 22.dp,

    roundedCornerSmallSize = 2.dp,
    roundedCornerMediumSize = 4.dp,
    roundedCornerLargeSize = 8.dp,

    //HeaderText
    headerTextPaddingTop = 32.dp,
    headerTextPaddingBottom = 12.dp,

    //Grid
    gridItemSpacer = 2.dp,
    gridItemLargeSpacer = 4.dp,
    gridItemHorizontalSpacer = 20.dp,
    gridItemVerticalSpacer = 24.dp,

    buttonHeight = 56.dp,
    buttonSidePadding = 36.dp,

    //Achievement Screen
    achievementScreenVerticalPaddings = 44.dp,

    //dialog
    dialogTopBarHeight = 64.dp,

    trailerCardPaddingTop = 24.dp,

    //Settings
    settingsTopPadding = 16.dp,

    // Video
    videoDetailsSidePadding = 32.dp,
    videoComponentTopPadding = 28.dp,
    videoComponentBottomPadding = 16.dp,
    videoItemExtraLargeSpacer = 16.dp,
    videoItemLargeSpacer = 12.dp,
    videoItemMediumSpacer = 8.dp,
    videoTabRowHeight = 30.dp,
    videoTabRowItemPadding = 8.dp
)

val SmallDimensions = BookAppDimensions(
    notificationContainerHeight = 52.dp,

    screenSidePadding = 10.dp,
    cardVerticalPadding = 10.dp,

    columnContentItemSpacing = 20.dp,

    // Bases Spacings
    basesSpacingsSpacingXxxl32 = 25.6.dp,
    basesSpacingsSpacingXxl24 = 19.2.dp,
    basesSpacingsSpacingXl20 = 18.dp,
    basesSpacingsSpacingLg16 = 12.8.dp,
    basesSpacingsSpacingMd12 = 9.6.dp,
    basesSpacingsSpacingSm8 = 6.4.dp,

    // Corners
    basesCornerRadiusRadiusXs4 = 4.dp,

    // Icons
    iconLargeSize = 33.dp,
    iconNormalSize = 20.dp,
    iconSmallSize = 16.dp,
    iconExtraSmallSize = 11.dp,
    playIconSmallSize = 14.dp,

    // Buttons
    horizontalButtonPadding = 18.dp,
    verticalButtonPadding = 14.dp,

    //Row
    rowItemLargeSpacer = 10.dp,
    rowItemMediumSpacer = 6.dp,
    rowItemSmallSpacer = 4.dp,
    rowItemExtraSmallSpacer = 2.dp,

    //TabRow
    tabRowContentSpacer = 18.dp,

    roundedCornerSmallSize = 2.dp,
    roundedCornerMediumSize = 4.dp,
    roundedCornerLargeSize = 6.dp,

    //HeaderText
    headerTextPaddingTop = 36.dp,
    headerTextPaddingBottom = 18.dp,

    //Grid
    gridItemSpacer = 1.dp,
    gridItemLargeSpacer = 2.dp,
    gridItemHorizontalSpacer = 18.dp,
    gridItemVerticalSpacer = 20.dp,

    buttonHeight = 48.dp,
    buttonSidePadding = 28.dp,

    //Achievement Screen
    achievementScreenVerticalPaddings = 16.dp,

    // Video
    videoDetailsSidePadding = 24.dp,
    videoComponentTopPadding = 24.dp,
    videoComponentBottomPadding = 14.dp,
    videoItemExtraLargeSpacer = 14.dp,
    videoItemLargeSpacer = 8.dp,
    videoItemMediumSpacer = 4.dp,
    videoTabRowHeight = 30.dp,
    videoTabRowItemPadding = 6.dp,

    //dialog
    dialogTopBarHeight = 56.dp,

    trailerCardPaddingTop = 24.dp,

    settingsTopPadding = 13.dp
)