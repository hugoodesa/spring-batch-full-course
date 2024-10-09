package br.com.stapassoli.spring_batch.processadores.scriptProcessador;

import br.com.stapassoli.spring_batch.processadores.domain.Cliente;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.ScriptItemProcessor;
import org.springframework.batch.item.support.builder.ScriptItemProcessorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcessadorScriptConfig {

    @Bean(name = "processadorScript")
    public ItemProcessor<Cliente, Cliente> processadorScript() {
        ScriptItemProcessor<Cliente,Cliente> scriptItemProcessor = new ScriptItemProcessorBuilder<Cliente,Cliente>()
                .language("nashorn")
                .scriptSource(
                 "let email = cliente.getEmail();"+
                 "let isArquivoExeiste = `ls | grep ${email}.txt;` "+
                 "if(!isArquivoExeiste) item; else null;"
                )
                .build();
        return cliente -> cliente;
    }

}
