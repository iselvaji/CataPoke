package com.cw.catapoke.domain.usecase

import com.cw.catapoke.domain.model.Evolution
import com.cw.catapoke.domain.model.ItemWithUrlName
import com.cw.catapoke.domain.model.SpeciesEvolution
import com.cw.catapoke.domain.repository.Repository
import com.cw.catapoke.utils.AppUtil
import com.cw.catapoke.utils.Resource

/**
 * Species evolution details use case
 * Get the first pokemon evolution id for given pokemon id from data layer
 * @property repository
 */

class SpeciesEvolutionDetailsUsecase(private val repository: Repository) {

    suspend fun getSpeciesEvolutionByOrder(id: Int, evolutionOrder : AppUtil.EvolutionOrder): Resource<Int> {
        return try {
            val response = repository.getSpeciesEvolution(id)
            val speciesId = getEvolutionSpeciesIdByOrder(response, evolutionOrder)
            Resource.Success(data = speciesId)
        } catch (ex: Exception) {
            Resource.Error(message = ex.localizedMessage)
        }
    }

    /**
     * Get evolution species id by order
     * Iterate the evolution chain and filter the first evolution pokeman id
     * @param evolution
     * @param order
     * @return first evolution pokeman id
     */
    private fun getEvolutionSpeciesIdByOrder(
        evolution: SpeciesEvolution,
        order: AppUtil.EvolutionOrder
    ): Int {
        val evolutionUrls = mutableListOf<ItemWithUrlName>()
        val evolutionList = ArrayList<Evolution>()
        evolution.let {
            it.chain?.let { chain ->
                evolutionList.add(chain)
                if (chain.evolvesTo.isNotEmpty()) {
                    evolutionList.addAll(it.chain.evolvesTo)

                    if (chain.evolvesTo[0].evolvesTo.isNotEmpty())
                        evolutionList.addAll(it.chain.evolvesTo[0].evolvesTo)
                }
                evolutionList.forEach { evolution ->
                    if (evolution.evolutionDetails.isNotEmpty()) {
                        evolution.evolutionDetails.forEach { evolDetails ->
                            evolDetails.minLevel?.let {
                                evolutionUrls.add(evolution.species)
                            }
                        }
                    }
                }
            }
        }
        return AppUtil.getId(evolutionUrls[order.position].url)
    }
}