package com.example.myapplication

// Estas importaciones son necesarias para que nuestra actividad funcione correctamente
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


// Esta es la actividad principal que se muestra cuando inicias la app.
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Aquí indicamos que el contenido de la actividad será lo que definamos en MyApp
        setContent {
            MyApp()
        }
    }
}

// Aquí comienza nuestra aplicación (MyApp).
@Composable
fun MyApp() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    )
    {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Elementos superiores
            Greeting(name = "Android")

            Spacer(modifier = Modifier.height(15.dp))

            TextoColoreado()

            Spacer(modifier = Modifier.height(16.dp))

            // Agregamos StyledTextWithIcon aquí
            StyledTextWithIcon()

            Spacer(modifier = Modifier.height(16.dp))

            // Box que ocupa el espacio restante y centra el contenido
            Box(
                modifier = Modifier
                    .weight(1f)  // Toma el espacio restante
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                MuestraAlCentro()
            }
        }
    }
}
//aki termina la app

// Greeting muestra un saludo con el nombre que le pasamos como parámetro.
@Composable
fun Greeting(name: String) {
    Text(text = "Hola, $name!")
}

// TextoColoreado muestra un texto con un estilo personalizado.
@Composable
fun TextoColoreado() {
    Text(
        text = "Styled Text",
        style = TextStyle(
            fontFamily = FontFamily.Serif,
            fontSize = 18.sp,
            fontWeight = SemiBold,
            color = Color.Red
        )
    )
}


// StyledTextWithIcon muestra texto con íconos y un diálogo emergente cuando se hace clic.
@Composable
fun StyledTextWithIcon() {
    var showAlternativeText by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }  // Estado para controlar el diálogo
    val myStyle = TextStyle(
        fontFamily = FontFamily.Serif,
        fontSize = 18.sp,
        fontWeight = SemiBold,
        color = Color.DarkGray
    )
    Column {
        // Texto que cambia al hacer clic (alterna entre dos textos)
        Text(
            text = if (showAlternativeText) "¡Texto alternativo!" else "Forma 2 de texto",
            style = myStyle,
            modifier = Modifier.clickable { showAlternativeText = !showAlternativeText }
        )

        // Aquí mostramos dos íconos, uno de persona y otro de cerrar.
        Row {
            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = "Person Icon",
                tint = Color.Blue,
                modifier = Modifier.padding(top = 8.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = "Close Icon",
                tint = Color.Green,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .clickable { showDialog = true }  // Activar el diálogo al hacer clic
            )
        }

        // Diálogo emergente
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Mensaje") },
                text = { Text("Hola Mundo") },
                confirmButton = {
                    Button(onClick = { showDialog = false }) {
                        Text("OK")
                    }
                }
            )
        }
    }
}

// MuestraAlCentro muestra un texto y un ícono centrado en la pantalla.
@Composable
fun MuestraAlCentro() {
    val myStyle = TextStyle(
        fontFamily = FontFamily.Serif,
        fontSize = 24.sp,  // Aumenté el tamaño para mejor visibilidad
        fontWeight = SemiBold,
        color = Color.Red
    )
    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "ke pex ya se pueden ir", style = myStyle)
        Spacer(modifier = Modifier.width(16.dp))

        // Box es donde colocamos el ícono y lo alineamos en el centro.
        Box(modifier = Modifier.width(48.dp)) {
            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = "Person Icon",
                tint = Color.Blue,
                modifier = Modifier
                    .size(40.dp)  // Aumenté el tamaño del ícono
                    .align(Alignment.Center)
            )
        }
    }
}

// PreviewMyApp es una función para previsualizar la app sin ejecutarla.
@Preview(showBackground = true)
@Composable
fun PreviewMyApp() {
    MyApp() // Previsualiza la interfaz que creamos en MyApp
}
