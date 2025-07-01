package com.example.zeroobserver

import android.content.Context

object LeaderPersonalityLoader {
    fun load(context: Context, country: String): String {
        return when (country) {
            "USA" -> "Assertive, Pragmatic, Values global dominance"
            "China" -> "Strategic, Long-term planner, Nationalist"
            else -> "Balanced, Neutral, Cooperative"
        }
    }
}