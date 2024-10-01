package br.com.stapassoli.spring_batch.leitores.delimitadoCSV;

import br.com.stapassoli.spring_batch.dbTwo_entity.Visitors;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DelimitadoWriterConfig {

    @Bean
    public ItemWriter<Visitors> delimitadoWriter (){
        return visitors-> visitors.forEach(System.out::println);
    }

}
