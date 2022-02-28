package com.example.newmoviessample.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import com.example.newmoviessample.R
import com.example.newmoviessample.models.MoviesEntity
import com.example.newmoviessample.models.ServiceResponse
import com.example.newmoviessample.networks.NetworkResult
import com.example.newmoviessample.viewmodels.MoviesViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val moviesViewModel by viewModels<MoviesViewModel>()
    private lateinit var adapter: ParentRecyclerAdapter
    private lateinit var recyclerView: RecyclerView

    private val list = mutableListOf(
        MoviesEntity("NowPlaying", nowResults = emptyList()),
        MoviesEntity("UpComing", upComingResults = emptyList()),
        MoviesEntity("Popular", popularResults = emptyList()),
        MoviesEntity("TopRated", topRatedResults = emptyList())
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = ParentRecyclerAdapter(list)
        recyclerView = findViewById(R.id.mRecycler)
        recyclerView.adapter = adapter
        moviesViewModel.updateData()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                moviesViewModel._flow.collect { result ->
                    result.map {
                        when (it) {
                            is NetworkResult.Error -> {
                                Log.d("TAG", "$it")
                            }
                            is NetworkResult.Success -> {
                                updateData(it.data)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun updateData(data: ServiceResponse?) {
        when (data?.type) {
            "NowPlaying" -> {
                list[0] = MoviesEntity("NowPlaying", data.results)
                adapter.notifyItemChanged(0)
            }
            "UpComing" -> {
                list[1] = MoviesEntity("UpComing", upComingResults = data.results)
                adapter.notifyItemChanged(1)
            }
            "Popular" -> {
                list[2] = MoviesEntity("Popular", popularResults = data.results)
                adapter.notifyItemChanged(2)
            }
            "TopRated" -> {
                list[3] = MoviesEntity("TopRated", topRatedResults = data.results)
                adapter.notifyItemChanged(3)
            }
        }
    }
}