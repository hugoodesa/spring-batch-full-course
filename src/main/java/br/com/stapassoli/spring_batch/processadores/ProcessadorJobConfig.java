package br.com.stapassoli.spring_batch.processadores;

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
public class ProcessadorJobConfig {

    private final JobRepository jobRepository;

    @Bean
    public Job procesadorJob(Step processadorStep) {
        return new JobBuilder("processadorJob",jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(processadorStep)
                .build();
    }

}
