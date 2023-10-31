package github.com.azkalum.controller;

import github.com.azkalum.domain.Agenda;
import github.com.azkalum.service.AgendaService;
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
@RequestMapping("/agendas")
@Tag(name = "Agenda Controller", description = "Endpoint para gerenciar agendas")
public class AgendaController {

    @Autowired
    private AgendaService agendaService;

    @Operation(summary = "Listar todas as agendas", description = "Retorna uma lista de todas as agendas.")
    @ApiResponse(responseCode = "200", description = "Lista de agendas", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Agenda.class)))
    @GetMapping
    public List<Agenda> listarAgendas() {
        return agendaService.listarTodos();
    }

    @Operation(summary = "Criar uma nova agenda", description = "Cria uma nova agenda.")
    @ApiResponse(responseCode = "201", description = "Agenda criada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro ao criar a agenda")
    @PostMapping("/criar")
    public ResponseEntity<String> criarAgenda(@RequestBody Agenda novaAgenda) {
        try {
            return new ResponseEntity<>("Agenda criada com sucesso", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao criar a agenda: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Obter detalhes de uma agenda por ID", description = "Retorna detalhes de uma agenda com base no ID fornecido.")
    @ApiResponse(responseCode = "200", description = "Agenda encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Agenda.class)))
    @ApiResponse(responseCode = "404", description = "Agenda não encontrada")
    @GetMapping("/{agendaId}")
    public ResponseEntity<Agenda> obterAgenda(@PathVariable Long agendaId) {
        Agenda agenda = agendaService.obterPorId(agendaId);
        if (agenda != null) {
            return new ResponseEntity<>(agenda, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Atualizar uma agenda existente", description = "Atualiza uma agenda existente com base no ID fornecido.")
    @ApiResponse(responseCode = "200", description = "Agenda atualizada com sucesso")
    @ApiResponse(responseCode = "404", description = "Agenda não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro ao atualizar a agenda")
    @PutMapping("/{agendaId}")
    public ResponseEntity<String> atualizarAgenda(@PathVariable Long agendaId, @RequestBody Agenda agendaAtualizada) {
        try {
            Agenda agenda = agendaService.obterPorId(agendaId);
            if (agenda != null) {
                agendaService.atualizar(agendaAtualizada, agendaId);
                return new ResponseEntity<>("Agenda atualizada com sucesso", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Agenda não encontrada", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao atualizar a agenda: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Excluir uma agenda", description = "Exclui uma agenda com base no ID fornecido.")
    @ApiResponse(responseCode = "200", description = "Agenda excluída com sucesso")
    @ApiResponse(responseCode = "404", description = "Agenda não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro ao excluir a agenda")
    @DeleteMapping("/{agendaId}")
    public ResponseEntity<String> excluirAgenda(@PathVariable Long agendaId) {
        try {
            Agenda agenda = agendaService.obterPorId(agendaId);
            if (agenda != null) {
                agendaService.deletar(agendaId);
                return new ResponseEntity<>("Agenda excluída com sucesso", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Agenda não encontrada", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao excluir a agenda: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}