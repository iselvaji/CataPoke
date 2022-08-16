package com.cw.catapoke.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpeciesEvolutionDto(
    val chain:EvolutionDto?,
    val id:Int?
)

/*@Serializable
data class Chain (
    @SerialName("evolution_details")
    val evolutionDetails : List<String>,
    @SerialName("evolves_to")
    val evolves_to : List<EvolutionDto>,
    @SerialName("is_baby")
    val isBaby: Boolean,
    @SerialName("species")
    val species : ItemWithUrlNameDto
)*/

@Serializable
data class EvolutionDto(
    @SerialName("evolution_details")
    val evolutionDetails: List<EvolutionDetailsDto>,
    @SerialName("evolves_to")
    val evolvesTo: List<EvolutionDto>,
    @SerialName("is_baby")
    val isBaby: Boolean,
    @SerialName("species")
    val species: ItemWithUrlNameDto
)

@Serializable
data class EvolutionDetailsDto(
    @SerialName("min_level")
    val minLevel:Int?
)



