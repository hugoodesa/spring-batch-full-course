package br.com.stapassoli.spring_batch.processadores.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cliente {

    @NotNull
    @Size(min = 1, max = 100)
    @Pattern(regexp = "[a-zA-z\\s]+",message = "Nome devem ser alfabético")
    private String nome;
    @NotNull
    @Range(min = 18, max = 200)
    private Integer idade;
    @NotNull
    @Size(min = 1, max = 50)
    @Email(message = "Email inválido")
    private String email;

}
