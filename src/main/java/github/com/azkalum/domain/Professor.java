package github.com.azkalum.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3, max = 50)
    private String nome;

    @CPF(message = "Informe um CPF válido")
    private String cpf;

    @NotNull
    @Size(min = 3, max = 50)
    private String rg;

    @NotNull
    @Size(min = 5, max = 120)
    private String endereco;

    @NotNull
    @Size(min = 10, max = 20)
    private String celular;

    @ManyToMany(mappedBy = "professores")
    @JsonManagedReference
    private List<Curso> cursos;

}
