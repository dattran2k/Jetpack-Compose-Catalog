package com.dat.jetpackcomposecatalog.presentation.feature

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
    LazyVerticalGrid(CatalogComposeGroup.Layout),
    LazyHorizontalStaggeredGrid(CatalogComposeGroup.Layout),
    LazyHorizontalGrid(CatalogComposeGroup.Layout),

}