package com.dat.designsystem.component
//
//import android.graphics.Insets.add
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.dat.ui.common.ui_model.ModPadding
//import com.dat.ui.common.ui_model.ModSize
//import com.dat.ui.common.ui_model.ModifierConfig
//import com.dat.ui.common.ui_model.updateModifier
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.SharingStarted
//import kotlinx.coroutines.flow.map
//import kotlinx.coroutines.flow.stateIn
//import org.burnoutcrew.reorderable.ItemPosition
//
//class ModifierConfigViewModel : ViewModel() {
//    val listConfigModifier = MutableStateFlow<List<ModifierConfig>>(listOf())
//    val modifier = listConfigModifier.map {
//        var modifier: Modifier = Modifier
//        it.forEach { config ->
//            modifier = modifier.updateModifier(config)
//        }
//        modifier
//    }.stateIn(
//        scope = viewModelScope,
//        started = SharingStarted.WhileSubscribed(),
//        initialValue = Modifier,
//    )
//
//    init {
//        listConfigModifier.value = listOf(
//            ModSize.SizeAll(400.dp),
//            ModPadding.All(10.dp),
//            ModPadding.All(20.dp),
//            ModPadding.All(30.dp),
//            ModPadding.All(1.dp),
//            ModPadding.All(1.dp),
//            ModPadding.All(1.dp),
//            ModPadding.All(1.dp),
//            ModPadding.All(1.dp),
//            ModPadding.All(1.dp)
//        )
//    }
//
//    fun addModifierConfig(modifierConfig: ModifierConfig) {
//        listConfigModifier.value += modifierConfig
//    }
//
//    fun replaceItem(from: ItemPosition, to: ItemPosition) {
//        listConfigModifier.value = listConfigModifier.value.toMutableList().apply {
////            if (from < size && to < size)
//                add(to.index, removeAt(from.index))
//        }
//    }
//
//}