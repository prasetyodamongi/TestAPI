package com.pcoding.testapi.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pcoding.testapi.ui.theme.TestAPITheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarBack(
    title: String,
    modifier: Modifier = Modifier,
    onBack: () -> Unit
) {
    val topBarColor = Color(0xFFADFFFF)

    TopAppBar(
        title = {
            Column(
                modifier = modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        modifier = modifier
            .height(60.dp)
            .fillMaxWidth()
            .shadow(elevation = 1.dp),
        colors = TopAppBarDefaults.topAppBarColors(topBarColor),
        navigationIcon = {
            Column(
                modifier = modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun MainTopBarPreview() {
    TestAPITheme {
        TopBarBack(title = "Test", onBack = { })
    }
}