package br.com.stapassoli.spring_batch.leitores.delimitado;

import br.com.stapassoli.spring_batch.dbOne_entity.Cliente;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EscritaDelimitadoFixoWriterConfig {

    @Bean
    @StepScope
    public ItemWriter<Cliente> escritaAquivoLarguraFixaReader() {
        return items -> items.forEach(System.out::println);
    }

}
