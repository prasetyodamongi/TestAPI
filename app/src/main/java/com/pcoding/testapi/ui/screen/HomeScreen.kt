package com.pcoding.testapi.ui.screen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.pcoding.testapi.R
import com.pcoding.testapi.navigation.ScreenConfig
import com.pcoding.testapi.ui.component.CardContent
import com.pcoding.testapi.ui.component.CardMenu
import com.pcoding.testapi.ui.component.TopBar
import com.pcoding.testapi.viewmodel.MainViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: MainViewModel = viewModel(),
) {
    val context: Context = LocalContext.current

    Scaffold(
        topBar = {
            TopBar(title = "TestApiApp")
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .background(Color(0xFFE0FFFF))
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Get Data dari API",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))
                MenuHome(navController = navController)
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Post Data ke API",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))
                PostScreen(viewModel, context)
            }
        }
    }
}

@Composable
fun MenuHome(navController: NavController, modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)
    ) {
        CardMenu(
            onClick = { navController.navigate(ScreenConfig.User.route) },
            modifier = modifier
                .weight(1f)
                .height(150.dp)
                .padding(end = 8.dp),
        ) {
            CardContent(
                modifier = modifier,
                image = painterResource(id = R.drawable.ic_view_list),
                title = "List User"
            )
        }
        CardMenu(
            onClick = { navController.navigate(ScreenConfig.Member.route) },
            modifier = modifier
                .weight(1f)
                .height(150.dp)
                .padding(start = 8.dp)
        ) {
            CardContent(
                modifier = modifier,
                image = painterResource(id = R.drawable.ic_view_list),
                title = "List Member"
            )
        }
    }
}

@Composable
fun PostScreen(viewModel: MainViewModel, context: Context) {
    var userId by remember { mutableStateOf("") }
    var pesan by remember { mutableStateOf("") }

    Column {
        OutlinedTextField(
            value = userId,
            onValueChange = { userId = it },
            label = { Text("User ID") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = pesan,
            onValueChange = { pesan = it },
            label = { Text("Pesan") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = {
                viewModel.postPesan(userId, pesan) { success ->
                    if (!success) {
                        Toast.makeText(context, "Gagal Mengirim Data!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Berhasil Mengirim Data!", Toast.LENGTH_SHORT)
                            .show()
                    }
                }

                //Textfield kembali kosong
                userId = ""
                pesan = ""
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Kirim")
        }
    }
}