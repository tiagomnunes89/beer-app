package br.com.tmn.beerapp.domain.useCases

import br.com.tmn.beerapp.domain.repositories.PunkRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

class GetBeerList : KoinComponent {

    private val punkRepository: PunkRepository by inject()

    operator fun invoke(page: Int, perPage: Int) = punkRepository.getBeersList(page, perPage)
}