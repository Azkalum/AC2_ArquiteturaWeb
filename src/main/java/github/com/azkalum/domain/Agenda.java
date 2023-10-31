package github.com.azkalum.domain;

import github.com.azkalum.converter.LocalTimeConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalTime;
import java.util.Date;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    @NotNull
    @Future
    private Date dataInicio;

    @Temporal(TemporalType.DATE)
    @Future
    private Date dataFim;

    @Convert(converter = LocalTimeConverter.class)
    @NotNull
    private LocalTime horarioInicio;

    @Convert(converter = LocalTimeConverter.class)
    @NotNull
    private LocalTime horarioFim;

    @ManyToOne
    @NotNull
    private Curso curso;

    @ManyToOne
    @NotNull
    private Professor professor;

    @Size(min = 3, max = 50)
    private String cidade;

    @Size(min = 2, max = 20)
    private String estado;

    @Size(min = 5, max = 20)
    private String cep;

}
