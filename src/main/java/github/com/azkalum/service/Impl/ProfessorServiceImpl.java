package github.com.azkalum.service.Impl;

import github.com.azkalum.domain.Curso;
import github.com.azkalum.domain.Professor;
import github.com.azkalum.repository.ProfessorRepository;
import github.com.azkalum.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorServiceImpl implements ProfessorService {

    private final ProfessorRepository professorRepository;

    @Autowired
    public ProfessorServiceImpl(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }
    @Override
    public Professor salvar(Professor professor) {
        return professorRepository.save(professor);
    }

    @Override
    public List<Professor> listarTodos() {
        return professorRepository.findAll();
    }

    @Override
    public Professor obterPorId(Long id) {
        return professorRepository.findById(id).orElse(null);
    }

    @Override
    public Professor atualizar(Professor professor, Long id) {
        if (professorRepository.existsById(id)) {
            professor.setId(id);
            return professorRepository.save(professor);
        }
        return null;
    }

    @Override
    public void deletar(Long id) {
        professorRepository.deleteById(id);
    }

    public List<Professor> getProfessoresEspecializados(Curso curso) {
        return professorRepository.findByCursosContaining(curso);
    }

}
