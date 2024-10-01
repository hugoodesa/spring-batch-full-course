package br.com.stapassoli.spring_batch.leitores.arquivoMultiplosFormatos;

import br.com.stapassoli.spring_batch.dbOne_entity.Cliente;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class ArquivoMultiplesFormatosStepConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;

    @Bean(name = "arquivoMultiplesFormatosStep")
    public Step arquivoMultiplesFormatosStep(
            @Qualifier("arquivoMultiplesFormatosStepReader") ItemReader arquivoMultiplesFormatosStepReader,
            @Qualifier("arquivoMultiplesFormatosStepWriter") ItemWriter arquivoMultiplesFormatosStepWriter) {

        return new StepBuilder("arquivoMultiplesFormatosStep",jobRepository)
                .<Cliente,Cliente>chunk(10,platformTransactionManager)
                .reader(arquivoMultiplesFormatosStepReader)
                .writer(arquivoMultiplesFormatosStepWriter)
                .build();

    }

}
