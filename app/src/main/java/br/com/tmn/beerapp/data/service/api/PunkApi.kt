package br.com.tmn.beerapp.data.service.api

import br.com.tmn.beerapp.data.service.response.PunkResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PunkApi {

    @GET("v2/beers")
    fun getBeersById(
        @Query("ids") id: Int
    ): Call<List<PunkResponse>>

    @GET("v2/beers")
    fun getBeersList(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Call<List<PunkResponse>>

    @GET("v2/beers")
    fun getSearchBeer(
        @Query("beer_name") beerName: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Call<List<PunkResponse>>
}