package com.example.zeroobserver

import android.content.Context

object MemoryStorage {

    private const val PREF_NAME = "memory_prefs"
    private const val KEY_MEMORY = "edited_memory"

    fun save(context: Context, memory: String) {
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            .edit()
            .putString(KEY_MEMORY, memory)
            .apply()
    }

    fun load(context: Context): String {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            .getString(KEY_MEMORY, "") ?: ""
    }
}
