package com.example.lab09

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.lab09.ui.theme.Lab09Theme
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab09Theme {
                ProgPrincipal9()
            }
        }
    }
}

@Composable
fun ProgPrincipal9() {
    val urlBase = "https://json-placeholder.mock.beeceptor.com/"
    val retrofit = Retrofit.Builder()
        .baseUrl(urlBase)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val navController = rememberNavController()

    Scaffold(
        topBar = { BarraSuperior() },
        bottomBar = { BarraInferior(navController) },
        content = { paddingValues ->
            Contenido(paddingValues, navController, retrofit)
        }
    )
}

// ✅ Usamos CenterAlignedTopAppBar (Material3)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraSuperior() {
    CenterAlignedTopAppBar(
        title = { Text("Mi App Retrofit") }
    )
}

@Composable
fun BarraInferior(navController: androidx.navigation.NavHostController) {
    BottomAppBar {
        Text(
            text = "Barra inferior",
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun Contenido(
    paddingValues: androidx.compose.foundation.layout.PaddingValues,
    navController: androidx.navigation.NavHostController,
    retrofit: Retrofit
) {
    Text(
        text = "Contenido principal con Retrofit configurado.",
        modifier = Modifier.padding(paddingValues)
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewProgPrincipal9() {
    Lab09Theme {
        ProgPrincipal9()
    }
}
