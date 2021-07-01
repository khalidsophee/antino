package com.myproject.antinolabs.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dubai.kashpay.di.ViewModelProviderFactory
import com.myproject.antinolabs.Event
import com.myproject.antinolabs.SessionManager
import com.myproject.antinolabs.data.Model
import com.myproject.antinolabs.data.remote.NetworkState
import com.myproject.antinolabs.databinding.ActivityMainBinding
import com.myproject.antinolabs.di.Injectable
import com.myproject.antinolabs.ui.BasicActivity
import com.myproject.antinolabs.ui.WebActivity
import com.myproject.antinolabs.ui.adapter.NewsAdapter
import com.myproject.antinolabs.utils.OnItemClickListener
import javax.inject.Inject

class MainActivity : BasicActivity(), Injectable, OnItemClickListener {
    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    @Inject
    lateinit var sessionManager: SessionManager

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this, providerFactory).get(MainViewModel::class.java)
    }


    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        // Toast.makeText(this,"hello",Toast.LENGTH_LONG).show()

        viewModel.newsState.observe(this, ::onFetch)

        viewModel.getNews()
    }

    private fun onFetch(s: Event<NetworkState<List<Model>>>) {
        val state = s.getContentIfNotHandled() ?: return
        if (state is NetworkState.Loading<Any>) {

            return
        }
        when (state) {
            is NetworkState.Success -> {
                binding.rvNotifications.layoutManager =
                    LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                binding.rvNotifications.adapter = state.articles?.let { NewsAdapter(it, this) }
            }

            is NetworkState.Error<Any> -> {
                Toast.makeText(this, state.message, Toast.LENGTH_LONG).show()
            }
            is NetworkState.Failure<Any> -> {
                Toast.makeText(this, state.throwable.toString(), Toast.LENGTH_LONG).show()
            }

            else -> {

            }
        }
    }

    override fun onItemClick(index: Int) {
        val intent = Intent(this, WebActivity::class.java)
        startActivity(intent)
    }
}