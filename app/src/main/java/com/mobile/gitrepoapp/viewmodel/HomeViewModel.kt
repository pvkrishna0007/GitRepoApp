package com.mobile.gitrepoapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.distinctUntilChanged
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mobile.gitrepoapp.api.Repository
import com.mobile.gitrepoapp.api.response.RepoDetailModel
import com.mobile.gitrepoapp.utils.AppConfig
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
@ActivityRetainedScoped
class HomeViewModel  @Inject constructor(
    dispatcher: CoroutineDispatcher,
    private val repository: Repository
) : ViewModel()
{
    private val mJob = Job()
    private val mRepositoryScope = CoroutineScope(dispatcher + mJob)
    private val queryLiveData = MutableLiveData(AppConfig.DEFAULT_QUERY)
    val repositoryResultsFlow : Flow<PagingData<RepoDetailModel>>

    init {
        Log.d("HomeViewModel", "ViewModel Constructor")
        repositoryResultsFlow = getRepositoryListAsFlow()
    }

    /**
     * let's define page size, page size is the only required param, rest is optional
     */
    private fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = AppConfig.PER_PAGE_COUNT, enablePlaceholders = false)
    }

    private fun getRepositoryListAsFlow(): Flow<PagingData<RepoDetailModel>> {
        return queryLiveData
            .asFlow()
            .distinctUntilChanged()
            .debounce(300)
            .flatMapLatest { searchQuery ->
                repository.getRepositoryResultsFlow(getDefaultPageConfig(), searchQuery)
                    .cachedIn(mRepositoryScope)
            }
    }

    fun searchRepositories(search: String) {
        queryLiveData.postValue(search)
    }

    override fun onCleared() {
        super.onCleared()
        mJob.cancel()
    }
}