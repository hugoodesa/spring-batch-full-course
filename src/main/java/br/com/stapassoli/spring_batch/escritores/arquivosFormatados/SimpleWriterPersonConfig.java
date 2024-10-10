package br.com.stapassoli.spring_batch.escritores.arquivosFormatados;

import br.com.stapassoli.spring_batch.dbTwo_entity.Person;
import br.com.stapassoli.spring_batch.dbTwo_entity.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.*;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.builder.MultiResourceItemWriterBuilder;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.batch.item.support.builder.CompositeItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.WritableResource;

@Configuration
@RequiredArgsConstructor
public class SimpleWriterPersonConfig {

    private final PersonRepository personRepository;

    @Bean(name = "simpleWriterPerson")
    public ItemWriter<Person> simpleWriterPerson() {
        return person -> person.forEach(System.out::println);
    }

    //cria varios arquivos
    @Bean(name = "formatedWriterPersonMultiplosArquivos")
    public MultiResourceItemWriter<Person> formatedWriterPersonMultiplosArquivos() {
        WritableResource resource = new FileSystemResource("d:/DEV/batch-udemy/visitors_fixed_width_multiple");

        return new MultiResourceItemWriterBuilder<Person>()
                .name("formatedWriterPersonMultiplosArquivos")
                .delegate(getPersonFlatFileItemWriter(resource))
                .resourceSuffixCreator(createSuffix())
                .resource(resource)
                .itemCountLimitPerResource(1)
                .build();
    }

    private ResourceSuffixCreator createSuffix() {
        return index -> index + ".txt";
    }

    //criar um unico arquivo
    @Bean(name = "formatedWriterPerson")
    public FlatFileItemWriter<Person> formatedWriterPerson() {
        WritableResource resource = new FileSystemResource("d:/DEV/batch-udemy/visitors_fixed_width_personalized.txt");

        return getPersonFlatFileItemWriter(resource);
    }

    private FlatFileItemWriter<Person> getPersonFlatFileItemWriter(WritableResource resource) {
        return new FlatFileItemWriterBuilder<Person>()
                .name("formatedWriterPerson")
                .lineAggregator(lineAggregator())
                .headerCallback(headerPrimary())
                .footerCallback(fotter())
                .resource(resource)
                .build();
    }


    private FlatFileHeaderCallback headerPrimary() {
        String start = "========= PERSON ========";
        String columns = "ID   NAME             AGE";

        return header -> header.write(start + "\n" + columns);
    }

    private FlatFileFooterCallback fotter() {
        return header -> header.write("========= DONE PERSON ========");
    }

    private LineAggregator<Person> lineAggregator() {
        String car = "=== CAR ===";
        String tabPularLina = "    \n";

        return person -> new StringBuilder()
                .append(String.format("Id %s ", person.getId()))
                .append(String.format("Name %s ", person.getName()))
                .append(String.format("Age %s ", person.getAge()))
                .append("\n")
                .append(car)
                .append(tabPularLina)
                .append(String.format("ID: %s ", person.getCar().getId()))
                .append(String.format("NOME: %s ", person.getCar().getNome()))
                .toString();
    }

    //CompositeWriter
    @Bean(name = "myCompositeItemWriter")
    public CompositeItemWriter<Person> myCompositeItemWriter() {
        return new CompositeItemWriterBuilder<Person>()
                .delegates(salvePerson(), formatedWriterPersonMultiplosArquivos())
                .build();
    }

    private ItemWriter<Person> salvePerson() {
        return item -> {
            personRepository.save(Person
                    .builder()
                    .age(30)
                    .name("Roberto")
                    .id(999L)
                    .build());
        };
    }

}
