package br.com.stapassoli.spring_batch.leitores.lerDeVariasFontes;

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
public class LerVariasFontesJobConfig {

    private final JobRepository jobRepository;

    @Bean
    public Job lerVariasFontesJob(Step lerVariasFontesStep) {
        return new JobBuilder("lerVariasFontesJob", jobRepository)
                .start(lerVariasFontesStep)
                .incrementer(new RunIdIncrementer())
                .build();
    }

}
