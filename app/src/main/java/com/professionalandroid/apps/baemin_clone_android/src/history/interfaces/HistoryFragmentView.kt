package com.professionalandroid.apps.baemin_clone_android.src.history.interfaces

import com.professionalandroid.apps.baemin_clone_android.src.history.models.HistoryResponse

interface HistoryFragmentView {
    fun getHistory(body: HistoryResponse)
    fun HistoryIsEmpty()
}