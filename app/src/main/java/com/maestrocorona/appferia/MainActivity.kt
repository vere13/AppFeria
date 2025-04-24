package com.maestrocorona.appferia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
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
import androidx.compose.ui.platform.LocalContext
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

//Esta funcion muestra el diseño principal que vera el usuario
@Composable
fun MainScreen(onNavigateToSecondActivity: () -> Unit) {
    // Pantalla principal que contiene todos los elementos en columnas
    Surface(
        modifier = Modifier.fillMaxSize(),  // Ocupatoda la pantalla
        color = MaterialTheme.colorScheme.background // Color de fondo
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp), // Espaciado entodo el contenido
            //horizontalAlignment = Alignment.CenterHorizontally // Centra los elementos
            verticalArrangement = Arrangement.spacedBy(16.dp) // Espaciado entre elementos
        ) {
            // Titulo principal de la pantalla
            Text(
                text = "Naves",
                style = TextStyle(
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif,
                    color = Color(0xFF6650a4)
                ),
                modifier = Modifier.padding(start = 4.dp) //pequeño margen desde la izquierda

            )
            // Secciones con informacion de negocios o atracciones con sus imágenes
            BusinessItem("Negocios de la Nave 1", "Venta de ropa y accesorios")
            BusinessItem("Negocios de la Nave 2", "Comida típica y antojitos")
            BusinessItem("Negocios de la Nave 3", "Productos astesanales")
            BusinessItem("Atracciones y conciertos", "Consulta eventos y horarios")

            // Botón que, al ser presionado, navega a la segunda actividad
            Button(
                onClick = onNavigateToSecondActivity,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6650a4)) //Purple40, Color claro
            ) {
                Text("Fechas importantes", color = Color.White, fontFamily = FontFamily.SansSerif, fontSize = 16.sp)
            }
        }
    }
}

//Esta funcion muestra una card o cuadro de informacion con imagen, texto y un boton
@Composable
fun BusinessItem(text: String, descripcion: String) {
    val context = LocalContext.current //Para acceder al contexto (necesario para abrir otra pantalla)

    // Componente reutilizable para mostrar las cards con imagen
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp), // Tamaño del card
        colors = CardDefaults.cardColors(containerColor = Color(0xFFD0BCFF)), //Purple80, Color modo obscuro
        shape = RoundedCornerShape(12.dp) // Bordes redondeados
    ) {
        //Ordena verticalmente los elementos dentro de la card
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp), // Espaciado dentro del card
        ) {
            // Fila que contiene la imagen y los textos juntos
            Row(
                verticalAlignment = Alignment.CenterVertically // Alinea la imagen y el texto
            ) {
                // Imagen de la feria
                Image(
                    painter = painterResource(id = R.drawable.logo_feria), // Recurso de la imagen
                    contentDescription = "Logo feria", // Descripción de la imagen
                    modifier = Modifier
                        .size(100.dp)
                        .padding(8.dp)
                )
                // Columna con el titulo y la descripcion
                Column(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(
                        text = text,
                        style = TextStyle(
                            color = Color(0xFF6650a4),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.SansSerif
                        )
                    )
                    Text(
                        text = descripcion,
                        style = TextStyle(
                            color = Color(0xFF6650a4),
                            fontSize = 14.sp,
                            fontFamily = FontFamily.SansSerif
                        )
                    )
                }
            }
            // Boton "ver mas", que cambia de pantalla dependiendo del texto de la card
            Button(
                onClick = {
                    val intent = if (text == "Atracciones y conciertos") {
                        // Si es la card de conciertos, se muestra una imagen de internet
                        Intent(context, ImageWebActivity::class.java).apply {
                            putExtra(
                                "image_url",
                                "https://www.buscandouni.com/wp-content/uploads/2018/07/39268133394_65445b0abe_k.jpg"
                            )
                        }
                    } else {
                        // Para las demas, se abre una pagina web de ejemplo
                        Intent(context, WebViewActivity::class.java).apply {
                            putExtra("url", "https://www.lipsum.com/")
                        }
                    }
                    context.startActivity(intent)
                },
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6650a4))
            ) {
                //Icono mas texto dentro del boton
                Icon(
                    imageVector = Icons.Default.AddCircle,
                    contentDescription = "Ver más",
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(6.dp)) // Espacio entre ícono y texto
                Text(
                    text = "Ver más",
                    color = Color.White,
                    fontSize = 14.sp
                )
            }
        }
    }
}

// Esta funcion muestra una vista previa del diseño en Android Studio (Sin necesidad de correr la app)
@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    MainScreen(onNavigateToSecondActivity = {})
}
