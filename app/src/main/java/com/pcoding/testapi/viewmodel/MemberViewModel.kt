package com.pcoding.testapi.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pcoding.testapi.data.api.ApiConfig
import com.pcoding.testapi.data.model.AddMemberModel
import com.pcoding.testapi.data.model.MemberModel
import com.pcoding.testapi.data.model.PostModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.security.auth.callback.Callback

class MemberViewModel : ViewModel() {
    private val apiService = ApiConfig.createApiClient()

    private val _members = MutableStateFlow<Array<MemberModel>>(emptyArray())
    val members: StateFlow<Array<MemberModel>> = _members

    private val _selectedMember = MutableStateFlow<MemberModel?>(null)
    val selectedMember: StateFlow<MemberModel?> = _selectedMember

    init {
        loadMembers()
    }

    private fun loadMembers() {
        viewModelScope.launch(Dispatchers.IO) {
            val membersArray = apiService.getAllMembers()
            _members.value = membersArray
        }
    }

    fun getMemberById(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val memberList = apiService.getMemberById(id)
            if (memberList.isNotEmpty()) {
                _selectedMember.value = memberList[0]
            }
        }
    }

    fun addMember(nama: String, alamat: String, umur: String, callBack: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                apiService.addMember(AddMemberModel(nama, alamat, umur))
                callBack(true)
            } catch (e: Exception) {
                Log.e(ContentValues.TAG, "Gagal menambahkan member", e)
                callBack(false)
            }
        }
    }
}