package br.com.stapassoli.spring_batch.leitores.leituraJson;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class LeituraJsonStepConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    @Bean
    public Job leituraJsonStep(@Qualifier("leituraJsonJob") Step leituraJsonJob) {
        return new JobBuilder("leituraJsonJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(leituraJsonJob)
                .build();
    }


}
