package com.cw.catapoke.domain.model

data class SpeciesDetails(
    var id: Int = -1,
    val captureRate : Int?,
    val name : String?,
    val flavorEntries : List<FlavorText>,
    val evolutionChainUrl : ItemWithUrl?
)

data class FlavorText(
    val text : String?,
    var language : ItemWithUrlName?,
    val version : ItemWithUrlName?
)

data class ItemWithUrlName(
    val name : String?,
    val url : String?
)

data class ItemWithUrl(
    val url : String?
)



