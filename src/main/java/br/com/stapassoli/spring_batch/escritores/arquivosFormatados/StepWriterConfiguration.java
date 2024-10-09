package br.com.stapassoli.spring_batch.escritores.arquivosFormatados;

import br.com.stapassoli.spring_batch.processadores.domain.Cliente;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.WritableResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class StepWriterConfiguration {

    private final PlatformTransactionManager platformTransactionManager;
    private final JobRepository jobRepository;

    @Bean(name = "stepWriter")
    public Step stepWriter(FlatFileItemReader<Cliente> processadorReader, ItemProcessor<Cliente, Cliente> compositeItemProcessor) {
        return new StepBuilder("stepWriter",jobRepository)
                .<Cliente,Cliente>chunk(10,platformTransactionManager)
                .reader(processadorReader)
                .processor(compositeItemProcessor)
                .writer(myWriter())
                .build();
    }

    private ItemWriter<Cliente> myWriter() {
        WritableResource resource = new FileSystemResource("c:/temp/outputData.csv");

        var aggregator = new DelimitedLineAggregator<Cliente>();
        var wrapperFieldExtractor = new BeanWrapperFieldExtractor<Cliente>();
        wrapperFieldExtractor.setNames(new String[]{"nome","idade","email"});

        aggregator.setDelimiter(",");
        aggregator.setFieldExtractor(wrapperFieldExtractor);

        return new FlatFileItemWriterBuilder<Cliente>()
                .name("myWriter")
                .resource(resource)
                .append(true)
                .lineAggregator(aggregator)
                .build();

    }

}
