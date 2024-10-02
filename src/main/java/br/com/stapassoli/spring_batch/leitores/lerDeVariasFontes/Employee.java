package br.com.stapassoli.spring_batch.leitores.lerDeVariasFontes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Employee {
    String id;
    String firstName;
    String lastName;
}
