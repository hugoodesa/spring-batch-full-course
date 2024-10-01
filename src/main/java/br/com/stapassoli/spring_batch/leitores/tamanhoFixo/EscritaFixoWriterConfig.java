package br.com.stapassoli.spring_batch.leitores.tamanhoFixo;

import br.com.stapassoli.spring_batch.dbOne_entity.Cliente;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EscritaFixoWriterConfig {

    @Bean
    @StepScope
    public ItemWriter<Cliente> escritaAquivoLarguraFixaReader() {
        return clientes -> clientes.forEach(System.out::println);
        /*return items -> items.forEach(item->{
            if(item.getNome().equals("Maria")) throw new RuntimeException("Deu ruim");
        });*/
    }

}
