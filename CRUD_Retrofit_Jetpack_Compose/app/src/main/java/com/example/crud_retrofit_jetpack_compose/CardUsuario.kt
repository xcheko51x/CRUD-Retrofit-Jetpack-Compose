package com.example.crud_retrofit_jetpack_compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.unit.dp

@Composable
fun CardUsuario(
    funIdUsuario: (String) -> Unit,
    funNombre: (String) -> Unit,
    funTextButton: (String) -> Unit,
    funIsEditando: (Boolean) -> Unit,
    usuario: Usuario,
    funBorrarUsuario: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            Arrangement.Center
        ) {
            Text(
                text = "ID: ${usuario.id_usuario}",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = "Nombre: ${usuario.nombre}",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .weight(1f),
                    onClick = {
                        funIdUsuario(usuario.id_usuario)
                        funNombre(usuario.nombre)
                        funTextButton("Editar Usuario")
                        funIsEditando(true)
                    }
                ) {
                    Text(
                        text = "Editar"
                    )
                }
                Button(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .weight(1f),
                    onClick = { funBorrarUsuario(usuario.id_usuario) }
                ) {
                    Text(
                        text = "Borrar"
                    )
                }
            }
        }
    }
}