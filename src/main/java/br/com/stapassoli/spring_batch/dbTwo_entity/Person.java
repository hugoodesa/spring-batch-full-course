package br.com.stapassoli.spring_batch.dbTwo_entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "persons")
@Builder
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Integer age;

    @OneToOne
    @JoinColumn(name = "person_car")
    private Car car;

}
