package com.mobile.gitrepoapp.api

import androidx.lifecycle.liveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mobile.gitrepoapp.api.response.RepoDetailModel
import com.mobile.gitrepoapp.home.RepoPagingDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(private val apiInterface: ApiInterface): Repository {


    //region Repositories

    /**
     * To get the list of repositories for the given user as live data
     */
    override fun getRepositories(repoUser: String, perPage: Int, page: Int) = liveData(Dispatchers.IO) {
        emit(ApiResponse.loading(data = null))
        try {
            val baseModel = apiInterface.getRepositories(repoUser = repoUser, perPage = perPage, page = page)
            emit(ApiResponse.success(data = baseModel))
        } catch (exception: Exception) {
            emit(ApiResponse.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }


    //endregion

    override fun getRepositoryResultsFlow(pagingConfig: PagingConfig): Flow<PagingData<RepoDetailModel>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { RepoPagingDataSource(apiInterface) }
        ).flow
    }

}