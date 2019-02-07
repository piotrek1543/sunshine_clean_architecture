package com.piotrek1543.android.boilerplate.ui.browse

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.piotrek1543.android.boilerplate.presentation.ViewModelFactory
import com.piotrek1543.android.boilerplate.presentation.browse.GetForecastViewModel
import com.piotrek1543.android.boilerplate.presentation.data.ResourceState
import com.piotrek1543.android.boilerplate.presentation.model.BufferooView
import com.piotrek1543.android.boilerplate.presentation.model.ForecastView
import com.piotrek1543.android.boilerplate.ui.R
import com.piotrek1543.android.boilerplate.ui.mapper.BufferooMapper
import com.piotrek1543.android.boilerplate.ui.widget.empty.EmptyListener
import com.piotrek1543.android.boilerplate.ui.widget.error.ErrorListener
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_browse.*
import javax.inject.Inject

class BrowseActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var browseAdapter: BrowseAdapter
    @Inject
    lateinit var mapper: BufferooMapper
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var getForecastViewModel: GetForecastViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browse)
        getForecastViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(GetForecastViewModel::class.java)

        setupBrowseRecycler()
        setupViewListeners()
    }

    override fun onStart() {
        super.onStart()
        getForecastViewModel.getForecast().observe(this, Observer {
            if (it != null) this.handleDataState(it.status, it.data, it.message)
        })
    }

    private fun setupBrowseRecycler() {
        recycler_browse.layoutManager = LinearLayoutManager(this)
        recycler_browse.adapter = browseAdapter
    }

    private fun handleDataState(resourceState: ResourceState, data: ForecastView?,
                                message: String?) {
        when (resourceState) {
            ResourceState.LOADING -> setupScreenForLoadingState()
            ResourceState.SUCCESS -> setupScreenForSuccess(data)
            ResourceState.ERROR -> setupScreenForError(message)
        }
    }

    private fun setupScreenForLoadingState() {
        progress.visibility = View.VISIBLE
        recycler_browse.visibility = View.GONE
        view_empty.visibility = View.GONE
        view_error.visibility = View.GONE
    }

    private fun setupScreenForSuccess(data: ForecastView?) {
        view_error.visibility = View.GONE
        progress.visibility = View.GONE
        if (data != null) {
            supportActionBar?.title = "${data.cod} ${data.cnt} ${data.message}"
            recycler_browse.visibility = View.VISIBLE
        } else {
            view_empty.visibility = View.VISIBLE
        }
    }

    private fun updateListView(bufferoos: List<BufferooView>) {
        browseAdapter.bufferoos = bufferoos.map { mapper.mapToViewModel(it) }
        browseAdapter.notifyDataSetChanged()
    }

    private fun setupScreenForError(message: String?) {
        progress.visibility = View.GONE
        recycler_browse.visibility = View.GONE
        view_empty.visibility = View.GONE
        view_error.visibility = View.VISIBLE
        Toast.makeText(this@BrowseActivity, message, Toast.LENGTH_LONG).show()

    }

    private fun setupViewListeners() {
        view_empty.emptyListener = emptyListener
        view_error.errorListener = errorListener
    }

    private val emptyListener = object : EmptyListener {
        override fun onCheckAgainClicked() {
            getForecastViewModel.fetchForecast()
        }
    }

    private val errorListener = object : ErrorListener {
        override fun onTryAgainClicked() {
            getForecastViewModel.fetchForecast()
        }
    }

}