package com.pcoding.testapi.ui.screen.member

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pcoding.testapi.ui.component.TopBarBack
import com.pcoding.testapi.viewmodel.MemberViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun AddMemberScreen(navController: NavController, modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            TopBarBack(title = "Add Member") {
                navController.popBackStack()
            }
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .background(Color(0xFFE0FFFF))
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = modifier.padding(16.dp)
            ) {
                FormAddMember()
            }
        }
    }
}

@Composable
fun FormAddMember(viewModel: MemberViewModel = viewModel()) {
    val context: Context = LocalContext.current
    var namaState by remember { mutableStateOf("") }
    var alamatState by remember { mutableStateOf("") }
    var umurState by remember { mutableStateOf("") }

    OutlinedTextField(
        value = namaState,
        onValueChange = { namaState = it },
        label = { Text("Nama") },
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(8.dp))
    OutlinedTextField(
        value = alamatState,
        onValueChange = { alamatState = it },
        label = { Text("Alamat") },
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(8.dp))
    OutlinedTextField(
        value = umurState,
        onValueChange = { umurState = it },
        label = { Text("Umur") },
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(16.dp))
    Button(
        onClick = {
            viewModel.addMember(namaState, alamatState, umurState) { success ->
                if (!success) {
                    Toast.makeText(context, "Gagal Menambahkan Member!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Berhasil Menambahkan Member!", Toast.LENGTH_SHORT).show()
                }
            }
            namaState = ""
            alamatState = ""
            umurState = ""
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text("Add Member")
    }
}