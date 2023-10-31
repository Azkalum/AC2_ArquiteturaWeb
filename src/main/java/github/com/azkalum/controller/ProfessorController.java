package github.com.azkalum.controller;

import github.com.azkalum.domain.Curso;
import github.com.azkalum.domain.Professor;
import github.com.azkalum.service.CursoService;
import github.com.azkalum.service.Impl.ProfessorServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professores")
@Tag(name = "Professor Controller", description = "Endpoint para Controle de Professores")
public class ProfessorController {

    @Autowired
    private CursoService cursoService;

    @Autowired
    private ProfessorServiceImpl professorService;

    @Operation(summary = "Listar todos os professores")
    @ApiResponse(responseCode = "200", description = "Lista de professores recuperada com sucesso", content = @Content(schema = @Schema(implementation = Professor.class)))
    @GetMapping
    public List<Professor> listarProfessores() {
        return professorService.listarTodos();
    }

    @Operation(summary = "Listar os Professores especializados em um Curso")
    @ApiResponse(responseCode = "200", description = "Professor recuperado com sucesso", content = @Content(schema = @Schema(implementation = Professor.class)))
    @ApiResponse(responseCode = "404", description = "Professor não encontrado")
    @GetMapping("/especializados/{cursoId}")
    public List<Professor> listarProfessoresEspecializados(@PathVariable Long cursoId) {
        Curso curso = cursoService.obterPorId(cursoId);
        return professorService.getProfessoresEspecializados(curso);
    }

    @Operation(summary = "Criar um novo professor")
    @ApiResponse(responseCode = "201", description = "Professor criado com sucesso", content = @Content(schema = @Schema(implementation = Professor.class)))
    @PostMapping("/criar")
    public ResponseEntity<String> criarProfessor(@RequestBody Professor novoProfessor) {
        try {
            professorService.salvar(novoProfessor);
            return new ResponseEntity<>("Professor criado com sucesso", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao criar o professor: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Obter um professor por ID")
    @ApiResponse(responseCode = "200", description = "Professor recuperado com sucesso", content = @Content(schema = @Schema(implementation = Professor.class)))
    @ApiResponse(responseCode = "404", description = "Professor não encontrado")
    @GetMapping("/{professorId}")
    public ResponseEntity<Professor> obterProfessorPorId(@PathVariable Long professorId) {
        Professor professor = professorService.obterPorId(professorId);
        if (professor != null) {
            return new ResponseEntity<>(professor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Atualizar um professor por ID")
    @ApiResponse(responseCode = "200", description = "Professor atualizado com sucesso", content = @Content(schema = @Schema(implementation = Professor.class)))
    @ApiResponse(responseCode = "404", description = "Professor não encontrado")
    @PutMapping("/{professorId}")
    public ResponseEntity<String> atualizarProfessor(@PathVariable Long professorId, @RequestBody Professor professorAtualizado) {
        try {
            Professor professor = professorService.obterPorId(professorId);
            if (professor != null) {
                professorService.atualizar(professorAtualizado,professorId);
                return new ResponseEntity<>("Professor atualizado com sucesso", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Professor não encontrado", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao atualizar o professor: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Excluir um professor por ID")
    @ApiResponse(responseCode = "200", description = "Professor excluído com sucesso")
    @ApiResponse(responseCode = "404", description = "Professor não encontrado")
    @DeleteMapping("/{professorId}")
    public ResponseEntity<String> excluirProfessor(@PathVariable Long professorId) {
        try {
            Professor professor = professorService.obterPorId(professorId);
            if (professor != null) {
                professorService.deletar(professorId);
                return new ResponseEntity<>("Professor excluído com sucesso", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Professor não encontrado", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao excluir o professor: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
