package br.com.stapassoli.spring_batch.escritores.arquivosFormatados;

import br.com.stapassoli.spring_batch.dbTwo_entity.Person;
import br.com.stapassoli.spring_batch.dbTwo_entity.repository.PersonRepository;
import br.com.stapassoli.spring_batch.processadores.domain.Cliente;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.MultiResourceItemWriter;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.batch.item.support.builder.CompositeItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class StepWriterConfiguration {

    private final PlatformTransactionManager platformTransactionManager;
    private final JobRepository jobRepository;

    @Bean(name = "stepWriter")
    public Step stepWriter(FlatFileItemReader<Cliente> processadorReader,
                           ItemProcessor<Cliente, Cliente> compositeItemProcessor,
                           @Qualifier("escreverArquivosWriter") ItemWriter<Cliente> escreverArquivosWriter) {
        return new StepBuilder("stepWriter", jobRepository)
                .<Cliente, Cliente>chunk(10, platformTransactionManager)
                .reader(processadorReader)
                .processor(compositeItemProcessor)
                .writer(escreverArquivosWriter)
                .build();
    }

    @Bean(name = "simpleStepWriter")
    public Step simpleStepWriter(@Qualifier("simpleReaderPerson") ItemReader<Person> simpleReaderPerson,
                                 @Qualifier("myCompositeItemWriter") CompositeItemWriter<Person> myCompositeItemWriter) {
        return new StepBuilder("stepWriter", jobRepository)
                .<Person, Person>chunk(1, platformTransactionManager)
                .reader(simpleReaderPerson)
                .writer(myCompositeItemWriter)
                .build();
    }


}
