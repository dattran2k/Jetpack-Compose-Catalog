package com.dat.jetpackcomposecatalog.presenstation.view.catalog_compose

import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dat.jetpackcomposecatalog.data.model.catalog.ModPadding
import com.dat.jetpackcomposecatalog.data.model.catalog.ModSize
import com.dat.jetpackcomposecatalog.data.model.catalog.ModifierConfig
import com.dat.jetpackcomposecatalog.data.model.catalog.updateModifier
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class ConfigModifierViewModel : ViewModel() {
    val listConfigModifier = MutableStateFlow<List<ModifierConfig>>(listOf())
    val modifier = listConfigModifier.map {
        var modifier: Modifier = Modifier
        it.forEach {
            modifier = modifier.updateModifier(it)
        }
        modifier
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = Modifier,
    )

    init {
        listConfigModifier.value = listOf(
            ModSize.SizeAll(100.dp),
            ModPadding.All(10.dp),
        )
    }

    fun addModifierConfig(modifierConfig: ModifierConfig) {
        listConfigModifier.value += modifierConfig
    }


}