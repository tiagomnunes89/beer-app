package br.com.tmn.beerapp.data.service

import br.com.tmn.beerapp.data.PunkRequestGenerator
import br.com.tmn.beerapp.data.mapper.PunkMapperService
import br.com.tmn.beerapp.data.service.api.PunkApi
import br.com.tmn.beerapp.domain.entities.Beer
import br.com.tmn.beerapp.domain.utils.Result


class PunkService {
    private val api: PunkRequestGenerator = PunkRequestGenerator()
    private val mapper: PunkMapperService = PunkMapperService()

    fun getBeersById(id: Int): Result<List<Beer>> {
        val callResponse = api.createService(PunkApi::class.java).getBeersById(id)
        val response = callResponse.execute()
        if (response != null) {
            if (response.isSuccessful) {
                return Result.Success(response.body()?.map {
                    mapper.transform(it)
                })
            }
            return Result.Failure(Exception(response.message()))
        }
        return Result.Failure(Exception("Bad request/response"))
    }

    fun getBeersList(page: Int, perPage: Int): Result<List<Beer>> {
        val callResponse = api.createService(PunkApi::class.java).getBeersList(page, perPage)
        val response = callResponse.execute()
        if (response != null) {
            if (response.isSuccessful) {
                return Result.Success(response.body()?.map {
                    mapper.transform(it)
                })
            }
            return Result.Failure(Exception(response.message()))
        }
        return Result.Failure(Exception("Bad request/response"))
    }

    fun getSearchBeer(beerName: String, page: Int, perPage: Int): Result<List<Beer>> {
        val callResponse =
            api.createService(PunkApi::class.java).getSearchBeer(beerName, page, perPage)
        val response = callResponse.execute()
        if (response != null) {
            if (response.isSuccessful) {
                return Result.Success(response.body()?.map {
                    mapper.transform(it)
                })
            }
            return Result.Failure(Exception(response.message()))
        }
        return Result.Failure(Exception("Bad request/response"))
    }
}