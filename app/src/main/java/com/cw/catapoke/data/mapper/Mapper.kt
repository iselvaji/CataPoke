package com.cw.catapoke.data.mapper

import com.cw.catapoke.data.remote.dto.*
import com.cw.catapoke.domain.model.*

/**
 * Mapping functions which converts data layer objects to UI layer objects
 */

fun SpeciesResponseDto.toSpeciesResponse() : SpeciesResponse {
    return SpeciesResponse(
        count = count,
        next = next,
        previous = previous,
        results = results.map { it.toSpeciesResult() }
    )
}

fun SpeciesResultDto.toSpeciesResult() :SpeciesResult {
    return SpeciesResult(
        name = name,
        url = url
    )
}

fun SpeciesDetailsDto.toSpeciesDetails(id: Int): SpeciesDetails {
    return SpeciesDetails(
        id=id,
        name = name,
        captureRate = captureRate,
        flavorEntries = flavorEntries.map { it.toFlavorText() },
        evolutionChainUrl = evolutionChainUrl?.toItemWithUrl()
    )
}

fun FlavorTextDto.toFlavorText() : FlavorText {
    return FlavorText(
        text = text,
        language = language?.toItemWithUrlName(),
        version = version?.toItemWithUrlName()
    )
}

fun ItemWithUrlNameDto.toItemWithUrlName() : ItemWithUrlName {
    return ItemWithUrlName(
        name = name,
        url = url
    )
}

fun ItemWithUrlDto.toItemWithUrl() : ItemWithUrl {
    return ItemWithUrl(url = url)
}

fun SpeciesEvolutionDto.toSpeciesEvolution() : SpeciesEvolution {
    return SpeciesEvolution(
        id = id,
        chain = chain?.toEvolution()
    )
}

fun EvolutionDto.toEvolution() : Evolution {
    return Evolution(
        evolutionDetails = evolutionDetails.map { it.toEvolutionDetails() },
        evolvesTo = toEvolution(evolvesTo),
        isBaby = isBaby,
        species = species.toItemWithUrlName()
    )
}

fun EvolutionDetailsDto.toEvolutionDetails() : EvolutionDetails {
    return EvolutionDetails(minLevel = minLevel)
}

fun toEvolution(evolutionList: List<EvolutionDto>): MutableList<Evolution> {
    val result = mutableListOf<Evolution>()
    evolutionList.forEach {
        result.add(it.toEvolution())
    }
    return result
}