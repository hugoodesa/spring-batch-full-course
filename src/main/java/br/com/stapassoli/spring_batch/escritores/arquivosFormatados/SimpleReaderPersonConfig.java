package br.com.stapassoli.spring_batch.escritores.arquivosFormatados;

import br.com.stapassoli.spring_batch.dbTwo_entity.Person;
import br.com.stapassoli.spring_batch.dbTwo_entity.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.*;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SimpleReaderPersonConfig {

    private final PersonRepository personRepository;

    @Bean(name = "simpleReaderPerson")
    public ItemReader<Person> simpleReaderPerson() {
        List<Person> persons = personRepository.findAll();
        return new IteratorItemReader<>(persons);
    }

}
