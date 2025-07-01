package com.example.zeroobserver

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LeaderReportViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GptRepository()

    val reportText = MutableLiveData<String>()

    fun generateLeaderReport(country: String) {
        val context = getApplication<Application>()

        val memory = MemoryStorage.load(context)
        val personality = LeaderPersonalityLoader.load(context, country)

        val prompt = """
            You are simulating the geopolitical behavior of $country.
            The leader has the following personality: $personality.
            The current memory of past decisions is: $memory.
            Based on these, write a strategic report on how this country will act in global affairs.
        """.trimIndent()

        viewModelScope.launch {
            val result = repository.getResponse(prompt)
            reportText.value = result
            ReportStorage.saveReport(context, country, result)
        }
    }
}
