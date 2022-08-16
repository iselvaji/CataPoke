package com.cw.catapoke.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class SpeciesResponseDto(
    val count : Int,
    val next : String?,
    val previous : String?,
    val results : List<SpeciesResultDto>
)

@Serializable
data class SpeciesResultDto(
    val name : String?,
    val url : String?
)
