package br.com.oaleixo.eleticcarapp.domain

data class Carro (
    var id: Int,
    val preco: String,
    val bateria: String,
    val potencia: String,
    val recarga: String,
    val urlPhoto: String,
)