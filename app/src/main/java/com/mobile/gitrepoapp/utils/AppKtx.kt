package com.mobile.gitrepoapp.utils

import android.content.Context
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.text.SpannableStringBuilder
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.text.bold
import androidx.core.text.color
import androidx.core.text.underline
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.mobile.gitrepoapp.R
import java.text.SimpleDateFormat
import java.util.*

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

@BindingAdapter("load_image")
fun ImageView.loadImage(imageUrl: String?) {
    Glide.with(context)
        .load(imageUrl)
        .centerCrop()
        .placeholder(ContextCompat.getDrawable(context, R.drawable.ic_placeholder))
        .error(ContextCompat.getDrawable(context, R.drawable.ic_placeholder))
        .into(this)
}

@BindingAdapter(value = ["prefix", "suffix", "suffixAsLink"], requireAll = false)
fun TextView.setContent(prefix: String?, suffix: String?, suffixAsLink: Boolean = false) {
    this.isVisible = !suffix.isNullOrEmpty()
    this.text = SpannableStringBuilder()
        .color ( Color.DKGRAY) { append(prefix?:"") }
        .append(" :  ")
        .bold { if(suffixAsLink) underline { color(Color.BLUE) { append(suffix?:"") } } else append(suffix?:"") }
}

fun String?.convertToDateTimeFormat(): String {
    return this.convertStringFromSourceFormatToDestinationFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", "dd MMM yyyy HH:mm:ss")
}
/**
 * This method is used to converting the date string to targeted format.
 *
 * @param originalFormat input date format of dateString
 * @param targetFormat   target date format of the resultant string.
 * @return which returns the converted date as specified in targetFormat. Which returns empty, if any of the format is invalid
 */
fun String?.convertStringFromSourceFormatToDestinationFormat(originalFormat: String, targetFormat: String): String {
    if (this.isNullOrEmpty()) return ""
    try {
        val iFormatter = SimpleDateFormat(originalFormat, Locale.US)
        val oFormatter = SimpleDateFormat(targetFormat, Locale.US)

        return oFormatter.format(iFormatter.parse(this)!!)
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return this
}