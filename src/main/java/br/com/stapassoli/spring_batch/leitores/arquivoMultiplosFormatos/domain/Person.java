package br.com.stapassoli.spring_batch.leitores.arquivoMultiplosFormatos.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Person {
    private String name;
    private int age;
}
