package com.example.proyecto_scaffold_david_ruiz.componentes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Scaffold_3() {

    // imagen principal poco tipica
    var imagenPrincipal by remember {
        mutableStateOf("https://cdn-icons-png.flaticon.com/512/9960/9960774.png")
    }

    // miniaturas raras tambien
    val listaImagenes = listOf(
        "https://cdn-icons-png.flaticon.com/512/9960/9960774.png",
        "https://cdn-icons-png.flaticon.com/512/702/702814.png",
        "https://cdn-icons-png.flaticon.com/512/168/168728.png"
    )

    // snackbar
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    // bottom bar seleccion
    var seccionSeleccionada by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Auriculares NeoWave",
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF4A148C)
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            scope.launch {
                                snackbarHostState.showSnackbar("Volver no implementado")
                            }
                        }
                    ) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = {
            NavigationBar(containerColor = Color(0xFFEDE7F6)) {

                NavigationBarItem(
                    selected = seccionSeleccionada == 0,
                    onClick = { seccionSeleccionada = 0 },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Inicio") },
                    label = { Text("Inicio") }
                )

                NavigationBarItem(
                    selected = seccionSeleccionada == 1,
                    onClick = { seccionSeleccionada = 1 },
                    icon = { Icon(Icons.Default.ArrowDropDown, contentDescription = "Catalogo") },
                    label = { Text("Catalogo") }
                )

                NavigationBarItem(
                    selected = seccionSeleccionada == 2,
                    onClick = { seccionSeleccionada = 2 },
                    icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Carrito") },
                    label = { Text("Carrito") }
                )
            }
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(12.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // IMAGEN PRINCIPAL
            AsyncImage(
                model = imagenPrincipal,
                contentDescription = "Imagen principal",
                modifier = Modifier
                    .padding(top = 10.dp)
                    .size(260.dp),
                contentScale = ContentScale.Fit
            )

            // MINIATURAS
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                listaImagenes.forEach { url ->
                    AsyncImage(
                        model = url,
                        contentDescription = "Miniatura",
                        modifier = Modifier
                            .size(90.dp)
                            .clickable { imagenPrincipal = url },
                        contentScale = ContentScale.Crop
                    )
                }
            }

            // TITULO Y PRECIO
            Text(
                "Auriculares NeoWave",
                modifier = Modifier.padding(top = 16.dp),
                fontWeight = FontWeight.Bold,
                color = Color(0xFF6A1B9A)
            )

            Text(
                "Precio: 145.50 €",
                color = Color(0xFF2E7D32),
                modifier = Modifier.padding(6.dp)
            )

            // DESCRIPCION
            Text(
                text = "Modelo exclusivo con graves reforzados, panel lateral retroiluminado y conexion hiperestatica UltraLink 5G.",
                modifier = Modifier
                    .padding(horizontal = 22.dp, vertical = 12.dp)
                    .fillMaxWidth()
            )

            // BOTONES
            Row(
                modifier = Modifier.padding(top = 10.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = {
                        scope.launch {
                            snackbarHostState.showSnackbar("Producto anadido al carrito")
                        }
                    }
                ) {
                    Text("Anadir")
                }

                Button(
                    onClick = {
                        scope.launch {
                            snackbarHostState.showSnackbar("Compra instantanea no disponible")
                        }
                    }
                ) {
                    Text("Comprar")
                }
            }

            // TEXTO SEGUN SECCION DEL MENU
            Text(
                text = when (seccionSeleccionada) {
                    0 -> "Estas en: Inicio"
                    1 -> "Estas en: Catalogo"
                    else -> "Estas en: Carrito"
                },
                modifier = Modifier.padding(top = 22.dp),
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}




















