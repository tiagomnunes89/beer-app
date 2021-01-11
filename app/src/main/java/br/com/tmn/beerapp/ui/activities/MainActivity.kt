package br.com.tmn.beerapp.ui.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.tmn.beerapp.R
import br.com.tmn.beerapp.domain.entities.Beer
import br.com.tmn.beerapp.ui.adapters.PunkAdapter
import br.com.tmn.beerapp.ui.utils.Data
import br.com.tmn.beerapp.ui.utils.Status
import br.com.tmn.beerapp.ui.viewmodels.PunkViewModel
import com.airbnb.lottie.LottieAnimationView
import org.koin.androidx.viewmodel.ext.android.viewModel

const val MINIMUM_LOADING_TIME = 1000L

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<PunkViewModel>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var beerLoading: LottieAnimationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_beer_list)
        beerLoading = findViewById(R.id.loading_beer)

        showLoading()

        minimumLoadingTime()

        viewModel.mainStateList.observe(::getLifecycle, ::updateUI)
    }

    private fun updateUI(beersData: Data<List<Beer>>) {
        when (beersData.responseType) {
            Status.ERROR -> {
                hideLoading()
                beersData.error?.message?.let { showMessage(it) }
                beersData.data?.let { setBeerList(it) }
            }
            Status.LOADING -> {
                showLoading()
            }
            Status.SUCCESSFUL -> {
                hideLoading()
                beersData.data?.let { setBeerList(it) }
            }
        }
    }

    private fun minimumLoadingTime() {
        Handler(Looper.getMainLooper()).postDelayed({
            viewModel.onStartHome(1, 80)
        }, MINIMUM_LOADING_TIME)
    }

    private fun showLoading() {
        beerLoading.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        beerLoading.visibility = View.GONE
    }

    private fun setBeerList(beerList: List<Beer>) {
        recyclerView.adapter = PunkAdapter(beerList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}