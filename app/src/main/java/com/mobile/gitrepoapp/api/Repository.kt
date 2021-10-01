package com.mobile.gitrepoapp.api

import androidx.lifecycle.LiveData
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mobile.gitrepoapp.api.response.RepoDetailModel
import kotlinx.coroutines.flow.Flow

interface Repository {

//    fun getRepositories(repoUser: String, perPage: Int = 12, page: Int = 1): LiveData<ApiResponse<List<RepoDetailModel>>>

    fun getRepositoryResultsFlow(pagingConfig: PagingConfig, search: String): Flow<PagingData<RepoDetailModel>>

    fun getUserRepositories(userName: String, perPage: Int, page: Int): LiveData<ApiResponse<List<RepoDetailModel>>>

    fun getRepositoryDetailsByPath(userName: String, repositoryName: String): LiveData<ApiResponse<RepoDetailModel>>
}