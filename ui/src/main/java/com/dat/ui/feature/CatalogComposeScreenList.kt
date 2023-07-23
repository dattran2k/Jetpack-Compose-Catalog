package com.dat.ui.feature

enum class CatalogComposeGroup {
    Layout,
    Animation,
    Widgets,

}

enum class CatalogComposeEnum(val group: CatalogComposeGroup) {
//    Box(CatalogComposeGroup.Widgets),

    AnimationShowCase(CatalogComposeGroup.Animation),
    ContentVisibility(CatalogComposeGroup.Animation),
    AnimateContentSize(CatalogComposeGroup.Animation),
    AnimatedValue(CatalogComposeGroup.Animation),
    AnimatedContent(CatalogComposeGroup.Animation),
    AnimationOffsetBouncingBall(CatalogComposeGroup.Animation),

    Column(CatalogComposeGroup.Layout),
    LazyColumn(CatalogComposeGroup.Layout),
    Row(CatalogComposeGroup.Layout),
    LazyRow(CatalogComposeGroup.Layout),
    LazyVerticalStaggeredGrid(CatalogComposeGroup.Layout),
    LazyVerticalGrid(CatalogComposeGroup.Layout),
    LazyHorizontalStaggeredGrid(CatalogComposeGroup.Layout),
    LazyHorizontalGrid(CatalogComposeGroup.Layout),


}