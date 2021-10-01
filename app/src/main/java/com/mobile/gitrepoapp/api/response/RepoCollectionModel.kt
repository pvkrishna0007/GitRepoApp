package com.mobile.gitrepoapp.api.response

import com.fasterxml.jackson.annotation.JsonProperty

class RepoCollectionModel {
    @JsonProperty("total_count")
    var totalCount: Int? = null

    @JsonProperty("incomplete_results")
    var incompleteResults: Boolean? = null

    @JsonProperty("items")
    var items: List<RepoDetailModel> = arrayListOf()
}