package com.example.crud_retrofit_jetpack_compose

data class UsuariosResponse(
    var codigo: String,
    var mensaje: String,
    var data: ArrayList<Usuario>
)
