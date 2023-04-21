package com.example.crud_retrofit_jetpack_compose

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Formulario(
    idUsuario: String,
    nombre: String,
    funNombre: (String) -> Unit,
    isEditando: Boolean,
    funIsEditando: () -> Unit,
    textButton: String,
    funTextButton: (String) -> Unit,
    funResetCampos: () -> Unit,
    viewModel: UsuariosViewModel
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = nombre,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        singleLine = true,
        maxLines = 1,
        onValueChange = { funNombre(it) },
        label = { Text(text = "Nombre") }
    )
    Spacer(modifier = Modifier.padding(vertical = 8.dp))
    Button(modifier = Modifier.fillMaxWidth(),
        onClick = {
            if (isEditando) {
                viewModel.updateUsuario(
                    idUsuario = idUsuario,
                    Usuario(
                        id_usuario = idUsuario,
                        nombre = nombre
                    )
                )
                funTextButton("Agregar Usuario")
                funIsEditando()

            } else {
                viewModel.addUsuario(
                    Usuario(
                        id_usuario = "",
                        nombre = nombre
                    )
                )
            }
            funResetCampos()
        }
    ) {
        Text(
            text = textButton
        )
    }
}