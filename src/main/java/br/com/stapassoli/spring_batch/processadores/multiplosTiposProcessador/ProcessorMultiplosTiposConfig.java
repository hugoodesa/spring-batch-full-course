package br.com.stapassoli.spring_batch.processadores.multiplosTiposProcessador;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcessorMultiplosTiposConfig {

    @Bean("processorMultiplosTipos")
    public ItemProcessor<Object, Object> processorMultiplosTipos() {
        return item -> item;
    }

}
