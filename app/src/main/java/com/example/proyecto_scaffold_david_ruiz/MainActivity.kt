package com.example.proyecto_scaffold_david_ruiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
// Importa tu función Scaffold_1
import com.example.proyecto_scaffold_david_ruiz.componentes.Scaffold_1
import com.example.proyecto_scaffold_david_ruiz.componentes.Scaffold_2
import com.example.proyecto_scaffold_david_ruiz.componentes.Scaffold_3
import com.example.proyecto_scaffold_david_ruiz.ui.theme.PROYECTO_SCAFFOLD_DAVID_RUIZTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PROYECTO_SCAFFOLD_DAVID_RUIZTheme {
                // Mantenemos la llamada a Greeting, pero SIN el Scaffold que la envolvía
                Greeting("Android")
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    // Greeting ahora se encarga de llamar a la pantalla principal
    //Scaffold_1()
    //Scaffold_2()
    Scaffold_3()

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PROYECTO_SCAFFOLD_DAVID_RUIZTheme {
        Greeting("Android")
    }
}
