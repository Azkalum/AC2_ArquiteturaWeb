package github.com.azkalum.repository;

import github.com.azkalum.domain.Agenda;
import github.com.azkalum.domain.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long> {

    List<Agenda> findByProfessorAndDataInicioLessThanEqualAndDataFimGreaterThanEqual(
            Professor professor, Date dataFim, Date dataInicio);

}
