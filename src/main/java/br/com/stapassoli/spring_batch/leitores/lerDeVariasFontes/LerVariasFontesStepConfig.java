package br.com.stapassoli.spring_batch.leitores.lerDeVariasFontes;

import lombok.AllArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@AllArgsConstructor
public class LerVariasFontesStepConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    @Bean
    public Step lerVariasFontesStep(@Qualifier("lerVariasFontesReader") MultiResourceItemReader<Employee> lerVariasFontesReader, ItemWriter<Employee> lerVariasFontesWriter) {
        return new StepBuilder("lerVariasFontesStep", jobRepository)
                .<Employee, Employee>chunk(100, transactionManager)
                .reader(lerVariasFontesReader)
                .writer(lerVariasFontesWriter)
                .build();
    }

}
