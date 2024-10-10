package br.com.stapassoli.spring_batch.leitores.delimitadoCSV;

import br.com.stapassoli.spring_batch.dbTwo_entity.Visitors;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
@RequiredArgsConstructor
public class DelimitadoReaderConfig {

    @Value("classpath:files/visitors.csv")
    Resource visitorsFile;

    @Bean(name = "delimitadoReader")
    public FlatFileItemReader<Visitors> delimitadoReader(LineMapper<Visitors> delimitadorLineMapper){
        return new FlatFileItemReaderBuilder<Visitors>()
                .name("delimitadoReader")
                .resource(visitorsFile)
                .linesToSkip(1)
                .targetType(Visitors.class)
                .lineMapper(delimitadorLineMapper)
                .build();
    }

}
