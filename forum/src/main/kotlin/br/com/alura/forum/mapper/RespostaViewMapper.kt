package br.com.alura.forum.mapper

import br.com.alura.forum.dto.RespostaView
import br.com.alura.forum.model.Resposta
import org.springframework.stereotype.Component

@Component
class RespostaViewMapper: Mapper<Resposta, RespostaView> {
    override fun map(resposta: Resposta): RespostaView {
        return RespostaView(
            id = resposta.id,
            mensagem = resposta.mensagem,
            dataCriacao = resposta.dataCriacao,
            idAutor = resposta.autor.id,
            idTopico = resposta.topico?.id,
            solucao = resposta.solucao
        )
    }
}
