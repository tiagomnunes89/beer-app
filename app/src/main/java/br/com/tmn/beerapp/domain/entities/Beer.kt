package br.com.tmn.beerapp.domain.entities

val NOT_FOUND = "NOT FOUND"
val NO_IMAGE = "NO IMAGE"
val DEFAULT_ID = 0
val DEFAULT_ABV = 0f

class Beer(
    val id: Int = DEFAULT_ID,
    val name: String = NOT_FOUND,
    val description: String = NOT_FOUND,
    val tagline: String = NOT_FOUND,
    val imageURL: String = NO_IMAGE,
    val abv: Float = DEFAULT_ABV
)