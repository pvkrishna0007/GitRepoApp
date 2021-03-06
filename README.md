# GitRepoApp

GIT DEVELOPERS URL: https://developer.github.com/v3/search/

To download the APK: [GitRepoApp_V1.apk](https://github.com/pvkrishna0007/GitRepoApp/raw/master/GitRepoApp_V1.apk)

## Functionality
### 1. Home Screen: 
#### _Implemented search functionality for finding the repositories in the github_

*  A search bar to search from git APIs
*  A recycler view using card view to display the search results.
*  The results count should be limited to 10 per page.
*  Clicking on an item to go to Repo Details Activity.

**Deep link**

syntax: http://www.example.com/search/{RepositorySearchText}

example: http://www.example.com/search/River

Adb command for testing: **_adb shell am start -a android.intent.action.VIEW -d "http://www.example.com/search/River"_**


### 2. Repo Details: 
#### _Designed screen to view the repository details for the given username and repository name_
*  This Activity should have a detailed description of the selected item.
*  Details such as Image, Name, Project Link, Description, Contributors should be displayed.
*  When you click on the "project link" section, need to open a web view to show the content of the link.

**Deep link**

syntax: http://www.example.com/repoDetails/{userName}/{repositoryName}

example: http://www.example.com/repoDetails/pvkrishna0007/GitRepoApp

Adb command for testing: **_adb shell am start -a android.intent.action.VIEW -d "http://www.example.com/repoDetails/pvkrishna0007/GitRepoApp"_**

### 3. View Repo Code/Details in WebView: 
#### _Which loads repository related information in WebView._

**Deep link**

syntax: http://www.example.com/repo/{repoPath}

example: http://www.example.com/repo/pvkrishna0007/GitRepoApp

Adb command for testing: **_adb shell am start -a android.intent.action.VIEW -d "http://www.example.com/repo/pvkrishna0007/GitRepoApp"_**

***

## Following concepts are used in this project.

## Navigation Library
Contains all of your destinations and actions. The graph represents all of our app's navigation paths.

## Hilt Dagger
Hilt is a dependency injection library for Android that reduces the boilerplate of doing manual dependency injection

## Paging 3
The Paging library helps you load and display pages of data from a larger dataset from local storage or over network.

## Room Persistence Library
The Room persistence library provides an abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite.

## Retrofit Networking Library
Provides a powerful framework for authenticating and interacting with APIs and sending network requests with OkHttp.

## Jackson Parse Library
Which parses the API response into the Java object.

## Glide Image Library
To load the image from network in efficient way

## Stetho Library
To monitor the api request and responses

## Other Concepts
DataBinding, Suspend Functions, Coroutines, Flows, LiveData, Extension Functions, BindingAdapters

