package com.mobile.gitrepoapp.utils

import android.util.Log

class MyCustomException constructor(): Exception() {

    constructor(message: String) : this() {
        Log.d("TAG", ": ")
    }
}