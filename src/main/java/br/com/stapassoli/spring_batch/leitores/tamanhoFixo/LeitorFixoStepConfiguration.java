package br.com.stapassoli.spring_batch.leitores.tamanhoFixo;

import br.com.stapassoli.spring_batch.dbOne_entity.Cliente;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class LeitorFixoStepConfiguration {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;

    @Bean
    public Step leitorFixoStep(ItemReader<Cliente> leituraAquivoLarguraFixaReader, ItemWriter<Cliente> escritaAquivoLarguraFixaReader) {
        return new StepBuilder("leitorDelimitadoStep", jobRepository)
                .<Cliente, Cliente>chunk(100, platformTransactionManager)
                .reader(leituraAquivoLarguraFixaReader)
                .writer(escritaAquivoLarguraFixaReader)
                .build();
    }

}
