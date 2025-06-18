package com.example.zeroobserver

import android.content.Context
import org.json.JSONObject

object ReportStorage {

    private const val PREF_NAME = "report_storage"

    fun saveReport(context: Context, country: String, report: String) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(country, report).apply()
    }

    fun loadAllReports(context: Context): Map<String, String> {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return prefs.all.mapValues { it.value.toString() }
    }

    fun clearAll(context: Context) {
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit().clear().apply()
    }
}
