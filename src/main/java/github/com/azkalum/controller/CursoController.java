package github.com.azkalum.controller;

import github.com.azkalum.domain.Curso;
import github.com.azkalum.service.CursoService;
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
@RequestMapping("/cursos")
@Tag(name = "Curso Controller", description = "Endpoints para gerenciar cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @Operation(summary = "Listar todos os cursos", description = "Lista todos os cursos disponíveis.")
    @GetMapping
    public List<Curso> listarCursos() {
        return cursoService.listarTodos();
    }

    @Operation(summary = "Criar um novo curso", description = "Cria um novo curso.")
    @ApiResponse(responseCode = "201", description = "Curso criado com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro ao criar o curso", content = @Content(mediaType = "text/plain"))
    @PostMapping("/criar")
    public ResponseEntity<String> criarCurso(@RequestBody Curso novoCurso) {
        try {
            cursoService.salvar(novoCurso);
            return new ResponseEntity<>("Curso criado com sucesso", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao criar o curso: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Obter detalhes de um curso por ID", description = "Obtém detalhes de um curso por ID.")
    @ApiResponse(responseCode = "200", description = "Curso encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Curso.class)))
    @ApiResponse(responseCode = "404", description = "Curso não encontrado")
    @GetMapping("/{cursoId}")
    public ResponseEntity<Curso> obterCursoPorId(@PathVariable Long cursoId) {
        Curso curso = cursoService.obterPorId(cursoId);
        if (curso != null) {
            return new ResponseEntity<>(curso, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Atualizar um curso existente", description = "Atualiza um curso existente por ID.")
    @ApiResponse(responseCode = "200", description = "Curso atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Curso não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro ao atualizar o curso", content = @Content(mediaType = "text/plain"))
    @PutMapping("/{cursoId}")
    public ResponseEntity<String> atualizarCurso(@PathVariable Long cursoId, @RequestBody Curso cursoAtualizado) {
        try {
            Curso curso = cursoService.obterPorId(cursoId);
            if (curso != null) {
                cursoService.atualizar(cursoAtualizado, cursoId);
                return new ResponseEntity<>("Curso atualizado com sucesso", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Curso não encontrado", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao atualizar o curso: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Excluir um curso", description = "Exclui um curso por ID.")
    @ApiResponse(responseCode = "200", description = "Curso excluído com sucesso")
    @ApiResponse(responseCode = "404", description = "Curso não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro ao excluir o curso", content = @Content(mediaType = "text/plain"))
    @DeleteMapping("/{cursoId}")
    public ResponseEntity<String> excluirCurso(@PathVariable Long cursoId) {
        try {
            Curso curso = cursoService.obterPorId(cursoId);
            if (curso != null) {
                cursoService.deletar(cursoId);
                return new ResponseEntity<>("Curso excluído com sucesso", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Curso não encontrado", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao excluir o curso: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}