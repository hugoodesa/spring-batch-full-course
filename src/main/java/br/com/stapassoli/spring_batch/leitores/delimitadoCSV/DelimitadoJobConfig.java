package br.com.stapassoli.spring_batch.leitores.delimitadoCSV;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class DelimitadoJobConfig {

    private final JobRepository jobRepository;

    @Bean
    public Job delimitadoJob(Step delimitadoStep) {
        return new JobBuilder("delimitadoJob",jobRepository)
                .start(delimitadoStep)
                .incrementer(new RunIdIncrementer())
                .build();
    }

}
