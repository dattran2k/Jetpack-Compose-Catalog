/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dat.jetpackcomposecatalog.presenstation.feature.main

import com.dat.jetpackcomposecatalog.R
import com.dat.jetpackcomposecatalog.core.designsystem.icon.Icon
import com.dat.jetpackcomposecatalog.core.designsystem.icon.Icon.DrawableResourceIcon
import com.dat.jetpackcomposecatalog.core.designsystem.icon.MyIcons


/**
 * Type for the top level destinations in the application. Each of these destinations
 * can contain one or more screens (based on the window size). Navigation from one screen to the
 * next within a single destination will be handled directly in composables.
 */
enum class TopLevelDestination(
    val selectedIcon: Icon,
    val unselectedIcon: Icon,
) {
    Catalog(
        selectedIcon = DrawableResourceIcon(MyIcons.MenuBook),
        unselectedIcon = DrawableResourceIcon(MyIcons.MenuBookBorder),

    ),
    Menu(
        selectedIcon = DrawableResourceIcon(MyIcons.Info),
        unselectedIcon = DrawableResourceIcon(MyIcons.InfoBorder),
    ),
}