package github.com.azkalum.service;

import github.com.azkalum.domain.Agenda;

import java.util.List;

public interface AgendaService {

    Agenda salvar(Agenda agenda);
    List<Agenda> listarTodos();
    Agenda obterPorId(Long id);
    Agenda atualizar(Agenda agenda, Long id);
    void deletar(Long id);
    boolean professorIsAvailable(Agenda novaAgenda);

}
