package com.pcoding.testapi.navigation

const val HOME = "home"
const val USER = "user"
const val MEMBER = "member"
const val DETAIL_MEMBER = "detail_member"
const val ADD_MEMBER = "add_member"

sealed class ScreenConfig(val route: String) {
    object Home: ScreenConfig(HOME)
    object User: ScreenConfig(USER)
    object Member: ScreenConfig(MEMBER)
    object DetailMember: ScreenConfig(DETAIL_MEMBER)
    object AddMember: ScreenConfig(ADD_MEMBER)
}