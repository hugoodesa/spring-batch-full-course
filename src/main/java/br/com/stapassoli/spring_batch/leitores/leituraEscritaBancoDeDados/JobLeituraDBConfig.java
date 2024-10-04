package br.com.stapassoli.spring_batch.leitores.leituraEscritaBancoDeDados;

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
public class JobLeituraDBConfig {

    private final JobRepository jobRepository;

    // SOMENTE LEITURA
    /*@Bean(name = "jobLeituraDB")
    public Job jobLeituraDB(@Qualifier("stepLeituraDB") Step stepLeituraDB) {
        return new JobBuilder("jobLeituraDB", jobRepository)
                .start(stepLeituraDB)
                .incrementer(new RunIdIncrementer())
                .build();
    }*/

    // ESCRITA E LEITURA
    @Bean(name = "jobLeituraDB")
    public Job jobLeituraDB(@Qualifier("stepLeituraDB") Step stepLeituraDB,@Qualifier("stepPersistenciaDBTasklet") Step stepPersistenciaDBTasklet) {
        return new JobBuilder("jobLeituraDB", jobRepository)
                .start(stepPersistenciaDBTasklet)
                .next(stepLeituraDB)
                .incrementer(new RunIdIncrementer())
                .build();
    }

}
