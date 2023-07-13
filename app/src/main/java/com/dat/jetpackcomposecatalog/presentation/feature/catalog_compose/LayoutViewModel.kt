@file:OptIn(ExperimentalFoundationApi::class, ExperimentalCoroutinesApi::class)

package com.dat.jetpackcomposecatalog.presentation.feature.catalog_compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dat.jetpackcomposecatalog.data.model.catalog.MyGridCells
import com.dat.jetpackcomposecatalog.data.model.catalog.MyHorizontalAlignment
import com.dat.jetpackcomposecatalog.data.model.catalog.MyHorizontalArrangement
import com.dat.jetpackcomposecatalog.data.model.catalog.MyStaggeredGridCells
import com.dat.jetpackcomposecatalog.data.model.catalog.MyVerticalAlignment
import com.dat.jetpackcomposecatalog.data.model.catalog.MyVerticalArrangement
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


class CatalogViewModel : ViewModel() {

    var verticalAlignmentState = MutableStateFlow(MyVerticalAlignment.Top)
    var verticalArrangementState = MutableStateFlow(MyVerticalArrangement.Top)
    var horizontalAlignmentState = MutableStateFlow(MyHorizontalAlignment.Start)
    var horizontalArrangementState = MutableStateFlow(MyHorizontalArrangement.Start)
    var itemCountState = MutableStateFlow(5)

    fun onUpdateItemCount(itemCount: Int) {
        itemCountState.value = itemCount
    }

    fun onVerticalAlignmentSelected(verticalAlignment: MyVerticalAlignment) {
        verticalAlignmentState.value = verticalAlignment
    }

    fun onVerticalArrangementSelected(verticalArrangement: MyVerticalArrangement) {
        verticalArrangementState.value = verticalArrangement
    }

    fun onHorizontalAlignmentSelected(horizontalAlignment: MyHorizontalAlignment) {
        horizontalAlignmentState.value = horizontalAlignment
    }

    fun onHorizontalArrangementSelected(horizontalArrangement: MyHorizontalArrangement) {
        horizontalArrangementState.value = horizontalArrangement
    }

    // Start Grid =======================================================
    private val fixedState = MutableStateFlow(2)
    private val adaptiveState = MutableStateFlow(100)

    // Start StaggeredGridCells
    private val staggeredGridCellsTypeState = MutableStateFlow(MyStaggeredGridCells.Adaptive)
    val staggeredGridCells = staggeredGridCellsTypeState.flatMapLatest { staggeredGridCellsType ->
        if (staggeredGridCellsType.value == StaggeredGridCells.Adaptive::class.java)
            adaptiveState.map {
                StaggeredGridCellsData(
                    StaggeredGridCells.Adaptive(it.dp),
                    it,
                    staggeredGridCellsType
                )
            }
        else
            fixedState.map {
                StaggeredGridCellsData(StaggeredGridCells.Fixed(it), it, staggeredGridCellsType)
            }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = StaggeredGridCellsData(
            StaggeredGridCells.Adaptive(100.dp),
            100,
            MyStaggeredGridCells.Adaptive
        ),
    )

    fun updateStaggeredAdaptive(adaptive: Int) {
        adaptiveState.value = adaptive
    }

    fun updateStaggeredFixed(fixed: Int) {
        fixedState.value = fixed
    }

    fun onSelectStaggeredGridCells(myStaggeredGridCells: MyStaggeredGridCells) {
        staggeredGridCellsTypeState.value = myStaggeredGridCells
    }
    // Start StaggeredGridCells

    // Start GridCells
    private val gridCellsTypeState = MutableStateFlow(MyGridCells.Adaptive)
    val gridCells = gridCellsTypeState.flatMapLatest { gridCellsType ->
        if (gridCellsType.value == GridCells.Adaptive::class.java)
            adaptiveState.map {
                GridCellsData(
                    GridCells.Adaptive(it.dp),
                    it,
                    gridCellsType
                )
            }
        else
            fixedState.map {
                GridCellsData(
                    GridCells.Fixed(it),
                    it,
                    gridCellsType
                )
            }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = GridCellsData(
            GridCells.Adaptive(100.dp),
            100,
            MyGridCells.Adaptive
        ),
    )

    fun updateAdaptive(adaptive: Int) {
        adaptiveState.value = adaptive
    }

    fun updateFixed(fixed: Int) {
        fixedState.value = fixed
    }

    fun onSelectGridCells(myGridCells: MyGridCells) {
        gridCellsTypeState.value = myGridCells
    }
    // End GridCells
    // End Grid ==============================================================
}


data class StaggeredGridCellsData(
    val staggeredGridCells: StaggeredGridCells,
    val value: Int,
    val myStaggeredGridCells: MyStaggeredGridCells
)

data class GridCellsData(
    val gridCells: GridCells,
    val value: Int,
    val myGridCells: MyGridCells
)
