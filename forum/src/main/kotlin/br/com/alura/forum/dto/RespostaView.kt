package br.com.alura.forum.dto

import java.time.LocalDateTime

data class RespostaView(
    var id: Long?,
    val mensagem: String,
    val dataCriacao: LocalDateTime,
    val idAutor: Long?,
    var idTopico: Long?,
    val solucao: Boolean
)
