package br.com.tmn.beerapp.domain.useCases

import br.com.tmn.beerapp.domain.repositories.PunkRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

class GetBeersById : KoinComponent {

    private val punkRepository: PunkRepository by inject()

    operator fun invoke(id: Int) = punkRepository.getBeersById(id)
}