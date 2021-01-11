package br.com.tmn.beerapp

import android.app.Application
import br.com.tmn.beerapp.di.repositoriesModule
import br.com.tmn.beerapp.di.sharedPreferences
import br.com.tmn.beerapp.di.useCasesModule
import br.com.tmn.beerapp.di.viewModelModule
import com.facebook.drawee.backends.pipeline.Fresco
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BeerApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Fresco.initialize(this)

        startKoin {
            androidContext(this@BeerApplication)
            modules(listOf(repositoriesModule, viewModelModule, useCasesModule, sharedPreferences))
        }
    }
}