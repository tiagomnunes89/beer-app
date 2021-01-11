package br.com.tmn.beerapp.data.service.response

import com.google.gson.annotations.SerializedName

class PunkResponse(
        val id: Int,
        val name: String,
        val description: String,
        val tagline: String,
        @SerializedName("image_url") val imageURL: String,
        val abv: Float
)