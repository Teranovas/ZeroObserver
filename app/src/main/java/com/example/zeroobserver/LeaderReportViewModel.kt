package com.example.zeroobserver

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.zeroobserver.ReportStorage

class LeaderReportViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = GptRepository()
    private val memoryStorage = MemoryStorage(application)
    private val reportStorage = ReportStorage(application)

    val reportText = MutableLiveData<String>()

    fun generateLeaderReport(country: String) {
        val memory = memoryStorage.loadMemory(country)
        val personality = LeaderPersonalityLoader.load(application, country)

        val prompt = """
            You are simulating the geopolitical behavior of $country. 
            The leader has the following personality: $personality. 
            The current memory of past decisions is: $memory. 
            Based on these, write a strategic report on how this country will respond to ongoing global crises.
        """.trimIndent()

        viewModelScope.launch {
            val result = repository.getResponse(prompt)
            reportText.postValue(result)
            reportStorage.saveReport(country, result)
        }
    }
}
