package com.maestrocorona.appferia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.content.Intent
import androidx.compose.ui.tooling.preview.Preview

// La clase MainActivity es la actividad principal de la aplicación.
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Permite que la pantalla usetodo el espacio disponible sin barras
        enableEdgeToEdge()
        setContent {
            // Se define la pantalla principal de la app
            MainScreen(onNavigateToSecondActivity = {
                // Iniciamos la segunda actividad cuando se presione el botón
                startActivity(Intent(this, Activity2::class.java))
            })
        }
    }
}

@Composable
fun MainScreen(onNavigateToSecondActivity: () -> Unit) {
    // Pantalla principal que contiene todos los elementos en columnas
    Surface(
        modifier = Modifier.fillMaxSize(),  // Ocupatodo el espacio de la pantalla
        color = MaterialTheme.colorScheme.background // Color de fondo
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp), // Espaciado entodo el contenido
            horizontalAlignment = Alignment.CenterHorizontally, // Centra los elementos
            verticalArrangement = Arrangement.spacedBy(16.dp) // Espaciado entre elementos
        ) {
            // Lista de negocios con sus imágenes
            BusinessItem("Negocios de la Nave 1")
            BusinessItem("Negocios de la Nave 2")
            BusinessItem("Negocios de la Nave 3")
            BusinessItem("Atracciones y conciertos")

            // Botón que, al ser presionado, navega a la segunda actividad
            Button(
                onClick = onNavigateToSecondActivity,
                modifier = Modifier.padding(top = 16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6650a4)) //Purple40, Color claro
            ) {
                Text("Fechas importantes", color = Color.White, fontFamily = FontFamily.SansSerif, fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun BusinessItem(text: String) {
    // Componente reutilizable para mostrar negocio con imagen
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp), // Tamaño del card
        colors = CardDefaults.cardColors(containerColor = Color(0xFFD0BCFF)), //Purple80, Color modo obscuro
        shape = RoundedCornerShape(12.dp) // Bordes redondeados
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp), // Espaciado dentro del card
            verticalAlignment = Alignment.CenterVertically // Alinea la imagen y el texto
        ) {
            // Imagen del restaurante
            Image(
                painter = painterResource(id = R.drawable.logo_rest), // Recurso de la imagen
                contentDescription = "Logo restaurante", // Descripción de la imagen
                modifier = Modifier
                    .size(100.dp)
                    .padding(8.dp)
            )
            // Texto que describe el negocio
            Text(
                text = text,
                modifier = Modifier.padding(8.dp),
                style = TextStyle(
                    color = Color(0xFF6650a4), //Purple40, Color claro
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    // Vista previa para visualizar la pantalla principal sin necesidad de ejecutar la app
    MainScreen(onNavigateToSecondActivity = {})
}
