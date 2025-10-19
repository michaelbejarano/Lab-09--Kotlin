package com.example.lab09

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.lab09.network.PostApiService
import com.example.lab09.ui.theme.Lab09Theme
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab09Theme {
                ProgPrincipal9()  // Aquí llamamos a la función principal
            }
        }
    }
}

@Composable
fun ProgPrincipal9() {
    val urlBase = "https://jsonplaceholder.typicode.com/"
    val retrofit = Retrofit.Builder()
        .baseUrl(urlBase)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // Se crea la instancia del servicio a partir de la interfaz
    val servicio = retrofit.create(PostApiService::class.java)

    val navController = rememberNavController()

    // Aquí podrías colocar el contenido de tu app
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { /* BarraSuperior() si ya la tienes creada */ },
        bottomBar = { /* BarraInferior(navController) si la tienes */ },
    ) { paddingValues ->
        Text(
            text = "Servicio Retrofit listo para usar",
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewApp() {
    Lab09Theme {
        ProgPrincipal9()
    }
}
