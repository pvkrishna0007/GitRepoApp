package com.mobile.gitrepoapp.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RepoCollectionModel {

    @JsonProperty("total_count")
    public Integer totalCount;
    @JsonProperty("incomplete_results")
    public Boolean incompleteResults;
    @JsonProperty("items")
    public List<RepoDetailModel> items = null;

}