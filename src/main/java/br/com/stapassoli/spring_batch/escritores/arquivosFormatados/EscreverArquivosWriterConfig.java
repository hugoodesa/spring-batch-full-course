package br.com.stapassoli.spring_batch.escritores.arquivosFormatados;

import br.com.stapassoli.spring_batch.processadores.domain.Cliente;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.WritableResource;

@Configuration
public class EscreverArquivosWriterConfig {

    @Bean(name = "escreverArquivosWriter")
    public ItemWriter<Cliente> escreverArquivosWriter() {
        WritableResource resource = new FileSystemResource("d:/DEV/batch-udemy/outputData.csv");

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
