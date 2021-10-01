package com.mobile.gitrepoapp.api

import com.mobile.gitrepoapp.api.response.RepoCollectionModel
import com.mobile.gitrepoapp.api.response.RepoDetailModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {


    @GET("users/{repoUser}/repos")
    suspend fun getUserRepositories(@Path("repoUser")repoUser: String,
                                @Query("per_page")perPage: Int,
                                @Query("page")page: Int
    ): List<RepoDetailModel>

    @GET("search/repositories")
    suspend fun searchRepositories(@Query("q")search: String,
                                @Query("per_page")perPage: Int,
                                @Query("page")page: Int
    ): RepoCollectionModel

    // https://api.github.com/repos/octocat/Hello-World
    @GET("repos/{userName}/{repositoryName}")
    suspend fun getRepositoryDetailsByPath(@Path("userName")userName: String,
                                           @Path("repositoryName")repositoryName: String
    ): RepoDetailModel
}