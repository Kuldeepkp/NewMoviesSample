package com.example.newmoviessample.repositories

import com.example.newmoviessample.networks.NetworkResult
import com.example.newmoviessample.networks.RestAPI
import com.example.newmoviessample.networks.RetrofitManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MoviesRepositoryImp(private val restAPI: RestAPI) : MoviesRepository {

    companion object {
        fun getMovieRepository(): MoviesRepositoryImp {
            return MoviesRepositoryImp(RetrofitManager.apiService)
        }
    }

    override suspend fun getNowPlaying() = flow {
        val response = restAPI.getNowPlaying()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            body.type = "NowPlaying"
            emit(NetworkResult.Success(body))
        } else {
            emit(NetworkResult.Error(response.message()))
        }
    }.flowOn(Dispatchers.IO).catch {
        emit(NetworkResult.Error(it.message))
    }

    override suspend fun getUpComing() = flow {
        val response = restAPI.getUpComing()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            body.type = "UpComing"
            emit(NetworkResult.Success(body))
        } else {
            emit(NetworkResult.Error(response.message()))
        }
    }.flowOn(Dispatchers.IO).catch { emit(NetworkResult.Error(it.message)) }

    override suspend fun getPopular() = flow {
        val response = restAPI.getPopular()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            body.type = "Popular"
            emit(NetworkResult.Success(body))
        } else {
            emit(NetworkResult.Error(response.message()))
        }
    }.flowOn(Dispatchers.IO).catch { emit(NetworkResult.Error(it.message)) }

    override suspend fun getTopRated() = flow {
        val response = restAPI.getTopRated()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            body.type = "TopRated"
            emit(NetworkResult.Success(body))
        } else {
            emit(NetworkResult.Error(response.message()))
        }
    }.flowOn(Dispatchers.IO).catch { emit(NetworkResult.Error(it.message)) }
}