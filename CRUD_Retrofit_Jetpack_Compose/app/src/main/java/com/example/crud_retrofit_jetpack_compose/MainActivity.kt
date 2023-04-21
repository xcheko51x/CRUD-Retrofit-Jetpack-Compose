package com.example.crud_retrofit_jetpack_compose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.crud_retrofit_jetpack_compose.ui.theme.CRUD_Retrofit_Jetpack_ComposeTheme

class MainActivity : ComponentActivity() {

    val viewModel by viewModels<UsuariosViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CRUD_Retrofit_Jetpack_ComposeTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        viewModel.getUsuarios()
                        ScreenCRUD(viewModel._listaUsuarios, viewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun ScreenCRUD(listaUsuarios: ArrayList<Usuario>, viewModel: UsuariosViewModel) {

    var id by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var isEditando by remember { mutableStateOf(false) }
    var textButton by remember { mutableStateOf("Agregar Usuario") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(12.dp)
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
        ) {
            Formulario(
                idUsuario = id,
                nombre = nombre,
                funNombre = { nombre = it },
                isEditando = isEditando,
                funIsEditando = { isEditando = false },
                textButton = textButton,
                funTextButton = { textButton = it },
                funResetCampos = {
                    nombre = ""
                    email = ""
                },
                viewModel = viewModel
            )
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    items(listaUsuarios) { usuario ->
                        CardUsuario(
                            usuario = usuario,
                            funIdUsuario = {id = it},
                            funNombre = { nombre = it },
                            funTextButton = { textButton = it },
                            funIsEditando = { isEditando = it },
                            funBorrarUsuario = {
                                viewModel.deleteUsuario(usuario.id_usuario)
                            }
                        )
                    }
                }
            }
        }
    }
}