package github.com.azkalum.service;

import github.com.azkalum.domain.Professor;

import java.util.List;

public interface ProfessorService {

    Professor salvar(Professor professor);
    List<Professor> listarTodos();
    Professor obterPorId(Long id);
    Professor atualizar(Professor professor, Long id);
    void deletar(Long id);

}
