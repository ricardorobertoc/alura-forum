package br.com.alura.forum.controller

import br.com.alura.forum.dto.AtualizaRespostaForm
import br.com.alura.forum.dto.RespostaForm
import br.com.alura.forum.dto.RespostaView
import br.com.alura.forum.model.Resposta
import br.com.alura.forum.service.RespostaService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/topicos/{id}/respostas")
class RespotasController(
    private val service: RespostaService
) {

    @GetMapping
    fun listar(@PathVariable id: Long): List<Resposta> {
        return service.listar(id)
    }

    @PostMapping
    fun cadastrar(
        @PathVariable id: Long,
        @RequestBody @Valid form: RespostaForm,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<RespostaView> {
        val respostaView = service.cadastrar(form, id)
        val uri = uriBuilder.path("/topicos/${id}/respostas").build().toUri()
        return ResponseEntity.created(uri).body(respostaView)
    }

    @PutMapping
    fun atualizar(@RequestBody @Valid form: AtualizaRespostaForm): ResponseEntity<RespostaView> {
        val respostaView = service.atualizar(form)
        return ResponseEntity.ok(respostaView)
    }

    @DeleteMapping("/{idResposta}")
    fun deletar(@PathVariable idResposta: Long) {
        service.deletar(idResposta)
    }
}