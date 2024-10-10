package br.com.stapassoli.spring_batch.escritores.larguraFixa;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class JobLaguraFixWriterConfig {

    private final JobRepository jobRepository;

    @Bean
    public Job jobLaguraFixWriter(@Qualifier("stepLarguraFixaWriter") Step stepLarguraFixaWriter) {
       return new JobBuilder("jobLaguraFixWriter",jobRepository)
               .incrementer(new RunIdIncrementer())
               .start(stepLarguraFixaWriter)
               .build();
    }

}
