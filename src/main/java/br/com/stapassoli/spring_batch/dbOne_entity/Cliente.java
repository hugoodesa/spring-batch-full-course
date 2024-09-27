package br.com.stapassoli.spring_batch.dbOne_entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
public class Cliente {

    private String nome;
    private String sobrenome;
    private String idade;
    private String email;

}
