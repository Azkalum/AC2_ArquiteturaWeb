package github.com.azkalum.repository;

import github.com.azkalum.domain.Curso;
import github.com.azkalum.domain.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    @Query("SELECT p FROM Professor p JOIN p.cursos c WHERE c = :curso")
    List<Professor> findByCursosContaining(@Param("curso") Curso curso);

}
