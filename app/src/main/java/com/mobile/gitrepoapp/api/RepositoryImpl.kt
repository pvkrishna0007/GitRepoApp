package com.mobile.gitrepoapp.api

import android.content.Context
import android.util.Log
import androidx.lifecycle.liveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mobile.gitrepoapp.api.response.RepoDetailModel
import com.mobile.gitrepoapp.database.RepoDatabase
import com.mobile.gitrepoapp.home.RepoPagingDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import java.io.IOException

class RepositoryImpl(private val apiInterface: ApiInterface, private val repoDatabase: RepoDatabase,
                     private val context: Context): Repository {

    //region Base Exception Handling
    private suspend fun <T> safeApiCall(apiCall: suspend () -> T): ApiResponse<T> {
        return try {
            val response = apiCall.invoke()
            ApiResponse.success(response)
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> ApiResponse.error(data = null, message = throwable.message ?: "Error Occurred!")
                else -> {
                    ApiResponse.error(data = null, message = throwable.message ?: "Error Occurred!")
                }
            }
        }
    }
    //endregion

    override fun getRepositoryResultsFlow(pagingConfig: PagingConfig, search: String): Flow<PagingData<RepoDetailModel>> {
        Log.d("RepositoryImpl", "Search: $search")
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { RepoPagingDataSource(apiInterface, repoDatabase, context, search) }
        ).flow
    }

    override fun getUserRepositories2(userName: String, perPage: Int, page: Int)  = liveData(Dispatchers.IO) {
        emit(safeApiCall { apiInterface.getRepositories(userName, perPage, page) })
    }

}