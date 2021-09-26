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

class RepositoryImpl(private val apiInterface: ApiInterface, private val repoDatabase: RepoDatabase,
                     private val context: Context): Repository {


    //region Repositories

    /**
     * To get the list of repositories for the given user as live data
     */
//    override fun getRepositories(repoUser: String, perPage: Int, page: Int) = liveData(Dispatchers.IO) {
//        emit(ApiResponse.loading(data = null))
//        try {
//            val baseModel = apiInterface.getRepositories(repoUser = repoUser, perPage = perPage, page = page)
//            emit(ApiResponse.success(data = baseModel))
//        } catch (exception: Exception) {
//            emit(ApiResponse.error(data = null, message = exception.message ?: "Error Occurred!"))
//        }
//    }

    //endregion

    override fun getRepositoryResultsFlow(pagingConfig: PagingConfig, search: String): Flow<PagingData<RepoDetailModel>> {
        Log.d("RepositoryImpl", "Search: $search")
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { RepoPagingDataSource(apiInterface, repoDatabase, context, search) }
        ).flow
    }

}