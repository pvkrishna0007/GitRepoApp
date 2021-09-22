package com.mobile.gitrepoapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mobile.gitrepoapp.api.Repository
import com.mobile.gitrepoapp.api.response.RepoDetailModel
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ActivityRetainedScoped
class HomeViewModel  @Inject constructor(
    dispatcher: CoroutineDispatcher,
    private val repository: Repository
) : ViewModel()
{
    private val mJob = Job()
    private val mRepositoryScope = CoroutineScope(dispatcher + mJob)
//    private var mResultLiveData = MutableLiveData<ApiResponse<RepoDetailModel>>()

//    fun getRepositoryResultsLiveData() = repository.getRepositories("pvkrishna0007")


    /**
     * let's define page size, page size is the only required param, rest is optional
     */
    private fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = 5, enablePlaceholders = false)
    }

    fun getRepositoryResultsFlow(search: String): Flow<PagingData<RepoDetailModel>> {
        return repository.getRepositoryResultsFlow(getDefaultPageConfig(), search)
            .cachedIn(mRepositoryScope)
    }

    override fun onCleared() {
        super.onCleared()
        mJob.cancel()
    }
}