package br.com.stapassoli.spring_batch.dbTwo_entity;

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
public class MyUser {

    @Id
    private Long id;
    private String nome;

}
