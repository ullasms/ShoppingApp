package com.ullasonlineshop

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.gturedi.views.CustomStateOptions
import com.ullasonlineshop.models.Content
import com.ullasonlineshop.models.KartContent
import com.ullasonlineshop.models.ResourceStatus
import com.ullasonlineshop.adapters.ContentAdapter
import com.ullasonlineshop.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    @Inject
    lateinit var requestManager: RequestManager

    private lateinit var viewModel: MainViewModel

    private val items = mutableListOf<Content>()
    private lateinit var contentAdapter: ContentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initLayouts()
        viewModel = ViewModelProviders.of(this, providerFactory).get(MainViewModel::class.java)
        subscribeObservers()
    }

    private fun initLayouts() {
        stateful_layout.isAnimationEnabled = true
        initRecyclerView()
    }

    private fun initRecyclerView() {
        contentAdapter = ContentAdapter(requestManager, items)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.adapter = contentAdapter
    }

    private fun subscribeObservers() {
        viewModel.getContent().observe(this, Observer {
            when (it) {
                is ResourceStatus.Loading ->
                    stateful_layout.showLoading()
                is ResourceStatus.Success ->
                    it.data?.let { it1 -> updateList(it1) }
                is ResourceStatus.Error ->
                    showError(it.message)
            }
        })
    }

    private fun updateList(kartContent: KartContent) {
        kartContent.content?.let {
            items.clear()
            if (it.isNotEmpty()) {
                items.addAll(it)
                contentAdapter.notifyDataSetChanged()
                stateful_layout.showContent()
                return
            }
        }
        showError(getString(R.string.empty_message))
    }

    private fun showError(error: String?) {
        stateful_layout.showCustom(
            CustomStateOptions()
                .image(android.R.drawable.stat_notify_error)
                .message(error ?: getString(R.string.something_went_wrong_message))
                .buttonText(getString(R.string.retry))
                .buttonClickListener { viewModel.pullContent() }
        )
    }
}