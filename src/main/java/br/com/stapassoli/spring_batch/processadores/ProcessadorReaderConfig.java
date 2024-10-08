package br.com.stapassoli.spring_batch.processadores;

import br.com.stapassoli.spring_batch.processadores.domain.Cliente;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class ProcessadorReaderConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;

    @Value("classpath:files/clientes_invalidos.txt")
    Resource clientesInvalidos;

    @Bean(name = "processadorReader")
    public FlatFileItemReader<Cliente> processadorReader() {
        return new FlatFileItemReaderBuilder<Cliente>()
                .name("clientesInvalidos")
                .strict(false)
                .resource(clientesInvalidos)
                .delimited()
                .names("nome","idade","email")
                .targetType(Cliente.class)
                .build();
    }

}
