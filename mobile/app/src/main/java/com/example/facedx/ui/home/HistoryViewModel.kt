package com.example.facedx.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import com.example.facedx.database.HistoryDatabase
import com.example.facedx.database.HistoryRepository

class HistoryViewModel(app: Application) : AndroidViewModel(app) {

    private val repo: HistoryRepository =
        HistoryRepository(HistoryDatabase.getInstance(app).historyDao())

    val histories = repo.allHistory.asLiveData()
}
