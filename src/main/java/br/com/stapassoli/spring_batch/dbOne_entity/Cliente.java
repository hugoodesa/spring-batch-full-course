package br.com.stapassoli.spring_batch.dbOne_entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
public class Cliente {

    @Id
    private Long id;
    private String nome;
    private String sobrenome;
    private String idade;
    private String email;

}
