package br.com.tmn.beerapp.di

import br.com.tmn.beerapp.data.repositories.PunkRepositoryImpl
import br.com.tmn.beerapp.data.service.PunkService
import br.com.tmn.beerapp.domain.repositories.PunkRepository
import br.com.tmn.beerapp.domain.useCases.GetBeerList
import br.com.tmn.beerapp.domain.useCases.GetBeersById
import br.com.tmn.beerapp.domain.useCases.GetSearchBeer
import br.com.tmn.beerapp.ui.viewmodels.PunkViewModel
import org.koin.dsl.module

val repositoriesModule = module {
    single { PunkService() }
    single<PunkRepository> { PunkRepositoryImpl(get()) }
}

val viewModelModule = module {
    single { PunkViewModel(get(),get(),get()) }
}

val useCasesModule = module {
    single { GetBeersById() }
    single { GetBeerList() }
    single { GetSearchBeer() }
}
