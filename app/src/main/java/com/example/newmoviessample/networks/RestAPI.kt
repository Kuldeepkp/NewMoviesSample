package com.example.newmoviessample.networks

import com.example.newmoviessample.models.ServiceResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RestAPI {

    @GET("now_playing")
    suspend fun getNowPlaying(
        @Query("page") page: Long = 1,
        @Query("api_key") api_key: String = "5370eed075b93dd79855f1c429b03ad8"
    ): Response<ServiceResponse?>

    @GET("upcoming")
    suspend fun getUpComing(
        @Query("page") page: Long = 1,
        @Query("api_key") api_key: String = "5370eed075b93dd79855f1c429b03ad8"
    ): Response<ServiceResponse?>

    @GET("popular")
    suspend fun getPopular(
        @Query("page") page: Long = 1,
        @Query("api_key") api_key: String = "5370eed075b93dd79855f1c429b03ad8"
    ): Response<ServiceResponse?>

    @GET("top_rated")
    suspend fun getTopRated(
        @Query("page") page: Long = 1,
        @Query("api_key") api_key: String = "5370eed075b93dd79855f1c429b03ad8"
    ): Response<ServiceResponse?>

}