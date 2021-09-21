package com.mobile.gitrepoapp.api

import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers

class RepositoryImpl(private val apiInterface: ApiInterface): Repository {


    //region Logout

    override fun getRepositories() = liveData(Dispatchers.IO) {
        emit(ApiResponse.loading(data = null))
        try {
            val baseModel = apiInterface.getRepositories("pvkrishna0007")
            emit(ApiResponse.success(data = baseModel))
        } catch (exception: Exception) {
            emit(ApiResponse.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    //endregion

}