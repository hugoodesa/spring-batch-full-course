package br.com.stapassoli.spring_batch.leitores.delimitadoCSV;

import br.com.stapassoli.spring_batch.dbTwo_entity.Visitors;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class DelimitadoStepConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;

    @Bean(name = "delimitadoStep")
    //public Step delimitadoStep(ItemReader<Visitors> delimitadoReader, ItemProcessor<Visitors,Visitors> delimitadoProcessor) {
    public Step delimitadoStep(ItemReader<Visitors> delimitadoReader, ItemWriter<Visitors> delimitadoWriter) {
        return new StepBuilder("delimitadoStep", jobRepository)
                .<Visitors, Visitors>chunk(10, platformTransactionManager)
                .reader(delimitadoReader)
                //.processor(delimitadoProcessor)
                .writer(delimitadoWriter)
                .build();
    }


}
