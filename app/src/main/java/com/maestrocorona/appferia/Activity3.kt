package com.maestrocorona.appferia

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

//Esta es una actividad (pantalla) que se usa para mostrar una pagina web
class WebViewActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //se escribe la URL que se mando desde otra pantalla (por ejemplo, desde una card)
        val url = intent.getStringExtra("url") ?: "https://www.google.com"

        //Se dibuja la interfaz usando Jetpack Compose
        setContent {
            //Se llama a la funcion que dibuja la pantalla web
            WebViewScreen(url = url, onBack = { finish() })
        }
    }
}

// Esta funcion dibuja la interfaz: la pagina web y un boton de regreso
@Composable
fun WebViewScreen(url: String, onBack: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) { //ocupala pantalla completa

        //Aqui se pone la vista Webview tradicional dentro de compose
        AndroidView(
            factory = { context ->
                WebView(context).apply {
                    settings.javaScriptEnabled = true //Activa JavaScript, necesario para algunas paginas
                    settings.cacheMode = android.webkit.WebSettings.LOAD_NO_CACHE //Para evitar errores de cache
                    webViewClient = WebViewClient() //Controla que los links se abran en esta misma vista
                    loadUrl(url) //Carga la pagina web
                }
            },
            modifier = Modifier.weight(1f) //Ocupa el espacio (menos el boton)
        )

        //Este boton sirve para regresar a la pantalla anterior
        Button(
            onClick = onBack, //Cuando se presiona, se ejecuta la funcion que cierra la pantalla
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6650a4)) //Color persoanlizado
        ) {
            Text("Regresar al inicio", color = MaterialTheme.colorScheme.onPrimary) //Texto del boton
        }
    }
}

