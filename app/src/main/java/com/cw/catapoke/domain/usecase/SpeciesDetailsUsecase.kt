package com.cw.catapoke.domain.usecase

import com.cw.catapoke.domain.model.SpeciesDetails
import com.cw.catapoke.domain.repository.Repository
import com.cw.catapoke.utils.Resource

/**
 * Species details Usecase - retrieve pokemon species details from remote data source
 *
 * @property repository
 */

class SpeciesDetailsUsecase(private val repository: Repository) {
    suspend fun getSpeciesDetails(id: Int): Resource<SpeciesDetails> {
        return try {
            val speciesDetails = repository.getSpeciesDetails(id)
            val filterdSpeciesDetails = getFlavorTextByLanguage(speciesDetails)
            Resource.Success(data = filterdSpeciesDetails)
        } catch (ex: Exception) {
            Resource.Error(message = ex.localizedMessage)
        }
    }

    /**
     * Get flavor text by language
     * SpeciesDetails contains flavor text for different languages, filter those only for english
     * @param speciesDetails
     * @return
     */
    private fun getFlavorTextByLanguage(speciesDetails: SpeciesDetails): SpeciesDetails {
        val language = "en" // By default english, We can get this from UI layer if required
        val flavorEntriesByLanguage = speciesDetails.flavorEntries.filter { it.language?.name == language }
        return speciesDetails.copy(flavorEntries = flavorEntriesByLanguage)
    }
}