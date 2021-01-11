package br.com.tmn.beerapp

import android.app.Application
import br.com.tmn.beerapp.di.repositoriesModule
import br.com.tmn.beerapp.di.useCasesModule
import br.com.tmn.beerapp.di.viewModelModule
import org.koin.core.context.startKoin

class BeerApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            modules(listOf(repositoriesModule, viewModelModule, useCasesModule))
        }
    }
}