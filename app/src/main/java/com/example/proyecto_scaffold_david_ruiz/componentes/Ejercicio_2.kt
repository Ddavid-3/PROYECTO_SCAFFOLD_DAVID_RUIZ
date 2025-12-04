package com.example.proyecto_scaffold_david_ruiz.componentes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import kotlinx.coroutines.launch


// PANTALLA PRINCIPAL DEL EJERCICIO


@Composable
fun Scaffold_2() {

    var pantallaActual by remember { mutableIntStateOf(0) }

    // datos del perfil
    var enlaceImagen by remember { mutableStateOf("https://cdn-icons-png.flaticon.com/512/6676/6676023.png") }
    var nombre by remember { mutableStateOf("FRANCO PEREZ") }
    var correo by remember { mutableStateOf("FRANCISCO@ejemplo.com") }

    // snackbar
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = { My_top_bar_2() },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = {
            My_bottom_bar_2(
                seleccionado = pantallaActual,
                onSeleccionado = { pantallaActual = it }
            )
        }
    ) { padding ->

        when (pantallaActual) {


            // PANTALLA PERFIL

            0 -> {
                Column(
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxSize()
                ) {

                    Card(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.Magenta),
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            imagen_desde_internet_2(enlaceImagen)
                            Text(nombre + "  " + correo)
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(
                            onClick = {
                                enlaceImagen = "https://cdn-icons-png.flaticon.com/512/6676/6676023.png"
                                scope.launch {
                                    snackbarHostState.showSnackbar("Avatar actualizado")
                                }
                            }
                        ) {
                            Text("Avatar 1")
                        }

                        Button(
                            onClick = {
                                enlaceImagen = "https://cdn-icons-png.flaticon.com/512/1149/1149826.png"
                                scope.launch {
                                    snackbarHostState.showSnackbar("Avatar actualizado")
                                }
                            }
                        ) {
                            Text("Avatar 2")
                        }

                        Button(
                            onClick = {
                                enlaceImagen = "https://cdn-icons-png.flaticon.com/512/10467/10467144.png"
                                scope.launch {
                                    snackbarHostState.showSnackbar("Avatar actualizado")
                                }
                            }
                        ) {
                            Text("Avatar 3")
                        }
                    }
                }
            }


            // PANTALLA NOTIFICACIONES

            1 -> {
                Column(
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("No tienes notificaciones")
                }
            }


            // PANTALLA AJUSTE
            2 -> {

                var recibirEmails by remember { mutableStateOf(false) }
                var modoOscuro by remember { mutableStateOf(false) }

                Column(
                    modifier = Modifier
                        .padding(padding)
                        .padding(20.dp)
                        .fillMaxSize()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Recibir emails")
                        Switch(
                            checked = recibirEmails,
                            onCheckedChange = { recibirEmails = it }
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Modo oscuro")
                        Switch(
                            checked = modoOscuro,
                            onCheckedChange = { modoOscuro = it }
                        )
                    }
                }
            }
        }
    }
}


// TOP BAR


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun My_top_bar_2() {
    TopAppBar(
        title = { Text("Mi perfil") }
    )
}


// BOTTOM BAR


@Composable
fun My_bottom_bar_2(
    seleccionado: Int,
    onSeleccionado: (Int) -> Unit
) {

    NavigationBar {

        NavigationBarItem(
            selected = seleccionado == 0,
            onClick = { onSeleccionado(0) },
            icon = { Icon(Icons.Default.AccountCircle, contentDescription = "PERFIL") },
            label = { Text("PERFIL") }
        )

        NavigationBarItem(
            selected = seleccionado == 1,
            onClick = { onSeleccionado(1) },
            icon = { Icon(Icons.Default.DateRange, contentDescription = "NOTIFICACIONES") },
            label = { Text("NOTIFICACIONES") }
        )

        NavigationBarItem(
            selected = seleccionado == 2,
            onClick = { onSeleccionado(2) },
            icon = { Icon(Icons.Default.Build, contentDescription = "AJUSTES") },
            label = { Text("AJUSTES") }
        )
    }
}


// IMAGEN DESDE INTERNET, aunque la yemgo duplicada ya pero bueno pa q lo entienda yo mejor

@Composable
fun imagen_desde_internet_2(url: String) {

    SubcomposeAsyncImage(
        model = url,
        contentDescription = "Imagen",
        modifier = Modifier.size(200.dp),
        contentScale = ContentScale.Crop,
        loading = { Text("Cargando...") },
        error = { Text("No existe la imagen") }
    )
}
