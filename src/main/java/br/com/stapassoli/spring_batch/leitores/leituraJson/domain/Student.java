package br.com.stapassoli.spring_batch.leitores.leituraJson.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student {

    private String firstName;
    private String lastName;
    private int age;
    private boolean active;

}
