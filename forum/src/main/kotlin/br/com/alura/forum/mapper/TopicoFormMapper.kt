package br.com.alura.forum.mapper

import br.com.alura.forum.dto.TopicoForm
import br.com.alura.forum.model.Topico
import br.com.alura.forum.service.CursoService
import br.com.alura.forum.service.UsuarioService
import org.springframework.stereotype.Component

@Component
class TopicoFormMapper(
    private val cursoService: CursoService,
    private val usuarioService: UsuarioService
) : Mapper<TopicoForm, Topico> {
    override fun map(form: TopicoForm): Topico {
        return Topico(
            titulo = form.titulo,
            mensagem = form.mensagem,
            curso = cursoService.buscarPorId(form.idCurso),
            autor = usuarioService.buscarPorId(form.idAutor)
        )
    }
}
