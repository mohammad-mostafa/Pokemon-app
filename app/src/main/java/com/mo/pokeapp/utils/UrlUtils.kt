package com.mo.pokeapp.utils

import android.net.Uri
import androidx.core.text.isDigitsOnly
import javax.inject.Inject
import kotlin.Exception

class UrlUtils @Inject constructor() {

    @Throws(Exception::class)
    fun getLastPathSegment(url: String): Int {
        val uri: Uri = Uri.parse(url)
        val token = uri.lastPathSegment
        return if (!token.isNullOrEmpty() && token.isDigitsOnly()) token.toInt() else throw Exception(
            "Couldn't find id as the last path segment"
        )
    }

    @Throws(Exception::class)
    fun getOffset(url: String): Int {

        var ans: Int? = null

        url.let {
            val uri: Uri = Uri.parse(it)
            val offset = uri.getQueryParameter("offset")

            if (!offset.isNullOrEmpty() && offset.isDigitsOnly()) {
                ans = offset.toInt()
            }
        }
        return ans ?: throw Exception("Couldn't find offset in the url")
    }
}