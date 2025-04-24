package com.maestrocorona.appferia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

//Esta actividad (pantalla) se abre para mostrar la imagen que viene desde otra parte de la app
class ImageWebActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() //Hace que el contenido llegue hasta los bordes
        // Se recibe la URL de la imagen enviada desde otra pantalla (desde una card)
        val imageUrl = intent.getStringExtra("image_url") ?: ""

        //Se dibuja la interfaz con Jetpack Compose
        setContent {
            FullScreenImage(imageUrl = imageUrl, onBack = { finish() }) //cierra esta pantalla
        }
    }
}

//Esta funcion dibuja la interfaz:imagen completa y boton para volver
@Composable
fun FullScreenImage(imageUrl: String, onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize() // Ocupa toda la pantalla
            .padding(16.dp), //Tamaño del margen alrededor
        verticalArrangement = Arrangement.SpaceBetween //Imagen arriba, boton abajo
    ) {
        // Se muestra la imagen que llegó por la URL
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl) //URL de la imagen
                .crossfade(true) //Efecto suave al cargar
                .build(),
            contentDescription = "Imagen de atracción", //Para accesibilidad
            contentScale = ContentScale.Crop, //Recorta para que llene el espacio
            modifier = Modifier
                .fillMaxWidth() //Ancho completo
                .weight(1f) // Ocupa la mayor parte de la pantalla
        )

        // Botón para regresar
        Button(
            onClick = onBack, //Llama la funcion que cierra esta pantalla
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6650a4)) //Color personalizado
        ) {
            Text("Regresar al inicio", color = MaterialTheme.colorScheme.onPrimary)
        }
    }
}


