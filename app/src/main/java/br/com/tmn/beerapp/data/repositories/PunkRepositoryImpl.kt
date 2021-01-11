package br.com.tmn.beerapp.data.repositories

import br.com.tmn.beerapp.data.service.PunkService
import br.com.tmn.beerapp.domain.entities.Beer
import br.com.tmn.beerapp.domain.repositories.PunkRepository
import br.com.tmn.beerapp.domain.utils.Result

class PunkRepositoryImpl(
    private val punkService: PunkService
) : PunkRepository {

    override fun getBeersById(id: Int): Result<List<Beer>> {
        return punkService.getBeersById(id)
    }

    override fun getBeersList(page: Int, perPage: Int): Result<List<Beer>> {
        return punkService.getBeersList(page, perPage)
    }

    override fun getSearchBeer(beerName: String, page: Int, perPage: Int): Result<List<Beer>> {
        return punkService.getSearchBeer(beerName, page, perPage)
    }
}