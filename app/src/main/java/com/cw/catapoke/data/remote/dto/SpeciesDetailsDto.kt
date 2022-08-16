package com.cw.catapoke.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpeciesDetailsDto(
    @SerialName("capture_rate")
    val captureRate : Int?,
    @SerialName("name")
    val name : String?,
    @SerialName("flavor_text_entries")
    val flavorEntries : List<FlavorTextDto>,
    @SerialName("evolution_chain")
    val evolutionChainUrl : ItemWithUrlDto?
)

@Serializable
data class FlavorTextDto(
    @SerialName("flavor_text")
    val text : String?,
    @SerialName("language")
    val language : ItemWithUrlNameDto?,
    @SerialName("version")
    val version : ItemWithUrlNameDto?
)

@Serializable
data class ItemWithUrlNameDto(
    @SerialName("name")
    val name : String?,
    @SerialName("url")
    val url : String?
)

@Serializable
data class ItemWithUrlDto(
    @SerialName("url")
    val url : String?
)



