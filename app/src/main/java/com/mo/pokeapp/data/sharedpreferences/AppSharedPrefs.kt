package com.mo.pokeapp.data.sharedpreferences

import android.content.Context
import androidx.preference.PreferenceManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AppSharedPrefs @Inject constructor(@ApplicationContext context: Context) {

    companion object {
        const val SPECIES_DETAILS_CACHE_TTL = 60 * 60 * 24 * 1000 // one day in milliseconds
    }

    private val prefs by lazy {
        PreferenceManager.getDefaultSharedPreferences(context)
    }

    fun getLong(key: String, default: Long): Long {
        return prefs.getLong(key, default)
    }

    fun putLong(key: String, value: Long) {
        prefs.edit().putLong(key, value).apply()
    }

    fun getCurrentTime(): Long = System.currentTimeMillis()
}