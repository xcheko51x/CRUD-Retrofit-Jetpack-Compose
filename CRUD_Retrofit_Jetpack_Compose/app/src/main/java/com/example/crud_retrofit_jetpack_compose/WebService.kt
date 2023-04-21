package com.example.crud_retrofit_jetpack_compose

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface WebService {

    @GET("/usuarios")
    suspend fun getUsuarios(): Response<UsuariosResponse>

    @POST("/usuarios/agregar")
    suspend fun addUsuarios(
        @Body usuario: Usuario
    ): Response<UsuariosResponse>

    @PUT("/usuarios/actualizar/{id}")
    suspend fun updateUsuario(
        @Path("id") id_usuario: String,
        @Body usuario: Usuario
    ): Response<UsuariosResponse>

    @DELETE("/usuarios/borrar/{id}")
    suspend fun deleteUsuario(
        @Path("id") id_usuario: String
    ): Response<UsuariosResponse>
}