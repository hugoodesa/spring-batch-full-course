package br.com.stapassoli.spring_batch.leitores.tamanhoFixo;

import br.com.stapassoli.spring_batch.dbOne_entity.Cliente;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.*;

import java.net.MalformedURLException;

@Configuration
@RequiredArgsConstructor
public class LeituraFixoReaderConfig {

    private final ResourceLoader resourceLoader;


   @Value("classpath:files/clientes.txt") Resource arquivosClientes;

    @Bean
    @StepScope
    public FlatFileItemReader<Cliente> leituraAquivoLarguraFixaReader() throws MalformedURLException {
        return new FlatFileItemReaderBuilder<Cliente>()
                .name("leituraAquivoLarguraFixaReader")
                .resource(arquivosClientes)
                .fixedLength()
                .columns(new Range[]{new Range(1,10),new Range(11,20),new Range(21,23),new Range(24,43)})
                .names("nome","sobrenome","idade","email")
                .targetType(Cliente.class)
                .build();
    }

    /*@Bean
    public Resource loadClientesFile() {
        return resourceLoader.getResource(
                "classpath:data/employees.dat");
    }*/

}
