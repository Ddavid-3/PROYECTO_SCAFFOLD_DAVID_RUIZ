package com.example.proyecto_scaffold_david_ruiz.componentes

import kotlinx.coroutines.launch // <-- ESTE ES EL IMPORT CORRECTO
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope


/*

Ejercicio 1: Selector de tema con previsualización de imagen
Crea una pantalla de configuración de tema para una app:
Requisitos:
1. Usa Scaffold como contenedor principal.
2. topBar:
• Título: "Configuración".
3. Contenido:
• Muestra tres tarjetas apiladas en una Column normal (sin LazyColumn):
• Cada tarjeta representa un tema diferente (claro, oscuro, sistema).
• Cada tarjeta incluye:
• Un título (texto).
• Una breve descripción.
• Una imagen de ejemplo del tema cargada con AsyncImage.
• Al pulsar una tarjeta:
• Cambia una variable de estado temaSeleccionado.
• Muestra una Snackbar con el mensaje: "Tema X activado".
4. snackbarHost:
• Usa un SnackbarHostState dentro del Scaffold para mostrar los mensajes.
5. bottomBar con NavigationBar:
• Tres pestañas: "Inicio", "Configuración", "Perfil".
• Visualmente resalta que estamos en **"Configuración"`.
• Al cambiar de pestaña:
• Actualiza un texto en el contenido que diga por ejemplo "Pestaña
actual: Inicio / Configuración / Perfil" (no hace falta
navegación compleja).

 */





@Composable
fun Scaffold_1(){

    var tema_seleccionado =  remember { mutableStateOf(1) }
    // mirar //
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    //      //
    var pestañaSeleccionada by remember { mutableStateOf(1) } // 0=Inicio, 1=Config, 2=Perfil





    Scaffold(
        topBar = { My_top_bar() }
        , bottomBar = {My_bottom_bar(penstana_actual = pestañaSeleccionada) {
            nueva_pestana_cambio ->
            pestañaSeleccionada = nueva_pestana_cambio
        }}
        , snackbarHost = { SnackbarHost(hostState = snackbarHostState) }

    ){ innerPadding ->

        // 1. La Column principal debe ocupar toda la pantalla
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            // el texto q me piden que vaya cambiando, no tengo q hacer nada mutable pq con
            // usar el pesataña selecioanda ya se recompondra solo
            val texto_de_cosa_seleccionada_en_buttom_bar = when (pestañaSeleccionada) {
                0 -> "Inicio"
                1 -> "Configuracion"
                else -> "Perfil"
            }

            Text(
                text = "Pestaña actual: $texto_de_cosa_seleccionada_en_buttom_bar",
                modifier = Modifier
                    .padding(16.dp) // Le damos un poco de espacio
                    .fillMaxWidth()
            )

                // 2. A cada Card se le da un modificador para que ocupe 1/3 del espacio
            Contenido01(modifier = Modifier.weight(1f), tema_seleccionado,snackbarHostState,scope)
            Contenido02(modifier = Modifier.weight(1f), tema_seleccionado,snackbarHostState,scope)
            Contenido03(modifier = Modifier.weight(1f), tema_seleccionado,snackbarHostState,scope)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun My_top_bar(){
    TopAppBar(
        title = {
            Text(text = "Configuracion")
        },

    )
}

@Composable
fun My_bottom_bar(penstana_actual : Int , nueva_pestana_cambio : (Int) -> Unit){


    NavigationBar(){
        NavigationBarItem(
            icon = { Icon(imageVector = Icons.Default.Home, contentDescription = "Inicio") },
            label = { Text("Inicio") }
            , selected = (penstana_actual == 0)
            , onClick = { nueva_pestana_cambio(0) }


            )
        NavigationBarItem(
            selected = (penstana_actual == 1),
            onClick = { nueva_pestana_cambio(1) },
            icon = { Icon(imageVector = Icons.Default.Build, contentDescription = "Configuración") },
            label = { Text("Configuración") }
        )

        NavigationBarItem(
            selected = (penstana_actual == 2),
            onClick = { nueva_pestana_cambio(2) },
            icon = { Icon(imageVector = Icons.Default.AccountBox, contentDescription = "Perfil") },
            label = { Text("Perfil") }
        )



    }




}




@Composable
fun Contenido01(modifier: Modifier = Modifier , tema_seleccionado: MutableState<Int>,snackbarHostState : SnackbarHostState,scope : CoroutineScope){
    // 3. La Card recibe el modifier (weight) y además le añadimos padding
    Card(
        modifier = modifier
            .padding(16.dp) // Añadimos espacio entre las tarjetas
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray)
        //• Al pulsar una tarjeta:
        //• Cambia una variable de estado temaSeleccionado.
        //• Muestra una Snackbar con el mensaje: "Tema X activado".
        , onClick = {
            tema_seleccionado.value = 1
            scope.launch { // <-- Usamos el scope
                snackbarHostState.showSnackbar("Tema Claro activado "+tema_seleccionado.value)
            }

        }
    ) {
        // 4. SOLUCIÓN CLAVE: La Column interna NO debe tener fillMaxSize.
        //    Debe ocupar el espacio que la Card le permite.
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Tema claro")
            Text("Descripcion")
            imagen_desde_internet(
                "https://blog.uptodown.com/wp-content/uploads/imagenes-libres-derechos.jpg"
            )
        }
    }
}

