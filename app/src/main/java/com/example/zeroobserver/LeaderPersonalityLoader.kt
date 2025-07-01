package com.example.zeroobserver

import android.content.Context

object LeaderPersonalityLoader {
    fun load(context: Context, country: String): String {
        // 하드코딩 예시. 실제론 JSON이나 DB 기반 가능.
        return when (country) {
            "USA" -> "Assertive, Pragmatic, Values global dominance"
            "China" -> "Strategic, Long-term planner, Nationalist"
            else -> "Balanced, Neutral, Cooperative"
        }
    }
}
