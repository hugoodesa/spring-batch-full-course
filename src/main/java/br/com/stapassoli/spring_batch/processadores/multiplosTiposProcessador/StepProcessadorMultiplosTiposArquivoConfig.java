package br.com.stapassoli.spring_batch.processadores.multiplosTiposProcessador;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class StepProcessadorMultiplosTiposArquivoConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    @Bean
    public Step StepProcessadorMultiplosTiposArquivo(
            @Qualifier("arquivoMultiplesFormatosStepReader") FlatFileItemReader<Object> arquivoMultiplesFormatosStepReader,
            @Qualifier("processadorMultiplosTipos") ItemProcessor<Object,Object> processadorMultiplosTipos) {

        return new StepBuilder("StepProcessadorMultiplosTiposArquivo", jobRepository)
                .chunk(10, transactionManager)
                .processor(processadorMultiplosTipos)
                .reader(arquivoMultiplesFormatosStepReader)
                .writer(writer())
                .build();

    }

    private ItemWriter<Object> writer() {
        return item -> item.forEach(System.out::println);
    }

}
