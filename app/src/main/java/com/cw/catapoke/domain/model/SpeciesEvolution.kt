package com.cw.catapoke.domain.model

data class SpeciesEvolution(
    val chain:Evolution?,
    val id:Int?,
    val evolutionUrlsNames : List<ItemWithUrlName> = listOf()
)

data class Evolution(
    val evolutionDetails: List<EvolutionDetails>,
    val evolvesTo: List<Evolution>,
    val isBaby: Boolean,
    val species: ItemWithUrlName
)


data class EvolutionDetails(
    val minLevel:Int?
)
