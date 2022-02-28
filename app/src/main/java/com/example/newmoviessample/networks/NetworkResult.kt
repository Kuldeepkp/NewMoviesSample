package com.example.newmoviessample.networks


/**
 *Created by Kuldeep on 28/12/21.
 **/

sealed class NetworkResult<T>(
    val mData: T? = null,
    val mErrorMessage: String? = null
) {

    data class Success<T>(val data: T?) : NetworkResult<T>(data)

    data class Error<T>(val message: String?) : NetworkResult<T>(null, message)


}