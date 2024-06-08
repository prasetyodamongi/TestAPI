package com.pcoding.testapi.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pcoding.testapi.data.api.ApiConfig
import com.pcoding.testapi.data.model.PostModel
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val apiService = ApiConfig.createApiClient()

    fun postPesan(userId: String, pesan: String, callback: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                apiService.postPesan(PostModel(userId, pesan))
                callback(true)
            } catch (e: Exception) {
                Log.e(TAG, "Gagal mengirim pesan", e)
                callback(false)
            }
        }
    }
}