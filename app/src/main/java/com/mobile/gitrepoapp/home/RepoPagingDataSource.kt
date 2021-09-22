package com.mobile.gitrepoapp.home

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mobile.gitrepoapp.api.ApiInterface
import com.mobile.gitrepoapp.api.response.RepoDetailModel
import com.mobile.gitrepoapp.utils.AppConfig

class RepoPagingDataSource(private val service: ApiInterface, private val search: String) :
    PagingSource<Int, RepoDetailModel>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepoDetailModel> {
        val pageNumber = params.key ?: 1
        return try {
            val repoCollectionModel = service.searchRepositories(search, page = pageNumber, perPage = AppConfig.PER_PAGE_COUNT)
            val repoList = repoCollectionModel.items

//            val prevKey = if (pageNumber == 1) null else pageNumber - 1

            LoadResult.Page(
                data = repoList,
                prevKey = null,
                nextKey = if (repoList.isEmpty()) null else pageNumber.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, RepoDetailModel>): Int? {
        return null
    }
}
