package com.pcoding.testapi.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pcoding.testapi.data.api.ApiConfig
import com.pcoding.testapi.data.model.UserModel
import kotlinx.coroutines.launch

class UserViewModel: ViewModel() {
    private val apiService = ApiConfig.createApiClient()

    val users = mutableStateOf<List<UserModel>>(emptyList())
    fun getUser() {
        viewModelScope.launch {
            try {
                val fetchData = apiService.getData()
                users.value = fetchData
            } catch (e: Exception) {
                Log.e(ContentValues.TAG, "Gagal mengambil data", e)
            }
        }
    }
}