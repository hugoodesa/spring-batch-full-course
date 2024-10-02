package br.com.stapassoli.spring_batch.leitores.arquivoMultiplosFormatos;

import br.com.stapassoli.spring_batch.leitores.arquivoMultiplosFormatos.domain.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class ArquivoMultiplesFormatosStepConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;

    @Bean(name = "arquivoMultiplesFormatosStep")
    public Step arquivoMultiplesFormatosStep(
            @Qualifier("arquivoMultiplesFormatosStepReader") ItemStreamReader arquivoMultiplesFormatosStepReader,
            @Qualifier("arquivoMultiplesFormatosStepWriter") ItemWriter arquivoMultiplesFormatosStepWriter) {

        return new StepBuilder("arquivoMultiplesFormatosStep",jobRepository)
                .<Person,Person>chunk(10,platformTransactionManager)
                .reader(new PersonProjectReader(arquivoMultiplesFormatosStepReader))
                .writer(arquivoMultiplesFormatosStepWriter)
                .build();

    }

}
