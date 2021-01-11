package br.com.tmn.beerapp.domain.useCases

import br.com.tmn.beerapp.domain.repositories.PunkRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

class GetSearchBeer : KoinComponent {

    private val punkRepository: PunkRepository by inject()

    operator fun invoke(beerName: String, page: Int, perPage: Int) =
        punkRepository.getSearchBeer(beerName, page, perPage)
}