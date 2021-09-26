package com.mobile.gitrepoapp.home

import android.content.Context
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mobile.gitrepoapp.api.ApiInterface
import com.mobile.gitrepoapp.api.response.RepoDetailModel
import com.mobile.gitrepoapp.database.RepoDatabase
import com.mobile.gitrepoapp.utils.AppConfig
import com.mobile.gitrepoapp.utils.isNetworkAvailable

class RepoPagingDataSource(private val service: ApiInterface, private val repoDatabase: RepoDatabase,
                           private val context: Context, private val search: String) :
    PagingSource<Int, RepoDetailModel>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepoDetailModel> {
        val pageNumber = params.key ?: 1
        return try {
            val repoList =
            if(context.isNetworkAvailable()) {
                service.searchRepositories(
                    search,
                    page = pageNumber,
                    perPage = AppConfig.PER_PAGE_COUNT
                ).items.apply {
                    if(pageNumber == 1 && this.isNotEmpty())
                        repoDatabase.repoDao().deleteAll()
                    if(repoDatabase.repoDao().getCount() <= AppConfig.OFFLINE_PAGE_COUNT)
                        repoDatabase.repoDao().insertAll(this)
                }
            }
            else {
                if(pageNumber == 1) {
                    repoDatabase.repoDao().getRepoListByLimit(
                        AppConfig.OFFLINE_PAGE_COUNT,
                        pageNumber * AppConfig.OFFLINE_PAGE_COUNT
                    )
                } else {
                    // SKIP for more offline entries
                    listOf()
                }
            }

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
