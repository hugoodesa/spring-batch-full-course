package br.com.stapassoli.spring_batch.leitores.tamanhoFixo;

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
public class LeitorFixoJobConfig {

    private final JobRepository jobRepository;

    @Bean
    public Job leitorDelimitadoJob (@Qualifier("delimitadoStep") Step delimitadoStep) {
        return new JobBuilder("leitorDelimitadoJob",jobRepository)
                .start(delimitadoStep)
                //.incrementer(new RunIdIncrementer())
                .build();
    }


}
