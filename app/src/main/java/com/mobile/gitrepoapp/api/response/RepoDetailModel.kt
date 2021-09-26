package com.mobile.gitrepoapp.api.response

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonProperty

@Entity(tableName = "RepoTable")
data class RepoDetailModel(
    @PrimaryKey
    @JsonProperty("id")
    var id: Int? = null,

    @JsonProperty("node_id")
    var nodeId: String? = null,

    @JsonProperty("name")
    var name: String? = null,

    @JsonProperty("full_name")
    var fullName: String? = null,

    @JsonProperty("private")
    var _private: Boolean? = null,

//
//    @JsonProperty("owner")
//    var owner: Owner? = null,

    @JsonProperty("html_url")
    var htmlUrl: String? = null,

    @JsonProperty("description")
    var description: String? = null,

    @JsonProperty("fork")
    var fork: Boolean? = null,

    @JsonProperty("url")
    var url: String? = null,

    @JsonProperty("forks_url")
    var forksUrl: String? = null,

    @JsonProperty("keys_url")
    var keysUrl: String? = null,

    @JsonProperty("collaborators_url")
    var collaboratorsUrl: String? = null,

    @JsonProperty("teams_url")
    var teamsUrl: String? = null,

    @JsonProperty("hooks_url")
    var hooksUrl: String? = null,

    @JsonProperty("issue_events_url")
    var issueEventsUrl: String? = null,

    @JsonProperty("events_url")
    var eventsUrl: String? = null,

    @JsonProperty("assignees_url")
    var assigneesUrl: String? = null,

    @JsonProperty("branches_url")
    var branchesUrl: String? = null,

    @JsonProperty("tags_url")
    var tagsUrl: String? = null,

    @JsonProperty("blobs_url")
    var blobsUrl: String? = null,

    @JsonProperty("git_tags_url")
    var gitTagsUrl: String? = null,

    @JsonProperty("git_refs_url")
    var gitRefsUrl: String? = null,

    @JsonProperty("trees_url")
    var treesUrl: String? = null,

    @JsonProperty("statuses_url")
    var statusesUrl: String? = null,

    @JsonProperty("languages_url")
    var languagesUrl: String? = null,

    @JsonProperty("stargazers_url")
    var stargazersUrl: String? = null,

    @JsonProperty("contributors_url")
    var contributorsUrl: String? = null,

    @JsonProperty("subscribers_url")
    var subscribersUrl: String? = null,

    @JsonProperty("subscription_url")
    var subscriptionUrl: String? = null,

    @JsonProperty("commits_url")
    var commitsUrl: String? = null,

    @JsonProperty("git_commits_url")
    var gitCommitsUrl: String? = null,

    @JsonProperty("comments_url")
    var commentsUrl: String? = null,

    @JsonProperty("issue_comment_url")
    var issueCommentUrl: String? = null,

    @JsonProperty("contents_url")
    var contentsUrl: String? = null,

    @JsonProperty("compare_url")
    var compareUrl: String? = null,

    @JsonProperty("merges_url")
    var mergesUrl: String? = null,

    @JsonProperty("archive_url")
    var archiveUrl: String? = null,

    @JsonProperty("downloads_url")
    var downloadsUrl: String? = null,

    @JsonProperty("issues_url")
    var issuesUrl: String? = null,

    @JsonProperty("pulls_url")
    var pullsUrl: String? = null,

    @JsonProperty("milestones_url")
    var milestonesUrl: String? = null,

    @JsonProperty("notifications_url")
    var notificationsUrl: String? = null,

    @JsonProperty("labels_url")
    var labelsUrl: String? = null,

    @JsonProperty("releases_url")
    var releasesUrl: String? = null,

    @JsonProperty("deployments_url")
    var deploymentsUrl: String? = null,

    @JsonProperty("created_at")
    var createdAt: String? = null,

    @JsonProperty("updated_at")
    var updatedAt: String? = null,

    @JsonProperty("pushed_at")
    var pushedAt: String? = null,

    @JsonProperty("git_url")
    var gitUrl: String? = null,

    @JsonProperty("ssh_url")
    var sshUrl: String? = null,

    @JsonProperty("clone_url")
    var cloneUrl: String? = null,

    @JsonProperty("svn_url")
    var svnUrl: String? = null,

    @JsonProperty("homepage")
    var homepage: String? = null,

    @JsonProperty("size")
    var size: Int? = null,

    @JsonProperty("stargazers_count")
    var stargazersCount: Int? = null,

    @JsonProperty("watchers_count")
    var watchersCount: Int? = null,

    @JsonProperty("language")
    var language: String? = null,

    @JsonProperty("has_issues")
    var hasIssues: Boolean? = null,

    @JsonProperty("has_projects")
    var hasProjects: Boolean? = null,

    @JsonProperty("has_downloads")
    var hasDownloads: Boolean? = null,

    @JsonProperty("has_wiki")
    var hasWiki: Boolean? = null,

    @JsonProperty("has_pages")
    var hasPages: Boolean? = null,

    @JsonProperty("forks_count")
    var forksCount: Int? = null,

    @JsonProperty("mirror_url")
    var mirrorUrl: String? = null,

    @JsonProperty("archived")
    var archived: Boolean? = null,

    @JsonProperty("disabled")
    var disabled: Boolean? = null,

    @JsonProperty("open_issues_count")
    var openIssuesCount: Int? = null,
//
//    @JsonProperty("license")
//    var license: String? = null,

    @JsonProperty("allow_forking")
    var allowForking: Boolean? = null,

    @JsonProperty("forks")
    var forks: Int? = null,

    @JsonProperty("open_issues")
    var openIssues: Int? = null,

    @JsonProperty("watchers")
    var watchers: Int? = null,

    @JsonProperty("default_branch")
    var defaultBranch: String? = null

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(nodeId)
        parcel.writeString(name)
        parcel.writeString(fullName)
        parcel.writeValue(_private)
        parcel.writeString(htmlUrl)
        parcel.writeString(description)
        parcel.writeValue(fork)
        parcel.writeString(url)
        parcel.writeString(forksUrl)
        parcel.writeString(keysUrl)
        parcel.writeString(collaboratorsUrl)
        parcel.writeString(teamsUrl)
        parcel.writeString(hooksUrl)
        parcel.writeString(issueEventsUrl)
        parcel.writeString(eventsUrl)
        parcel.writeString(assigneesUrl)
        parcel.writeString(branchesUrl)
        parcel.writeString(tagsUrl)
        parcel.writeString(blobsUrl)
        parcel.writeString(gitTagsUrl)
        parcel.writeString(gitRefsUrl)
        parcel.writeString(treesUrl)
        parcel.writeString(statusesUrl)
        parcel.writeString(languagesUrl)
        parcel.writeString(stargazersUrl)
        parcel.writeString(contributorsUrl)
        parcel.writeString(subscribersUrl)
        parcel.writeString(subscriptionUrl)
        parcel.writeString(commitsUrl)
        parcel.writeString(gitCommitsUrl)
        parcel.writeString(commentsUrl)
        parcel.writeString(issueCommentUrl)
        parcel.writeString(contentsUrl)
        parcel.writeString(compareUrl)
        parcel.writeString(mergesUrl)
        parcel.writeString(archiveUrl)
        parcel.writeString(downloadsUrl)
        parcel.writeString(issuesUrl)
        parcel.writeString(pullsUrl)
        parcel.writeString(milestonesUrl)
        parcel.writeString(notificationsUrl)
        parcel.writeString(labelsUrl)
        parcel.writeString(releasesUrl)
        parcel.writeString(deploymentsUrl)
        parcel.writeString(createdAt)
        parcel.writeString(updatedAt)
        parcel.writeString(pushedAt)
        parcel.writeString(gitUrl)
        parcel.writeString(sshUrl)
        parcel.writeString(cloneUrl)
        parcel.writeString(svnUrl)
        parcel.writeString(homepage)
        parcel.writeValue(size)
        parcel.writeValue(stargazersCount)
        parcel.writeValue(watchersCount)
        parcel.writeString(language)
        parcel.writeValue(hasIssues)
        parcel.writeValue(hasProjects)
        parcel.writeValue(hasDownloads)
        parcel.writeValue(hasWiki)
        parcel.writeValue(hasPages)
        parcel.writeValue(forksCount)
        parcel.writeString(mirrorUrl)
        parcel.writeValue(archived)
        parcel.writeValue(disabled)
        parcel.writeValue(openIssuesCount)
        parcel.writeValue(allowForking)
        parcel.writeValue(forks)
        parcel.writeValue(openIssues)
        parcel.writeValue(watchers)
        parcel.writeString(defaultBranch)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RepoDetailModel> {
        override fun createFromParcel(parcel: Parcel): RepoDetailModel {
            return RepoDetailModel(parcel)
        }

        override fun newArray(size: Int): Array<RepoDetailModel?> {
            return arrayOfNulls(size)
        }
    }
}

class Owner {
    @JsonProperty("login")
    var login: String? = null

    @JsonProperty("id")
    var id: Int? = null

    @JsonProperty("node_id")
    var nodeId: String? = null

    @JsonProperty("avatar_url")
    var avatarUrl: String? = null

    @JsonProperty("gravatar_id")
    var gravatarId: String? = null

    @JsonProperty("url")
    var url: String? = null

    @JsonProperty("html_url")
    var htmlUrl: String? = null

    @JsonProperty("followers_url")
    var followersUrl: String? = null

    @JsonProperty("following_url")
    var followingUrl: String? = null

    @JsonProperty("gists_url")
    var gistsUrl: String? = null

    @JsonProperty("starred_url")
    var starredUrl: String? = null

    @JsonProperty("subscriptions_url")
    var subscriptionsUrl: String? = null

    @JsonProperty("organizations_url")
    var organizationsUrl: String? = null

    @JsonProperty("repos_url")
    var reposUrl: String? = null

    @JsonProperty("events_url")
    var eventsUrl: String? = null

    @JsonProperty("received_events_url")
    var receivedEventsUrl: String? = null

    @JsonProperty("type")
    var type: String? = null

    @JsonProperty("site_admin")
    var siteAdmin: Boolean? = null

}