package br.com.tmn.beerapp.di

import android.content.Context
import android.content.SharedPreferences
import br.com.tmn.beerapp.data.repositories.PunkRepositoryImpl
import br.com.tmn.beerapp.data.service.PunkService
import br.com.tmn.beerapp.domain.repositories.PunkRepository
import br.com.tmn.beerapp.domain.useCases.GetBeerList
import br.com.tmn.beerapp.domain.useCases.GetBeersById
import br.com.tmn.beerapp.domain.useCases.GetSearchBeer
import br.com.tmn.beerapp.ui.utils.SharedPreferencesConfig
import br.com.tmn.beerapp.ui.viewmodels.PunkViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoriesModule = module {
    single { PunkService() }
    single<PunkRepository> { PunkRepositoryImpl(get()) }
}

val viewModelModule = module {
    single { PunkViewModel(get(), get(), get(), get()) }
}

val useCasesModule = module {
    single { GetBeersById(get()) }
    single { GetBeerList(get()) }
    single { GetSearchBeer(get()) }
}

val sharedPreferences = module {

    single { SharedPreferencesConfig(androidContext())}

    single {
        getSharedPrefs(androidContext(), "com.example.android.PREFERENCE_FILE")
    }

    single<SharedPreferences.Editor> {
        getSharedPrefs(androidContext(), "com.example.android.PREFERENCE_FILE").edit()
    }
}

fun getSharedPrefs(context: Context, fileName: String): SharedPreferences {
    return context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
}