package com.cw.catapoke.domain.model

import com.cw.catapoke.utils.AppUtil

data class SpeciesResponse(
    val count : Int,
    val next : String?,
    val previous : String?,
    val results : List<SpeciesResult>
)

data class SpeciesResult(
    val name : String?,
    val url : String?
) {
    val id = AppUtil.getId(this.url)
}