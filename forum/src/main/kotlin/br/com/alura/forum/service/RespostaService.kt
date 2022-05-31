package br.com.alura.forum.service

import br.com.alura.forum.dto.AtualizaRespostaForm
import br.com.alura.forum.dto.AtualizaTopicoForm
import br.com.alura.forum.dto.RespostaForm
import br.com.alura.forum.dto.RespostaView
import br.com.alura.forum.mapper.RespostaFormMapper
import br.com.alura.forum.mapper.RespostaViewMapper
import br.com.alura.forum.model.Curso
import br.com.alura.forum.model.Resposta
import br.com.alura.forum.model.Topico
import br.com.alura.forum.model.Usuario
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class RespostaService(
    private var respostas: List<Resposta>,
    private val respostaFormMapper: RespostaFormMapper,
    private val topicoService: TopicoService,
    private val respostaViewMapper: RespostaViewMapper
) {

    init {
        val curso = Curso(
            id = 1,
            nome = "Kotlin",
            categoria = "Programacao"
        )
        val autor = Usuario(
            id = 1,
            nome = "Ana da Silva",
            email = "ana@email.com",
            password = "123456"
        )
        val topico = Topico(
            id = 1,
            titulo = "Duvida Kotlin",
            mensagem = "Variaveis no Kotlin",
            curso = curso,
            autor = autor
        )
        val resposta = Resposta(
            id = 1,
            mensagem = "Resposta 1",
            autor = autor,
            topico = topico,
            solucao = false
        )

        val resposta2 = Resposta(
            id = 2,
            mensagem = "Resposta 2",
            autor = autor,
            topico = topico,
            solucao = false
        )

        respostas = listOf(resposta, resposta2)
    }

    fun listar(idTopico: Long): List<Resposta> {
        return respostas.stream().filter { r ->
            r.topico?.id == idTopico
        }.collect(Collectors.toList())
    }

    fun cadastrar(form: RespostaForm, idTopico: Long): RespostaView {
        val resposta = respostaFormMapper.map(form)
        resposta.id = respostas.size.toLong() + 1
        resposta.topico = topicoService.buscarTopicoId(idTopico)
        respostas = respostas.plus(resposta)
        return respostaViewMapper.map(resposta)
    }

    fun atualizar(form: AtualizaRespostaForm): RespostaView {
        val resposta = respostas.stream().filter { r ->
            r.id == form.id
        }.findFirst().get()
        val respostaAtualizada = Resposta(
            id = form.id,
            mensagem = form.mensagem,
            dataCriacao = resposta.dataCriacao,
            autor = resposta.autor,
            topico = resposta.topico,
            solucao = resposta.solucao
        )
        respostas = respostas.minus(resposta).plus(respostaAtualizada)
        return respostaViewMapper.map(respostaAtualizada)
    }

    fun deletar(id: Long) {
        val resposta = respostas.stream().filter { r ->
            r.id == id
        }.findFirst().get()
        respostas = respostas.minus(resposta)
    }
}
