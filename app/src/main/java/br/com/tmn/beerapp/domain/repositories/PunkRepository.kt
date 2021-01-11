package br.com.tmn.beerapp.domain.repositories

import br.com.tmn.beerapp.domain.entities.Beer
import br.com.tmn.beerapp.domain.utils.Result

interface PunkRepository {

    fun getBeersById(id: Int)
            : Result<List<Beer>>

    fun getBeersList(page: Int, perPage: Int)
            : Result<List<Beer>>

    fun getSearchBeer(beerName: String,
                      page: Int,
                      perPage: Int)
            : Result<List<Beer>>
}