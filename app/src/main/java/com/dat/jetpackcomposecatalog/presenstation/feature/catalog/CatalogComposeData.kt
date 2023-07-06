package com.dat.jetpackcomposecatalog.presenstation.feature.catalog

enum class CatalogComposeGroup {
    Widgets,
    Layout
}

enum class CatalogComposeEnum(val group: CatalogComposeGroup) {
//    Box(CatalogComposeGroup.Widgets),

    Column(CatalogComposeGroup.Layout),
    LazyColumn(CatalogComposeGroup.Layout),
    Row(CatalogComposeGroup.Layout),
    LazyRow(CatalogComposeGroup.Layout),
    LazyVerticalStaggeredGrid(CatalogComposeGroup.Layout),
    LazyHorizontalStaggeredGrid(CatalogComposeGroup.Layout),

}