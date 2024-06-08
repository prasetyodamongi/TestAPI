package com.pcoding.testapi.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pcoding.testapi.ui.screen.HomeScreen
import com.pcoding.testapi.ui.screen.member.AddMemberScreen
import com.pcoding.testapi.ui.screen.member.MemberDetailScreen
import com.pcoding.testapi.ui.screen.member.MemberScreen
import com.pcoding.testapi.ui.screen.user.UserScreen
import com.pcoding.testapi.viewmodel.MemberViewModel

@Composable
fun Navigasi() {
    val navController = rememberNavController()
    val memberViewModel: MemberViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = ScreenConfig.Home.route
    ) {
        composable(ScreenConfig.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(ScreenConfig.User.route) {
            UserScreen(navController = navController)
        }
        composable(ScreenConfig.Member.route) {
            MemberScreen(navController = navController)
        }
        composable(ScreenConfig.DetailMember.route + "/{memberId}") { backStackEntry ->
            val memberId = backStackEntry.arguments?.getString("memberId")
            memberId?.let {
                MemberDetailScreen(memberId = it, memberViewModel = memberViewModel, navController = navController)
            }
        }
        composable(ScreenConfig.AddMember.route) {
            AddMemberScreen(navController = navController)
        }
    }

}