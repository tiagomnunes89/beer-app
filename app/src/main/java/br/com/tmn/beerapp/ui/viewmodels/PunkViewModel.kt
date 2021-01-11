package br.com.tmn.beerapp.ui.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.tmn.beerapp.domain.entities.Beer
import br.com.tmn.beerapp.domain.useCases.GetBeerList
import br.com.tmn.beerapp.domain.useCases.GetBeersById
import br.com.tmn.beerapp.domain.useCases.GetSearchBeer
import br.com.tmn.beerapp.domain.utils.Result
import br.com.tmn.beerapp.ui.utils.Data
import br.com.tmn.beerapp.ui.utils.SharedPreferencesConfig
import br.com.tmn.beerapp.ui.utils.Status
import br.com.tmn.beerapp.ui.viewmodels.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PunkViewModel(
    private val sharedPreferencesConfig: SharedPreferencesConfig,
    val getBeersById: GetBeersById,
    val getBeerList: GetBeerList,
    val getSearchBeer: GetSearchBeer
) : BaseViewModel() {

    private var mutableMainStateList = MutableLiveData<Data<List<Beer>>>()
    val mainStateList: LiveData<Data<List<Beer>>>
        get() {
            return mutableMainStateList
        }

    private var mutableMainStateDetail = MutableLiveData<Data<List<Beer>>>()
    val mainStateDetail: LiveData<Data<List<Beer>>>
        get() {
            return mutableMainStateDetail
        }

    fun onStartHome(page: Int, perPage: Int) = launch {
        mutableMainStateList.value = Data(responseType = Status.LOADING)
        when (val result = withContext(Dispatchers.IO) { getBeerList(page, perPage) }) {
            is Result.Failure -> {
                mutableMainStateList.value =
                    Data(responseType = Status.ERROR, error = result.exception)
            }
            is Result.Success -> {
                mutableMainStateList.value =
                    Data(responseType = Status.SUCCESSFUL, data = result.data)
            }
        }
    }

    fun onClickToBeerDetails(id: Int, context: Context) = launch {
        mutableMainStateDetail.value = Data(responseType = Status.LOADING)
        when (val result = withContext(Dispatchers.IO) { getBeersById(id) }) {
            is Result.Failure -> {
                mutableMainStateDetail.value =
                    Data(responseType = Status.ERROR, error = result.exception)
            }
            is Result.Success -> {
                mutableMainStateDetail.value =
                    Data(responseType = Status.SUCCESSFUL, data = result.data)
                result.data?.get(0)
                    ?.let { sharedPreferencesConfig.saveCurrentBeerData(it) }
            }
        }
    }

    fun onSearchClick(beerName: String, page: Int, perPage: Int) = launch {
        mutableMainStateList.value = Data(responseType = Status.LOADING)
        when (val result = withContext(Dispatchers.IO) { getSearchBeer(beerName, page, perPage) }) {
            is Result.Failure -> {
                mutableMainStateList.value =
                    Data(responseType = Status.ERROR, error = result.exception)
            }
            is Result.Success -> {
                mutableMainStateList.value =
                    Data(responseType = Status.SUCCESSFUL, data = result.data)
            }
        }
    }
}