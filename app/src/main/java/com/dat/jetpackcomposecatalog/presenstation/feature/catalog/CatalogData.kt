package com.dat.jetpackcomposecatalog.presenstation.feature.catalog

enum class CatalogGroup {
    Widgets,
    Layout
}

enum class CatalogComposeEnum(val group: CatalogGroup) {
    Box(CatalogGroup.Widgets),

    Column(CatalogGroup.Layout),
    LazyColumn(CatalogGroup.Layout),
    Row(CatalogGroup.Layout),

}