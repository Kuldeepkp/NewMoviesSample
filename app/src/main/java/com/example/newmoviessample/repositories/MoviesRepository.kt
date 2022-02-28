package com.example.newmoviessample.repositories

import com.example.newmoviessample.models.ServiceResponse
import com.example.newmoviessample.networks.NetworkResult
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    suspend fun getNowPlaying(): Flow<NetworkResult<ServiceResponse>?>
    suspend fun getUpComing(): Flow<NetworkResult<ServiceResponse>?>
    suspend fun getPopular(): Flow<NetworkResult<ServiceResponse>?>
    suspend fun getTopRated(): Flow<NetworkResult<ServiceResponse>?>
}