package br.com.stapassoli.spring_batch.processadores.multiplosTiposProcessador;

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
public class JobProcessadorMultiplosTiposArquivoConfig {

    private final JobRepository jobRepository;

    @Bean
    public Job jobProcessadorMultiplosTiposArquivo(@Qualifier("StepProcessadorMultiplosTiposArquivo") Step StepProcessadorMultiplosTiposArquivo ) {
        return new JobBuilder("jobProcessadorMultiplosTiposArquivo",jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(StepProcessadorMultiplosTiposArquivo)
                .build();

    }

}
