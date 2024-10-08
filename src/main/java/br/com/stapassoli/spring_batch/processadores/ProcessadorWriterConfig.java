package br.com.stapassoli.spring_batch.processadores;

import br.com.stapassoli.spring_batch.processadores.domain.Cliente;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcessadorWriterConfig {

    @Bean(name = "processadorWriter")
    public ItemWriter<Cliente> processadorWriter(){
        return cliente -> cliente.forEach(System.out::println);
    }

}
