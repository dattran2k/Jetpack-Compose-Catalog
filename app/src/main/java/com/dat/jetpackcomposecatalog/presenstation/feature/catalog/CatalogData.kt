package com.dat.jetpackcomposecatalog.presenstation.feature.catalog

enum class CatalogGroup {
    Widgets,
    Layout
}

enum class CatalogComposeEnum(val group: CatalogGroup) {
    Box(CatalogGroup.Widgets),
    Column(CatalogGroup.Layout),
    Row(CatalogGroup.Layout),
    Row2(CatalogGroup.Layout),
    Row3(CatalogGroup.Layout),
    Row4(CatalogGroup.Layout),
    Row5(CatalogGroup.Layout),
    Row6(CatalogGroup.Layout),
    Row7(CatalogGroup.Layout),
    Row8(CatalogGroup.Layout),
    Row14(CatalogGroup.Layout),
    Row9(CatalogGroup.Layout),
    Row10(CatalogGroup.Layout),
    Row11(CatalogGroup.Layout),
    Row12(CatalogGroup.Layout),
    Row13(CatalogGroup.Layout),
    Row16(CatalogGroup.Layout),
    Row15(CatalogGroup.Layout),
}