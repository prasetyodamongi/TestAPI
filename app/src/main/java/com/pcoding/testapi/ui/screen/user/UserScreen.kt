package com.pcoding.testapi.ui.screen.user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.pcoding.testapi.ui.component.CardUser
import com.pcoding.testapi.ui.component.TopBarBack
import com.pcoding.testapi.viewmodel.UserViewModel

@Composable
fun UserScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    userViewModel: UserViewModel = viewModel(),
) {

    LaunchedEffect(Unit) {
        userViewModel.getUser()
    }

    Scaffold(
        topBar = {
            TopBarBack(title = "List User") {
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
            ListUser()
        }
    }
}

@Composable
fun ListUser(userViewModel: UserViewModel = viewModel()) {
    val users = userViewModel.users

    LazyColumn {
        items(users.value) { user ->
            CardUser(user)
        }
    }
}