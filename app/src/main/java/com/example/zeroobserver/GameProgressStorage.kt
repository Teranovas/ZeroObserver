package com.example.zeroobserver

import android.content.Context
import org.json.JSONArray

object GameProgressStorage {

    fun saveProgress(context: Context, country: String, history: List<String>, turn: Int) {
        val prefs = context.getSharedPreferences("progress_prefs", Context.MODE_PRIVATE)
        prefs.edit()
            .putInt("${country}_turn", turn)
            .putString("${country}_history", JSONArray(history).toString())
            .apply()
    }

    fun loadProgress(context: Context, country: String): Pair<List<String>, Int> {
        val prefs = context.getSharedPreferences("progress_prefs", Context.MODE_PRIVATE)
        val turn = prefs.getInt("${country}_turn", 0)
        val historyJson = prefs.getString("${country}_history", "[]")
        val jsonArray = JSONArray(historyJson)
        val history = List(jsonArray.length()) { i -> jsonArray.getString(i) }
        return history to turn
    }

    fun clearProgress(context: Context, country: String) {
        val prefs = context.getSharedPreferences("progress_prefs", Context.MODE_PRIVATE)
        prefs.edit()
            .remove("${country}_turn")
            .remove("${country}_history")
            .apply()
    }
}
