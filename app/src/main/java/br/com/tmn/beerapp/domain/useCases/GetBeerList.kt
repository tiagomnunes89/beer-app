package br.com.tmn.beerapp.domain.useCases

import br.com.tmn.beerapp.domain.repositories.PunkRepository
import org.koin.core.KoinComponent

class GetBeerList(private val punkRepository: PunkRepository) : KoinComponent {

    operator fun invoke(page: Int, perPage: Int) = punkRepository.getBeersList(page, perPage)
}