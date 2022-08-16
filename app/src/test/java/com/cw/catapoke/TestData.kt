package com.cw.catapoke

import com.cw.catapoke.data.mapper.toSpeciesResult
import com.cw.catapoke.data.remote.dto.SpeciesResponseDto
import com.cw.catapoke.data.remote.dto.SpeciesResultDto
import com.cw.catapoke.domain.model.*

object TestData {

    fun getSpeciesListDto(): List<SpeciesResultDto> {
        val speciesList = mutableListOf<SpeciesResultDto>()
        val speciesResult1 = SpeciesResultDto("Test1", "https://pokeapi.co/api/v2/pokemon-species/1/")
        val speciesResult2 = SpeciesResultDto("Test2", "https://pokeapi.co/api/v2/pokemon-species/2/")
        speciesList.add(speciesResult1)
        speciesList.add(speciesResult2)
        return speciesList
    }

    fun getSpeciesDto(): SpeciesResponseDto {
        return SpeciesResponseDto(
            count = 40,
            next = "next",
            previous = "previous",
            results = getSpeciesListDto()
        )
    }

    fun getSpeciesList(): List<SpeciesResult> {
       return getSpeciesListDto().map {
           it.toSpeciesResult()
       }
    }


    fun getSpeciesDetails(): SpeciesDetails {

        val flavorEntries = mutableListOf<FlavorText>()
        flavorEntries.add(FlavorText(text = "A strange seed at birth",
                language = ItemWithUrlName(name = "en", url = "https://pokeapi.co/api/v2/language/9/"),
                version = ItemWithUrlName(name = "red", url = "https://pokeapi.co/api/v2/version/1")))

        val evolutionChainUrl = ItemWithUrl(url = "https://pokeapi.co/api/v2/evolution-chain/1/")

        return SpeciesDetails(
            captureRate = 40,
            name = "Test Poke",
            flavorEntries = flavorEntries,
            evolutionChainUrl = evolutionChainUrl
        )
    }
}