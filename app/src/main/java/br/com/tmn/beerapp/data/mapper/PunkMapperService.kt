package br.com.tmn.beerapp.data.mapper

import br.com.tmn.beerapp.data.service.response.PunkResponse
import br.com.tmn.beerapp.domain.entities.Beer

class PunkMapperService : BaseMapperRepository<PunkResponse, Beer> {

    override fun transform(type: PunkResponse): Beer =
        Beer(
            type.id,
            type.name,
            type.description,
            type.tagline,
            type.imageURL,
            type.abv
        )

    override fun transformToRepository(type: Beer): PunkResponse =
        PunkResponse(
            type.id,
            type.name,
            type.description,
            type.tagline,
            type.imageURL,
            type.abv
        )
}