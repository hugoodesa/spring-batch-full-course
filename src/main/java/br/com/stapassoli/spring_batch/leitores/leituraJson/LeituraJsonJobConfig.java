package br.com.stapassoli.spring_batch.leitores.leituraJson;

import br.com.stapassoli.spring_batch.leitores.leituraJson.domain.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.json.JsonFileItemWriter;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class LeituraJsonJobConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    @Bean(name = "leituraJsonJob")
    public Step leituraJsonStepJob(JsonItemReader<Student> studentJsonItemReader, ItemWriter<Student> studentJsonFileItemWriter) {
        return new StepBuilder("leituraJsonJob", jobRepository)
                .<Student,Student>chunk(10,transactionManager)
                .reader(studentJsonItemReader)
                .writer(studentJsonFileItemWriter)
                .build();
    }

}
