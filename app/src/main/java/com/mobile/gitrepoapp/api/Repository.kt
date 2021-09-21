package com.mobile.gitrepoapp.api

import androidx.lifecycle.LiveData
import com.mobile.gitrepoapp.api.response.RepoDetailModel

interface Repository {

    fun getRepositories(): LiveData<ApiResponse<List<RepoDetailModel>>>

}