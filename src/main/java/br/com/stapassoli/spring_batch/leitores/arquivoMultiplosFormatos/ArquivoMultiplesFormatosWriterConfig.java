package br.com.stapassoli.spring_batch.leitores.arquivoMultiplosFormatos;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArquivoMultiplesFormatosWriterConfig {

    @Bean(name = "arquivoMultiplesFormatosStepWriter")
    public ItemWriter<Object> arquivoMultiplesFormatosStepWriter(){
        return domain -> domain.forEach(System.out::println);
    }

}
