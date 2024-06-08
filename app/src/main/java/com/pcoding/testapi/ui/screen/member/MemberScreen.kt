package com.pcoding.testapi.ui.screen.member

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.pcoding.testapi.ui.component.CardMember
import com.pcoding.testapi.ui.component.TopBarBack
import com.pcoding.testapi.viewmodel.MainViewModel
import com.pcoding.testapi.viewmodel.MemberViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import com.pcoding.testapi.navigation.ScreenConfig
import com.pcoding.testapi.ui.component.TopBarWithMenu

@Composable
fun MemberScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            TopBarWithMenu(
                title = "List Member",
                onClickMenu = {
                    navController.navigate(ScreenConfig.AddMember.route)
                },
                onBack = {
                    navController.popBackStack()
                }
            )
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
                ListMember(navController)
            }

        }
    }
}

@Composable
fun ListMember(navController: NavController, membersViewModel: MemberViewModel = viewModel()) {
    val members = membersViewModel.members.collectAsState()

    LazyColumn {
        items(members.value) { member ->
            CardMember(member) {
                navController.navigate("${ScreenConfig.DetailMember.route}/${member.id}")
            }
        }
    }
}