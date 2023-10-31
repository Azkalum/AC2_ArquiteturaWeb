package github.com.azkalum.service.Impl;

import github.com.azkalum.domain.Curso;
import github.com.azkalum.repository.CursoRepository;
import github.com.azkalum.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CursoServiceImpl implements CursoService {

    private final CursoRepository cursoRepository;

    @Autowired
    public CursoServiceImpl(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @Override
    public Curso salvar(Curso curso) {
        return cursoRepository.save(curso);
    }

    @Override
    public List<Curso> listarTodos() {
        return cursoRepository.findAll();
    }

    @Override
    public Curso obterPorId(Long id) {
        return cursoRepository.findById(id).orElse(null);
    }

    @Override
    public Curso atualizar(Curso curso, Long id) {
        if(cursoRepository.existsById(id)){
            curso.setId(id);
            return cursoRepository.save(curso);
        }
        return null;
    }

    @Override
    public void deletar(Long id) {
        cursoRepository.deleteById(id);
    }

}
