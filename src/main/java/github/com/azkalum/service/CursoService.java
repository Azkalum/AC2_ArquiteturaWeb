package github.com.azkalum.service;

import github.com.azkalum.domain.Curso;

import java.util.List;

public interface CursoService {

    Curso salvar(Curso curso);
    List<Curso> listarTodos();
    Curso obterPorId(Long id);
    Curso atualizar(Curso curso, Long id);
    void deletar(Long id);

}
