package com.mobile.gitrepoapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.mobile.gitrepoapp.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


/**
 * To force hide the keyboard
 *
 *  OR
 *
 * Use <b><i>android:imeOptions="actionDone"</i></b> with EditText
 */
fun View.hideSoftKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
    imm!!.hideSoftInputFromWindow(windowToken, 0)
}


fun Context.isNetworkAvailable(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                    return true
                }
            }
        }
    } else {
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
            return true
        }
    }
    return false
}

fun ImageView.loadImage(imageUrl: String?) {
    Glide.with(context)
        .load(imageUrl)
        .centerCrop()
        .placeholder(ContextCompat.getDrawable(context, R.drawable.ic_placeholder))
        .error(ContextCompat.getDrawable(context, R.drawable.ic_placeholder))
        .into(this)
}

fun EditText.getQueryTextChangeStateFlow(): StateFlow<String> {
    val query = MutableStateFlow("")
    doOnTextChanged { text, start, before, count ->
        query.value = text?.toString()?:""
    }
    return query
}

@BindingAdapter("label_text")
fun TextView.setLabelText(labelContent: String?) {
    this.text = labelContent
}


@BindingAdapter("prefix", "suffix")
fun TextView.setContent(prefix: String?, suffix: String?) {
    this.text = "$prefix::$suffix"
}