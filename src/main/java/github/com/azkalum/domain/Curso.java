package github.com.azkalum.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3, max = 100)
    private String descricao;

    @Positive
    private int cargaHoraria;

    @NotNull
    @Size(min = 3, max = 200)
    private String objetivos;

    @NotNull
    @Size(min = 3, max = 500)
    private String ementa;

    @ManyToMany
    @JoinTable(
            name = "curso_professor",
            joinColumns = @JoinColumn(name = "curso_id"),
            inverseJoinColumns = @JoinColumn(name = "professor_id")
    )
    @JsonBackReference
    private List<Professor> professores;

    @OneToMany(mappedBy = "curso")
    private List<Agenda> agendas;

}