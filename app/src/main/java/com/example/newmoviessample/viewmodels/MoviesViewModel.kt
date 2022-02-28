package com.example.newmoviessample.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newmoviessample.models.ServiceResponse
import com.example.newmoviessample.networks.NetworkResult
import com.example.newmoviessample.repositories.MoviesRepositoryImp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class MoviesViewModel : ViewModel() {

    private val repository = MoviesRepositoryImp.getMovieRepository()
    private val _statFLow =
        MutableStateFlow<List<NetworkResult<ServiceResponse>>>(emptyList())
    val _flow: Flow<List<NetworkResult<ServiceResponse>>> get() = _statFLow


    fun updateData() {
        viewModelScope.launch {
            val item = listOf(
                repository.getNowPlaying(),
                repository.getUpComing(),
                repository.getPopular(),
                repository.getTopRated()
            )

            combine(
                repository.getNowPlaying(),
                repository.getUpComing(),
                repository.getPopular(),
                repository.getTopRated()
            ) { t1, t2, t3, t4 -> listOf(t1, t2, t3, t4) }.collect {
                _statFLow.value = it
            }

//            item.scan(emptyFlow<NetworkResult<ServiceResponse>>()) { _, flow -> flow }
//                .map {
//                    it.collect {
//                        Log.d("TAG", "$it")
//                    }
//                }


//            merge(
//                repository.getNowPlaying(),
//                repository.getUpComing(),
//                repository.getPopular(),
//                repository.getTopRated()
//            ).collect {
//                _statFLow.value = it
//            }

        }
    }
}