package br.com.stapassoli.spring_batch.processadores;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import br.com.stapassoli.spring_batch.processadores.domain.Cliente;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class ProcessadorStepConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    @Bean
    public Step processadorStep(@Qualifier("processadorReader") FlatFileItemReader<Cliente> processadorReader,
                                @Qualifier("processadorValidacaoCustomizado") ItemProcessor<Cliente, Cliente> processadorValidacaoCustomizado,
                                @Qualifier("processadorWriter") ItemWriter<Cliente> processadorWriter) {
        return new StepBuilder("processadorStep", jobRepository)
                .<Cliente, Cliente>chunk(10, transactionManager)
                .reader(processadorReader)
                .processor(processadorValidacaoCustomizado)
                .writer(processadorWriter)
                .build();
    }

}
