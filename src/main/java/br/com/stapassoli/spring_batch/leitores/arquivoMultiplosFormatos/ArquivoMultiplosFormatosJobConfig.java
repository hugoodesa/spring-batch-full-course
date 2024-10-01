package br.com.stapassoli.spring_batch.leitores.arquivoMultiplosFormatos;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ArquivoMultiplosFormatosJobConfig {

    private final JobRepository jobRepository;

    @Bean
    public Job ArquivoMultiplosFormatosJob(@Qualifier("arquivoMultiplesFormatosStep") Step arquivoMultiplesFormatosStep){
        return new JobBuilder("arquivoMultiplosFormatosJob",jobRepository)
                .start(arquivoMultiplesFormatosStep)
                .build();
    }

}
