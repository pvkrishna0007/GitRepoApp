package com.mobile.gitrepoapp.api

import com.mobile.gitrepoapp.api.response.RepoDetailModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("search/code")
    suspend fun search(): String

    @GET("users/{repoUser}/repos")
    suspend fun getRepositories(@Path("repoUser")repoUser: String,
                                @Query("per_page")perPage: Int = 15,
                                @Query("page")page: Int = 1
    ): List<RepoDetailModel>




}