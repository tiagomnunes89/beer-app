package br.com.tmn.beerapp.ui.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.FrameLayout
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
import org.koin.androidx.viewmodel.ext.android.viewModel

const val MINIMUM_LOADING_TIME = 1500L

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<PunkViewModel>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var beerLoading: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_beer_list)
        beerLoading = findViewById(R.id.loader_layout)

        callStartService()

        viewModel.mainStateList.observe(::getLifecycle, ::updateUI)
        viewModel.mainStateDetail.observe(::getLifecycle, ::updateDetailUI)
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
                beersData.data?.let { setBeerList(it) }
                hideLoading()
            }
        }
    }

    private fun updateDetailUI(beersData: Data<List<Beer>>) {
        when (beersData.responseType) {
            Status.ERROR -> {
                hideLoading()
                beersData.error?.message?.let { showMessage(it) }
            }
            Status.LOADING -> {
                showLoading()
            }
            Status.SUCCESSFUL -> {
                startActivity(Intent(this, DetailActivity::class.java))
                hideLoading()
            }
        }
    }

    private fun callStartService() {
        showLoading()
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
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        val punkAdapter = PunkAdapter(beerList)
        punkAdapter.setOnItemClickListener(itemClickListener())
        recyclerView.adapter = punkAdapter
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun callClickDetailsService(item: Beer) {
        showLoading()
        Handler(Looper.getMainLooper()).postDelayed({
            viewModel.onClickToBeerDetails(item.id, this@MainActivity.applicationContext)
        }, MINIMUM_LOADING_TIME)
    }

    private fun itemClickListener() = object : PunkAdapter.OnItemClickListener {
        override fun onItemClick(item: Beer) {
            callClickDetailsService(item)
        }
    }
}