package br.com.alura.forum.dto

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class RespostaForm(

    @field:NotEmpty
    val mensagem: String,
    val idAutor: Long
)
