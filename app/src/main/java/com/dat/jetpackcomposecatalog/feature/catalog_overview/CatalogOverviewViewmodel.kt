package com.dat.jetpackcomposecatalog.feature.catalog_overview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dat.jetpackcomposecatalog.feature.CatalogComposeEnum
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CatalogOverviewViewModel : ViewModel() {
    private val listGroup = CatalogComposeEnum.values()
        .groupBy { it.group }
        .map { (group, itemList) ->
            CatalogOverViewGroup(group, itemList)
        }
    val catalogOverViewGroupList = MutableStateFlow(listGroup)


    // I have to do this because if I change it normally, the compare of CatalogOverViewGroup is the same object
    // so I want to copy new objet and set it
    // an other way to do this this override equals function of CatalogOverViewGroup and remove "==="
    // if anyone have better solution, pls update this
    fun updateExpand(group: CatalogOverViewGroup) {
        viewModelScope.launch {
            catalogOverViewGroupList.value = catalogOverViewGroupList.value.toMutableList().apply {
                val index = indexOfFirst {
                    it == group
                }
                this[index] = this[index].copy(
                    isExpand = !this[index].isExpand
                )
            }
        }
    }
}