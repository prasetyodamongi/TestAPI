package com.pcoding.testapi.ui.screen.member

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pcoding.testapi.ui.component.TopBarBack
import com.pcoding.testapi.viewmodel.MemberViewModel

@Composable
fun MemberDetailScreen(
    memberId: String,
    memberViewModel: MemberViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val memberState = memberViewModel.selectedMember.collectAsState().value

    LaunchedEffect(memberId) {
        memberViewModel.getMemberById(memberId)
    }

    Scaffold(
        topBar = {
            TopBarBack(title = "Detail Member ${memberState?.id}") {
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
            if (memberState != null) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(text = "Nama: ${memberState.nama}",)
                    Text(text = "Level: ${memberState.level}",)
                    Text(text = "Umur: ${memberState.umur}",)
                    Text(text = "Alamat: ${memberState.alamat}",)
                }
            }
        }
    }
}