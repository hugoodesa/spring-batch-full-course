package br.com.stapassoli.spring_batch.leitores.arquivoMultiplosFormatos.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Person {
    private String name;
    private int age;
    private List<Product> products;

    public void addProductToList(Product product) {
        if(Objects.isNull(this.products))
            this.products = new ArrayList<>();
        this.products.add(product);
    }

}
