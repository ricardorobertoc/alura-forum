package br.com.alura.forum.dto

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class AtualizaRespostaForm(

    @field:NotNull
    var id: Long,
    
    @field:NotEmpty
    val mensagem: String,
)
