package br.com.stapassoli.spring_batch.leitores.arquivoMultiplosFormatos;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class ArquivoMultiplesFormatosReaderConfig {

    @Value("classpath:files/sales.txt")
    Resource resource;

    @Bean(name = "arquivoMultiplesFormatosStepReader")
    public FlatFileItemReader<Object> arquivoMultiplesFormatosStepReader(@Qualifier("personProductTransacaoLineMapper") LineMapper<Object> lineMapper){
        return  new FlatFileItemReaderBuilder<>()
                .resource(resource)
                .name("arquivoMultiplesFormatosStepReader")
                .lineMapper(lineMapper)
                .build();
    }


}
