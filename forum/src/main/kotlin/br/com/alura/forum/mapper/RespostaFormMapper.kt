package br.com.alura.forum.mapper

import br.com.alura.forum.dto.RespostaForm
import br.com.alura.forum.model.Resposta
import br.com.alura.forum.service.UsuarioService
import org.springframework.stereotype.Component

@Component
class RespostaFormMapper(
    private val usuarioService: UsuarioService
) : Mapper<RespostaForm, Resposta> {
    override fun map(respostaForm: RespostaForm): Resposta {
        return Resposta(
            mensagem = respostaForm.mensagem,
            autor = usuarioService.buscarPorId(respostaForm.idAutor),
            topico = null,
            solucao = false
        )
    }
}
