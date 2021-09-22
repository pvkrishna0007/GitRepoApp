package com.mobile.gitrepoapp.api

import com.mobile.gitrepoapp.api.response.RepoCollectionModel
import com.mobile.gitrepoapp.api.response.RepoDetailModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("search/code")
    suspend fun search(): String

    @GET("users/{repoUser}/repos")
    suspend fun getRepositories(@Path("repoUser")repoUser: String,
                                @Query("per_page")perPage: Int,
                                @Query("page")page: Int
    ): List<RepoDetailModel>

    @GET("search/repositories?q=q&page=1&per_page=3")
    suspend fun searchRepositories(@Query("q")search: String,
                                @Query("per_page")perPage: Int,
                                @Query("page")page: Int
    ): RepoCollectionModel

}