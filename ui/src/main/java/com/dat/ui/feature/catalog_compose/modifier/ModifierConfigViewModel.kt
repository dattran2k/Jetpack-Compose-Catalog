package com.dat.ui.feature.catalog_compose.modifier

import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dat.core.model.ui.ModPadding
import com.dat.core.model.ui.ModSize
import com.dat.core.model.ui.ModifierConfig
import com.dat.core.model.ui.updateModifier
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.burnoutcrew.reorderable.ItemPosition

class ModifierConfigViewModel : ViewModel() {
    val listConfigModifier = MutableStateFlow<List<ModifierConfig>>(listOf())
    val modifier = listConfigModifier.map {
        var modifier: Modifier = Modifier
        it.forEach { config ->
            modifier = modifier.updateModifier(config)
        }
        modifier
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = Modifier,
    )

    init {
        listConfigModifier.value = listOf(
            ModSize.SizeAll(400.dp),
            ModPadding.All(10.dp),
            ModPadding.All(20.dp),
            ModPadding.All(30.dp),
            ModPadding.All(1.dp),
            ModPadding.All(1.dp),
            ModPadding.All(1.dp),
            ModPadding.All(1.dp),
            ModPadding.All(1.dp),
            ModPadding.All(1.dp)
        )
    }

    fun addModifierConfig(modifierConfig: ModifierConfig) {
        listConfigModifier.value += modifierConfig
    }

    fun replaceItem(from: ItemPosition, to: ItemPosition) {
        listConfigModifier.value = listConfigModifier.value.toMutableList().apply {
//            if (from < size && to < size)
            add(to.index, removeAt(from.index))
        }
    }

}