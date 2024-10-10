package br.com.stapassoli.spring_batch.escritores.arquivosFormatados;

import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class JobWriterConfiguration {

    private final JobRepository jobRepository;

    @Bean
    public Job jobWrite(@Qualifier("simpleStepWriter") Step simpleStepWriter) {
        return new JobBuilder("jobWrite",jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(simpleStepWriter)
                .build();
    }

}