@Composable
fun Contenido02(modifier: Modifier = Modifier, tema_seleccionado: MutableState<Int>,snackbarHostState : SnackbarHostState,scope : CoroutineScope){
    Card(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.DarkGray),
        //• Al pulsar una tarjeta:
        //• Cambia una variable de estado temaSeleccionado.
        //• Muestra una Snackbar con el mensaje: "Tema X activado".
        onClick = {
            tema_seleccionado.value = 2
            scope.launch { // <-- Usamos el scope
                snackbarHostState.showSnackbar("Tema oscuro activado "+tema_seleccionado.value)
            }
        }


    ) {
        // Se aplica la misma corrección aquí
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Tema OSCURO", color = Color.White)
            Text("Descripcion", color = Color.White)
            imagen_desde_internet(
                "https://www.deividart.com/wp-content/uploads/2014/02/imagenes-libre-derechos.jpg"
            )
        }
    }
}

@Composable
fun Contenido03(modifier: Modifier = Modifier, tema_seleccionado: MutableState<Int>,snackbarHostState : SnackbarHostState,scope : CoroutineScope){
    Card(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Green),

        //• Al pulsar una tarjeta:
        //• Cambia una variable de estado temaSeleccionado.
        //• Muestra una Snackbar con el mensaje: "Tema X activado".

        onClick = {
            tema_seleccionado.value = 3
            scope.launch { // <-- Usamos el scope
                snackbarHostState.showSnackbar("Tema medio activado "+tema_seleccionado.value)
            }


        }


    ) {
        // Y aquí también
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Tema SISTEMA")
            Text("Descripcion")
            imagen_desde_internet(
                "https://blog.arcadina.com/wp-content/uploads/2024/08/principal_blog_1170-6.jpg"
            )
        }
    }
}

@Composable
fun imagen_desde_internet(url: String) {
    // La imagen debe ocupar el espacio restante DENTRO de la tarjeta.
    //    Usar fillMaxSize aquí hará que ocupe todo el espacio que la Column interior le da.
    SubcomposeAsyncImage(
        model = url,
        contentDescription = "Imagen",
        modifier = Modifier.fillMaxSize(), // Ahora esto funciona porque su padre (la Column) tiene un tamaño bien definido.
        contentScale = ContentScale.Crop,
        loading = { Text("Cargando...") },
        error = { Text("No existe la imagen") }
    )
}























