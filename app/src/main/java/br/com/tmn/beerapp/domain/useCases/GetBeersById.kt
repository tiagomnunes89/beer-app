package br.com.tmn.beerapp.domain.useCases

import br.com.tmn.beerapp.domain.repositories.PunkRepository
import org.koin.core.KoinComponent


class GetBeersById (private val punkRepository: PunkRepository) : KoinComponent{

    operator fun invoke(id: Int) = punkRepository.getBeersById(id)
}