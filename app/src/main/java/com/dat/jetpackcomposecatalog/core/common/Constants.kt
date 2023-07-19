@file:OptIn(ExperimentalFoundationApi::class)

package com.dat.jetpackcomposecatalog.core.common

import androidx.compose.foundation.ExperimentalFoundationApi

const val DELAY: Long = 3000
const val DURATION: Int = (DELAY * 2 / 3L).toInt()

object TestTag {
    const val CIRCULAR_PROGRESS_INDICATOR_TAG = "CIRCULAR_PROGRESS_INDICATOR_TAG"
    const val CIRCULAR_PROGRESS_INDICATOR_TAG_2 = "CIRCULAR_PROGRESS_INDICATOR_TAG_2"
    const val LAZY_COLUMN_TAG = "LAZY_COLUMN_TAG"
}