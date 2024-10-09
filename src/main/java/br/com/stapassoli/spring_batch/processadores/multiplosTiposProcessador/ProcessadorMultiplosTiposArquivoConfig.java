package br.com.stapassoli.spring_batch.processadores.multiplosTiposProcessador;

import br.com.stapassoli.spring_batch.leitores.arquivoMultiplosFormatos.domain.Person;
import br.com.stapassoli.spring_batch.leitores.arquivoMultiplosFormatos.domain.Product;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.builder.ClassifierCompositeItemProcessorBuilder;
import org.springframework.classify.Classifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcessadorMultiplosTiposArquivoConfig {

    @Bean(name = "processadorMultiplosTipos")
    public ItemProcessor<Object,Object> processadorMultiplosTipos() {
        return new ClassifierCompositeItemProcessorBuilder<>()
                .classifier(classifier())
                .build();
    }

    public Classifier classifier() {
        return object -> {
            if (object instanceof Person) {
                return itemProcessorPerson();
            }

            return itemProcessorProduct();
        };
    }

    public ItemProcessor<Person, Person> itemProcessorPerson() {
        return person -> {
            System.out.printf("Aplicado regra de negocio para person: %s%n", person.getName());
            return null;
        };
    }

    public ItemProcessor<Product, Product> itemProcessorProduct() {
        return product -> {
            System.out.printf("Aplicado regra de negocio para product no valor de : %s , %s%n", product.getName(), product.getPrice());
            return null;
        };
    }

}
